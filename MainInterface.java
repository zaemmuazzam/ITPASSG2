import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Member {
    String name;

    Member(String name) {
        this.name = name;
    }
}

class Equipment {
    String name;
    boolean isLoaned;

    Equipment(String name) {
        this.name = name;
        this.isLoaned = false;
    }
}

class LoanedItem {
    Equipment equipment;
    String memberName;

    LoanedItem(Equipment equipment, String memberName) {
        this.equipment = equipment;
        this.memberName = memberName;
    }
}

public class MainInterface {
    private static List<Member> members = new ArrayList<>();
    private static List<Equipment> equipmentList = new ArrayList<>();
    private static List<LoanedItem> loanedItems = new ArrayList<>();

    public static void main(String[] args) {
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
                    System.out.print("Enter member name: ");
                    String memberName = scanner.nextLine();
                    members.add(new Member(memberName));
                    System.out.println("Member added: " + memberName);
                    break;

                case 2:
                    System.out.print("Enter equipment name: ");
                    String equipmentName = scanner.nextLine();
                    equipmentList.add(new Equipment(equipmentName));
                    System.out.println("Equipment added: " + equipmentName);
                    break;

                case 3:
                    System.out.print("Enter equipment name to loan: ");
                    String loanEquipmentName = scanner.nextLine();
                    Equipment equipmentToLoan = findEquipment(loanEquipmentName);
                    if (equipmentToLoan != null && !equipmentToLoan.isLoaned) {
                        System.out.print("Enter member name: ");
                        String loanMemberName = scanner.nextLine();
                        loanedItems.add(new LoanedItem(equipmentToLoan, loanMemberName));
                        equipmentToLoan.isLoaned = true;
                        System.out.println("Equipment loaned: " + loanEquipmentName);
                    } else {
                        System.out.println("Equipment not available for loan.");
                    }
                    break;

                case 4:
                    System.out.println("Loaned Equipment:");
                    for (LoanedItem item : loanedItems) {
                        System.out.println("Equipment: " + item.equipment.name + ", Loaned to: " + item.memberName);
                    }
                    break;

                case 5:
                    System.out.println("Overdue Equipment:");
                    // Placeholder for overdue logic (not implemented)
                    System.out.println("This feature is not implemented yet.");
                    break;

                case 6:
                    System.out.print("Enter equipment name to return: ");
                    String returnEquipmentName = scanner.nextLine();
                    LoanedItem itemToReturn = findLoanedItem(returnEquipmentName);
                    if (itemToReturn != null) {
                        itemToReturn.equipment.isLoaned = false;
                        loanedItems.remove(itemToReturn);
                        System.out.println("Equipment returned: " + returnEquipmentName);
                    } else {
                        System.out.println("No loaned equipment found with that name.");
                    }
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

    private static Equipment findEquipment(String name) {
        for (Equipment equipment : equipmentList) {
            if (equipment.name.equalsIgnoreCase(name)) {
                return equipment;
            }
        }
        return null;
    }

    private static LoanedItem findLoanedItem(String name) {
        for (LoanedItem item : loanedItems) {
            if (item.equipment.name.equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }
}