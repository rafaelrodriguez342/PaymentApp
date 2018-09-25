package com.studie.mercadolibre.repositories.network;

import com.studie.mercadolibre.models.retrofit.BanksDTO;
import com.studie.mercadolibre.models.retrofit.DuesRecommendedMessageDTO;
import com.studie.mercadolibre.models.retrofit.PaymentMethodDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitApiClient {

    @GET("/v1/payment_methods")
    Call<List<PaymentMethodDTO>> getPaymentMethods(@Query("public_key") String apiKey);

    @GET("/v1/payment_methods/card_issuers")
    Call<List<BanksDTO>> getBanks(@Query("public_key") String apiKey,
                                  @Query("payment_method_id") String paymentMethodId);

    @GET("/v1/payment_methods/installments")
    Call<List<DuesRecommendedMessageDTO>> getDueRecommendation(@Query("public_key") String apiKey,
                                                      @Query("payment_method_id") String paymentMethodId,
                                                      @Query("amount") String amount,
                                                      @Query("issuer.id") String issuerId);

}
