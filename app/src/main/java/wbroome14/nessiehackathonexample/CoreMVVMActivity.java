package wbroome14.nessiehackathonexample;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;

public abstract class CoreMVVMActivity<M extends ViewModel> extends AppCompatActivity {
    protected M viewModel;

    protected abstract Class<? extends M> getViewModelClass();

    protected void setContentView() {
        viewModel = ViewModelProviders.of(this, provideViewModelFactory()).get(getViewModelClass());
    }

    protected ViewModelProvider.Factory provideViewModelFactory() {
        return ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication());
    }
}
