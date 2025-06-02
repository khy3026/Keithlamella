public class Crop {
    private String cropName;
    private int cropQuantity; 


    public Crop (String cropName, int cropQuantity) {
        this.cropName = cropName;
        this.cropQuantity = cropQuantity;
        
    }

    public String getCropName() {
        return cropName;
    }
    public void setCropName(String cropName) {
        this.cropName = cropName;
    }
    public int getCropQuantity() {
        return cropQuantity;
    }
    public void setCropQuantity(int cropQuantity) {
        this.cropQuantity = cropQuantity;
    }
    @Override
    public String toString() {
        return "Crop{" +
                "cropName='" + cropName + '\'' +
                ", cropQuantity=" + cropQuantity +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Crop)) return false;

        Crop crop = (Crop) o;

        if (cropQuantity != crop.cropQuantity) return false;
        return cropName != null ? cropName.equals(crop.cropName) : crop.cropName == null;
    }
    @Override
    public int hashCode() {
        int result = cropName != null ? cropName.hashCode() : 0;
        result = 31 * result + cropQuantity;
        return result;
    }
    public void displayCrop() {
        System.out.println("Crop Name: " + cropName);
        System.out.println("Crop Quantity: " + cropQuantity);
    }
    public void updateCropQuantity(int quantity) {
        if (quantity < 0) {
            System.out.println("Quantity cannot be negative.");
        } else {
            this.cropQuantity = quantity;
            System.out.println("Updated Crop Quantity: " + cropQuantity);
        }
    }
    public void buyCrop(int quantity) {
        if (quantity <= 0) {
            System.out.println("Invalid quantity. Please enter a positive number.");
        } else {
            this.cropQuantity += quantity;
            System.out.println("Bought " + quantity + " units of " + cropName + ". New quantity: " + cropQuantity);
        }
    }
    public void sellCrop(int quantity) {
        if (quantity <= 0) {
            System.out.println("Invalid quantity. Please enter a positive number.");
        } else if (quantity > cropQuantity) {
            System.out.println("Insufficient stock. Available quantity: " + cropQuantity);
        } else {
            this.cropQuantity -= quantity;
            System.out.println("Sold " + quantity + " units of " + cropName + ". Remaining quantity: " + cropQuantity);
        }
    }
    public void applyFertilizer(String fertilizer) {
        System.out.println("Applying " + fertilizer + " to " + cropName + ".");
    }
    public void applyPesticide(String pesticide) {
        System.out.println("Applying " + pesticide + " to " + cropName + ".");
    }
    public void hireLabor(int hours) {
        System.out.println("Hiring labor for " + hours + " hours for " + cropName + ".");
    }
    public void makePayment(double amount) {
        System.out.println("Processing payment of $" + amount + " for " + cropName + ".");
    }
    public void printReceipt(double amount) {
        System.out.println("Receipt for " + cropName + ":");
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
        Crop wheat = new Crop("Wheat", 100);
        wheat.displayCrop();
        wheat.buyCrop(50);
        wheat.sellCrop(30);
        wheat.applyFertilizer("Nitrogen");
        wheat.applyPesticide("Insecticide");
        wheat.hireLabor(5);
        wheat.makePayment(200.0);
        wheat.printReceipt(200.0);
        wheat.saveToDatabase("Credit Card", "1234-5678-9012-3456", 200.0);
    }
    }