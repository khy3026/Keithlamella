class PayPalPayment extends AbstractPayment {
    private String email;  // Encapsulated

    public PayPalPayment(String transactionId, String email) {
        super(transactionId);
        this.email = email;
    }

    @Override
    public void processPayment(double amount) {
        System.out.println("Processing PayPal payment from account: " + email);
        printReceipt(amount);
    }
}
