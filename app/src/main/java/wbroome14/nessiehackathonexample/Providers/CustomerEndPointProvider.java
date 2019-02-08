package wbroome14.nessiehackathonexample.Providers;

import android.util.Log;

import java.util.ArrayList;

import wbroome14.nessiehackathonexample.DataModels.Account;
import wbroome14.nessiehackathonexample.DataModels.Customer;
import wbroome14.nessiehackathonexample.DataModels.Withdrawal;

public class CustomerEndPointProvider {
    private final static String API_KEY = "c2731de4c82120fecaa45bc319772751";
    private final static String API_URL = "http://api.reimaginebanking.com/";

    public static void getCustomers(ApiCaller.ApiResponse callback) {
        String endpoint  = API_URL + "customers?key=" + API_KEY;
        ApiCaller api = new ApiCaller(endpoint, "", Method.GET, callback);
        api.execute();
    }

    public static ArrayList<Account> getAccounts() {


        return new ArrayList<>();
    }

    public static void getAccounts(String customerId, ApiCaller.ApiResponse callback) {
        String endpoint = API_URL + "customers/" + customerId + "/accounts?key=" + API_KEY;
        ApiCaller api = new ApiCaller(endpoint, "", Method.GET, callback);
        api.execute();
    }

    public static void postCustomer(Customer customer, ApiCaller.ApiResponse callback) {
        String endpoint = API_URL + "customers?key=" + API_KEY;
        ApiCaller api = new ApiCaller(endpoint, customer.toJSONPayload(), Method.POST, callback);
        api.execute();
    }

    public static void postAccount(Account account, String customerId, ApiCaller.ApiResponse callback) {
        String endpoint = API_URL + "customers/" + customerId + "/accounts?key=" + API_KEY;
        ApiCaller api = new ApiCaller(endpoint, account.toJSONPayload(), Method.POST, callback);
        api.execute();
    }

    public static void getWithdrawals(String accountId, ApiCaller.ApiResponse callback) {
        String endpoint = API_URL + "accounts/" + accountId + "/withdrawals?key=" + API_KEY;
        ApiCaller api = new ApiCaller(endpoint, "", Method.GET, callback);
        api.execute();
    }

    public static void deleteWithdrawals(ApiCaller.ApiResponse callback) {
        String endpoint = API_URL + "data?type=Withdrawals&key=" + API_KEY;
        ApiCaller api = new ApiCaller(endpoint, "", Method.DELETE, callback);
        api.execute();
    }

    public static void postWithdrawal(Withdrawal withdrawal, String accountId, ApiCaller.ApiResponse callback) {
        String endpoint = API_URL + "accounts/" + accountId + "/withdrawals?key=" + API_KEY;
        ApiCaller api = new ApiCaller(endpoint, withdrawal.toJSONPayload(), Method.POST, callback);
        api.execute();
    }

    public static void getBills(String accountId, ApiCaller.ApiResponse callback) {
        String endpoint = API_URL + "accounts/" + accountId + "/bills?key=" + API_KEY;
        ApiCaller api = new ApiCaller(endpoint, "", Method.GET, callback);
        api.execute();
    }

    public static void getPurchases(String accountId, ApiCaller.ApiResponse callback) {
        String endpoint = API_URL + "accounts/" + accountId + "/purchases?key=" + API_KEY;
        ApiCaller api = new ApiCaller(endpoint, "", Method.GET, callback);
        api.execute();
    }

    public static void getMerchant(String merchantId, ApiCaller.ApiResponse callback) {
        String endpoint = API_URL + "merchants/" + merchantId + "?key=" + API_KEY;
        ApiCaller api = new ApiCaller(endpoint, "", Method.GET, callback);
        api.execute();
    }

    public static void getMerchants(ApiCaller.ApiResponse callback) {
        String endpoint = API_URL + "merchants?key=" + API_KEY;
        ApiCaller api = new ApiCaller(endpoint, "", Method.GET, callback);
        api.execute();
    }

    public static void getATMs(String radius, ApiCaller.ApiResponse callback) {
        String endpoint = API_URL + "atms?lat=38.9283&lng=-77.1753&rad=" + radius + "&key=" + API_KEY;
        ApiCaller api = new ApiCaller(endpoint, "", Method.GET, callback);
        api.execute();
    }

    public static void getATMsPageCall(String request, ApiCaller.ApiResponse callback) {
        String endpoint = API_URL.substring(0, API_URL.length() - 1) + request;
        ApiCaller api = new ApiCaller(endpoint, "", Method.GET, callback);
        api.execute();
    }
}
