package com.abdelmun3m.prototype;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import org.apache.commons.math3.util.MathArrays;

import java.util.ArrayList;

import cern.colt.list.DoubleArrayList;
import cern.jet.stat.Descriptive;


/**
 * Created by kimo on 05/12/2016.
 */

public class ACCSERVO extends Service implements SensorEventListener {

    SensorManager sensorManager;
    Sensor sensor;
    double initialNow;
    double skewnessX,skewnessY,skewnessZ;
    double kurtosisX,kurtosisY,kurtosisZ;
    double[] smootheningBox={0.3333,0.3333,0.3333};
    double[] accelDataArrayX,accelDataArrayY,accelDataArrayZ;
    ArrayList accelData,previousAccelData,smoothedAccelData;
    DoubleArrayList smoothedX,smoothedY,smoothedZ;
    double ACFX,ACFY,ACFZ;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        Toast.makeText(this,"Service Started!", Toast.LENGTH_LONG).show();
        return START_STICKY;
    }
   @Override
    public void onCreate(){
//1.Initialize Accelerometer
       sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
       sensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
       //2.Initialize initialNow
       initialNow= System.currentTimeMillis()/1000;
       //3.Initialize accelData,smoothedX,smoothedY,and smoothedZ then register listener
       accelData=new ArrayList();
       previousAccelData=new ArrayList();
       smoothedX=new DoubleArrayList();
       smoothedY=new DoubleArrayList();
       smoothedZ=new DoubleArrayList();
       sensorManager.registerListener(this,sensor,(1000000 / 2));//slowest(about 5 sample/sec more or less)
   }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Toast.makeText(this,"Service Destroyed!", Toast.LENGTH_LONG).show();
    }
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
//1.Set an interval
        double interval= System.currentTimeMillis()/1000-initialNow;
        if (interval==0){
            //adds the last 50 values of previous list to this list first(at the beginning of each interval)
            accelData=new ArrayList();
            if (!previousAccelData.isEmpty()&&previousAccelData.size()>=50) {
                for (int i=previousAccelData.size()-51;i<previousAccelData.size();i++){
                    accelData.add(previousAccelData.get(i));
                }
            }
        }
        //2.Adding values(samples) to the arraylist of data in the interval of 30 seconds
        if (interval>=30){
            Toast.makeText(this," "+accelData.size(), Toast.LENGTH_LONG).show();
            previousAccelData=accelData;
            //do all the operations here
            smoothedX=new DoubleArrayList();
            smoothedY=new DoubleArrayList();
            smoothedZ=new DoubleArrayList();
            //1.resetting the initialNow which results in resetting the interval to 0
            initialNow= System.currentTimeMillis()/1000;
            //won't start computation or copying into smoothedX,Y,Z until accelData.size()>=150
            if (accelData.size()>=150){
                //copying data to array to perform convolution
                accelDataArrayX=new double[accelData.size()];
                accelDataArrayY=new double[accelData.size()];
                accelDataArrayZ=new double[accelData.size()];
                for (int i=0;i<accelDataArrayX.length;i++){
                    AccelData temp=(AccelData)accelData.get(i);
                    accelDataArrayX[i]=temp.getX();
                }
                for (int i=0;i<accelDataArrayY.length;i++){
                    AccelData temp=(AccelData)accelData.get(i);
                    accelDataArrayY[i]=temp.getY();
                }
                for (int i=0;i<accelDataArrayZ.length;i++){
                    AccelData temp=(AccelData)accelData.get(i);
                    accelDataArrayZ[i]=temp.getZ();
                }
                //performing smoothing by convolution
                accelDataArrayX=MathArrays.convolve(accelDataArrayX,smootheningBox);
                accelDataArrayY=MathArrays.convolve(accelDataArrayY,smootheningBox);
                accelDataArrayZ=MathArrays.convolve(accelDataArrayZ,smootheningBox);
                //Putting smoothed values back in accelData
                for (int i=0;i<accelData.size();i++){
                    AccelData temp=(AccelData) accelData.get(i);
                    temp.x=accelDataArrayX[i];
                    temp.y=accelDataArrayY[i];
                    temp.z=accelDataArrayZ[i];
                    accelData.set(i,temp);
                }
                //2.Map to smoothed DoubleArrayLists
                for (int i=0;i<accelData.size();i++){
                    AccelData temp=(AccelData)accelData.get(i);
                    smoothedX.add(temp.getX());
                    smoothedY.add(temp.getY());
                    smoothedZ.add(temp.getZ());
                }
                //for X axis smoothed sequence
                ACFX=getACF(smoothedX,100);
                kurtosisX=getKurtosis(smoothedX);
                skewnessX=getSkewness(smoothedX);
                //for Y axis smoothed sequence
                ACFY=getACF(smoothedY,100);
                kurtosisY=getKurtosis(smoothedY);
                skewnessY=getSkewness(smoothedY);
                //for Z axis smoothed sequence
                ACFZ=getACF(smoothedZ,100);
                kurtosisZ=getKurtosis(smoothedZ);
                skewnessZ=getSkewness(smoothedZ);


                Toast.makeText(this,"accelData:"+accelData.size()+"\nACFX:"+ACFX+"KurtosisX:"+kurtosisX+"\nSkewnessX:"+skewnessX, Toast.LENGTH_LONG).show();
            }

            //Toast.makeText(this,"SmoothedX:"+smoothedX.size(),Toast.LENGTH_LONG).show();
        }
        if (accelData!=null)
        {
            if (accelData.size()>=200){//when it is almost 200 remove the first element then add a new element
                accelData.remove(0);
                AccelData sample=new AccelData(System.currentTimeMillis(),sensorEvent.values[0],sensorEvent.values[1],sensorEvent.values[2]);
                accelData.add(sample);}
            else {
                AccelData sample = new AccelData(System.currentTimeMillis(), sensorEvent.values[0], sensorEvent.values[1], sensorEvent.values[2]);
                accelData.add(sample);
            }
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }
    public double getACF(DoubleArrayList doubleArrayList,int lags){
        double sum=0;
        for (int i=0;i<lags;i++){
            double acf= Descriptive.autoCorrelation(doubleArrayList,i,Descriptive.mean(doubleArrayList),Descriptive.variance(doubleArrayList.size(),Descriptive.sum(doubleArrayList),Descriptive.sumOfSquares(doubleArrayList)));
            sum+=acf;
        }

        return sum;
    }
    public double getKurtosis(DoubleArrayList doubleArrayList){
        double kurtosis=Descriptive.kurtosis(doubleArrayList,Descriptive.mean(doubleArrayList),Descriptive.standardDeviation(Descriptive.variance(doubleArrayList.size(),Descriptive.sum(doubleArrayList),Descriptive.sumOfSquares(doubleArrayList))));
        return kurtosis;
    }
    public double getSkewness(DoubleArrayList doubleArrayList){
        double skewness=Descriptive.skew(doubleArrayList,Descriptive.mean(doubleArrayList),Descriptive.standardDeviation(Descriptive.variance(doubleArrayList.size(),Descriptive.sum(doubleArrayList),Descriptive.sumOfSquares(doubleArrayList))));
        return skewness;
    }
}
