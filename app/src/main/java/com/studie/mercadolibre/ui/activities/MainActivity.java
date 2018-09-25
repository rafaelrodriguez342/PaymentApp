package com.studie.mercadolibre.ui.activities;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.studie.mercadolibre.R;
import com.studie.mercadolibre.ui.fragments.BankSelectionFragment;
import com.studie.mercadolibre.ui.fragments.DuesSelectionFragment;
import com.studie.mercadolibre.ui.fragments.PaymentAmountFragment;
import com.studie.mercadolibre.ui.fragments.PaymentMethodSelectionFragment;
import com.studie.mercadolibre.viewmodels.MainViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    private MainViewModel viewModel;
    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel.class);
        observeLiveData();
    }

    private void observeLiveData() {
        viewModel.getSelectedViewLiveData().observe(this, paymentView -> {
            if (paymentView != null) {
                switch (paymentView) {
                    case ADD_AMOUNT:
                        putFragment(PaymentAmountFragment.newInstance());
                        break;

                    case ADD_PAYMENT_METHOD:
                        putFragment(PaymentMethodSelectionFragment.newInstance());
                        break;

                    case ADD_BANK:
                        putFragment(BankSelectionFragment.newInstance());
                        break;

                    case ADD_DUES:
                        putFragment(DuesSelectionFragment.newInstance());
                        break;
                }
            }
        });

    }

    public enum PaymentView {
        ADD_AMOUNT, ADD_PAYMENT_METHOD, ADD_BANK, ADD_DUES
    }

    private void putFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}
