package wbroome14.nessiehackathonexample.CustomViews;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.widget.TextView;

import wbroome14.nessiehackathonexample.DataModels.Customer;
import wbroome14.nessiehackathonexample.R;

public class CustomerListItem extends ConstraintLayout {
    private Customer customer;

    private TextView nameTextView;
    private TextView address;

    private int layout = R.layout.customer_list_item;

    public CustomerListItem(Context context, Customer customer) {
        super(context);
        this.customer = customer;
        init();
    }

    public CustomerListItem(Context context) {
        super(context);
    }

    public CustomerListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomerListItem(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        inflate(getContext(), layout, this);
        nameTextView = findViewById(R.id.customer_name);
        address = findViewById(R.id.account_count);

        nameTextView.setText(customer.getFirst_name() + " " + customer.getLast_name());
        address.setText(customer.getAddress().getStreet_number() + " " + customer.getAddress().getStreet_name());
    }

    public Customer getCustomer() {
        return customer;
    }
}
