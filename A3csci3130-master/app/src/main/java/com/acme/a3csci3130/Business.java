package com.acme.a3csci3130;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that defines how the data will be stored in the
 * Firebase databse. This is converted to a JSON format
 * Contains all of the necessary variables needed to define a business
 */

public class Business implements Serializable {

    public  String uid;
    public  String name;
    public  String busniessNum;
    public  String primaryBusiness;
    public  String address;
    public  String provinceOrTerritory;

    public Business() {
        // Default constructor required for calls to DataSnapshot.getValue
    }

    public Business(String uid, String name, String businessNum, String primaryBusiness, String address, String provinceOrTerritory){
        this.uid = uid;
        this.name = name;
        this.busniessNum = businessNum;
        this.primaryBusiness = primaryBusiness;
        this.address = address;
        this.provinceOrTerritory = provinceOrTerritory;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("name", name);
        result.put("business number", busniessNum);
        result.put("primary business", primaryBusiness);
        result.put("address", address);
        result.put("province/territory", provinceOrTerritory);

        return result;
    }
}