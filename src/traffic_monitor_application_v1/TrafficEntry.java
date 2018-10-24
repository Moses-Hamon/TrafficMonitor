package traffic_monitor_application_v1;


import java.io.Serializable;
import org.apache.commons.lang3.StringUtils;

/**
 * Class that holds the properties of one Traffic Entry
 *
 * @author Moses
 */

public class TrafficEntry implements Serializable
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
/**
 * 
 * @param time
 * @param stationLocationID
 * @param numberOfLanes
 * @param totalNumberOfVehicles
 * @param avgNumberOfVehicles
 * @param avgVelocity 
 */
    public TrafficEntry(String time, int stationLocationID, int numberOfLanes, int totalNumberOfVehicles, int avgNumberOfVehicles, int avgVelocity )
    {
        this.time = time;
        this.stationLocationID = stationLocationID;
        this.numberOfLanes = numberOfLanes;
        this.totalNumberOfVehicles = totalNumberOfVehicles;
        this.avgNumberOfVehicles = avgNumberOfVehicles;
        this.avgVelocity = avgVelocity;
    }
    
    /**
     * Method used for Table model (as the table only requires 4 fields)
     * @return 
     */
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
    /**
     * Converts the traffic entry into a String[]
     * 
     * @return String[] 
     */
    public String[] toStringArray()
    {
        String[] array = new String[]
        {
            this.time,
            Integer.toString(this.stationLocationID),
            Integer.toString(this.numberOfLanes),
            Integer.toString(this.totalNumberOfVehicles),
            Integer.toString(this.avgNumberOfVehicles),
            Integer.toString(this.avgVelocity)
        };
        return array;
    }
    /**
     * Converts the TrafficEntry to a Single String.
     * @return String
     * 
     */
    public String convertToString()
    {
        String str;
        
        str = StringUtils.join(this.toStringArray(), ",");
        
        
//        StringBuilder trafficEntryString = new StringBuilder();
//        trafficEntryString.append(time).append(",");
//        trafficEntryString.append(stationLocationID).append(",");
//        trafficEntryString.append(numberOfLanes).append(",");
//        trafficEntryString.append(totalNumberOfVehicles).append(",");
//        trafficEntryString.append(avgNumberOfVehicles).append(",");
//        trafficEntryString.append(avgVelocity);
//
//        String str = trafficEntryString.toString();
        return str;
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
