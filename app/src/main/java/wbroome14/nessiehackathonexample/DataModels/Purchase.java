package wbroome14.nessiehackathonexample.DataModels;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

public class Purchase implements Parcelable {

    private String _id;
    @Expose
    private String type;
    @Expose
    private String merchant_id;
    @Expose
    private String payer_id;
    @Expose
    private String purchase_date;
    @Expose
    private double amount;
    @Expose
    private String status;
    @Expose
    private String medium;
    @Expose
    private String description;

    public Purchase(String _id, String type, String merchant_id, String payer_id, String purchase_date, double amount, String status, String medium, String description) {
        this._id = _id;
        this.type = type;
        this.merchant_id = merchant_id;
        this.payer_id = payer_id;
        this.purchase_date = purchase_date;
        this.amount = amount;
        this.status = status;
        this.medium = medium;
        this.description = description;
    }

    public Purchase(Parcel in) {
        _id = in.readString();
        type = in.readString();
        merchant_id = in.readString();
        payer_id = in.readString();
        purchase_date = in.readString();
        amount = in.readDouble();
        status = in.readString();
        medium = in.readString();
        description = in.readString();
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

    public String getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(String merchant_id) {
        this.merchant_id = merchant_id;
    }

    public String getPayer_id() {
        return payer_id;
    }

    public void setPayer_id(String payer_id) {
        this.payer_id = payer_id;
    }

    public String getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_date(String purchase_date) {
        this.purchase_date = purchase_date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
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
        parcel.writeString(merchant_id);
        parcel.writeString(payer_id);
        parcel.writeString(purchase_date);
        parcel.writeDouble(amount);
        parcel.writeString(status);
        parcel.writeString(medium);
        parcel.writeString(description);
    }

    public static final Parcelable.Creator<Purchase> CREATOR
            = new Parcelable.Creator<Purchase>() {
        public Purchase createFromParcel(Parcel in) {
            return new Purchase(in);
        }

        public Purchase[] newArray(int size) {
            return new Purchase[size];
        }
    };

    public String toJSONPayload() {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(this, this.getClass());
    }
}

