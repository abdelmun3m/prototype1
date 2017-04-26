package com.abdelmun3m.prototype;

/**
 * Created by Abdelmunem on 12/2/2016.
 */

public class userDB {

    public String name;
    public String password;
    public String e_mail;
    public String governorate;
    public String city;
    public float distance_N;
    public int authentication;
    public int notification_Mode;
    public String Profile_Pic;

    public userDB(String name, String password, String e_mail,
                  String governorate, String city, float distance_N,
                  int authentication, int notification_Mode ,String pic){
        this.name = name;
        this.password = password;
        this.e_mail = e_mail;
        this.governorate = governorate;
        this.city=city;
        this.distance_N =distance_N;
        this.authentication=authentication;
        this.notification_Mode = notification_Mode;
        this.Profile_Pic=pic;
    }
    public userDB(){

    }
}
