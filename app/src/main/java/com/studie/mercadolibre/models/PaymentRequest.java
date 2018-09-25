package com.studie.mercadolibre.models;

public class PaymentRequest {

    private final String amount;
    private final PaymentMethod paymentMethod;
    private final Bank bank;
    private final String dues;

    public String getAmount() {
        return amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public Bank getBank() {
        return bank;
    }

    PaymentRequest(Builder builder) {
        this.amount = builder.amount;
        this.paymentMethod = builder.paymentMethod;
        this.bank = builder.bank;
        this.dues = builder.dues;
    }

    public String getDues() {
        return dues;
    }

    public static class Builder {

        private String amount;
        private PaymentMethod paymentMethod;
        private Bank bank;
        private String dues;

        public Builder withAmount(String amount) {
            this.amount = amount;
            return this;
        }

        public Builder withPaymentMethod(PaymentMethod paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public Builder withBank(Bank bank) {
            this.bank = bank;
            return this;
        }

        public Builder withDues(String dues) {
            this.dues = dues;
            return this;
        }



        public String getAmount() {
            return amount;
        }

        public PaymentMethod getPaymentMethod() {
            return paymentMethod;
        }

        public Bank getBank() {
            return bank;
        }

        public String getDues() {
            return dues;
        }

        public PaymentRequest build() {
            return new PaymentRequest(this);
        }
    }
}
