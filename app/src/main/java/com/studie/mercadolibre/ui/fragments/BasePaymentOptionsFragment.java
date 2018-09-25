package com.studie.mercadolibre.ui.fragments;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.studie.mercadolibre.R;
import com.studie.mercadolibre.viewmodels.MainViewModel;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

/**
 * A simple {@link Fragment} subclass.
 */
public class BasePaymentOptionsFragment extends Fragment {
    protected MainViewModel mainViewModel;
    protected RecyclerView recyclerView;
    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(MainViewModel.class);
        observeLiveData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_base_payment_options, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.menu_options);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        TextView optionsLabel = view.findViewById(R.id.options_label);
        optionsLabel.setText(getMenuResourceId());
    }

    @Override
    public void onResume() {
        super.onResume();
        requestData();
    }

    protected void observeLiveData() {
        mainViewModel.getNetworkErrorLiveData().observe(this, aVoid -> handleError());
    }

    protected int getMenuResourceId() {
        return -1;
    }

    protected void requestData() {

    }

    protected void displayToast(@StringRes int message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    private void handleError() {
        displayToast(R.string.network_error);
    }
}
