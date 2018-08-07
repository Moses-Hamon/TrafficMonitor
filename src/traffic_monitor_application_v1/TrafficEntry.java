package traffic_monitor_application_v1;

import java.util.Date;

/**
 * Class that holds the properties of one Traffic Entry
 *
 * @author Moses
 */
public class TrafficEntry extends Object
{

    public TrafficEntry(String date, String stationLocationID, String avgNumberOfVehicles, String avgVelocity)
    {
        this.date = date;
        this.stationLocationID = stationLocationID;
        this.avgNumberOfVehicles = avgNumberOfVehicles;
        this.avgVelocity = avgVelocity;
    }

    //properties
    public String date;
    public String stationLocationID;
    public String numberOfLanes;
    public String totalNumberOfVehicles;
    public String avgNumberOfVehicles;
    public String avgVelocity;

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getStationLocationID()
    {
        return stationLocationID;
    }

    public void setStationLocationID(String stationLocationID)
    {
        this.stationLocationID = stationLocationID;
    }

    public String getNumberOfLanes()
    {
        return numberOfLanes;
    }

    public void setNumberOfLanes(String numberOfLanes)
    {
        this.numberOfLanes = numberOfLanes;
    }

    public String getTotalNumberOfVehicles()
    {
        return totalNumberOfVehicles;
    }

    public void setTotalNumberOfVehicles(String totalNumberOfVehicles)
    {
        this.totalNumberOfVehicles = totalNumberOfVehicles;
    }

    public String getAvgNumberOfVehicles()
    {
        return avgNumberOfVehicles;
    }

    public void setAvgNumberOfVehicles(String avgNumberOfVehicles)
    {
        this.avgNumberOfVehicles = avgNumberOfVehicles;
    }

    public String getAvgVelocity()
    {
        return avgVelocity;
    }

    public void setAvgVelocity(String avgVelocity)
    {
        this.avgVelocity = avgVelocity;
    }

    
}
