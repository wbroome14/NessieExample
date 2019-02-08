package wbroome14.nessiehackathonexample.DataModels;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

public class Merchant implements Parcelable {
    private String _id;
    @Expose
    private String name;
    @Expose
    private String[] category;
    @Expose
    private Geocode geocode;
    @Expose
    private Address address;

    public Merchant(String _id, String name, String[] category, Geocode geocode, Address address) {
        this._id = _id;
        this.name = name;
        this.category = category;
        this.geocode = geocode;
        this.address = address;
    }

    public Merchant(Parcel in) {
        _id = in.readString();
        name = in.readString();
        category = in.createStringArray();
        geocode = new Geocode(in);
        address = new Address(in);
    }

    public Merchant() {

    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getCategory() {
        return category;
    }

    public void setCategory(String[] category) {
        this.category = category;
    }

    public Geocode getGeocode() {
        return geocode;
    }

    public void setGeocode(Geocode geocode) {
        this.geocode = geocode;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(_id);
        parcel.writeString(name);
        parcel.writeStringArray(category);
        geocode.writeToParcel(parcel, i);
        address.writeToParcel(parcel, i);
    }

    public static final Parcelable.Creator<Merchant> CREATOR
            = new Parcelable.Creator<Merchant>() {
        public Merchant createFromParcel(Parcel in) {
            return new Merchant(in);
        }

        public Merchant[] newArray(int size) {
            return new Merchant[size];
        }
    };

    public String toJSONPayload() {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(this, this.getClass());
    }
}

