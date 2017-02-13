package com.abdelmun3m.prototype;

/**
 * Created by 3M on 04/12/2016.
 */


public class LvqInput {
    public double sekwenessX,KurtosisX,sumOflagsX,sekwenessY,KurtosisY,sumOflagsY,sekwenessZ,KurtosisZ,sumOflagsZ;
    public int classType;
    public LvqInput( double sekwenessX,double KurtosisX,double sumOflagsX,double sekwenessY
            ,double KurtosisY,double sumOflagsY,double sekwenessZ,double KurtosisZ,double sumOflagsZ ,int classType)
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
}

