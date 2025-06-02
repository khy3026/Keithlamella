import java.sql.*;
import java.util.Scanner;

interface Payment {
    void processPayment(double amount);
}

abstract class AbstractPayment implements Payment {
    private String transactionId;

    public AbstractPayment(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void printReceipt(double amount) {
        System.out.println("Transaction " + transactionId + ": $" + amount + " processed.");
    }

    public abstract void processPayment(double amount);
}

class CreditCardPayment extends AbstractPayment {
    private String cardNumber;

    public CreditCardPayment(String transactionId, String cardNumber) {
        super(transactionId);
        this.cardNumber = cardNumber;
    }

    @Override
    public void processPayment(double amount) {
        System.out.println("Processing credit card payment with card: ****" + cardNumber.substring(cardNumber.length() - 4));
        printReceipt(amount);
        saveToDatabase("CreditCard", "****" + cardNumber.substring(cardNumber.length() - 4), amount);
    }

    private void saveToDatabase(String method, String detail, double amount) {
        try (Connection conn = DBUtil.getConnection()) {
            String sql = "INSERT INTO transactions (transaction_id, method, detail, amount) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, getTransactionId());
            stmt.setString(2, method);
            stmt.setString(3, detail);
            stmt.setDouble(4, amount);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

class PayPalPayment extends AbstractPayment {
    private String email;

    public PayPalPayment(String transactionId, String email) {
        super(transactionId);
        this.email = email;
    }

    @Override
    public void processPayment(double amount) {
        System.out.println("Processing PayPal payment from account: " + email);
        printReceipt(amount);
        saveToDatabase("PayPal", email, amount);
    }

    private void saveToDatabase(String method, String detail, double amount) {
        try (Connection conn = DBUtil.getConnection()) {
            String sql = "INSERT INTO transactions (transaction_id, method, detail, amount) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, getTransactionId());
            stmt.setString(2, method);
            stmt.setString(3, detail);
            stmt.setDouble(4, amount);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Transaction ID: ");
        String txnId = scanner.nextLine();

        System.out.print("Enter Amount to Pay: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Select Payment Method:");
        System.out.println("1. Credit Card");
        System.out.println("2. PayPal");
        System.out.print("Your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        Payment payment;

        if (choice == 1) {
            System.out.print("Enter Credit Card Number: ");
            String card = scanner.nextLine();
            payment = new CreditCardPayment(txnId, card);
        } else if (choice == 2) {
            System.out.print("Enter PayPal Email: ");
            String email = scanner.nextLine();
            payment = new PayPalPayment(txnId, email);
        } else {
            System.out.println("Invalid option.");
            return;
        }

        payment.processPayment(amount);
        scanner.close();
    }
}
