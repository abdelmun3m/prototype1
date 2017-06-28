package com.abdelmun3m.prototype;

/**
 * Created by kimo on 07/10/2016.
 */
public class AccelData {
    long timeStamp;
    double x,y,z;
    public AccelData(long timeStamp, double x, double y, double z){
        this.timeStamp=timeStamp;
        this.x=x;
        this.y=y;
        this.z=z;
    }
    public AccelData(){}
    public long getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(long timeStamp){
        this.timeStamp=timeStamp;
    }
    public double getX(){
        return x;
    }
    public void setX(double x) {
        this.x=x;
    }
    public double getY(){
        return y;
    }
    public void setY(double y){
        this.y=y;
    }
    public double getZ(){
        return z;
    }
    public void setZ(double z){
        this.z=z;
    }
    public String toString()
    {
        return "t="+timeStamp+", x="+x+", y="+y+", z="+z;
    }
}
