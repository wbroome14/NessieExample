package wbroome14.nessiehackathonexample;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import wbroome14.nessiehackathonexample.CustomViews.AccountListItem;
import wbroome14.nessiehackathonexample.DataModels.Account;
import wbroome14.nessiehackathonexample.DataModels.Customer;
import wbroome14.nessiehackathonexample.Providers.ApiCaller;
import wbroome14.nessiehackathonexample.Providers.CustomerEndPointProvider;

public class AccountListActivity extends AppCompatActivity implements ApiCaller.ApiResponse, View.OnClickListener {
    Customer customer;

    LinearLayout accountList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_list);

        customer = getIntent().getParcelableExtra("customer");

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Bring up create customer
                openAccountCreation();
            }
        });
        accountList = findViewById(R.id.account_list);
    }

    @Override
    protected void onResume() {
        super.onResume();
        CustomerEndPointProvider.getAccounts(customer.getId(), this);
    }


    @Override
    public void apiResponseCallback(String response) {
        try {
            accountList.removeAllViews();
            Gson gson = new Gson();
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Account a = gson.fromJson(jsonObject.toString(), Account.class);

                AccountListItem item = new AccountListItem(this, a);
                accountList.addView(item);
                item.setOnClickListener(this);
            }
        } catch (JSONException e) {
            Log.e("ERROR", "", e);
        }
    }

    private void openAccountCreation() {
        Intent intent = new Intent(this, AccountCreationActivity.class);
        intent.putExtra("customer", customer);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if (view instanceof AccountListItem) {
            AccountListItem item = (AccountListItem) view;

            Intent intent = new Intent(AccountListActivity.this, AccountDetailsActivity.class);
            intent.putExtra("customer", customer);
            intent.putExtra("account", item.getAccount());
            startActivity(intent);
        }
    }

}
