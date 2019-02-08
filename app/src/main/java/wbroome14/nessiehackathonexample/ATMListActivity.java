package wbroome14.nessiehackathonexample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import wbroome14.nessiehackathonexample.CustomViews.ATMListItem;
import wbroome14.nessiehackathonexample.DataModels.ATM;
import wbroome14.nessiehackathonexample.Providers.ApiCaller;
import wbroome14.nessiehackathonexample.Providers.CustomerEndPointProvider;

public class ATMListActivity extends AppCompatActivity implements ApiCaller.ApiResponse {
    private LinearLayout atmList;
    private EditText radiusET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atm_list);

        atmList = findViewById(R.id.atm_list);
        radiusET = findViewById(R.id.radius_input_et);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Bring up create customer
                makeATMCall();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void makeATMCall() {
        if (!TextUtils.isEmpty(radiusET.getText().toString())) {
            atmList.removeAllViews();
            CustomerEndPointProvider.getATMs(radiusET.getText().toString(), this);
        }
//        CustomerEndPointProvider.getATMs("3", this);
    }

    @Override
    public void apiResponseCallback(String response) {
        try {
            Gson gson = new Gson();
            JSONObject object = new JSONObject(response);
            JSONArray jsonArray = object.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                ATM atm = gson.fromJson(jsonObject.toString(), ATM.class);

                ATMListItem item = new ATMListItem(this, atm);
                atmList.addView(item);
            }
            if (jsonArray.length() > 0) {
                String nextRequest = object.getJSONObject("paging").getString("next");
                CustomerEndPointProvider.getATMsPageCall(nextRequest, this);
            }
        } catch (JSONException e) {
            Log.e("ERROR", "", e);
        }
    }
}
