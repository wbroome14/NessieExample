package wbroome14.nessiehackathonexample;

import android.databinding.ObservableList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import wbroome14.nessiehackathonexample.CustomViews.BillListItem;
import wbroome14.nessiehackathonexample.DataModels.Bill;

public class BillsFragment extends Fragment {
    public static final String TITLE = "Bills";

    private AccountDetailsViewModel viewModel;
    private LinearLayout billsList;

    public static BillsFragment newInstance() {
        return new BillsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewModel = (AccountDetailsViewModel) ((ActivityWithViewModel) getActivity()).getViewModel();

        return inflater.inflate(R.layout.bills_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        billsList = view.findViewById(R.id.bills_list);

        viewModel.bills.addOnListChangedCallback(new ObservableList.OnListChangedCallback<ObservableList<Bill>>() {
            @Override
            public void onChanged(ObservableList<Bill> sender) {
                createSubViews(sender);
            }

            @Override
            public void onItemRangeChanged(ObservableList<Bill> sender, int positionStart, int itemCount) {
                createSubViews(sender);
            }

            @Override
            public void onItemRangeInserted(ObservableList<Bill> sender, int positionStart, int itemCount) {
                createSubViews(sender);
            }

            @Override
            public void onItemRangeMoved(ObservableList<Bill> sender, int fromPosition, int toPosition, int itemCount) {
                createSubViews(sender);
            }

            @Override
            public void onItemRangeRemoved(ObservableList<Bill> sender, int positionStart, int itemCount) {
                createSubViews(sender);
            }
        });
        createSubViews(viewModel.bills);
    }

    private void createSubViews(ObservableList<Bill> data) {
        billsList.removeAllViews();
        for (Bill b: data) {
            BillListItem item = new BillListItem(getContext(), b);
            billsList.addView(item);
        }
    }
}
