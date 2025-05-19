// A) This is a Solo or Pair activity
// B) Write your name(s) in 1/8 crosswise
// C) You can open previous sample program
// D) Use of internet is strictly prohibited (Violaton = ZERO)
// E) Hands-on quiz duration = 90 minutes (Time is UP - Everyone should stay outside the lab)

// 1) Play the video (OutputFlow.mp4)
// 2) Analyze the program that implements abstract class (HotelRoom - StandardRoom, DeluxeRoom, SuiteRoom)
// 3) In reference to the video, construct your source program here...
// 4) Use the sample inputs sequentially from the video when you test your program
// 5) Every correct output is equivalent to 4 pts

// ONE TIME CHECKING ONLY
// YOU CAN ASK FOR CHECKING EVEN IF TIME DURATION IS NOT OVER
// "UNNECESSARY NOISE" WOULD MEAN -5 pts PER OCCURENCE
// INTERACTION WITH OTHER PAIR = ZERO

import java.util.Scanner;
public class HotelManagementApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Available Rooms: ");
        double  totalPrice = 0;
        displayRooms();
        
        while(true){
            
        System.out.println("\n");
        System.out.println("--- MENU ---");
        System.out.println("1. Book a room.");
        System.out.println("2. Cancel a room.");
        System.out.println("3. Payment.");
        System.out.println("4. Exit.");
        System.out.print("\nEnter your option: ");
        int op = scanner.nextInt();
            scanner.nextLine();
        switch (op) {
            case 1:
                int roomIndex = -1;

        while(true){
            while(true){
                System.out.print("\nEnter Room Number to book: ");
                String roomToBook = scanner.nextLine();
                roomIndex = -1;
                for(int i = 0; 1 < HotelRoom.rooms.length; i++){
                    if(HotelRoom.rooms[i].roomNumber.equals(roomToBook)){
                        roomIndex = i;
                        break;
                    }
                }
                if(roomIndex == -1){
                    System.out.println("Invalid room number. Please try agian.");
                    continue;
                }
                if(HotelRoom.rooms[roomIndex].isAvailable){
                    break;
                }else{
                    System.out.println("Sorry, Room" + roomToBook + "is not available. Please choose another room.");
                }
            }
            System.out.print("\nEnter number of nights: ");
            int nights = scanner.nextInt();
            boolean isMember = false;
            while(true){
                System.out.print("Are you a member? (T/F): ");
                String memberInput = scanner.next().toUpperCase();
                if(memberInput.equals("T")){
                    isMember = true;
                    break;
                }else if(memberInput.equals("F")) {
                    isMember = false;
                    break;
                }else{
                    System.out.println("Invalid input. Plaese enter either 'T' (for true) or 'F' (for false).");
                }
            }
            double price = HotelRoom.rooms[roomIndex].calculatePrice(nights, isMember);
            System.out.println("Total Price for " +  nights  + " night(s):" + price);
            totalPrice += price;
            HotelRoom.rooms[roomIndex].bookRoom(roomIndex);
            System.out.println("\nUpdated Room Availability: ");
            displayRooms();
            System.out.print("\nDo you want to book an additional room? (Y/N): ");
            scanner.nextLine();
            String response = scanner.nextLine().toUpperCase();
            if(!response.equals("Y")){
                break;
            }
        }
        System.out.println("\nTotal amount for all bookings: " + totalPrice);
        System.out.print("\nDo you want to return to the menu? (Y/N): ");
        String dis = scanner.nextLine().toUpperCase();
        if(dis.equals("Y")){
            break;
        }else{
            System.out.println("Thank you for choosing us.");
        }
        
    
                break;
        
            case 2:
            System.out.println();
                break;
            case 3:
            if (totalPrice == 0){
                System.out.println("\nNothing to pay.");
                break;
            }
            else{
            while (totalPrice != 0){
            System.out.print("\nEnter your payment: ");
            double pay = scanner.nextDouble();
            if (pay == totalPrice){
                pay-=totalPrice;
                System.out.println("\nPayment successful!");
                break;
            }else{
                System.out.println("\nYou must pay exact amount.");
            }}}
            break;
            case 4:
            System.out.println("Thank you for staying at banago hotel.");
            return;
        }
        }
    }

    private static void displayRooms(){
        for (int i = 0; i < HotelRoom.rooms.length; i++){
            if (HotelRoom.rooms[i].isAvailable){
                System.out.println(HotelRoom.rooms[i]);
            }else{
                System.out.println(HotelRoom.rooms[i]+"| Status: Not Available");
            }
        }
    }
    
}
