package wbroome14.nessiehackathonexample.CustomViews;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import wbroome14.nessiehackathonexample.DataModels.Withdrawal;
import wbroome14.nessiehackathonexample.R;

public class WithdrawalListItem extends LinearLayout {

    private Withdrawal withdrawal;

    public WithdrawalListItem(Context context, Withdrawal withdrawal) {
        super(context);
        this.withdrawal = withdrawal;
        init();
    }

    public WithdrawalListItem(Context context) {
        super(context);
    }

    public WithdrawalListItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WithdrawalListItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        int layout = R.layout.withdrawal_list_item;
        inflate(getContext(), layout, this);
        TextView status = findViewById(R.id.status);
        TextView description = findViewById(R.id.description);
        TextView amount = findViewById(R.id.amount);
        TextView transaction_date = findViewById(R.id.transaction_date);

        status.setText(withdrawal.getStatus());
        description.setText(withdrawal.getDescription());
        amount.setText(Double.toString(withdrawal.getAmount()));
        transaction_date.setText(withdrawal.getTransaction_date());
    }

    public Withdrawal getWithdrawal() {
        return withdrawal;
    }
}
