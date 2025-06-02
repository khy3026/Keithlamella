class CreditCardPayment extends AbstractPayment {
    private String cardNumber;  // Encapsulated

    public CreditCardPayment(String transactionId, String cardNumber) {
        super(transactionId);
        this.cardNumber = cardNumber;
    }

    @Override
    public void processPayment(double amount) {
        System.out.println("Processing credit card payment with card: ****" + cardNumber.substring(cardNumber.length() - 4));
        printReceipt(amount);
    }
}
