import java.time.LocalDate;
import java.util.*;

public class MainInterface {


    public static void main(String[] args) {
    	LocalDate currentdate =LocalDate.parse("2024-09-25");
    	MemberArray memarray= new MemberArray("member.txt");
    	EquipmentArray eqarray= new EquipmentArray("equipment.txt");
    	Loan.loadLoans();
    	//Loan.main(args);
    	
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        do {
            System.out.println("\nMain Menu:");
            System.out.println("1. Add Member");
            System.out.println("2. Add Equipment");
            System.out.println("3. Loan Equipment");
            System.out.println("4. List Loaned Equipment");
            System.out.println("5. List Overdue Equipment");
            System.out.println("6. Return Equipment");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            
            try {
                choice = scanner.nextInt();  // Try reading the integer input
                scanner.nextLine();  // Consume newline
            } catch (InputMismatchException e) {
                System.out.println("Invalid choice. Please enter a number from 0 to 6.");
                scanner.nextLine(); // Consume the invalid input
                continue;  // Go back to the start of the loop
            }

         // Confirm choice before proceeding
            System.out.print("You selected option " + choice + ". Confirm? (yes/no): ");
            String confirm = scanner.nextLine().trim();

            // If confirmation is "no", restart loop to select a new choice
            if (!confirm.equalsIgnoreCase("yes")) {
                System.out.println("Returning to main menu.");
                continue;
            }
            
            switch (choice) {
                case 1:
                    //System.out.print("Enter member name: ");
					String firstname;
					String lastname;
					do {
						System.out.print("Enter first name: ");
						firstname = scanner.nextLine();
					
						if (!firstname.matches("[a-zA-Z]+")) {
							System.out.println("Invalid first name. Please enter a name containing only letters.");
						} else {
							break; // Exit the loop if the first name is valid
						}
					} while (true);
				
					do {
						System.out.print("Enter last name: ");
						lastname = scanner.next();

						if (!lastname.matches("[a-zA-Z]+")) {
							System.out.println("Invalid last name. Please enter a name containing only letters.");
						} else {
							break; // Exit the loop if the last name is valid
						}
					} while (true);


					String email;

					do {
						System.out.print("Enter email: ");
						// scanner.nextLine();
						email = scanner.next();
					
						if (!isValidEmailUTB(email)) {
							System.out.println("Invalid email. Email must contain '@student.utb.edu.bn' or '@staff.utb.edu.bn'. Please re-enter:");
						} else {
							break; // Exit the loop if the email is valid
						}
					} while (true);


					System.out.print("Enter address: ");
					// scanner.nextLine();
					String address = scanner.nextLine();
					String staffId;
					int studentrollnumber;
					
					if (email.contains("@staff.utb.edu.bn")) {
						do {
							System.out.print("Enter staff ID (SXXXXXXXXX): ");
							staffId = scanner.next().toUpperCase(); // Convert to uppercase
					
							if (!staffId.matches("S\\d{8}")) {
								System.out.println("Invalid staff ID. Please enter a valid ID starting with 'S' followed by 8 digits.");
							} else {
								break; // Exit the loop if the staff ID is valid
							}
						} while (true);
					
						// Parse the staff ID (assuming you need the numeric part)
						studentrollnumber = Integer.parseInt(staffId.substring(1));
					} else {
						String studentId;
					
						do {
							System.out.print("Enter student ID (BXXXXXXXXX, MXXXXXXXXX, or PXXXXXXXXX): ");
							studentId = scanner.next().toUpperCase(); // Convert to uppercase
					
							if (!studentId.matches("[BMP]\\d{8}")) {
								System.out.println("Invalid student ID. Please enter a valid ID starting with 'B', 'M', or 'P' followed by 8 digits.");
							} else {
								break; // Exit the loop if the student ID is valid
							}
						} while (true);
					
						// Parse the student ID (assuming you need the numeric part)
						studentrollnumber = Integer.parseInt(studentId.substring(1));
					}
                    // System.out.print("Enter student roll number: ");
                    // int studentrollnumber= scanner.nextInt();
					String phoneNumberStr;
					int phonenumber;
					
					do {
						System.out.print("Enter phone number: ");
						phoneNumberStr = scanner.next();
					
						if (phoneNumberStr.length() != 7 || !phoneNumberStr.matches("\\d+")) {
							System.out.println("Invalid phone number. Please enter a 7-digit number.");
						} else {
							break; // Exit the loop if the phone number is valid
						}
					} while (true);
					
					phonenumber = Integer.parseInt(phoneNumberStr);

                    memarray.addMember(firstname, lastname, email, address, studentrollnumber, phonenumber);
                    
                    System.out.println("Member added: " +  firstname + ";"+  lastname + ";"+  email + ";"+  address + ";"
                    +  studentrollnumber + ";"+  phonenumber + ";");
                    
                    continue;

                case 2:

                    System.out.print("Enter name of equipment: ");
                    String equipmentname= scanner.next();
                    System.out.print("Enter description of the equipment: ");
                    scanner.nextLine();
                    String description= scanner.nextLine();
                    System.out.print("Enter date of purchase (dd/mm/yyyy): ");
                    String dateofpurchase= scanner.next();
                    System.out.print("Enter purchase cost: ");
                    int purchasecost= scanner.nextInt();
                    System.out.print("Enter the cost per weekend: ");
                    int costperweekend= scanner.nextInt();
                    System.out.print("Enter the cost per week: ");
                    int costperweek= scanner.nextInt();
                    System.out.print("Enter the activity: ");
                    //activity here
                    String activity = Activity.selectActivity();
                    
                    eqarray.addEquipment(equipmentname, description, dateofpurchase, purchasecost, costperweekend, costperweek, activity, true);
                    
                    System.out.println("Equipment added: " + equipmentname + ";" + description + ";"+ dateofpurchase
                    		+ ";"+ purchasecost + ";"+ costperweekend + ";" + costperweek + ";"+ activity + ";");
                    continue;

                case 3:
                	

                	loanEquipment(memarray,eqarray);
                    continue;

                case 4:
                    System.out.println("Loaned Equipment:");
                    
                    eqarray.displayLoanedEquipment();
                    //for testing
//                    System.out.println();
//                    eqarray.displayEquipments(); 
//                    System.out.println();
//                    Loan.viewLoans();

                    continue;

                case 5:
 
                	Loan.listOverdue(eqarray,currentdate);
                    continue;

                case 6:
                	 returnEquipment(eqarray, scanner);
                	 continue;

                    

                case 0:
                	eqarray.saveEquipments("equipment.txt");
                	memarray.saveMembers("member.txt");
                	Loan.saveLoans("loan.txt");
                	
                    System.out.println("Exiting the program.");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            System.out.println(); // Blank line for better readability
        } while (choice != 0);

        scanner.close();
    }

	private static boolean isValidEmailUTB(String email) {
		// checks email to ensure it contains the correct student or staff email
		return email.matches("^[^\\s@]+@(?:staff|student)?\\.utb\\.edu\\.bn$");

    }
    
    private static void loanEquipment(MemberArray memberarray, EquipmentArray equipmentarray) {
    	while(true) {
	        Scanner scanner = new Scanner(System.in);
	        int  equipmentNumber, memberNumber;
	        int eqnumber, costperweekend, costperweek;
	        String selectedmember, membername;
	        LocalDate loanDate = null, returnDate = null;
	        String[] equipment;
	        
	        while (true) {
	            try {
	                System.out.print("\nEnter loan date (yyyy-MM-dd): ");
	                loanDate = LocalDate.parse(scanner.next());

	                // Validate the return date to ensure it's after the loan date
	                while (true) {
	                    System.out.print("Enter expected return date (yyyy-MM-dd): ");
	                    returnDate = LocalDate.parse(scanner.next());

	                    if (!returnDate.isBefore(loanDate)) {
	                        break;  // If the return date is valid, break the loop
	                    } else {
	                        System.out.println("Return date cannot be before loan date. Please enter a valid return date.");
	                    }
	                }
	                break;  // Exit the main date validation loop if all dates are valid
	            } catch (Exception e) {
	                System.out.println("Invalid date format. Please try again.");
	            }
	        }

	        
	        while(true) {
	      
	        	String activity=Activity.selectActivity();
	        	
		        equipmentarray.displayEquipmentByActivity(activity);
		        System.out.print("Enter equipment number: ");
		        equipmentNumber = scanner.nextInt();
		        if (equipmentarray.checkActivityEquipmentNumberMatch(activity, equipmentNumber)) {
		        	equipment=equipmentarray.getEquipmentNumberByNumber(equipmentNumber);
			        if(equipment.length==0) {
			        	System.out.println("Please enter a valid equipment number");
			        	continue;
			        }
			        else {
				        eqnumber=Integer.parseInt(equipment[0]);
				        costperweekend=Integer.parseInt(equipment[1]);
				        costperweek=Integer.parseInt(equipment[2]);
			        	break;
			        }
		        }
		        else {
		        	System.out.println("No matching equipment number and activity");
		        	continue;
		        }
	        }
	       
	        //scanner.next();
	        while(true) {
		        memberarray.displayMembers();
		        System.out.println("Enter member number: ");
		        String x=scanner.next();
		        memberNumber = Integer.parseInt(x);
		        membername=memberarray.getMemberNameByNumber(memberNumber);
		        
		        if (membername=="Null") {
		        	System.out.println("Enter a valid member number.");
		        	continue;
		        }
		        else {
		        	selectedmember = memberNumber +". "+membername;
		        	break;
		        }
	        }
	
	        int cost = Loan.calculateCost(loanDate,returnDate,costperweekend,costperweek);
	
	        System.out.println("\nPlease review the loan details:");
	        System.out.println("Loan Date: " + loanDate);
	        System.out.println("Return Date: " + returnDate);
	        System.out.println("Equipment Number: " + eqnumber);
	        System.out.println("Equipment Name: " + (equipmentarray.getEquipmentByNumber(eqnumber)).getName());
	        System.out.println("Activity: " + (equipmentarray.getEquipmentByNumber(eqnumber)).getActivity());
	        System.out.println("Gear Officer: " + selectedmember);
	        System.out.println("Cost: $" + cost);
	        
	        while(true) {
		        System.out.println("\nConfirm your input? (yes/no): ");
		        scanner.nextLine();
		        String confirmation = scanner.nextLine();
		        if (confirmation.equalsIgnoreCase("yes")) {
		        	Loan.loanEquipment(loanDate, returnDate, equipmentNumber, memberNumber, membername, cost);
		        	equipmentarray.loaningEquipmentByNumber(eqnumber);
		        	return;
		        	
		        }
		        else if (confirmation.trim().equalsIgnoreCase("no")) {
		        	
		        	while(true) {
			        	System.out.println("Redo input?\n1.Yes\n2.No(return to main-menu)");
			        	String redo =scanner.next();
			        	if (redo.trim().equalsIgnoreCase("yes")) {
			        		break;
			        	}
			        	
			        	else if (redo.trim().equalsIgnoreCase("no")) {
			        	
			        		return;
			        		
			        	}
			        	else {
			        		System.out.println("Invalid input");
			        		continue;
			        	}		
			        	
		        	}
		        	
		        }
		        else {
		        	System.out.println("Invalid input");
		        	continue;
		        }
		       break;
	        }
	        continue;

    	}
    }

    private static void returnEquipment(EquipmentArray eqarray, Scanner scanner) {
        try {
            boolean validInput = false;
            int equipmentNumber = -1;
            System.out.print("Returnable equipment:");
            eqarray.displayLoanedEquipment();
            
            // Loop until valid equipment number is entered
            while (!validInput) {
                System.out.print("Enter the equipment number you want to return: ");
                if (scanner.hasNextInt()) {
                    equipmentNumber = scanner.nextInt();
                    validInput = true; // valid input, break the loop
                } else {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.nextLine(); // Clear the invalid input
                }
            }


            // Find the equipment by number
          Equipment equipment = eqarray.getLoanedEquipmentByNumber(equipmentNumber);
          if (equipment == null) {
              System.out.println("Invalid equipment number. Please try again.");
              return;
          }

            // Ask for confirmation before returning the equipment
            System.out.println("Are you sure you want to return the following equipment?");
            System.out.println("Equipment Number: " + equipment.getEquipmentNumber());
            System.out.println("Equipment Name: " + equipment.getName());
            System.out.print("Confirm return? (yes/no): ");
            scanner.nextLine();  // Consume any leftover newline
            String confirmReturn = scanner.nextLine().trim();
            
            if (confirmReturn.equalsIgnoreCase("yes")) {
                // Proceed with returning the equipment
                eqarray.returnEquipmentByNumber(equipment.getEquipmentNumber());
                System.out.println("Equipment returned successfully.");
            } else {
                System.out.println("Equipment return canceled.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
