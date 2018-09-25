package com.studie.mercadolibre;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.studie.mercadolibre.models.PaymentMethod;
import com.studie.mercadolibre.models.retrofit.PaymentMethodDTO;
import com.studie.mercadolibre.repositories.network.NetworkRequestListener;
import com.studie.mercadolibre.repositories.network.PaymentMethodsApiClient;
import com.studie.mercadolibre.repositories.network.RetrofitApiClient;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import retrofit2.mock.Calls;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class PaymentMethodApiClientTest {

    PaymentMethodsApiClient paymentMethodsApiClient;
    @Mock
    RetrofitApiClient retrofitApiClient;
    @Mock
    Context context;

    private static final String TEST_RESOURCES_PATH = "../app/src/test/java/com/studie/mercadolibre/data/";

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        paymentMethodsApiClient = new PaymentMethodsApiClient(retrofitApiClient, context);
    }

    @Test
    public void getPaymentMethodsTest() throws IOException {
        mockDataSource();
        NetworkRequestListener<List<PaymentMethod>> networkRequestListener = new NetworkRequestListener<List<PaymentMethod>>() {
            @Override
            public void onSuccessNetworkRequest(List<PaymentMethod> response) {
                assertNotNull(response);
                assertTrue(response.size() == 20);
            }

            @Override
            public void onFailureRequest(Throwable t) {

            }
        };
        paymentMethodsApiClient.getPaymentMethods(networkRequestListener);

    }

    private void mockDataSource() throws IOException {
        Gson gson = new GsonBuilder().create();
        List<PaymentMethodDTO> paymentMethods = gson.fromJson(loadSampleDataFromLocalJSON("payment_methods.json"), new TypeToken<List<PaymentMethodDTO>>() {
        }.getType());
        when(retrofitApiClient.getPaymentMethods(anyString())).thenReturn(Calls.response(paymentMethods));
    }

    private String loadSampleDataFromLocalJSON(String filename) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(TEST_RESOURCES_PATH + filename)));
        StringBuilder stringBuilder = new StringBuilder();
        String line = bufferedReader.readLine();
        while (line != null) {
            stringBuilder.append(line);
            line = bufferedReader.readLine();
        }
        return stringBuilder.toString();
    }

}
