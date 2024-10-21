import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.io.*;

public class Loan {
    private String loanNumber;
    private LocalDate loanDate;
    private LocalDate returnDate;
    private String equipmentNumber;
    private String memberNumber;
    private String gearOfficer;
    private double cost;

    private static List<Loan> loans = new ArrayList<>();

    // Constructor
    public Loan(String loanNumber, LocalDate loanDate, LocalDate returnDate, 
                String equipmentNumber, String memberNumber, 
                String gearOfficer, double cost) {
        this.loanNumber = loanNumber;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
        this.equipmentNumber = equipmentNumber;
        this.memberNumber = memberNumber;
        this.gearOfficer = gearOfficer;
        this.cost = cost;
    }

    // Method to calculate loan cost based on the duration
    public static double calculateCost(LocalDate loanDate, LocalDate returnDate, double dailyRate) {
        long days = java.time.temporal.ChronoUnit.DAYS.between(loanDate, returnDate);
        return days * dailyRate;
    }

    // Load loans from file
    private static void loadLoans() {
        try (BufferedReader reader = new BufferedReader(new FileReader("loan.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                Loan loan = new Loan(details[0], LocalDate.parse(details[1]), LocalDate.parse(details[2]),
                        details[3], details[4], details[5], Double.parseDouble(details[6].substring(1)));
                loans.add(loan);
            }
        } catch (IOException e) {
            System.out.println("Error loading loans: " + e.getMessage());
        }
    }

