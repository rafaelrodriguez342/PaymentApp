package com.studie.mercadolibre;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.Observer;

import com.studie.mercadolibre.models.PaymentMethod;
import com.studie.mercadolibre.repositories.interfaces.BanksRepository;
import com.studie.mercadolibre.repositories.interfaces.DuesRecommendationRepository;
import com.studie.mercadolibre.repositories.interfaces.PaymentMethodRepository;
import com.studie.mercadolibre.repositories.network.NetworkRequestListener;
import com.studie.mercadolibre.ui.activities.MainActivity;
import com.studie.mercadolibre.viewmodels.MainViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static com.studie.mercadolibre.ui.activities.MainActivity.PaymentView.ADD_BANK;
import static com.studie.mercadolibre.ui.activities.MainActivity.PaymentView.ADD_PAYMENT_METHOD;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

public class MainViewModelTest {
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    private MainViewModel mainViewModel;

    @Mock
    Observer<List<PaymentMethod>> paymentMethodObserver;

    @Mock
    Observer<MainActivity.PaymentView> selectedViewObserver;

    @Mock
    PaymentMethodRepository paymentMethodRepository;

    @Mock
    BanksRepository banksRepository;

    @Mock
    DuesRecommendationRepository duesRecommendationRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mainViewModel = new MainViewModel(paymentMethodRepository, banksRepository, duesRecommendationRepository);
    }

    @Test
    public void addAmountToPaymentRequestTest() {
        mainViewModel.getSelectedViewLiveData().observeForever(selectedViewObserver);
        mainViewModel.addAmountToPaymentRequest("10");
        verify(selectedViewObserver).onChanged(ADD_PAYMENT_METHOD);
    }

    @Test
    public void requestPaymentMethodsTest() {
        doAnswer(invocation -> {
            Object arg0 = invocation.getArgument(0);
            ((NetworkRequestListener<List<PaymentMethod>>) arg0).onSuccessNetworkRequest(Collections.emptyList());
            return null;
        }).when(paymentMethodRepository).getPaymentMethods(any(NetworkRequestListener.class));

        mainViewModel.getPaymentMethodsLiveData().observeForever(paymentMethodObserver);
        mainViewModel.requestPaymentMethods();
        verify(paymentMethodRepository).getPaymentMethods(any());
        verify(paymentMethodObserver).onChanged(Collections.emptyList());
    }

    @Test
    public void addPaymentMethodToPaymentRequestTest() {
        mainViewModel.getSelectedViewLiveData().observeForever(selectedViewObserver);
        mainViewModel.addPaymentMethodToPaymentRequest(new PaymentMethod("dummyId", "name", "dummyUrl"));
        verify(selectedViewObserver).onChanged(ADD_BANK);
    }

    // TODO: add more unit test for the other MainViewModel methods

}
