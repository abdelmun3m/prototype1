package com.abdelmun3m.prototype;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 3M on 27/11/2016.
 */

public class LvQTrain {

    public double sekwenessX,KurtosisX,sumOflagsX,sekwenessY,KurtosisY,sumOflagsY,sekwenessZ,KurtosisZ,sumOflagsZ;
    public String classType;
    public  double alpha=0.1;
    public   ArrayList<LvQTrain> obstacles=new ArrayList<LvQTrain>();
    public  ArrayList<LvQTrain> NonObstacles=new ArrayList<LvQTrain>();
    public LvQTrain w1,w2;
    public LvQTrain( double sekwenessX,double KurtosisX,double sumOflagsX,double sekwenessY
            ,double KurtosisY,double sumOflagsY,double sekwenessZ,double KurtosisZ,double sumOflagsZ ,String classType)
    {
        this.sekwenessX=sekwenessX;
        this.KurtosisX=KurtosisX;
        this.sumOflagsX=sumOflagsX;
        this.sekwenessY=sekwenessY;
        this.KurtosisY=KurtosisY;
        this.sumOflagsY=sumOflagsY;
        this.sekwenessZ=sekwenessZ;
        this.KurtosisZ=KurtosisZ;
        this.sumOflagsZ=sumOflagsZ;
        this.classType=classType;
    }
    public  void defineType(ArrayList<LvQTrain> x)
    {
        for(int i=0;i<x.size();i++) {
            if (x.get(i).classType.toLowerCase() == "obstacles")
                obstacles.add(x.get(i));
            else
                NonObstacles.add(x.get(i));
        }
        w1=obstacles.get(0);
        w2=NonObstacles.get(0);
    }
    public  void Train(List<LvQTrain> temp) {
        for (LvQTrain input : temp) {
            String T = input.classType;
            double D1 = CalcDistance(w1, input);
            double D2 = CalcDistance(w2, input);
            double D;
            String c;
            if (D1 < D2) {
                D = D1;
                c = "obstacles";
            } else {
                D = D2;
                c = "NonObstacles";
            }
            if (c == T && D == D1)
                updateWeight(true, input, w1);
            else if (c == T && D == D2)
                updateWeight(true, input, w2);
            else if (c != T && D == D1)
                updateWeight(false, input, w1);
            else
                updateWeight(false, input, w1);

        }
    }
    public  double CalcDistance(LvQTrain w,LvQTrain x)
    {
        double D=Math.sqrt((Math.pow(w.sekwenessX,2)-Math.pow(x.sekwenessX,2))+(Math.pow(w.KurtosisX,2)-Math.pow(x.KurtosisX,2))+
                (Math.pow(w.sumOflagsX,2)-Math.pow(x.sumOflagsX,2))+(Math.pow(w.sekwenessY,2)-Math.pow(x.sekwenessY,2))+
                (Math.pow(w.KurtosisY,2)-Math.pow(x.KurtosisY,2))+(Math.pow(w.sumOflagsY,2)-Math.pow(x.sumOflagsY,2))+
                (Math.pow(w.sekwenessZ,2)-Math.pow(x.sekwenessZ,2))+(Math.pow(w.KurtosisZ,2)-Math.pow(x.KurtosisZ,2))+
                (Math.pow(w.sumOflagsZ,2)-Math.pow(x.sumOflagsZ,2)));
        return D;
    }
    public  void updateWeight(boolean isRight,LvQTrain x ,LvQTrain w)
    {
        double[] d={(x.sekwenessX-w.sekwenessX)*alpha,(x.KurtosisX-w.KurtosisX)*alpha,(x.sumOflagsX+w.sumOflagsX)*alpha,
                (x.sekwenessY-w.sekwenessY)*alpha,(x.KurtosisY-w.KurtosisY)*alpha,(x.sumOflagsY+w.sumOflagsY)*alpha,
                (x.sekwenessZ-w.sekwenessZ)*alpha,(x.KurtosisZ-w.KurtosisZ)*alpha,(x.sumOflagsZ+w.sumOflagsZ)*alpha};
        if(isRight)
        {
            w.sekwenessX+=d[0];
            w.KurtosisX+=d[1];
            w.sumOflagsX+=d[2];
            w.sekwenessY+=d[3];
            w.KurtosisY+=d[4];
            w.sumOflagsY+=d[5];
            w.sekwenessZ+=d[6];
            w.KurtosisZ+=d[7];
            w.sumOflagsZ+=d[8];
        }
        else
        {
            w.sekwenessX-=d[0];
            w.KurtosisX-=d[1];
            w.sumOflagsX-=d[2];
            w.sekwenessY-=d[3];
            w.KurtosisY-=d[4];
            w.sumOflagsY-=d[5];
            w.sekwenessZ-=d[6];
            w.KurtosisZ-=d[7];
            w.sumOflagsZ-=d[8];
        }
    }
    public double[] getW1()
    {
        double[] w={w1.sekwenessX,w1.KurtosisX,w1.sumOflagsX,w1.sekwenessY,w1.KurtosisY,w1.sumOflagsY,w1.sekwenessZ,w1.KurtosisZ,w1.sumOflagsZ};
        return w;
    }
    public double[] getW2()
    {
        double[] w={w2.sekwenessX,w2.KurtosisX,w2.sumOflagsX,w2.sekwenessY,w2.KurtosisY,w2.sumOflagsY,w2.sekwenessZ,w2.KurtosisZ,w2.sumOflagsZ};
        return w;
    }
}
