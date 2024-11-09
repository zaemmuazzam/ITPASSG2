import java.time.LocalDate;
import java.util.Scanner;

public class TestMenu {
	//private static Member[] members;

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		LocalDate currentdate =LocalDate.parse("2024-09-25");
		Scanner scanner= new Scanner(System.in);
		Loan.loadLoans();
//		int equipmentNumber;
		MemberArray memarray= new MemberArray("member.txt");
		EquipmentArray eqarray= new EquipmentArray("equipment.txt");
//		loanEquipment(memarray,eqarray);
//		Loan.saveLoans("loan.txt");
		 //String[] equipment = eqarray.getEquipmentNumberByNumber(7);
//		 System.out.println(eqarray.getArray()[7]);
		//System.out.println(eqarray.getLoanedEquipmentByNumber(9));
		
//		System.out.println(Loan.checkEquipmentNumberExists(9));
		
////		memarray.displayMembers();
//		System.out.println();
//		System.out.println();
//		
//		String activity=Activity.selectActivity();
//		System.out.println(activity);
//		eqarray.displayEquipmentByActivity(activity);
////		String activity="paddling";
//		equipmentNumber = input.nextInt();
//		//equipmentNumber=8;
//		System.out.println(eqarray.checkActivityEquipmentNumberMatch(activity, equipmentNumber));
//		String[] equipment=eqarray.getEquipmentNumberByNumber(equipmentNumber);
//		System.out.println(equipment[0]);
//		System.out.println(equipment[1]);
//		System.out.println(equipment[2]);
//        int eqnumber=Integer.parseInt(equipment[0]);
//        int costperweekend=Integer.parseInt(equipment[1]);
//        int costperweek=Integer.parseInt(equipment[2]);
//        System.out.println( eqnumber);
//        System.out.println(costperweekend);
//        System.out.println(costperweek);
//		memarray.addMember("Haruhi", "Suzumiya", "HaruSu@utb.edu.bn", "No 34,Jln 190, Spg 10", 32301451, 4519012);
//		memarray.displayMembers();
//		System.out.println();
//		System.out.println();
//
//		memarray.saveMembers("member.txt");
//		System.out.println();
//		System.out.println();
//
//		//Thread.sleep(5000);
//		memarray= new MemberArray("member.txt");
//		memarray.displayMembers();
//		System.out.println();
//		System.out.println();
		
	
//		eqarray.displayEquipments();


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

    

}
