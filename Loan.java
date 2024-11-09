//TEAM:
//Hidayat - B20240386
//Za’em - B20240387
//Fa’izah - B20240050
//Asyraf - B20240436
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
    private Boolean isLoaned;
    
    
    private static Loan[] loans = new Loan[300]; // Fixed-size array of 300 elements
    private static int loanCount = 0; // Track the number of loans added

    
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
    
    //getters
    public LocalDate getReturnDate() {return returnDate;}
    public int getEquipmentNumber() {return Integer.parseInt(equipmentNumber.trim());}
    public String getLoanNumber() {return loanNumber;}
    public String getGearOfficer() {return gearofficer;}
    
    //setters
    public static void setLoanCount(int number) {loanCount=number;} 
    
    //Checks if loan return date is overdue
    public static boolean isOverdue(Loan loan, LocalDate currentDate) {
    	//System.out.println(loan); 
    	if (loan.getReturnDate().isBefore(currentDate)==true) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    //Lists all loans with overdue loan dates and their not having been returned yet
    public static void listOverdue(EquipmentArray equipmentarray, LocalDate currentDate) {
    	for(int i=0;i<loans.length;i++) {
   		 	if (loans[i] == null || loans[i].equals("")) {
   		    	System.out.println("\nThere are no overdue loaned equipment.");
   		    	return;
   		    	
				
   		 	}
   		 	else if ((loans[i].getReturnDate().isBefore(currentDate))==true 
   		 			&& (equipmentarray.getEquipmentByNumber(loans[i].getEquipmentNumber()).getReturned())==false) {
   		 		break;
   		 		
   		 	}
    	}
   		
    	
    	System.out.println("\nAs of the current date: "+currentDate);
    	
    	System.out.println("\nThese equipment are overdue: ");
    	
    	
    	System.out.println("Equipment no, Equipment name, Description, Activity, Loan no, Expected return date, Gearofficer");

    	
    	for(int i=0;i<loans.length;i++) {
   		 	if (loans[i] == null || loans[i].equals("")) {
				return;
   		 	} else {
	    		if ((loans[i].getReturnDate().isBefore(currentDate))==true) { 
	    			
	    			Equipment item = equipmentarray.getEquipmentByNumber(loans[i].getEquipmentNumber());
	    			
	    			if ((item.getReturned())==false) {
	    			
	    				System.out.println(item.getEquipmentNumber()+ "  " + item.getName()+ "	" +item.getDescription()+ "  " + item.getActivity()
	    				+ " "+ loans[i].getLoanNumber()+ "  "+loans[i].getReturnDate()+ "  "+loans[i].getGearOfficer());
	    			}
	    			else {
	    				continue;
	    			}
	    			
	    		}
   		 	}
    	}
    	
    	
    	
    }

    //Calculates hire cost 
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

    

    
    
    // Load loans from file
    public static void loadLoans() {
	   	File myFile;
		String path = "loan.txt";
		Scanner input;
		

    	setLoanCount(0);
    	while(true) {
	    	try {
	    		myFile = new File (path);
	    		input = new Scanner(myFile);
	    		int count=0;
			
	    		
				while(input.hasNextLine()) {
					String line = input.nextLine();
					//System.out.println(line);
					Scanner word = new Scanner(line);
					word.useDelimiter(";");
					
					String loannumber = word.next();
					LocalDate loandate = LocalDate.parse(word.next());
					LocalDate returndate = LocalDate.parse(word.next());
					String equipmentnumber = word.next();
					String membernumber = word.next();
					String gearofficer = word.next();
					double cost = word.nextDouble();
					
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
				return;
	    		
	    	}catch (IOException e) {
//		            System.out.println("Error loading loans: " + e.getMessage());
				System.out.println("loan.txt not found");
				Scanner pathtxt = new Scanner(System.in);
				System.out.println("Please state the file path to loan.txt (Example = C:\\Users\\USER\\Desktop\\folderexample\\equipment.txt): ");
				String x=pathtxt.nextLine();

	
				x.replace("\\","/");
				x.replace("C:","");
				path = x;

				continue;
		     
	    	}
    	}

    }
    
    //Checks if specific equipment number exists in loans
    public static boolean checkEquipmentNumberExists(int number) {
    	for (int i = 0; i<loans.length;i++) {
			if (loans[i] == null || loans[i].equals("")) {
			
				break;
			} else {
				if (loans[i].getEquipmentNumber()==number) {
					return true;
				}
				else {
					return false;
				}
				
			}
				
    	}
    	return false;
    	
    }
    
    //gets a specific loan member object based on specified loan number
    public static Loan getloan(int number) {
    	for (int i = 0; i<loans.length;i++) {
			if (loans[i] == null || loans[i].equals("")) {
			
				break;
			} else {
				if (loans[i].getEquipmentNumber()==number) {
					return loans[i];
				}
				else {
					return null;
				}
				
			}
				
    	}
    	return null;
    	
    }

    // Save loans to file
    public static void saveLoans(String txtfile) {

    	try {
//			File myFile = new File (txtfile);
//			FileWriter writer= new FileWriter(myFile, false);
			FileWriter writer= new FileWriter(txtfile, false);
			writer.write("");
			writer.close();
			
//			writer = new FileWriter(myFile, true);
			writer= new FileWriter(txtfile, true);
			for (int i=0;i<loans.length;i++) {
				if (loans[i] == null || loans[i].equals("")) {
					break;
				}
				else { writer.write(loans[i].toString()+"\n");}
			}
			
			writer.close();
			System.out.println("Loans saved");
		}
		
		catch (IOException e) {
			System.out.println("An error has occured");
			e.printStackTrace();
			return;
		}
        
    }

    // Display loan details in a string format
    @Override
    public String toString() {
        return loanNumber + ";" + loanDate + ";" + returnDate + ";" +
               equipmentNumber + ";" + memberNumber + ";" + gearofficer + ";" + (int)cost;
    }
    public String toString2() {
        return loanNumber + ", " + loanDate + ", " + returnDate + ", " +
               equipmentNumber + ", " + memberNumber + ", " + gearofficer + ", " + (int)cost;
    }

    // Loan equipment logic with confirmation
    public static void loanEquipment(LocalDate loanDate, LocalDate returnDate, int equipmentNumber, int memberNumber, String gearofficer, int hirecost) {
    
            if (loanCount < loans.length) {
            	
            	String loannumber = Integer.toString(loanCount);
            	String eqnumber = Integer.toString(equipmentNumber);
            	String memnumber = Integer.toString(memberNumber);
            	Double cost = (double) hirecost;
    
                loans[loanCount] = new Loan(loannumber, loanDate, returnDate, eqnumber, memnumber,gearofficer, cost);
                loanCount++;
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

}

