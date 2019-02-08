package wbroome14.nessiehackathonexample.DataModels;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

public class Withdrawal implements Parcelable {

    private String _id;
    private String type;
    @Expose
    private String transaction_date;
    @Expose
    private String status;
    private String payer_id;
    @Expose
    private String medium;
    @Expose
    private double amount;
    @Expose
    private String description;

    public Withdrawal(String _id, String type, String transaction_date, String status, String payer_id, String medium, double amount, String description) {
        this._id = _id;
        this.type = type;
        this.transaction_date = transaction_date;
        this.status = status;
        this.payer_id = payer_id;
        this.medium = medium;
        this.amount = amount;
        this.description = description;
    }

    public Withdrawal(Parcel in) {
        _id = in.readString();
        type = in.readString();
        transaction_date = in.readString();
        status = in.readString();
        payer_id = in.readString();
        medium = in.readString();
        amount = in.readDouble();
        description = in.readString();
    }

    public Withdrawal() {

    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPayer_id() {
        return payer_id;
    }

    public void setPayer_id(String payer_id) {
        this.payer_id = payer_id;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(_id);
        parcel.writeString(type);
        parcel.writeString(transaction_date);
        parcel.writeString(status);
        parcel.writeString(payer_id);
        parcel.writeString(medium);
        parcel.writeDouble(amount);
        parcel.writeString(description);
    }

    public static final Parcelable.Creator<Withdrawal> CREATOR
            = new Parcelable.Creator<Withdrawal>() {
        public Withdrawal createFromParcel(Parcel in) {
            return new Withdrawal(in);
        }

        public Withdrawal[] newArray(int size) {
            return new Withdrawal[size];
        }
    };

    public String toJSONPayload() {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(this, this.getClass());
    }
}

