package wbroome14.nessiehackathonexample;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.res.Resources;
import android.databinding.ObservableArrayList;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import wbroome14.nessiehackathonexample.DataModels.Account;
import wbroome14.nessiehackathonexample.DataModels.Bill;
import wbroome14.nessiehackathonexample.DataModels.Customer;
import wbroome14.nessiehackathonexample.DataModels.Merchant;
import wbroome14.nessiehackathonexample.DataModels.Purchase;
import wbroome14.nessiehackathonexample.DataModels.Withdrawal;
import wbroome14.nessiehackathonexample.Providers.ApiCaller;
import wbroome14.nessiehackathonexample.Providers.CustomerEndPointProvider;

public class AccountDetailsViewModel extends AndroidViewModel {
    public ObservableArrayList<Bill> bills = new ObservableArrayList<>();
    public ObservableArrayList<Purchase> purchases = new ObservableArrayList<>();
    public ObservableArrayList<Withdrawal> withdrawals = new ObservableArrayList<>();
    public ObservableArrayList<Merchant> merchants = new ObservableArrayList<>();

    private Account account;
    private Customer customer;

    private final Resources resources;

    public AccountDetailsViewModel(Application application, Account account, Customer customer) {
        super(application);
        resources = application.getResources();
        this.account = account;
        this.customer = customer;
        populateLists();
        refreshMerchants();
    }

    private void populateLists() {
        refreshBills();
        refreshPurchases();
        refreshWithdrawals();
    }

    private void refreshBills() {
        CustomerEndPointProvider.getBills(account.getId(), new Callback<>(bills, Bill.class));
    }

    private void refreshPurchases() {
      CustomerEndPointProvider.getPurchases(account.getId(), new Callback<>(purchases, Purchase.class));
    }

    void refreshWithdrawals() {
        CustomerEndPointProvider.getWithdrawals(account.getId(), new Callback<>(withdrawals, Withdrawal.class));
    }

    private void refreshMerchants() {
        CustomerEndPointProvider.getMerchants(new Callback<>(merchants, Merchant.class));
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    private class Callback<T> implements ApiCaller.ApiResponse {
        private ArrayList<T> list;
        private Class<T> classType;

        Callback(ArrayList<T> list, Class<T> classType) {
            this.list = list;
            this.classType = classType;
        }

        @Override
        public void apiResponseCallback(String response) {
            try {
                list.clear();
                ArrayList<T> temp = new ArrayList<>();
                Gson gson = new Gson();
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    try {
                        T c = gson.fromJson(jsonObject.toString(), classType);
                        temp.add(c);
                    } catch (JsonSyntaxException e) {
                        Log.e("Invalid JSON", "Invalid JSON Response Format: " + jsonObject, e);
                    }
                }
                list.addAll(temp);
            } catch (Exception e) {
                Log.e("ERROR", "", e);
            }
        }
    }
}
