package traffic_monitor_application_v1;

import java.util.Date;

/**
 * Class that holds the properties of one Traffic Entry
 *
 * @author Moses
 */
public class TrafficEntry extends Object[]
{

    Date date;
    int stationLocationID;
    int numberOfLanes;
    int totalNumberOfVehicles;
    int avgNumberOfVehicles;
    int avgVelocity;

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
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
