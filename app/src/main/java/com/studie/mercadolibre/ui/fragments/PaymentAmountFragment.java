package com.studie.mercadolibre.ui.fragments;

import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.studie.mercadolibre.R;
import com.studie.mercadolibre.models.PaymentRequest;
import com.studie.mercadolibre.viewmodels.MainViewModel;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link PaymentAmountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PaymentAmountFragment extends Fragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private MainViewModel mainViewModel;
    private Button addAmountButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(MainViewModel.class);
        observeLiveData();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final EditText amountEditText = view.findViewById(R.id.amount_edit_text);
        addAmountButton = view.findViewById(R.id.add_amount_continue_button);
        addAmountButton.setOnClickListener(view1 -> {
            if (isValidAmount(amountEditText.getText())) {
                mainViewModel.addAmountToPaymentRequest(amountEditText.getText().toString());
            } else {
                Toast.makeText(getContext(), R.string.please_validate_value, Toast.LENGTH_SHORT);
            }
        });
    }

    public static PaymentAmountFragment newInstance() {
        return new PaymentAmountFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment_amount, container, false);
    }

    private void observeLiveData() {
        mainViewModel.getPaymentRequestLiveData().observe(this, this::displayPaymentDialog);
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    private void displayPaymentDialog(PaymentRequest paymentRequest) {
        if (paymentRequest != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
            builder.setTitle(R.string.payment_data)
                    .setMessage(getString(R.string.payment_description, paymentRequest.getAmount(),
                            paymentRequest.getPaymentMethod().getName(), paymentRequest.getBank().getName(), paymentRequest.getDues()))
                    .setPositiveButton(android.R.string.yes, (dialog, which) -> mainViewModel.completePaymentRequest()).show();
        }
    }

    private boolean isValidAmount(Editable amount) {
        return (amount != null && !amount.toString().isEmpty() && TextUtils.isDigitsOnly(amount.toString()));
    }
}
