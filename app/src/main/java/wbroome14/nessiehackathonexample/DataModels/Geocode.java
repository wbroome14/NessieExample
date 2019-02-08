package wbroome14.nessiehackathonexample.DataModels;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

public class Geocode implements Parcelable {
    @Expose
    private double lat;
    @Expose
    private double lng;

    public Geocode(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public Geocode(Parcel in) {
        lat = in.readDouble();
        lng = in.readDouble();
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(lat);
        parcel.writeDouble(lng);
    }

    public static final Parcelable.Creator<Geocode> CREATOR
            = new Parcelable.Creator<Geocode>() {
        public Geocode createFromParcel(Parcel in) {
            return new Geocode(in);
        }

        public Geocode[] newArray(int size) {
            return new Geocode[size];
        }
    };

    public String toJSONPayload() {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(this, this.getClass());
    }
}
