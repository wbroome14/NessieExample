package wbroome14.nessiehackathonexample.CustomViews;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import wbroome14.nessiehackathonexample.DataModels.Bill;
import wbroome14.nessiehackathonexample.R;

public class BillListItem extends ConstraintLayout {
    private Bill bill;

    private TextView status;
    private TextView payee;
    private TextView nickname;

    private int layout = R.layout.bill_list_item;

    public BillListItem(Context context, Bill bill) {
        super(context);
        this.bill = bill;
        init();
    }

    public BillListItem(Context context) {
        super(context);
    }

    public BillListItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BillListItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        inflate(getContext(), layout, this);
        status = findViewById(R.id.status);
        payee = findViewById(R.id.payee);
        nickname = findViewById(R.id.nickname);

        status.setText(bill.getStatus());
        payee.setText(bill.getPayee());
        nickname.setText(bill.getNickname());
    }

    public Bill getBill() {
        return bill;
    }
}
