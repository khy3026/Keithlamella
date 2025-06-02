abstract class AbstractPayment implements Payment {
    private String transactionId;  // Encapsulated field

    public AbstractPayment(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void printReceipt(double amount) {
        System.out.println("Transaction " + transactionId + ": $" + amount + " processed.");
    }

    public abstract void processPayment(double amount);  // Polymorphic method
}

