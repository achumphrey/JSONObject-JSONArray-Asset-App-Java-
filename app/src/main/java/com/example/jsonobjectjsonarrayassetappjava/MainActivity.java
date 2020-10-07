package com.example.jsonobjectjsonarrayassetappjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //ArrayList for person, name, email, id's, and mobile numbers
    ArrayList<String> personNames = new ArrayList<>();
    ArrayList<String> emails = new ArrayList<>();
    ArrayList<String> mobileNo = new ArrayList<>();
    ArrayList<String> userId = new ArrayList<>();
    ArrayList<String> gender = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get the reference of the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvUser);

        //set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new
                LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(linearLayoutManager);

        try {
            //get JSONObject from JSON file
            JSONObject object = new JSONObject(loadJSONFromAsset());

            //fetch JSONArray from JSONObject named users
            JSONArray userArray = object.getJSONArray("users");

            //get an array of each member of the object users
            for (int i = 0; i < userArray.length(); i++){

                //create a JSONObject for fetching a single user data
                JSONObject userDetail = userArray.getJSONObject(i);

                //fetch name, email, and user id
                personNames.add(userDetail.getString("name"));
                emails.add(userDetail.getString("email"));
                userId.add(userDetail.getString("id"));
                gender.add(userDetail.getString("gender"));

                //create an object for getting contact data from JSONObject
                JSONObject contact = userDetail.getJSONObject("contact");

                //fetch the mobile number and store them in the ArrayList
                mobileNo.add(contact.getString("mobile"));
            }

        } catch (JSONException e){
            e.printStackTrace();
        }

        //call the CustomAdapter Constructor and pass the data to the adapter
        CustomAdapter customAdapter = new CustomAdapter(
                MainActivity.this,
                userId,
                personNames,
                emails,
                gender,
                mobileNo);

        recyclerView.setAdapter(customAdapter);
    }

    //create assets folder -> navigate to Packages -> New ->
    // Folder -> Assets Folder -> finish
    private String loadJSONFromAsset() {

        String json = null;
        try {

            InputStream is = getAssets().open("users_list.json");
            int size = is.available(); //returns the number of bytes to read in the file
            byte[] buffer = new byte[size]; //create an array called buffer of length (size)
            is.read(buffer); // read and put the data in the array one character at a time.
            is.close();

            //This constructor decodes the byte array depending on the character set
            // mentioned in the string (e.g. utf-8).
            //The byte values are actually the ASCII values provided. The string is created by
            // converting them to their respective character values and then encoding the string.
            json = new String(buffer, "UTF-8");

        } catch (IOException ex){

            ex.printStackTrace();
            return null;
        }
        return json;
    }
}