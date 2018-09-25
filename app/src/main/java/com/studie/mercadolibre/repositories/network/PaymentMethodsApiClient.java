package com.studie.mercadolibre.repositories.network;

import android.content.Context;

import com.studie.mercadolibre.R;
import com.studie.mercadolibre.models.PaymentMethod;
import com.studie.mercadolibre.models.retrofit.PaymentMethodDTO;
import com.studie.mercadolibre.repositories.interfaces.PaymentMethodRepository;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentMethodsApiClient implements PaymentMethodRepository {

    private final RetrofitApiClient retrofitApiClient;
    private final Context context;

    @Inject
    public PaymentMethodsApiClient(RetrofitApiClient retrofitApiClient, Context context) {
        this.retrofitApiClient = retrofitApiClient;
        this.context = context;
    }

    @Override
    public void getPaymentMethods(NetworkRequestListener<List<PaymentMethod>> networkRequestListener) {
        Call<List<PaymentMethodDTO>> networkRequest = retrofitApiClient.getPaymentMethods(context.getString(R.string.mercadolibre_api_key));
        try {
            networkRequest.enqueue(new Callback<List<PaymentMethodDTO>>() {
                @Override
                public void onResponse(Call<List<PaymentMethodDTO>> call, Response<List<PaymentMethodDTO>> response) {
                    networkRequestListener.onSuccessNetworkRequest(response.body()
                            .stream()
                            .map(paymentMethodDTO -> new PaymentMethod(paymentMethodDTO.getId(), paymentMethodDTO.getName(), paymentMethodDTO.getThumbnail()))
                            .collect(Collectors.toList())
                    );
                }

                @Override
                public void onFailure(Call<List<PaymentMethodDTO>> call, Throwable t) {
                    networkRequestListener.onFailureRequest(t);
                }
            });
        } catch (NullPointerException e) {
            networkRequestListener.onFailureRequest(e);
        }
    }
}
