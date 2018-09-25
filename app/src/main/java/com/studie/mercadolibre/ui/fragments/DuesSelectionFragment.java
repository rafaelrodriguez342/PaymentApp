package com.studie.mercadolibre.ui.fragments;


import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import com.studie.mercadolibre.R;
import com.studie.mercadolibre.models.PayerCost;
import com.studie.mercadolibre.ui.adapters.PaymentOptionsAdapter;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DuesSelectionFragment extends BasePaymentOptionsFragment {

    @Override
    protected void requestData() {
        mainViewModel.requestPayerCosts();
    }

    protected void observeLiveData() {
        super.observeLiveData();
        mainViewModel.getPayerCostLiveData().observe(this, this::populateBanks);
    }

    private void populateBanks(List<PayerCost> payerCosts) {
        RecyclerView.Adapter adapter = new PaymentOptionsAdapter(payerCosts, payerCost -> mainViewModel.addDuesToPaymentRequest(((PayerCost) payerCost).getInstallments()));
        recyclerView.setAdapter(adapter);
    }

    public static DuesSelectionFragment newInstance() {
        return new DuesSelectionFragment();
    }

    @Override
    protected int getMenuResourceId() {
        return R.string.installments_selection_label;
    }
}
