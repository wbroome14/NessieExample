package wbroome14.nessiehackathonexample;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import wbroome14.nessiehackathonexample.DataModels.Account;
import wbroome14.nessiehackathonexample.DataModels.Customer;

public class AccountDetailsActivity extends AppCompatActivity implements ActivityWithViewModel<AccountDetailsViewModel> {

    private AccountDetailsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_details);

        Account account = getIntent().getParcelableExtra("account");
        Customer customer = getIntent().getParcelableExtra("customer");

        viewModel = new AccountDetailsViewModel(getApplication(), account, customer);

        final ViewPager viewPager = findViewById(R.id.container);

        AccountDetailsPageAdapter mAccountDetailsPageAdapter = new AccountDetailsPageAdapter(getSupportFragmentManager());

        viewPager.setAdapter(mAccountDetailsPageAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreateWithdrawalActivity();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (viewModel != null) {
            viewModel.refreshWithdrawals();
        } else {
            Log.d("AccountDetailsActivity", "viewModel was null onResume()");
        }
    }

    private void openCreateWithdrawalActivity() {
        Intent intent = new Intent(this, WithdrawalCreationActivity.class);
        intent.putExtra("account", viewModel.getAccount());
        startActivity(intent);
    }

    public AccountDetailsViewModel getViewModel() {
        return viewModel;
    }

    private class AccountDetailsPageAdapter extends FragmentPagerAdapter {

        AccountDetailsPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return BillsFragment.newInstance();
                case 1:
                    return PurchaseFragment.newInstance();
                case 2:
                    return WithdrawalsFragment.newInstance();
                default:
                    return BillsFragment.newInstance();
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return BillsFragment.TITLE;
                case 1:
                    return PurchaseFragment.TITLE;
                case 2:
                    return WithdrawalsFragment.TITLE;
                default:
                    return BillsFragment.TITLE;
            }
        }
    }
}
