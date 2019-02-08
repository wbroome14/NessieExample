package wbroome14.nessiehackathonexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import wbroome14.nessiehackathonexample.DataModels.Customer;
import wbroome14.nessiehackathonexample.Providers.ApiCaller;
import wbroome14.nessiehackathonexample.Providers.CustomerEndPointProvider;
import wbroome14.nessiehackathonexample.CustomViews.CustomerListItem;

public class CustomerListActivity extends AppCompatActivity implements View.OnClickListener, ApiCaller.ApiResponse {
    LinearLayout customerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Bring up create customer
                openCustomerCreation();
            }
        });

        customerList = findViewById(R.id.customer_list);
    }

    @Override
    protected void onResume() {
        super.onResume();
        CustomerEndPointProvider.getCustomers(this);
    }

    private void openCustomerCreation() {
        Intent intent = new Intent(this, CustomerCreationActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if (view instanceof CustomerListItem) {
            CustomerListItem item = (CustomerListItem) view;

            Intent intent = new Intent(CustomerListActivity.this, AccountListActivity.class);
            intent.putExtra("customer", item.getCustomer());
            startActivity(intent);
        }
    }

    @Override
    public void apiResponseCallback(String response) {
        try {
            customerList.removeAllViews();
            Gson gson = new Gson();
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Customer c = gson.fromJson(jsonObject.toString(), Customer.class);

                CustomerListItem item = new CustomerListItem(this, c);
                customerList.addView(item);
                item.setOnClickListener(this);
            }
        } catch (JSONException e) {
            Log.e("ERROR", "", e);
        }
    }
}
