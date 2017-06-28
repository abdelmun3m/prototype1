package com.abdelmun3m.prototype;



import java.util.ArrayList;




public class Data_Analyzer {
    public Location get_nearest_obstacle(Location location){

        ArrayList<Location> database_locations=new ArrayList<Location>();
        for (Location item :database_locations) {
            item.setDistance(distanceBetweenLogLat(location,item));
        }

        Location min=database_locations.get(0);
        for (Location item :database_locations) {
            if (item.getDistance() < min.getDistance()){
                min.setDistance(item.getDistance());
            }
        }
        return min;
    }

    public double calculate_D(Location location_1,Location location_2){


        return  distanceBetweenLogLat(location_1,location_2);

    }

    public double calculate_Delay(Location location_1,Location location_2,double n, float speed){
        double distance=distanceBetweenLogLat(location_1,location_2)-n;
        double time=distance/speed;

        return time;
    }


    private double distanceBetweenLogLat(Location l1,Location l2)
    {
        double lat1=l1.getLatitude();
        double log1=l1.getLongtiude();
        double lat2=l2.getLatitude();
        double log2=l2.getLongtiude();
        lat1=removeLastTowNum(lat1);
        log1=removeLastTowNum(log1);
        lat2=removeLastTowNum(lat2);
        log2=removeLastTowNum(log2);
        double distance= CalculateLocation(lat1,log1,lat2,log2);
        //return distance in meter
        return distance*1000;
    }
    private double CalculateLocation(double sLatitude, double sLongitude, double eLatitude,
                                     double eLongitude)
    {
        double radiansOverDegrees = (Math.PI / 180.0);

        double sLatitudeRadians = sLatitude * radiansOverDegrees;
        double sLongitudeRadians = sLongitude * radiansOverDegrees;
        double eLatitudeRadians = eLatitude * radiansOverDegrees;
        double eLongitudeRadians = eLongitude * radiansOverDegrees;

        double dLongitude = eLongitudeRadians - sLongitudeRadians;
        double dLatitude = eLatitudeRadians - sLatitudeRadians;


        double result1 = Math.pow(Math.sin(dLatitude / 2.0), 2.0) +
                Math.cos(sLatitudeRadians) * Math.cos(eLatitudeRadians) *
                        Math.pow(Math.sin(dLongitude / 2.0), 2.0);

        // Using 3956 as the number of miles around the earth
        double result2 = 3956.0 * 2.0 *
                Math.atan2(Math.sqrt(result1), Math.sqrt(1.0 - result1));

        return result2;
    }
    private  double removeLastTowNum(double num)
    {
        String number = Double.toString(num);
        number = number.substring(0, number.length() - 2);
        num = Double.parseDouble(number);
        return  num;
    }

}
