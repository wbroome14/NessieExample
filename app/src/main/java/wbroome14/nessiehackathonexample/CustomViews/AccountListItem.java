package wbroome14.nessiehackathonexample.CustomViews;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.widget.TextView;

import wbroome14.nessiehackathonexample.DataModels.Account;
import wbroome14.nessiehackathonexample.R;

public class AccountListItem extends ConstraintLayout {
    private Account account;

    private TextView nickname;
    private TextView type;
    private TextView balance;

    private int layout = R.layout.account_list_item;

    public AccountListItem(Context context, Account account) {
        super(context);
        this.account = account;
        init();
    }

    public AccountListItem(Context context) {
        super(context);
    }

    public AccountListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AccountListItem(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        inflate(getContext(), layout, this);
        nickname = findViewById(R.id.nickname);
        type = findViewById(R.id.type);
        balance = findViewById(R.id.balance);

        nickname.setText(account.getNickname());
        type.setText(account.getType());
        balance.setText(account.getBalance() + "");
    }

    public Account getAccount() {
        return account;
    }
}