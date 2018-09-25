package com.studie.mercadolibre.ui.fragments;


import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import com.studie.mercadolibre.R;
import com.studie.mercadolibre.models.Bank;
import com.studie.mercadolibre.ui.adapters.PaymentOptionsAdapter;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class BankSelectionFragment extends BasePaymentOptionsFragment {

    @Override
    protected void requestData() {
        mainViewModel.requestBanks();
    }

    @Override
    protected void observeLiveData() {
        super.observeLiveData();
        mainViewModel.getBanksLiveData().observe(this, this::populateBanks);
    }

    private void populateBanks(List<Bank> banks) {
        RecyclerView.Adapter adapter = new PaymentOptionsAdapter(banks, paymentMethod -> mainViewModel.addBankToPaymentRequest((Bank) paymentMethod));
        recyclerView.setAdapter(adapter);
    }

    public static BankSelectionFragment newInstance() {
        return new BankSelectionFragment();
    }

    @Override
    protected int getMenuResourceId() {
        return R.string.bank_selection_label;
    }

}
