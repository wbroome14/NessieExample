package wbroome14.nessiehackathonexample.DataModels;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

public class EnterpriseCustomer implements Parcelable {
    private String _id;
    @Expose
    private String first_name;
    @Expose
    private String last_name;
    @Expose
    private Address address;

    public EnterpriseCustomer(String _id, String first_name, String last_name, Address address) {
        this._id = _id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
    }

    public EnterpriseCustomer(Parcel in) {
        _id = in.readString();
        first_name = in.readString();
        last_name = in.readString();
        address = new Address(in);
    }

    public EnterpriseCustomer() {

    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(_id);
        parcel.writeString(first_name);
        parcel.writeString(last_name);
        address.writeToParcel(parcel, i);
    }

    public static final Parcelable.Creator<EnterpriseCustomer> CREATOR
            = new Parcelable.Creator<EnterpriseCustomer>() {
        public EnterpriseCustomer createFromParcel(Parcel in) {
            return new EnterpriseCustomer(in);
        }

        public EnterpriseCustomer[] newArray(int size) {
            return new EnterpriseCustomer[size];
        }
    };

    public String toJSONPayload() {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(this, this.getClass());
    }
}