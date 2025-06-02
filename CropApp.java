import java.util.Scanner;

public class CropApp {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        CropDB cropDB = new CropDB();

        while (true) {

            System.out.println("\n--- Crop Menu ---\n");
            System.out.println("1. Check Quantity");
            System.out.println("2. Buy Crops");
            System.out.println("3. Buy Fertilizers");
            System.out.println("4. Pest Control");
            System.out.println("5. Labor payment");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if(choice == 1){
                System.out.println("\n--- Check Quantity ---\n");
                cropDB.displayCrops();
                
            }

            else if(choice == 2){
                System.out.println("\n--- Buy Crops ---\n");
                System.out.println("1. Buy Rice");
                System.out.println("2. Buy Eggplant");
                System.out.println("3. Buy Corn");
                System.out.println("4. Buy Sugarcane");
                System.out.print("Select an option: ");
                int cropChoice = scanner.nextInt();
                scanner.nextLine();
                if(cropChoice == 1){
                    System.out.println("You have selected Rice");
                    System.out.println("Price: 1000 per sack");
                }
                else if(cropChoice == 2){
                    System.out.println("You have selected Eggplant");
                    System.out.println("Price: 500 per sack");
                }
                else if(cropChoice == 3){
                    System.out.println("You have selected Corn");
                    System.out.println("Price: 800 per sack");
                }
                else if(cropChoice == 4){
                    System.out.println("You have selected Sugarcane");
                    System.out.println("Price: 1200 per sack");
                }
                else{
                    System.out.println("Invalid option selected.");
                }
                
            }

            else if(choice == 3){
                System.out.println("\n--- Buy Fertilizers ---\n");
                System.out.println("1. Buy UREA");
                System.out.println("2. Buy NPK");
                System.out.println("3. Buy MURIATE OF POTASH");
                System.out.print("Select an option: ");
                int fertChoice = scanner.nextInt();
                scanner.nextLine();
                if(fertChoice == 1){
                    System.out.println("You have selected UREA");
                    System.out.println("Price: 500 per bag");
                }
                else if(fertChoice == 2){
                    System.out.println("You have selected NPK");
                    System.out.println("Price: 600 per bag");
                }
                else if(fertChoice == 3){
                    System.out.println("You have selected MURIATE OF POTASH");
                    System.out.println("Price: 550 per bag");
                }
                else{
                    System.out.println("Invalid option selected.");
                }
                
                
            }

            else if(choice == 4){
                System.out.println("\n---  Pest Control ---\n");
                System.out.println("1. Buy BPMC+CHLORPYRIFOS");
                System.out.println("2. Buy CYPERMETHRIN");
                System.out.println("3. Buy LAMBDACYHALOTHRIN");
                System.out.print("Select an option: ");
                int pestChoice = scanner.nextInt();
                scanner.nextLine();
                if(pestChoice == 1){
                    System.out.println("You have selected BPMC+CHLORPYRIFOS");
                    System.out.println("Price: 552.50 per liter");
                }
                else if(pestChoice == 2){
                    System.out.println("You have selected CYPERMETHRIN");
                    System.out.println("Price: 447.50 per liter");
                }
                else if(pestChoice == 3){
                    System.out.println("You have selected LAMBDACYHALOTHRIN");
                    System.out.println("Price: 632.50 per liter");
                }
                else{
                    System.out.println("Invalid option selected.");
                }
                
            }

            else if(choice == 5){
                System.out.println("\n--- Labor payment ---\n");
                System.out.println("1. Pay Labor for Rice");
                System.out.println("2. Pay Labor for Eggplant");
                System.out.println("3. Pay Labor for Corn");
                System.out.println("4. Pay Labor for Sugarcane");
                System.out.print("Select an option: ");
                int laborChoice = scanner.nextInt();
                scanner.nextLine();
                if(laborChoice == 1){
                    System.out.println("You have selected Rice");
                    System.out.println("Labor Payment: 2000 per acre");
                }
                else if(laborChoice == 2){
                    System.out.println("You have selected Eggplant");
                    System.out.println("Labor Payment: 1500 per acre");
                }
                else if(laborChoice == 3){
                    System.out.println("You have selected Corn");
                    System.out.println("Labor Payment: 1800 per acre");
                }
                else if(laborChoice == 4){
                    System.out.println("You have selected Sugarcane");
                    System.out.println("Labor Payment: 2200 per acre");
                }
                else{
                    System.out.println("Invalid option selected.");
                }
                
                
            }

            else if(choice == 6){
                System.out.println("\n--- Exit ---\n");
                scanner.close();
                break;
    
                
            }






        }


    }
}