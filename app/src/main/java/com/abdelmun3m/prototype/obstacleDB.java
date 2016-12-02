package com.abdelmun3m.prototype;

/**
 * Created by Abdelmunem on 12/2/2016.
 */

public class obstacleDB {

    public float danger_Rate;
    public float existing_Rate;
    public int type;
    public obstacleDB(float danger_Rate, float existing_Rate, int type){
        this.danger_Rate = danger_Rate ;
        this.existing_Rate = existing_Rate;
        this.type = type;
    }
    public  obstacleDB(){

    }
}
