package com.studie.mercadolibre.ui.fragments;


import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import com.studie.mercadolibre.R;
import com.studie.mercadolibre.models.PaymentMethod;
import com.studie.mercadolibre.ui.adapters.PaymentOptionsAdapter;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentMethodSelectionFragment extends BasePaymentOptionsFragment {

    @Override
    protected void requestData() {
        mainViewModel.requestPaymentMethods();
    }

    protected void observeLiveData() {
        super.observeLiveData();
        mainViewModel.getPaymentMethodsLiveData().observe(this, this::populatePaymentMethods);
    }

    private void populatePaymentMethods(List<PaymentMethod> paymentMethods) {
        RecyclerView.Adapter adapter = new PaymentOptionsAdapter(paymentMethods, paymentMethod -> mainViewModel.addPaymentMethodToPaymentRequest((PaymentMethod) paymentMethod));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected int getMenuResourceId() {
        return R.string.payment_method_selection_label;
    }

    public static PaymentMethodSelectionFragment newInstance() {
        return new PaymentMethodSelectionFragment();
    }
}
