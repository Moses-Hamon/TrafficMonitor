package traffic_monitor_application_v1;

import java.util.Date;
import java.util.Arrays;

/**
 * Class that holds the properties of one Traffic Entry
 *
 * @author Moses
 */
public class TrafficEntry
{

    //properties
    public String time;
    public int stationLocationID;
    public int numberOfLanes;
    public int totalNumberOfVehicles;
    public int avgNumberOfVehicles;
    public int avgVelocity;

    public TrafficEntry()
    {
    }

    public TrafficEntry(String time, int stationLocationID, int numberOfLanes, int totalNumberOfVehicles, int avgNumberOfVehicles, int avgVelocity )
    {
        this.time = time;
        this.stationLocationID = stationLocationID;
        this.numberOfLanes = numberOfLanes;
        this.totalNumberOfVehicles = totalNumberOfVehicles;
        this.avgNumberOfVehicles = avgNumberOfVehicles;
        this.avgVelocity = avgVelocity;
    }
    
    public Object[] ToArray()
    {
           
        Object[] entryArray = new Object[]
        {
            this.time,
            this.stationLocationID,
            this.avgNumberOfVehicles,
            this.avgVelocity

        };
        
        return entryArray;
    }
    public String[] toStringArray(){
    
        String[] array = new  String[]{
            this.time.toString(),
            Integer.toString(this.stationLocationID) ,
            Integer.toString(this.numberOfLanes),
            Integer.toString(this.totalNumberOfVehicles),
            Integer.toString(this.avgNumberOfVehicles),
            Integer.toString(this.avgVelocity)
        };
        return array;
}

    //Switch case for Table Model Indexer
//    public Object getTrafficEntry(int index)
//    {
//        Object property = new Object();
//        
//        switch (index)
//        {
//            case 0: property = getDate();
//            break;
//            case 1: property = getStationLocationID();
//            break;
//            case 2: property = getAvgNumberOfVehicles();
//            break;
//            case 3: property = getAvgVelocity();
//            break;
//            default: property = "error";
//            break;
//        }
//        return property;
//    }



    //getters and setters for properties
    public String getDate()
    {
        return time;
    }

    public void setDate(String date)
    {
        this.time = date;
    }

    public int getStationLocationID()
    {
        return stationLocationID;
    }

    public void setStationLocationID(int stationLocationID)
    {
        this.stationLocationID = stationLocationID;
    }

    public int getNumberOfLanes()
    {
        return numberOfLanes;
    }

    public void setNumberOfLanes(int numberOfLanes)
    {
        this.numberOfLanes = numberOfLanes;
    }

    public int getTotalNumberOfVehicles()
    {
        return totalNumberOfVehicles;
    }

    public void setTotalNumberOfVehicles(int totalNumberOfVehicles)
    {
        this.totalNumberOfVehicles = totalNumberOfVehicles;
    }

    public int getAvgNumberOfVehicles()
    {
        return avgNumberOfVehicles;
    }

    public void setAvgNumberOfVehicles(int avgNumberOfVehicles)
    {
        this.avgNumberOfVehicles = avgNumberOfVehicles;
    }

    public int getAvgVelocity()
    {
        return avgVelocity;
    }

    public void setAvgVelocity(int avgVelocity)
    {
        this.avgVelocity = avgVelocity;
    }

}
