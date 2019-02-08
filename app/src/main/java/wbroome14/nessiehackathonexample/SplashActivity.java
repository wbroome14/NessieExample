package wbroome14.nessiehackathonexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        Button enterpriseBtn = findViewById(R.id.enterprise_btn);
        Button customerBtn = findViewById(R.id.customer_btn);
        Button atmBtn = findViewById(R.id.atm_btn);

        customerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this, CustomerListActivity.class);
                startActivity(intent);
            }
        });

        enterpriseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this, EnterpriseCustomerListActivity.class);
                startActivity(intent);
            }
        });

        atmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this, ATMListActivity.class);
                startActivity(intent);
            }
        });
    }
}
