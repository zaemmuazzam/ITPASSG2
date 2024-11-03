import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.*;

public class Loan {
    private String loanNumber;
    private LocalDate loanDate;
    private LocalDate returnDate;
    private String equipmentNumber;
    private String memberNumber;
    private String gearofficer;
    private double cost;

    private static Loan[] loans = new Loan[300]; // Fixed-size array of 300 elements
    private static int loanCount = 0; // Track the number of loans added

    // Constructor
    public Loan(String loanNumber, LocalDate loanDate, LocalDate returnDate,
                String equipmentNumber, String memberNumber,String gearofficer, double cost) {
    	
        this.loanNumber = loanNumber;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
        this.equipmentNumber = equipmentNumber;
        this.memberNumber = memberNumber;
        this.gearofficer = gearofficer;
        this.cost = cost;
        
    }

    // Method to calculate loan cost based on the duration
    public static int calculateCost(LocalDate loanDate, LocalDate returnDate,int hirecostperweekend, int hirecostperweek) {
        long days = java.time.temporal.ChronoUnit.DAYS.between(loanDate, returnDate);
        //System.out.println(days);
        if(days<7) {
        	return hirecostperweekend;
        }
        else {
        	int numberofweeks= Math.toIntExact(days)/7;
        	int cost=numberofweeks*hirecostperweek;
        	return cost;
        }
    }

    
    //setters
    public static void setLoanCount(int number) {loanCount=number;} 
    // Load loans from file
    public static void loadLoans() {
//        try (BufferedReader reader = new BufferedReader(new FileReader("loan.txt"))) {
//            String line;
//            int count=0;
//            while ((line = reader.readLine()) != null && loanCount < loans.length) {
//            	System.out.println(line);
//            	count++;
//                String[] details = line.split(",");
//                Loan loan = new Loan(details[0], LocalDate.parse(details[1]), LocalDate.parse(details[2]),
//                        details[3], details[4], Double.parseDouble(details[5].substring(1)));
//                loans[loanCount++] = loan;
//                
//            }
//            System.out.println("Loaded "+ count +" loans");
//        } 
    	setLoanCount(0);
    	try {
    		File myFile = new File ("loan.txt");
    		Scanner input = new Scanner(myFile);
    		int count=0;
		
    		
			while(input.hasNextLine()) {
				String line = input.nextLine();
				//System.out.println(line);
				Scanner word = new Scanner(line);
				word.useDelimiter(";");
				
				String loannumber = word.next();
//				System.out.println(loannumber);
				LocalDate loandate = LocalDate.parse(word.next());
//				System.out.println(loandate);
				LocalDate returndate = LocalDate.parse(word.next());
//				System.out.println(returndate);
				String equipmentnumber = word.next();
//				System.out.println(equipmentnumber);
				String membernumber = word.next();
//				System.out.println(membernumber);
				String gearofficer = word.next();
				//System.out.println(gearofficer);
				double cost = word.nextDouble();
//				System.out.println(cost);
				
//				System.out.println(loannumber);
//				System.out.println(loandate);
//				System.out.println(returndate);
//				System.out.println(equipmentnumber);
//				System.out.println(membernumber);
//				System.out.println(gearofficer);
//				System.out.println(cost);
				
				
		        Loan loan = new Loan(loannumber, loandate, returndate,equipmentnumber, membernumber,gearofficer, cost);
		  		loans[loanCount] = loan;
		  		loanCount++;
		  		word.close();
			}
			
			for(int i=0;i<loans.length;i++) {
				if (loans[i] == null || loans[i].equals("")) {
					break;
				}
				else {count+=1;}
			}
			
			System.out.println("Loaded "+ count +" loans");
			
			input.close();
    		
    	}catch (IOException e) {
            System.out.println("Error loading loans: " + e.getMessage());
        }

    }

