package wbroome14.nessiehackathonexample;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class CoreMVVMFragment<M extends ViewModel> extends Fragment {
    protected M viewModel;

    protected abstract Class<? extends M> getViewModelClass();

    protected abstract int getContentViewId();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity(), provideViewModelFactory()).get(getViewModelClass());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected ViewModelProvider.Factory provideViewModelFactory() {
        return ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication());
    }
}
