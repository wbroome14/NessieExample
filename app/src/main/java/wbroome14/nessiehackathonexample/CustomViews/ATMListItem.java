package wbroome14.nessiehackathonexample.CustomViews;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.widget.TextView;

import wbroome14.nessiehackathonexample.DataModels.ATM;
import wbroome14.nessiehackathonexample.R;

public class ATMListItem extends ConstraintLayout {
    private ATM atm;

    public ATMListItem(Context context, ATM atm) {
        super(context);
        this.atm = atm;
        init();
    }

    public ATMListItem(Context context) {
        super(context);
    }

    public ATMListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ATMListItem(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        int layout = R.layout.atm_list_item;
        inflate(getContext(), layout, this);
        TextView nameTextView = findViewById(R.id.name);
        TextView address = findViewById(R.id.address_text_view);
        TextView amountLeft = findViewById(R.id.amount_left);

        nameTextView.setText(atm.getName());
        address.setText(atm.getAddress().getStreet_number() + " " + atm.getAddress().getStreet_name());
        amountLeft.setText(Double.toString(atm.getAmount_left()));
    }

    public ATM getAtm() {
        return atm;
    }
}
