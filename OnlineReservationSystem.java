import java.util.HashMap;
import java.util.Scanner;

// Class for user authentication
class UserLogin {
    private HashMap<String, String> users = new HashMap<>();
    
    public UserLogin() {
        // Default user for login purposes
        users.put("jayasree", "jaya098");
        users.put("user", "password");
    }
    
    public boolean login(String userId, String password) {
        return users.containsKey(userId) && users.get(userId).equals(password);
    }
}

// Class for reservation system
class ReservationSystem {
    private HashMap<String, String> reservations = new HashMap<>();
    private int pnrCounter = 1000;
    
    public void reserveTicket(String trainNumber, String trainName, String classType, String from, String to, String journeyDate) {
        String pnr = "PNR" + (++pnrCounter);
        String ticketDetails = "Train Number: " + trainNumber + ", Train Name: " + trainName + 
                ", Class Type: " + classType + ", From: " + from + ", To: " + to + ", Date of Journey: " + journeyDate;
        reservations.put(pnr, ticketDetails);
        System.out.println("Reservation Successful! Your PNR: " + pnr);
        System.out.println("Ticket Details: " + ticketDetails);
    }
    
    public void cancelTicket(String pnr) {
        if (reservations.containsKey(pnr)) {
            System.out.println("Ticket Found: " + reservations.get(pnr));
            System.out.println("Do you really want to cancel this ticket? (yes/no): ");
            @SuppressWarnings("resource")
            Scanner sc = new Scanner(System.in);
            String confirm = sc.nextLine();
            if (confirm.equalsIgnoreCase("yes")) {
                reservations.remove(pnr);
                System.out.println("Ticket cancelled successfully!");
            } else {
                System.out.println("Cancellation aborted.");
            }
        } else {
            System.out.println("No ticket found with the given PNR.");
        }
    }
}

// Main class for the online reservation system
public class OnlineReservationSystem {
    public static void main(String[] args) {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        UserLogin userLogin = new UserLogin();
        ReservationSystem reservationSystem = new ReservationSystem();
        
        // Login Form
        System.out.println("Welcome to the Online Reservation System");
        System.out.println("Please login to continue");
        System.out.print("Enter user ID: ");
        String userId = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        
        if (userLogin.login(userId, password)) {
            System.out.println("Login successful!");
            
            // Menu
            while (true) {
                System.out.println("\n1. Reserve Ticket");
                System.out.println("\n2. Cancel Ticket");
                System.out.println("\n3. Exit");
                System.out.print("Choose an option: ");
                int choice = sc.nextInt();
                sc.nextLine();  // consume newline
                
                if (choice == 1) {
                    // Reservation Form
                    System.out.print("Enter Train Number: ");
                    String trainNumber = sc.nextLine();
                    System.out.print("Enter Train Name: ");
                    String trainName = sc.nextLine();
                    System.out.print("Enter Class Type: ");
                    String classType = sc.nextLine();
                    System.out.print("Enter From (Place): ");
                    String from = sc.nextLine();
                    System.out.print("Enter To (Destination): ");
                    String to = sc.nextLine();
                    System.out.print("Enter Date of Journey (dd-mm-yyyy): ");
                    String journeyDate = sc.nextLine();
                    
                    reservationSystem.reserveTicket(trainNumber, trainName, classType, from, to, journeyDate);
                } else if (choice == 2) {
                    // Cancellation Form
                    System.out.print("Enter PNR Number: ");
                    String pnr = sc.nextLine();
                    reservationSystem.cancelTicket(pnr);
                } else if (choice == 3) {
                    System.out.println("Thank you for using the Online Reservation System!");
                    break;
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            }
        } else {
            System.out.println("Invalid login credentials!");
        }
    }
}