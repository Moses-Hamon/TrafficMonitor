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
    public String date;
    public int stationLocationID;
    public int numberOfLanes;
    public int totalNumberOfVehicles;
    public int avgNumberOfVehicles;
    public int avgVelocity;

    public TrafficEntry(String date, int stationLocationID, int numberOfLanes, int avgNumberOfVehicles, int avgVelocity, int totalNumberOfVehicles)
    {
        this.date = date;
        this.stationLocationID = stationLocationID;
        this.numberOfLanes = numberOfLanes;
        this.avgNumberOfVehicles = avgNumberOfVehicles;
        this.avgVelocity = avgVelocity;
        this.totalNumberOfVehicles = totalNumberOfVehicles;
    }
    
    public Object[] ToArray()
    {
           
        Object[] entryArray = new Object[]
        {
            this.date,
            this.stationLocationID,
            this.avgNumberOfVehicles,
            this.avgVelocity

        };
        
        return entryArray;
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
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
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
