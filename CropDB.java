import java.sql.*;
import java.util.Scanner;

public class CropDB {

    private String url = "jdbc:mysql://localhost:3306/javadatabase";
    private String dbUser = "root";
    private String dbPassword = "";  


    public void checkCrops(String cropName){
        String checkC = "SELECT * FROM crop WHERE cropName = ?";

        try(Connection conn = DriverManager.getConnection(url,dbUser,dbPassword);
            PreparedStatement pst = conn.prepareStatement(checkC)){

                pst.setString(1, cropName);
      
                try(ResultSet rs = pst.executeQuery()){
                    rs.next();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }   
    }


    public void displayCrops(){
        String viewQuery = "SELECT cropName, cropQuantity FROM crop";
        try(Connection conn = DriverManager.getConnection(url,dbUser,dbPassword);
            Statement statement = conn.createStatement()){

            ResultSet rs = statement.executeQuery(viewQuery);
            
            while (rs.next()) {
               
                String cropName = rs.getString("cropName");
                int cropQuantity = rs.getInt("cropQuantity");
                

        
                System.out.println("CropName: " + cropName + " | CropQuantity: " + cropQuantity ); 
                
               
            }    
                  
          }catch (SQLException e){
            e.printStackTrace();
        }
}

    public void updateCropQuantity(String cropName, int newQuantity) {
        String updateQuery = "UPDATE crop SET cropQuantity = ? WHERE cropName = ?";
        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
             PreparedStatement pst = conn.prepareStatement(updateQuery)) {

            pst.setInt(1, newQuantity);
            pst.setString(2, cropName);
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Crop quantity updated successfully.");
            } else {
                System.out.println("Crop not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addCrop(String cropName, int cropQuantity) {
        String insertQuery = "INSERT INTO crop (cropName, cropQuantity) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
             PreparedStatement pst = conn.prepareStatement(insertQuery)) {

            pst.setString(1, cropName);
            pst.setInt(2, cropQuantity);
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Crop added successfully.");
            } else {
                System.out.println("Failed to add crop.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteCrop(String cropName) {
        String deleteQuery = "DELETE FROM crop WHERE cropName = ?";
        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
             PreparedStatement pst = conn.prepareStatement(deleteQuery)) {

            pst.setString(1, cropName);
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Crop deleted successfully.");
            } else {
                System.out.println("Crop not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void buyCrop(String cropName, int quantity) {
        String updateQuery = "UPDATE crop SET cropQuantity = cropQuantity - ? WHERE cropName = ?";
        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
             PreparedStatement pst = conn.prepareStatement(updateQuery)) {

            pst.setInt(1, quantity);
            pst.setString(2, cropName);
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Crop purchased successfully.");
            } else {
                System.out.println("Crop not found or insufficient quantity.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void buyFertilizer(String fertilizerName, int quantity) {
        String updateQuery = "UPDATE fertilizer SET fertilizerQuantity = fertilizerQuantity - ? WHERE fertilizerName = ?";
        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
             PreparedStatement pst = conn.prepareStatement(updateQuery)) {

            pst.setInt(1, quantity);
            pst.setString(2, fertilizerName);
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Fertilizer purchased successfully.");
            } else {
                System.out.println("Fertilizer not found or insufficient quantity.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void pestControl(String pestControlName, int quantity) {
        String updateQuery = "UPDATE pest_control SET pestControlQuantity = pestControlQuantity - ? WHERE pestControlName = ?";
        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
             PreparedStatement pst = conn.prepareStatement(updateQuery)) {

            pst.setInt(1, quantity);
            pst.setString(2, pestControlName);
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Pest control purchased successfully.");
            } else {
                System.out.println("Pest control not found or insufficient quantity.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void payLabor(String laborName, double amount) {
        String updateQuery = "UPDATE labor SET laborPayment = laborPayment + ? WHERE laborName = ?";
        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
             PreparedStatement pst = conn.prepareStatement(updateQuery)) {

            pst.setDouble(1, amount);
            pst.setString(2, laborName);
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Labor payment processed successfully.");
            } else {
                System.out.println("Labor not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void makePayment(String paymentMethod, double amount) {
        String insertQuery = "INSERT INTO payments (paymentMethod, amount) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
             PreparedStatement pst = conn.prepareStatement(insertQuery)) {

            pst.setString(1, paymentMethod);
            pst.setDouble(2, amount);
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Payment processed successfully.");
            } else {
                System.out.println("Failed to process payment.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void viewPayments() {
        String viewQuery = "SELECT paymentMethod, amount FROM payments";
        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(viewQuery)) {

            while (rs.next()) {
                String paymentMethod = rs.getString("paymentMethod");
                double amount = rs.getDouble("amount");
                System.out.println("Payment Method: " + paymentMethod + " | Amount: $" + amount);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void printReceipt(String paymentMethod, double amount) {
        System.out.println("Receipt:");
        System.out.println("Payment Method: " + paymentMethod);
        System.out.println("Amount Paid: $" + amount);
        System.out.println("Thank you for your purchase!");
    }
    public void saveToDatabase(String method, String detail, double amount) {
        // Simulate saving to a database
        System.out.println("Saving transaction to database:");
        System.out.println("Method: " + method);
        System.out.println("Detail: " + detail);
        System.out.println("Amount: $" + amount);
    }
    public static void main(String[] args) {
        CropDB cropDB = new CropDB();
        cropDB.displayCrops();
        cropDB.addCrop("Wheat", 100);
        cropDB.updateCropQuantity("Wheat", 150);
        cropDB.buyCrop("Wheat", 20);
        cropDB.deleteCrop("Wheat");
        cropDB.viewPayments();
        cropDB.makePayment("Credit Card", 50.0);
        cropDB.printReceipt("Credit Card", 50.0);
    }
    public void saveTransaction(String method, String detail, double amount) {
        // Simulate saving to a database
        System.out.println("Saving transaction to database:");
        System.out.println("Method: " + method);
        System.out.println("Detail: " + detail);
        System.out.println("Amount: $" + amount);
        // Add import for Scanner at the top: import java.util.Scanner;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose payment method:");
        System.out.println("1. PayPal");
        System.out.println("2. Credit Card");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        // Generate a transaction ID (for example, using current time)
        String txnId = "TXN" + System.currentTimeMillis();

        if (choice == 1) {
            System.out.print("Enter your email: ");
            String email = scanner.next();
            PayPalPayment payment = new PayPalPayment(txnId, email);
            payment.processPayment(amount);
        } else if (choice == 2) {
            System.out.print("Enter your card number: ");
            String cardNumber = scanner.next();
            CreditCardPayment payment = new CreditCardPayment(txnId, cardNumber);
            payment.processPayment(amount);
        } else {
            System.out.println("Invalid choice.");
        }
        
        scanner.close();
    }
}