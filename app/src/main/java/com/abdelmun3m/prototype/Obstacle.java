package com.abdelmun3m.prototype;

/**
 * Created by Abdelmunem on 11/4/2016.
 */

public class Obstacle {
    private float danger_Rate;
    private float existing_Rate;
    private String id;
    //private int location_id;
    private int type;

    public Obstacle(float danger_Rate, float existing_Rate, String id, int type) {
        this.danger_Rate = danger_Rate;
        this.existing_Rate = existing_Rate;
        this.id = id;
        this.type = type;
    }

    public Obstacle(){

    }


    public void decrease_Dangerous(){}

    public void get_Obstacle_Data(String id){}

    public void get_Obstacle_Id(Location location){}
}
