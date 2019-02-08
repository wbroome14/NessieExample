package wbroome14.nessiehackathonexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import wbroome14.nessiehackathonexample.DataModels.Address;
import wbroome14.nessiehackathonexample.DataModels.Customer;
import wbroome14.nessiehackathonexample.Providers.ApiCaller;
import wbroome14.nessiehackathonexample.Providers.CustomerEndPointProvider;

public class CustomerCreationActivity extends AppCompatActivity implements ApiCaller.ApiResponse {
    private EditText firstName, lastName, streetNumber, streetName, city, state, zip;
    private Button cancelButton, createButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_creation);

        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        streetNumber = findViewById(R.id.street_number);
        streetName = findViewById(R.id.street_name);
        city = findViewById(R.id.city);
        state = findViewById(R.id.state);
        zip = findViewById(R.id.zip);

        cancelButton = findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        createButton = findViewById(R.id.create_button);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAndCreate();
            }
        });
    }

    private void validateAndCreate() {
        //check first name
        boolean invalid = false;
        Customer customer = new Customer();
        customer.setFirst_name(firstName.getText().toString());
        if (TextUtils.isEmpty(firstName.getText().toString())) {
            firstName.setError("Required!");
            invalid = true;
        }
        //check last name
        customer.setLast_name(lastName.getText().toString());
        if (TextUtils.isEmpty(lastName.getText().toString())) {
            lastName.setError("Required!");
            invalid = true;
        }

        Address address = new Address();
        //check street number
        address.setStreet_number(streetNumber.getText().toString());
        if (TextUtils.isEmpty(streetNumber.getText().toString())) {
            streetNumber.setError("Required!");
            invalid = true;
        }

        //check street name
        address.setStreet_name(streetName.getText().toString());
        if (TextUtils.isEmpty(streetName.getText().toString())) {
            streetName.setError("Required!");
            invalid = true;
        }

        //check city
        address.setCity(city.getText().toString());
        if (TextUtils.isEmpty(city.getText().toString())) {
            city.setError("Required!");
            invalid = true;
        }

        //check state
        address.setState(state.getText().toString());
        if (TextUtils.isEmpty(state.getText().toString())) {
            state.setError("Required!");
            invalid = true;
        }

        //check zip
        address.setZip(zip.getText().toString());
        if (TextUtils.isEmpty(zip.getText().toString())) {
            zip.setError("Required!");
            invalid = true;
        }

        customer.setAddress(address);
        if (!invalid) {
            CustomerEndPointProvider.postCustomer(customer, this);
            //TODO: set progress marker on create button and turn off in callback
        }

    }

    @Override
    public void apiResponseCallback(String response) {
        //TODO: validate response code
        try {
            JSONObject jsonObject = new JSONObject(response);
            Toast.makeText(this, "Code: " + jsonObject.getInt("code"), Toast.LENGTH_SHORT).show();
            if (jsonObject.getInt("code") == 201) {
                finish();
            } else {
                //TODO: error handling
            }
        } catch (JSONException e) {
            Log.e("ERROR", "", e);
        }

    }
}
