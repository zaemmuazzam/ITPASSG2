import java.util.*;

public class MainInterface {


    public static void main(String[] args) {
    	MemberArray memarray= new MemberArray("member.txt");
    	EquipmentArray eqarray= new EquipmentArray("equipment.txt");
    	
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Main Menu:");
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
                    String firstname= scanner.next();
                    System.out.print("Enter last name: ");
                    String lastname= scanner.next();
                    System.out.print("Enter email: ");
                    String email= scanner.next();
                    System.out.print("Enter address: ");
                    String address= scanner.next();
                    System.out.print("Enter student roll number: ");
                    int studentrollnumber= scanner.nextInt();
                    System.out.print("Enter phone number: ");
                    int phonenumber= scanner.nextInt();
                    memarray.addMember(firstname, lastname, email, address, studentrollnumber, phonenumber);
                    
                    System.out.println("Member added: " +  firstname + ";"+  lastname + ";"+  email + ";"+  address + ";"
                    +  studentrollnumber + ";"+  phonenumber + ";");
                    
                    break;

                case 2:

                    System.out.print("Enter name of equipment: ");
                    String equipmentname= scanner.next();
                    System.out.print("Enter description of the equipment: ");
                    String description= scanner.next();
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
                    String activity = Activity.main(args);
                    
                    eqarray.addEquipment(equipmentname, description, dateofpurchase, purchasecost, costperweekend, costperweek, activity, true);
                    
                    System.out.println("Equipment added: " + equipmentname + ";" + description + ";"+ dateofpurchase
                    		+ ";"+ purchasecost + ";"+ costperweekend + ";" + costperweek + ";"+ activity + ";");
                    break;

                case 3:
                    System.out.print("Enter equipment name to loan: ");
                    String loanEquipmentName = scanner.nextLine();
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
                    break;

                case 4:
                    System.out.println("Loaned Equipment:");
//                    for (LoanedItem item : loanedItems) {
//                        System.out.println("Equipment: " + item.equipment.name + ", Loaned to: " + item.memberName);
//                    }
                    break;

                case 5:
                    System.out.println("Overdue Equipment:");
                    // Placeholder for overdue logic (not implemented)
                    System.out.println("This feature is not implemented yet.");
                    break;

                case 6:
                    System.out.print("Enter equipment name to return: ");
                    String returnEquipmentName = scanner.nextLine();
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
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            System.out.println(); // Blank line for better readability
        } while (choice != 0);

        scanner.close();
    }


}