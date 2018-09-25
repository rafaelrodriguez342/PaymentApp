package com.studie.mercadolibre.repositories.network;

import android.content.Context;

import com.studie.mercadolibre.R;
import com.studie.mercadolibre.models.PayerCost;
import com.studie.mercadolibre.models.retrofit.DuesRecommendedMessageDTO;
import com.studie.mercadolibre.repositories.interfaces.DuesRecommendationRepository;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DuesRecommendationApiClient implements DuesRecommendationRepository {
    private final RetrofitApiClient retrofitApiClient;
    private final Context context;

    @Inject
    public DuesRecommendationApiClient(RetrofitApiClient retrofitApiClient, Context context) {
        this.retrofitApiClient = retrofitApiClient;
        this.context = context;
    }

    @Override
    public void getRecommendedMessages(NetworkRequestListener<List<PayerCost>> responseListener, String paymentMethodId, String amount, String issuerId) {
        Call<List<DuesRecommendedMessageDTO>> request = retrofitApiClient.getDueRecommendation(context.getString(R.string.mercadolibre_api_key), paymentMethodId, amount, issuerId);
        try {
            request.enqueue(new Callback<List<DuesRecommendedMessageDTO>>() {
                @Override
                public void onResponse(Call<List<DuesRecommendedMessageDTO>> call, Response<List<DuesRecommendedMessageDTO>> response) {
                    responseListener.onSuccessNetworkRequest(response.body().get(0).getPayerCosts()
                            .stream()
                            .map(payerCostDTO -> new PayerCost(payerCostDTO.getRecommendedMessage(), payerCostDTO.getInstallments()))
                            .collect(Collectors.toList())
                    );
                }

                @Override
                public void onFailure(Call<List<DuesRecommendedMessageDTO>> call, Throwable t) {
                    responseListener.onFailureRequest(t);
                }
            });
        } catch (NullPointerException e) {
            responseListener.onFailureRequest(e);
        }
    }
}
