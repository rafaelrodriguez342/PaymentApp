package com.studie.mercadolibre.di.modules;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;

import com.studie.mercadolibre.ApplicationClass;
import com.studie.mercadolibre.ApplicationViewModelFactory;
import com.studie.mercadolibre.repositories.interfaces.BanksRepository;
import com.studie.mercadolibre.repositories.interfaces.DuesRecommendationRepository;
import com.studie.mercadolibre.repositories.interfaces.PaymentMethodRepository;
import com.studie.mercadolibre.repositories.network.BanksApiClient;
import com.studie.mercadolibre.repositories.network.DuesRecommendationApiClient;
import com.studie.mercadolibre.repositories.network.PaymentMethodsApiClient;
import com.studie.mercadolibre.repositories.network.RetrofitApiClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Module to provide general application dependencies.
 */
@Module
public class CoreModule {

    @Provides
    Context provideApplicationContext(ApplicationClass applicationClass) {
        return applicationClass;
    }

    @Provides
    @Singleton
    public RetrofitApiClient provideVideosRetrofitApiClient() {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.mercadopago.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .build();

        return retrofit.create(RetrofitApiClient.class);
    }

    @Provides
    @Singleton
    public ViewModelProvider.Factory provideViewModelFactory(ApplicationViewModelFactory applicationViewModelFactory) {
        return applicationViewModelFactory;
    }

    @Provides
    @Singleton
    public BanksRepository provideBanksRepository(BanksApiClient banksApiClient) {
        return banksApiClient;
    }

    @Provides
    @Singleton
    public PaymentMethodRepository providePaymentMethodRepository(PaymentMethodsApiClient paymentMethodsApiClient) {
        return paymentMethodsApiClient;
    }

    @Provides
    @Singleton
    public DuesRecommendationRepository provideDuesRecommendationRepository(DuesRecommendationApiClient duesRecommendationApiClient) {
        return duesRecommendationApiClient;
    }
}
