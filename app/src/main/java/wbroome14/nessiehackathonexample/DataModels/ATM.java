package wbroome14.nessiehackathonexample.DataModels;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

public class ATM implements Parcelable {
    private String _id;
    @Expose
    private String name;
    @Expose
    private Geocode geocode;
    @Expose
    private boolean accessibility;
    @Expose
    private String[] hours;
    @Expose
    private Address address;
    @Expose
    private String[] language_list;
    @Expose
    private double amount_left;

    public ATM(String _id, String name, Geocode geocode, boolean accessibility, String[] hours,
               Address address, String[] language_list, double amount_left) {
        this._id = _id;
        this.name = name;
        this.geocode = geocode;
        this.accessibility = accessibility;
        this.hours = hours;
        this.address = address;
        this.language_list = language_list;
        this.amount_left = amount_left;
    }

    public ATM(Parcel in) {
        _id = in.readString();
        name = in.readString();
        geocode = new Geocode(in);
        accessibility = in.readByte() != 0;
        in.readStringArray(hours);
        address = new Address(in);
        in.readStringArray(language_list);
        amount_left = in.readDouble();
    }

    public ATM() {

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

    public Geocode getGeocode() {
        return geocode;
    }

    public void setGeocode(Geocode geocode) {
        this.geocode = geocode;
    }

    public boolean getAccessibility() {
        return accessibility;
    }

    public void setAccessibility(boolean accessibility) {
        this.accessibility = accessibility;
    }

    public String[] getHours() {
        return hours;
    }

    public void setHours(String[] hours) {
        this.hours = hours;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String[] getLanguage_list() {
        return language_list;
    }

    public void setLanguage_list(String[] language_list) {
        this.language_list = language_list;
    }

    public double getAmount_left() {
        return amount_left;
    }

    public void setAmount_left(double amount_left) {
        this.amount_left = amount_left;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(_id);
        parcel.writeString(name);
        geocode.writeToParcel(parcel, i);
        parcel.writeByte((byte) (accessibility ? 1 : 0));
        parcel.writeStringArray(hours);
        address.writeToParcel(parcel, i);
        parcel.writeStringArray(language_list);
        parcel.writeDouble(amount_left);
    }

    public static final Parcelable.Creator<ATM> CREATOR
            = new Parcelable.Creator<ATM>() {
        public ATM createFromParcel(Parcel in) {
            return new ATM(in);
        }

        public ATM[] newArray(int size) {
            return new ATM[size];
        }
    };

    public String toJSONPayload() {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(this, this.getClass());
    }
}