package wbroome14.nessiehackathonexample.Providers;

public class EnterpriseEndpointProvider {
    private final static String API_KEY = "c2731de4c82120fecaa45bc319772751";
    private final static String API_URL = "http://api.reimaginebanking.com/enterprise/";

    public static void getCustomers(ApiCaller.ApiResponse callback) {
        String endpoint  = API_URL + "customers?key=" + API_KEY;
        ApiCaller api = new ApiCaller(endpoint, "", Method.GET, callback);
        api.execute();
    }
}
