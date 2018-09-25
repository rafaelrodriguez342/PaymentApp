package com.studie.mercadolibre.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.squareup.picasso.Picasso;
import com.studie.mercadolibre.R;
import com.studie.mercadolibre.repositories.interfaces.PaymentOption;

import java.util.List;

public class PaymentOptionsAdapter extends RecyclerView.Adapter<PaymentOptionsAdapter.PaymentViewHolder> {

    private List<? extends PaymentOption> paymentMethods;
    private OptionsListener optionsListener;

    public PaymentOptionsAdapter(List<? extends PaymentOption> paymentMethods, OptionsListener optionsListener) {
        this.paymentMethods = paymentMethods;
        this.optionsListener = optionsListener;
    }

    @NonNull
    @Override
    public PaymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PaymentViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.payment_method_option, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentViewHolder holder, int position) {
        holder.bind(paymentMethods.get(position), optionsListener);
    }

    @Override
    public int getItemCount() {
        return paymentMethods.size();
    }

    static class PaymentViewHolder extends RecyclerView.ViewHolder {
        private RadioButton radioButton;
        private ImageView imageView;

        PaymentViewHolder(View itemView) {
            super(itemView);
            radioButton = itemView.findViewById(R.id.payment_method_radio_button);
            imageView = itemView.findViewById(R.id.payment_method_image);
        }

        void bind(PaymentOption paymentMethod, OptionsListener optionsListener) {
            radioButton.setText(paymentMethod.getName());
            if (paymentMethod.getImageUrl() != null) {
                Picasso.get().load(paymentMethod.getImageUrl()).noPlaceholder().fit().centerInside().into(imageView);
            } else {
                imageView.setVisibility(View.GONE);
            }
            radioButton.setOnCheckedChangeListener((compoundButton, b) -> optionsListener.onSelected(paymentMethod));
        }
    }

    public interface OptionsListener {
        void onSelected(PaymentOption paymentMethod);
    }
}
