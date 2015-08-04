package entities;

/**
 * Created by srish on 8/3/15.
 */
public class ListLocation {

    private double latitude;
    private double longitude;
     private String list_name;

    public ListLocation(double latitude, double longitude, String list_name) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.list_name = list_name;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getList_name() {
        return list_name;
    }

    public void setList_name(String list_name) {
        this.list_name = list_name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }


}
