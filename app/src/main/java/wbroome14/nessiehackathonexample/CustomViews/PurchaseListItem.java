package wbroome14.nessiehackathonexample.CustomViews;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import wbroome14.nessiehackathonexample.DataModels.Purchase;
import wbroome14.nessiehackathonexample.R;

public class PurchaseListItem extends ConstraintLayout {
    private Purchase purchase;

    private TextView status;
    private TextView merchant_id;
    private TextView amount;
    private TextView purchase_date;

    private int layout = R.layout.purchase_list_item;

    public PurchaseListItem(Context context, Purchase purchase) {
        super(context);
        this.purchase = purchase;
        init();
    }

    public PurchaseListItem(Context context) {
        super(context);
    }

    public PurchaseListItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PurchaseListItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        inflate(getContext(), layout, this);
        status = findViewById(R.id.status);
        merchant_id = findViewById(R.id.merchant_id);
        amount = findViewById(R.id.amount);
        purchase_date = findViewById(R.id.purchase_date);

        status.setText(purchase.getStatus());
        merchant_id.setText(purchase.getMerchant_id());
        amount.setText(purchase.getAmount() + "");
        purchase_date.setText(purchase.getPurchase_date());
    }

    public Purchase getPurchase() {
        return purchase;
    }
}
