package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * This class takes the input text from the user on the CreateContact Activity. Information is used to create a new business contact.
 * Information is business information pertaining to that business such as the business name etc.
 */

public class CreateBusinessActivity extends Activity {

    private Button submitButton;
    private EditText nameField, businessNumField, primaryBusinessField, addressField, provinceOrTerritoryField;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_business_acitivity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        submitButton = (Button) findViewById(R.id.submitButton);
        nameField = (EditText) findViewById(R.id.name);
        businessNumField = (EditText) findViewById(R.id.businessNum);
        primaryBusinessField = (EditText) findViewById(R.id.primaryBusiness);
        addressField = (EditText) findViewById(R.id.address);
        provinceOrTerritoryField = (EditText) findViewById(R.id.provinceOrTerritory);
    }

    public void submitInfoButton(View v) {
        //each entry needs a unique ID
        String personID = appState.firebaseReference.push().getKey();
        String name = nameField.getText().toString();
        String businessNum = businessNumField.getText().toString();
        String primaryBusiness = primaryBusinessField.getText().toString();
        String address = addressField.getText().toString();
        String provinceOrTerritory = provinceOrTerritoryField.getText().toString();

        com.acme.a3csci3130.Business business = new com.acme.a3csci3130.Business(personID, name, businessNum, primaryBusiness, address, provinceOrTerritory);

        appState.firebaseReference.child(personID).setValue(business);

        finish();

    }
}