    // Save loans to file
    private static void saveLoans() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("loan.txt", true))) {
            for (int i = 0; i < loanCount; i++) {
                writer.write(loans[i].toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving loans: " + e.getMessage());
        }
        
    }

    // Display loan details in a string format
    @Override
    public String toString() {
        return loanNumber + "," + loanDate + "," + returnDate + "," +
               equipmentNumber + "," + memberNumber + ",$" + cost;
    }

    // Loan equipment logic with confirmation
    public static void loanEquipment(LocalDate loanDate, LocalDate returnDate, int equipmentNumber, int memberNumber, String gearofficer, int hirecost) {
//        Scanner scanner = new Scanner(System.in);
//        String loanNumber, equipmentNumber, memberNumber;
//        LocalDate loanDate = null, returnDate;
//
//        while (true) {
//            try {
//                System.out.print("Enter loan number: ");
//                loanNumber = scanner.nextLine();
//                System.out.print("Enter loan date (yyyy-MM-dd): ");
//                loanDate = LocalDate.parse(scanner.nextLine());
//                break;
//            } catch (Exception e) {
//                System.out.println("Invalid date format. Please try again.");
//            }
//        }
//
//        returnDate = loanDate.plusWeeks(1);
//        System.out.println("Expected return date: " + returnDate);
//
//        System.out.print("Enter equipment number: ");
//        equipmentNumber = scanner.nextLine();
//        System.out.print("Enter member number: ");
//        memberNumber = scanner.nextLine();
//
//        double dailyRate = 10.0;
//        double cost = calculateCost(loanDate, returnDate, dailyRate);
//
//        System.out.println("\nPlease review the loan details:");
//        System.out.println("Loan Number: " + loanNumber);
//        System.out.println("Loan Date: " + loanDate);
//        System.out.println("Return Date: " + returnDate);
//        System.out.println("Equipment Number: " + equipmentNumber);
//        System.out.println("Member Number: " + memberNumber);
//        System.out.println("Cost: $" + cost);
//
//        System.out.print("\nConfirm your input? (yes/no): ");
//        if (scanner.nextLine().trim().equalsIgnoreCase("yes")) {
    
            if (loanCount < loans.length) {
            	loanCount++;
            	String loannumber = Integer.toString(loanCount);
            	String eqnumber = Integer.toString(equipmentNumber);
            	String memnumber = Integer.toString(memberNumber);
            	Double cost = (double) hirecost;
    
                loans[loanCount] = new Loan(loannumber, loanDate, returnDate, eqnumber, memnumber,gearofficer, cost);
                System.out.println("Loan successfully added.");
            } else {
                System.out.println("Loan storage is full.");
            }
        }

    // Return equipment logic
    public static void returnEquipment() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter loan number to return: ");
        String loanNumber = scanner.nextLine();

        Loan loanToReturn = null;
        int indexToRemove = -1;

        for (int i = 0; i < loanCount; i++) {
            if (loans[i].loanNumber.equals(loanNumber)) {
                loanToReturn = loans[i];
                indexToRemove = i;
                break;
            }
        }

        if (loanToReturn != null) {
            System.out.println("Loan Details:");
            System.out.println(loanToReturn);

            LocalDate currentDate = LocalDate.now();
            if (currentDate.isAfter(loanToReturn.returnDate)) {
                long daysOverdue = java.time.temporal.ChronoUnit.DAYS.between(loanToReturn.returnDate, currentDate);
                double lateFee = daysOverdue * 5.0;
                System.out.println("Late Fee: $" + lateFee);
                System.out.println("Total Cost: $" + (loanToReturn.cost + lateFee));
            } else {
                System.out.println("Equipment returned on time.");
            }

            System.out.print("Return equipment now? (yes/no): ");
            if (scanner.nextLine().trim().equalsIgnoreCase("yes")) {
                for (int i = indexToRemove; i < loanCount - 1; i++) {
                    loans[i] = loans[i + 1];
                }
                loans[--loanCount] = null;
                System.out.println("Equipment returned successfully.");
            }
        } else {
            System.out.println("Loan not found.");
        }
        scanner.close();
    }

    // View loans
    public static void viewLoans() {
        if (loanCount == 0) {
            System.out.println("No loans found.");
        } else {
            for (int i = 0; i < loanCount; i++) {
                System.out.println(loans[i]);
            }
        }
    }
//    public static void main(String[] args) {
//    	System.out.println(calculateCost(LocalDate.parse("2023-03-10"),LocalDate.parse("2023-03-13"),3,5));
//    }

//    public static void main(String[] args) {
//        loadLoans();
//        Scanner scanner = new Scanner(System.in);
//        int choice;
//
//        do {
//            System.out.println("\n===== Loan System =====");
//            System.out.println("1. Loan Equipment");
//            System.out.println("2. View Loans");
//            System.out.println("3. Return Equipment");
//            System.out.println("4. Exit");
//            System.out.print("Enter your choice: ");
//
//            choice = scanner.nextInt();
//            scanner.nextLine(); // Consume newline
//
//            switch (choice) {
//                case 1 -> loanEquipment();
//                case 2 -> viewLoans();
//                case 3 -> returnEquipment();
//                case 4 -> System.out.println("Goodbye!");
//                default -> System.out.println("Invalid choice. Please try again.");
//            }
//        } while (choice != 4);
//    }
}
