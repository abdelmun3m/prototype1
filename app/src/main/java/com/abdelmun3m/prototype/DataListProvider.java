package com.abdelmun3m.prototype;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 3M on 19/11/2016.
 */
public class DataListProvider {

    public  static HashMap<String,List<String>> getInfo()
    {
        HashMap<String,List<String>> Feature_service=new  HashMap<String,List<String>>();
        List<String>Feature=new ArrayList<String>();
        Feature.add("Traffic Jam");
        Feature.add("Public_Service");
        Feature_service.put("Feature_service",Feature);
        return Feature_service;

    }
}