    // Save loans to file
    private static void saveLoans() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("loan.txt", true))) {
            for (Loan loan : loans) {
                writer.write(loan.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving loans: " + e.getMessage());
        }
    }

    // Display loan details in a string format
    @Override
    public String toString() {
        return loanNumber + "," + loanDate + "," + returnDate + "," + equipmentNumber + "," + 
               memberNumber + "," + gearOfficer + ",$" + cost;
    }

    // Loan equipment logic with confirmation and modification option
    public static void loanEquipment() {
        Scanner scanner = new Scanner(System.in);
        String loanNumber, equipmentNumber, memberNumber, gearOfficer;
        LocalDate loanDate = null, returnDate;

        // Input loop to ensure valid loan date format
        while (true) {
            try {
                System.out.print("Enter loan number: ");
                loanNumber = scanner.nextLine();
                System.out.print("Enter loan date (yyyy-MM-dd): ");
                loanDate = LocalDate.parse(scanner.nextLine());
                break; // Exit loop if input is valid
            } catch (Exception e) {
                System.out.println("Invalid date format. Please try again.");
            }
        }

        // Automatically set return date one week after the loan date
        returnDate = loanDate.plusWeeks(1);
        System.out.println("Expected return date: " + returnDate);

        // Collect other details
        System.out.print("Enter equipment number: ");
        equipmentNumber = scanner.nextLine();
        System.out.print("Enter member number: ");
        memberNumber = scanner.nextLine();
        System.out.print("Enter gear officer: ");
        gearOfficer = scanner.nextLine();

        double dailyRate = 10.0; // Assume a fixed daily rate
        double cost = calculateCost(loanDate, returnDate, dailyRate);

        // Display details for confirmation
        System.out.println("\nPlease review the loan details:");
        System.out.println("Loan Number: " + loanNumber);
        System.out.println("Loan Date: " + loanDate);
        System.out.println("Return Date: " + returnDate);
        System.out.println("Equipment Number: " + equipmentNumber);
        System.out.println("Member Number: " + memberNumber);
        System.out.println("Gear Officer: " + gearOfficer);
        System.out.println("Cost: $" + cost);

        // Confirmation loop
        while (true) {
            System.out.print("\nAre you sure you want to confirm your input? (yes/no): ");
            String confirm = scanner.nextLine().trim().toLowerCase();

            if (confirm.equals("yes")) {
                Loan newLoan = new Loan(loanNumber, loanDate, returnDate, equipmentNumber, memberNumber, gearOfficer, cost);
                loans.add(newLoan);
                saveLoans(); // Save the updated loan list
                System.out.println("Loan successfully added.");
                break; // Exit loop on confirmation
            } else if (confirm.equals("no")) {
                // Allow user to modify specific details
                System.out.println("\nWhich detail would you like to modify?");
                System.out.println("1. Loan Number");
                System.out.println("2. Loan Date");
                System.out.println("3. Equipment Number");
                System.out.println("4. Member Number");
                System.out.println("5. Gear Officer");
                System.out.print("Choice: ");

                int modifyChoice;
                try {
                    modifyChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    switch (modifyChoice) {
                        case 1:
                            System.out.print("Enter new loan number: ");
                            loanNumber = scanner.nextLine();
                            break;
                        case 2:
                            System.out.print("Enter new loan date (yyyy-MM-dd): ");
                            loanDate = LocalDate.parse(scanner.nextLine());
                            returnDate = loanDate.plusWeeks(1); // Update return date accordingly
                            System.out.println("Updated return date: " + returnDate);
                            break;
                        case 3:
                            System.out.print("Enter new equipment number: ");
                            equipmentNumber = scanner.nextLine();
                            break;
                        case 4:
                            System.out.print("Enter new member number: ");
                            memberNumber = scanner.nextLine();
                            break;
                        case 5:
                            System.out.print("Enter new gear officer: ");
                            gearOfficer = scanner.nextLine();
                            break;
                        default:
                            System.out.println("Invalid choice. No changes made.");
                    }

                    // Display updated details for confirmation
                    System.out.println("\nUpdated loan details:");
                    System.out.println("Loan Number: " + loanNumber);
                    System.out.println("Loan Date: " + loanDate);
                    System.out.println("Return Date: " + returnDate);
                    System.out.println("Equipment Number: " + equipmentNumber);
                    System.out.println("Member Number: " + memberNumber);
                    System.out.println("Gear Officer: " + gearOfficer);
                    System.out.println("Cost: $" + cost);
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please try again.");
                    scanner.nextLine(); // Clear invalid input
                }
            } else {
                System.out.println("Please enter 'yes' or 'no'.");
            }
        }
    }

    // Method to return equipment
    public static void returnEquipment() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter loan number to return: ");
        String loanNumber = scanner.nextLine();

        Loan loanToReturn = null;
        for (Loan loan : loans) {
            if (loan.loanNumber.equals(loanNumber)) {
                loanToReturn = loan;
                break;
            }
        }

        if (loanToReturn != null) {
            // Show loan details and calculate late fees
            System.out.println("Loan Details:");
            System.out.println("Loan Number: " + loanToReturn.loanNumber);
            System.out.println("Loan Date: " + loanToReturn.loanDate);
            System.out.println("Return Date: " + loanToReturn.returnDate);
            System.out.println("Equipment Number: " + loanToReturn.equipmentNumber);
            System.out.println("Member Number: " + loanToReturn.memberNumber);
            System.out.println("Gear Officer: " + loanToReturn.gearOfficer);
            System.out.println("Original Cost: $" + loanToReturn.cost);
            LocalDate currentDate = LocalDate.now();
            System.out.println("Current Date: " + currentDate);

            if (currentDate.isAfter(loanToReturn.returnDate)) {
                long daysOverdue = java.time.temporal.ChronoUnit.DAYS.between(loanToReturn.returnDate, currentDate);
                double lateFeePerDay = 5.0; // Set a fixed late fee rate
                double lateFee = daysOverdue * lateFeePerDay;
                System.out.println("This equipment is being returned late. Late fee per day: $" + lateFeePerDay);
                System.out.println("Days overdue: " + daysOverdue);
                System.out.println("Late Fee: $" + lateFee);
                System.out.println("Total Cost: $" + (loanToReturn.cost + lateFee));
            } else {
                System.out.println("Equipment returned on time. No late fee applied.");
            }

            // Confirmation to return
            System.out.print("Would you like to return the equipment now? (yes/no): ");
            String confirmReturn = scanner.nextLine().trim().toLowerCase();
            if (confirmReturn.equals("yes")) {
                loans.remove(loanToReturn); // Remove loan from list
                System.out.println("Equipment returned successfully.");
            } else {
                System.out.println("Return cancelled.");
            }
        } else {
            System.out.println("Loan not found or already returned.");
        }
    }

    // Method to view existing loans
    public static void viewLoans() {
        if (loans.isEmpty()) {
            System.out.println("No loans found.");
            return;
        }

        System.out.println("Existing Loans:");
        for (Loan loan : loans) {
            System.out.println(loan.toString());
        }
    }

    public static void main(String[] args) {
        loadLoans(); // Load existing loans from the file
        Scanner scanner = new Scanner(System.in);
        int choice;

        // Main menu loop
        do {
            System.out.println("\n===== Welcome to the Loan System =====");
            System.out.println("1. Loan Equipment");
            System.out.println("2. View Loans");
            System.out.println("3. Return Equipment");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    loanEquipment();
                    break;
                case 2:
                    viewLoans();
                    break;
                case 3:
                    returnEquipment();
                    break;
                case 4:
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);
    }
}
