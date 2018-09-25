package com.studie.mercadolibre.repositories.network;

import android.content.Context;

import com.studie.mercadolibre.R;
import com.studie.mercadolibre.models.Bank;
import com.studie.mercadolibre.models.retrofit.BanksDTO;
import com.studie.mercadolibre.repositories.interfaces.BanksRepository;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BanksApiClient implements BanksRepository {
    private RetrofitApiClient retrofitApiClient;
    private final Context context;

    @Inject
    public BanksApiClient(RetrofitApiClient retrofitApiClient, Context context) {
        this.retrofitApiClient = retrofitApiClient;
        this.context = context;
    }

    @Override
    public void getBanks(NetworkRequestListener<List<Bank>> responseListener, String paymentMethodId) {
        Call<List<BanksDTO>> request = retrofitApiClient.getBanks(context.getString(R.string.mercadolibre_api_key), paymentMethodId);
        try {
            request.enqueue(new Callback<List<BanksDTO>>() {
                @Override
                public void onResponse(Call<List<BanksDTO>> call, Response<List<BanksDTO>> response) {
                    responseListener.onSuccessNetworkRequest(response.body()
                            .stream()
                            .map(bankDTO -> new Bank(bankDTO.getId(), bankDTO.getName(), bankDTO.getSecureThumbnail()))
                            .collect(Collectors.toList())
                    );
                }

                @Override
                public void onFailure(Call<List<BanksDTO>> call, Throwable t) {
                    responseListener.onFailureRequest(t);
                }
            });
        } catch (NullPointerException e) {
            responseListener.onFailureRequest(e);
        }
    }
}
