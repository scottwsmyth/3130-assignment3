package com.acme.a3csci3130;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.FirebaseDatabase;

/**
 * The main activity simply displays a listview with all of the current businesses in the app & database.
 * There is a button above the listview which allows the user to create a new business and by clicking on a business in the listview
 * the user will be brought to the DetailViewActivity screen
 */

public class MainActivity extends Activity {


    private ListView businessListView;
    private FirebaseListAdapter<Business> firebaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get the app wide shared variables
        MyApplicationData appData = (MyApplicationData)getApplication();

        //Set-up Firebase
        appData.firebaseDBInstance = FirebaseDatabase.getInstance();
        appData.firebaseReference = appData.firebaseDBInstance.getReference("businesses");

        //Get the reference to the UI contents
        businessListView = (ListView) findViewById(R.id.listView);

        //Set up the List View
       firebaseAdapter = new FirebaseListAdapter<Business>(this, Business.class,
                android.R.layout.simple_list_item_1, appData.firebaseReference) {
            @Override
            protected void populateView(View v, Business model, int position) {
                TextView contactName = (TextView)v.findViewById(android.R.id.text1);
                contactName.setText(model.name);
            }
        };
        businessListView.setAdapter(firebaseAdapter);
        businessListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // onItemClick method is called everytime a user clicks an item on the list
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Business person = (Business) firebaseAdapter.getItem(position);
                showDetailView(person);
            }
        });
    }

    public void createBusinessButton(View v)
    {
        Intent intent=new Intent(this, CreateBusinessActivity.class);
        startActivity(intent);
    }

    private void showDetailView(Business person)
    {
        Intent intent = new Intent(this, DetailViewActivity.class);
        intent.putExtra("business", person);
        startActivity(intent);
    }



}
