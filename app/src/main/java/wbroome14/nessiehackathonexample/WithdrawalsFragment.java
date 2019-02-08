package wbroome14.nessiehackathonexample;

import android.databinding.ObservableList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import wbroome14.nessiehackathonexample.CustomViews.WithdrawalListItem;
import wbroome14.nessiehackathonexample.DataModels.Withdrawal;
import wbroome14.nessiehackathonexample.Providers.ApiCaller;
import wbroome14.nessiehackathonexample.Providers.CustomerEndPointProvider;


public class WithdrawalsFragment extends Fragment implements ApiCaller.ApiResponse, View.OnClickListener {
    public static final String TITLE = "Withdrawals";

    private AccountDetailsViewModel viewModel;
    private LinearLayout withdrawalsList;

    public static WithdrawalsFragment newInstance() {
        return new WithdrawalsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewModel = (AccountDetailsViewModel) ((ActivityWithViewModel) getActivity()).getViewModel();

        return inflater.inflate(R.layout.fragment_withdrawals, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        withdrawalsList = view.findViewById(R.id.withdrawals_list);

        Button deleteAllBtn = view.findViewById(R.id.delete_withdrawals_btn);
        deleteAllBtn.setOnClickListener(this);

        viewModel.withdrawals.addOnListChangedCallback(new ObservableList.OnListChangedCallback<ObservableList<Withdrawal>>() {
            @Override
            public void onChanged(ObservableList<Withdrawal> sender) {
                createSubViews(sender);
            }

            @Override
            public void onItemRangeChanged(ObservableList<Withdrawal> sender, int positionStart, int itemCount) {
                createSubViews(sender);
            }

            @Override
            public void onItemRangeInserted(ObservableList<Withdrawal> sender, int positionStart, int itemCount) {
                createSubViews(sender);
            }

            @Override
            public void onItemRangeMoved(ObservableList<Withdrawal> sender, int fromPosition, int toPosition, int itemCount) {
                createSubViews(sender);
            }

            @Override
            public void onItemRangeRemoved(ObservableList<Withdrawal> sender, int positionStart, int itemCount) {
                createSubViews(sender);
            }
        });

        createSubViews(viewModel.withdrawals);
    }

    @Override
    public void onClick(View v) {
        CustomerEndPointProvider.deleteWithdrawals(this);
    }

    @Override
    public void apiResponseCallback(String response) {
        viewModel.refreshWithdrawals();
    }

    private void createSubViews(ObservableList<Withdrawal> data) {
        withdrawalsList.removeAllViews();
        for (Withdrawal w : data) {
            WithdrawalListItem item = new WithdrawalListItem(getContext(), w);
            withdrawalsList.addView(item);
        }
    }
}