package com.example.jsonobjectjsonarrayassetappjava;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.ArrayList;

class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.UserViewHolder> {

    private ArrayList<String> name;
    private ArrayList<String> emal;
    private ArrayList<String> gender;
    private ArrayList<String> id;
    private ArrayList<String> mobile;
    Context context;

    public CustomAdapter(
            Context context,
            ArrayList<String> id,
            ArrayList<String> name,
            ArrayList<String> emal,
            ArrayList<String> gender,
            ArrayList<String> mobile) {
        this.name = name;
        this.emal = emal;
        this.gender = gender;
        this.id = id;
        this.mobile = mobile;
        this.context = context;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //inflate the view to use by the ViewHolder
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.rowlayout,
                        parent,
                        false);

        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, final int position) {

        holder.tvName.setText(name.get(position));
        holder.tvEmail.setText(emal.get(position));
        holder.tvGender.setText(gender.get(position));
        holder.tvMobile.setText(mobile.get(position));
        holder.tvUserId.setText(id.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //display the name of the user in a Toast when item is clicked
                Toast.makeText(context, name.get(position), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    class UserViewHolder extends ViewHolder{

        TextView tvName, tvEmail, tvMobile, tvGender, tvUserId;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvMobile = itemView.findViewById(R.id.tvMobileNo);
            tvGender = itemView.findViewById(R.id.tvGender);
            tvUserId = itemView.findViewById(R.id.tvId);
        }

    }
}
