package wbroome14.nessiehackathonexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.LinearLayout;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import wbroome14.nessiehackathonexample.CustomViews.EnterpriseCustomerListItem;
import wbroome14.nessiehackathonexample.DataModels.EnterpriseCustomer;
import wbroome14.nessiehackathonexample.Providers.ApiCaller;
import wbroome14.nessiehackathonexample.Providers.EnterpriseEndpointProvider;

public class EnterpriseCustomerListActivity extends AppCompatActivity implements ApiCaller.ApiResponse {
    LinearLayout customerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterprise_customer_list);

        customerList = findViewById(R.id.customer_list);
    }

    @Override
    protected void onResume() {
        super.onResume();
        EnterpriseEndpointProvider.getCustomers(this);
    }

    @Override
    public void apiResponseCallback(String response) {
        try {
            customerList.removeAllViews();
            Gson gson = new Gson();
            JSONObject temp = new JSONObject(response);
            JSONArray jsonArray = temp.getJSONArray("results");
            for (int i = 0; i < 10; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                EnterpriseCustomer c = gson.fromJson(jsonObject.toString(), EnterpriseCustomer.class);

                EnterpriseCustomerListItem item = new EnterpriseCustomerListItem(this, c);
                customerList.addView(item);
            }
        } catch (JSONException e) {
            Log.e("ERROR", "", e);
        }
    }
}
