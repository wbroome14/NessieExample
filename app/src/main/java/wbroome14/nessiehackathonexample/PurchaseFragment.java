package wbroome14.nessiehackathonexample;

import android.content.Intent;
import android.databinding.ObservableList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import wbroome14.nessiehackathonexample.CustomViews.PurchaseListItem;
import wbroome14.nessiehackathonexample.DataModels.Purchase;


public class PurchaseFragment extends Fragment {
    public static final String TITLE = "Purchases";

    private AccountDetailsViewModel viewModel;
    private LinearLayout purchaseList;

    public static PurchaseFragment newInstance() {
        return new PurchaseFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewModel = (AccountDetailsViewModel) ((ActivityWithViewModel) getActivity()).getViewModel();

        return inflater.inflate(R.layout.fragment_purchases, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        purchaseList = view.findViewById(R.id.purchases_list);

        viewModel.purchases.addOnListChangedCallback(new ObservableList.OnListChangedCallback<ObservableList<Purchase>>() {
            @Override
            public void onChanged(ObservableList<Purchase> sender) {
                createSubViews(sender);
            }

            @Override
            public void onItemRangeChanged(ObservableList<Purchase> sender, int positionStart, int itemCount) {
                createSubViews(sender);
            }

            @Override
            public void onItemRangeInserted(ObservableList<Purchase> sender, int positionStart, int itemCount) {
                createSubViews(sender);
            }

            @Override
            public void onItemRangeMoved(ObservableList<Purchase> sender, int fromPosition, int toPosition, int itemCount) {
                createSubViews(sender);
            }

            @Override
            public void onItemRangeRemoved(ObservableList<Purchase> sender, int positionStart, int itemCount) {
                createSubViews(sender);
            }
        });
        createSubViews(viewModel.purchases);
    }

    private void createSubViews(ObservableList<Purchase> data) {
        purchaseList.removeAllViews();
        for (Purchase b: data) {
            PurchaseListItem item = new PurchaseListItem(getContext(), b);
            purchaseList.addView(item);
        }
    }
}
