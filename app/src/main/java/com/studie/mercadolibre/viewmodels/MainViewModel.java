package com.studie.mercadolibre.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.studie.mercadolibre.commons.SingleLiveEvent;
import com.studie.mercadolibre.models.Bank;
import com.studie.mercadolibre.models.PayerCost;
import com.studie.mercadolibre.models.PaymentMethod;
import com.studie.mercadolibre.models.PaymentRequest;
import com.studie.mercadolibre.repositories.interfaces.BanksRepository;
import com.studie.mercadolibre.repositories.interfaces.DuesRecommendationRepository;
import com.studie.mercadolibre.repositories.interfaces.PaymentMethodRepository;
import com.studie.mercadolibre.repositories.network.NetworkRequestListener;
import com.studie.mercadolibre.ui.activities.MainActivity;

import java.util.List;

import javax.inject.Inject;

import static com.studie.mercadolibre.ui.activities.MainActivity.PaymentView.ADD_AMOUNT;
import static com.studie.mercadolibre.ui.activities.MainActivity.PaymentView.ADD_BANK;
import static com.studie.mercadolibre.ui.activities.MainActivity.PaymentView.ADD_DUES;
import static com.studie.mercadolibre.ui.activities.MainActivity.PaymentView.ADD_PAYMENT_METHOD;


public class MainViewModel extends ViewModel {

    private PaymentRequest.Builder paymentRequestBuilder = new PaymentRequest.Builder();
    private PaymentMethodRepository paymentMethodRepository;
    private BanksRepository banksRepository;
    private DuesRecommendationRepository duesRecommendationRepository;

    private MutableLiveData<List<PaymentMethod>> paymentMethodsLiveData;
    private MutableLiveData<List<Bank>> banksLiveData;
    private MutableLiveData<List<PayerCost>> payerCostsLiveData;
    private MutableLiveData<MainActivity.PaymentView> currentViewLiveData;
    private MutableLiveData<PaymentRequest> paymentRequestLiveData;
    private SingleLiveEvent<Void> handleNetworkError;


    @Inject
    public MainViewModel(PaymentMethodRepository paymentMethodRepository, BanksRepository banksRepository,
                         DuesRecommendationRepository duesRecommendationRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
        this.banksRepository = banksRepository;
        this.duesRecommendationRepository = duesRecommendationRepository;

        payerCostsLiveData = new MutableLiveData<>();
        paymentMethodsLiveData = new MutableLiveData<>();
        banksLiveData = new MutableLiveData<>();
        currentViewLiveData = new MutableLiveData<>();
        paymentRequestLiveData = new MutableLiveData<>();
        handleNetworkError = new SingleLiveEvent<>();
        currentViewLiveData.setValue(ADD_AMOUNT);
    }

    public void addAmountToPaymentRequest(String amount) {
        paymentRequestBuilder.withAmount(amount);
        currentViewLiveData.setValue(ADD_PAYMENT_METHOD);
    }

    public void requestPaymentMethods() {
        paymentMethodRepository.getPaymentMethods(new NetworkRequestListener<List<PaymentMethod>>() {
            @Override
            public void onSuccessNetworkRequest(List<PaymentMethod> response) {
                paymentMethodsLiveData.setValue(response);
            }

            @Override
            public void onFailureRequest(Throwable t) {
                handleNetworkError.call();
            }
        });
    }

    public void addPaymentMethodToPaymentRequest(PaymentMethod paymentMethod) {
        paymentRequestBuilder.withPaymentMethod(paymentMethod);
        currentViewLiveData.setValue(ADD_BANK);
    }

    public void requestBanks() {
        banksRepository.getBanks(new NetworkRequestListener<List<Bank>>() {
            @Override
            public void onSuccessNetworkRequest(List<Bank> response) {
                banksLiveData.setValue(response);
            }

            @Override
            public void onFailureRequest(Throwable t) {
                handleNetworkError.call();
            }
        }, paymentRequestBuilder.getPaymentMethod().getId());
    }

    public void addBankToPaymentRequest(Bank bank) {
        paymentRequestBuilder.withBank(bank);
        currentViewLiveData.setValue(ADD_DUES);
    }

    public void requestPayerCosts() {
        duesRecommendationRepository.getRecommendedMessages(new NetworkRequestListener<List<PayerCost>>() {
            @Override
            public void onSuccessNetworkRequest(List<PayerCost> payerCosts) {
                payerCostsLiveData.setValue(payerCosts);

            }

            @Override
            public void onFailureRequest(Throwable t) {
                handleNetworkError.call();
            }
        }, paymentRequestBuilder.getPaymentMethod().getId(), paymentRequestBuilder.getAmount(), paymentRequestBuilder.getBank().getId());
    }

    public void addDuesToPaymentRequest(String dues) {
        paymentRequestBuilder.withDues(dues);
        currentViewLiveData.setValue(ADD_AMOUNT);
        paymentRequestLiveData.setValue(paymentRequestBuilder.build());
    }

    public void completePaymentRequest() {
        paymentRequestLiveData.setValue(null);
    }

    public LiveData<List<PaymentMethod>> getPaymentMethodsLiveData() {
        return paymentMethodsLiveData;
    }

    public LiveData<MainActivity.PaymentView> getSelectedViewLiveData() {
        return currentViewLiveData;
    }

    public LiveData<List<Bank>> getBanksLiveData() {
        return banksLiveData;
    }

    public LiveData<List<PayerCost>> getPayerCostLiveData() {
        return payerCostsLiveData;
    }

    public LiveData<PaymentRequest> getPaymentRequestLiveData() {
        return paymentRequestLiveData;
    }

    public LiveData<Void> getNetworkErrorLiveData() {
        return handleNetworkError;
    }
}
