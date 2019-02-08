package wbroome14.nessiehackathonexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import wbroome14.nessiehackathonexample.DataModels.Account;
import wbroome14.nessiehackathonexample.DataModels.Customer;
import wbroome14.nessiehackathonexample.Providers.ApiCaller;
import wbroome14.nessiehackathonexample.Providers.CustomerEndPointProvider;

public class AccountCreationActivity extends AppCompatActivity implements ApiCaller.ApiResponse {
    private Customer customer;
    private TextView nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_creation);

        customer = getIntent().getParcelableExtra("customer");

        nickname = findViewById(R.id.nickname);

        Button cancelButton = findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button createButton = findViewById(R.id.create_button);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAndCreate();
            }
        });
    }


    private void validateAndCreate() {
        //check nickname
        boolean invalid = false;
        Account account = new Account();
        account.setNickname(nickname.getText().toString());
        if (TextUtils.isEmpty(nickname.getText().toString())) {
            nickname.setError("Required!");
            invalid = true;
        }

        account.setType("Credit Card");
        account.setBalance(0);
        account.setRewards(0);

        if (!invalid) {
            CustomerEndPointProvider.postAccount(account, customer.getId(), this);
        }
    }

    @Override
    public void apiResponseCallback(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getInt("code") == 201) {
                finish();
            }
        } catch (JSONException e) {
            Log.e("ERROR", "", e);
        }
    }
}
