import java.time.LocalDate;
import java.util.*;

public class MainInterface {


    public static void main(String[] args) {
    	MemberArray memarray= new MemberArray("member.txt");
    	EquipmentArray eqarray= new EquipmentArray("equipment.txt");
    	Loan.loadLoans();
    	//Loan.main(args);
    	
        Scanner scanner = new Scanner(System.in);
        int choice;

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

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    //System.out.print("Enter member name: ");
					System.out.print("Enter first name: ");
					String firstname = scanner.next();
					
					if (!firstname.matches("[a-zA-Z]+")) {
						System.out.println("Invalid first name. Please enter a name containing only letters.");
						continue;
					}
					
					System.out.print("Enter last name: ");
					String lastname = scanner.next();
					
					if (!lastname.matches("[a-zA-Z]+")) {
						System.out.println("Invalid last name. Please enter a name containing only letters.");
						continue;
					}


                    System.out.print("Enter email: ");
					scanner.nextLine();

                    String email= scanner.nextLine();

					if (!isValidEmailUTB(email)) {
						System.out.println("Invalid email. Email must contain '@utb.edu.bn'. Please re-enter:");
						continue;
					}


					System.out.print("Enter address: ");
					// scanner.nextLine();
					String address = scanner.nextLine();

					int studentrollnumber;





					if (email.contains("@staff.utb.edu.bn")) {
						System.out.print("Enter staff ID (SXXXXXXXXX): ");
						String staffId = scanner.next();

						if (!staffId.matches("S\\d{8}")) {
							System.out.println("Invalid staff ID. Please enter a valid ID starting with 'S' followed by 8 digits.");
							continue;
						}

						// Parse the staff ID (assuming you need the numeric part)
						studentrollnumber = Integer.parseInt(staffId.substring(1));
					} else {
						System.out.print("Enter student ID (BXXXXXXXXX, MXXXXXXXXX, or PXXXXXXXXX): ");
						String studentId = scanner.next();

						if (!studentId.matches("[BMP]\\d{8}")) {
							System.out.println("Invalid student ID. Please enter a valid ID starting with 'B', 'M', or 'P' followed by 8 digits.");
							continue;
						}

						// Parse the student ID (assuming you need the numeric part)
						studentrollnumber = Integer.parseInt(studentId.substring(1));
					}
                    // System.out.print("Enter student roll number: ");
                    // int studentrollnumber= scanner.nextInt();
					System.out.print("Enter phone number: ");
					String phoneNumberStr = scanner.next();
					
					if (phoneNumberStr.length() != 7 || !phoneNumberStr.matches("\\d+")) {
						System.out.println("Invalid phone number. Please enter a 7-digit number.");
						continue;
					}
					
					int phonenumber = Integer.parseInt(phoneNumberStr);





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
//                    System.out.print("Enter equipment name to loan: ");
//                    String loanEquipmentName = scanner.nextLine();
//                    Equipment equipmentToLoan = findEquipment(loanEquipmentName);
//                    if (equipmentToLoan != null && !equipmentToLoan.isLoaned) {
//                        System.out.print("Enter member name: ");
//                        String loanMemberName = scanner.nextLine();
//                        loanedItems.add(new LoanedItem(equipmentToLoan, loanMemberName));
//                        equipmentToLoan.isLoaned = true;
//                        System.out.println("Equipment loaned: " + loanEquipmentName);
//                    } else {
//                        System.out.println("Equipment not available for loan.");
//                    }
                	loanEquipment(memarray,eqarray);
                    continue;

                case 4:
                    System.out.println("Loaned Equipment:");
                    
                    eqarray.displayLoanedEquipment();

                    continue;

                case 5:
                    System.out.println("Overdue Equipment:");
                    // Placeholder for overdue logic (not implemented)
                    System.out.println("This feature is not implemented yet.");
                    break;

                case 6:
                    System.out.print("Enter equipment name to return: ");
//                    String returnEquipmentName = scanner.nextLine();
//                    LoanedItem itemToReturn = findLoanedItem(returnEquipmentName);
//                    if (itemToReturn != null) {
//                        itemToReturn.equipment.isLoaned = false;
//                        loanedItems.remove(itemToReturn);
//                        System.out.println("Equipment returned: " + returnEquipmentName);
//                    } else {
//                        System.out.println("No loaned equipment found with that name.");
//                    }
                    break;

                case 0:
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
		// return email.matches("^[^@]+@(?:staff|student)?\\.utb\\.edu\\.bn$");
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
	                System.out.println("Enter expected return date (yyyy-MM-dd): ");
	                returnDate = LocalDate.parse(scanner.next());
	                break;
	            } catch (Exception e) {
	                System.out.println("Invalid date format. Please try again.");
	                continue;
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
	        System.out.println("Gear Officer: " + selectedmember);
	        System.out.println("Cost: $" + cost);
	        
	        while(true) {
		        System.out.println("\nConfirm your input? (yes/no): ");
		        scanner.nextLine();
		        String confirmation = scanner.nextLine();
		        if (confirmation.equalsIgnoreCase("yes")) {
		        	Loan.loanEquipment(loanDate, returnDate, equipmentNumber, memberNumber, membername, cost);
		        	equipmentarray.loaningEquipmentByNumber(eqnumber);
		        	
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


}