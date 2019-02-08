package wbroome14.nessiehackathonexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import wbroome14.nessiehackathonexample.DataModels.Account;
import wbroome14.nessiehackathonexample.DataModels.Withdrawal;
import wbroome14.nessiehackathonexample.Providers.ApiCaller;
import wbroome14.nessiehackathonexample.Providers.CustomerEndPointProvider;

public class WithdrawalCreationActivity extends AppCompatActivity implements ApiCaller.ApiResponse {
    private Account account;

    private TextView accountNickname, date;
    private EditText amount, description;
    private Button cancelButton, createButton;
    //date - pre-fill
    //status - pre-fill pending
    //medium - pre-fill balance
    //description
    //amount

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawal_creation);

        account = getIntent().getParcelableExtra("account");

        amount = findViewById(R.id.amount);
        description = findViewById(R.id.description);
        cancelButton = findViewById(R.id.cancel_button);
        createButton = findViewById(R.id.create_button);

        accountNickname = findViewById(R.id.account_nickname);
        accountNickname.setText(account.getNickname());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        Date today = new Date();
        date = findViewById(R.id.date);
        date.setText(formatter.format(today));

        setupClickListeners();
    }

    private void setupClickListeners() {
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (formIsValid()) {
                    CustomerEndPointProvider.postWithdrawal(createWithdrawal(), account.getId(),
                            WithdrawalCreationActivity.this);
                }
            }
        });
    }

    private boolean formIsValid() {
        return !TextUtils.isEmpty(amount.getText().toString())
                && !TextUtils.isEmpty(description.getText().toString());
    }

    private Withdrawal createWithdrawal() {
        Withdrawal w = new Withdrawal();
        w.setAmount(Double.parseDouble(amount.getText().toString()));
        w.setDescription(description.getText().toString());
        w.setMedium("balance");
        w.setStatus("pending");
        return w;
    }

    @Override
    public void apiResponseCallback(String response) {
        finish();
    }
}
