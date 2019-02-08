package wbroome14.nessiehackathonexample.DataModels;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

public class Address implements Parcelable {
    @Expose
    private String street_number;
    @Expose
    private String street_name;
    @Expose
    private String city;
    @Expose
    private String state;
    @Expose
    private String zip;

    public Address(String street_number, String street_name, String city, String state, String zip) {
        this.street_number = street_number;
        this.street_name = street_name;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public Address(Parcel in) {
        street_number = in.readString();
        street_name = in.readString();
        city = in.readString();
        state = in.readString();
        zip = in.readString();
    }

    public Address() {

    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet_name() {
        return street_name;
    }

    public void setStreet_name(String street_name) {
        this.street_name = street_name;
    }

    public String getStreet_number() {
        return street_number;
    }

    public void setStreet_number(String street_number) {
        this.street_number = street_number;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(street_number);
        parcel.writeString(street_name);
        parcel.writeString(city);
        parcel.writeString(state);
        parcel.writeString(zip);
    }

    public static final Parcelable.Creator<Address> CREATOR
            = new Parcelable.Creator<Address>() {
        public Address createFromParcel(Parcel in) {
            return new Address(in);
        }

        public Address[] newArray(int size) {
            return new Address[size];
        }
    };
}
