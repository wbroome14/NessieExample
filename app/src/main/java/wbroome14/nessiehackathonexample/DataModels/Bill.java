package wbroome14.nessiehackathonexample.DataModels;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

public class Bill implements Parcelable {

    private String _id;
    @Expose
    private String status;
    @Expose
    private String payee;
    @Expose
    private String nickname;
    @Expose
    private String creation_date;
    @Expose
    private String payment_date;
    @Expose
    private double payment_amount;
    @Expose
    private String upcoming_payment_date;
    @Expose
    private String account_id;

    public Bill(String _id, String status, String payee, String nickname, String creation_date, String payment_date, double payment_amount, String upcoming_payment_date, String account_id) {
        this._id = _id;
        this.status = status;
        this.payee = payee;
        this.nickname = nickname;
        this.creation_date = creation_date;
        this.payment_date = payment_date;
        this.payment_amount = payment_amount;
        this.upcoming_payment_date = upcoming_payment_date;
        this.account_id = account_id;
    }

    public Bill(Parcel in) {
        _id = in.readString();
        status = in.readString();
        payee = in.readString();
        nickname = in.readString();
        creation_date = in.readString();
        payment_date = in.readString();
        payment_amount = in.readDouble();
        upcoming_payment_date = in.readString();
        account_id = in.readString();
    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public String getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(String payment_date) {
        this.payment_date = payment_date;
    }

    public String getUpcoming_payment_date() {
        return upcoming_payment_date;
    }

    public void setUpcoming_payment_date(String upcoming_payment_date) {
        this.upcoming_payment_date = upcoming_payment_date;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(_id);
        parcel.writeString(status);
        parcel.writeString(payee);
        parcel.writeString(nickname);
        parcel.writeString(creation_date);
        parcel.writeString(payment_date);
        parcel.writeDouble(payment_amount);
        parcel.writeString(upcoming_payment_date);
        parcel.writeString(account_id);
    }

    public static final Parcelable.Creator<Bill> CREATOR
            = new Parcelable.Creator<Bill>() {
        public Bill createFromParcel(Parcel in) {
            return new Bill(in);
        }

        public Bill[] newArray(int size) {
            return new Bill[size];
        }
    };

    public String toJSONPayload() {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(this, this.getClass());
    }

    public double getPayment_amount() {
        return payment_amount;
    }

    public void setPayment_amount(double payment_amount) {
        this.payment_amount = payment_amount;
    }
}
