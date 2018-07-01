package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * This class allows the user to view the details of a business that has already been created.
 * In addition to viewing the content the user has the ability to erase or update the business as well with the two methods at the bottom
 */

public class DetailViewActivity extends Activity {

    private EditText nameField, businessNumField, primaryBusinessField, addressField, provinceOrTerritoryField;
    Business receivedPersonInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedPersonInfo = (Business)getIntent().getSerializableExtra("business");

        Button updateButton = (Button) findViewById(R.id.updateButton);
        final Button deleteButton = (Button) findViewById(R.id.deleteButton);
        nameField = (EditText) findViewById(R.id.name);
        businessNumField = (EditText) findViewById(R.id.businessNum);
        primaryBusinessField = (EditText) findViewById(R.id.primaryBusiness);
        addressField = (EditText) findViewById(R.id.address);
        provinceOrTerritoryField = (EditText) findViewById(R.id.provinceOrTerritory);


        if(receivedPersonInfo != null){
            nameField.setText(receivedPersonInfo.name);
            businessNumField.setText(receivedPersonInfo.busniessNum);
            primaryBusinessField.setText(receivedPersonInfo.primaryBusiness);
            addressField.setText(receivedPersonInfo.address);
            provinceOrTerritoryField.setText(receivedPersonInfo.provinceOrTerritory);
        }

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                eraseContact(receivedPersonInfo.uid);
                //deleteButton.setText("hello");

            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateContact(receivedPersonInfo.uid);

            }
        });

    }

    public void updateContact(String uid){

        String name = nameField.getText().toString();
        String businessNum = businessNumField.getText().toString();
        String primaryBusiness = primaryBusinessField.getText().toString();
        String address = addressField.getText().toString();
        String provinceOrTerritory = provinceOrTerritoryField.getText().toString();

        Business business = new Business(uid, name, businessNum, primaryBusiness, address, provinceOrTerritory);

        DatabaseReference updateBusiness = FirebaseDatabase.getInstance().getReference("businesses").child(uid);

        updateBusiness.setValue(business);

    }

    public void eraseContact(String uid)
    {
        DatabaseReference deleteContact = FirebaseDatabase.getInstance().getReference("businesses").child(uid);
        deleteContact.removeValue();

    }
}
