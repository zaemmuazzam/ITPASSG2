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
    private String name;
    private String description;
    private double costWeekend;
    private double costPerWeek;
    private boolean isLoaned;

    Equipment(String name, String description, double costWeekend, double costPerWeek) {
        this.name = name;
        this.description = description;
        this.costWeekend = costWeekend;
        this.costPerWeek = costPerWeek;
        this.isLoaned = false;
    }

    public String getName() {
        return name;
    }

    public boolean isLoaned() {
        return isLoaned;
    }

    public void setLoaned(boolean loaned) {
        isLoaned = loaned;
    }

    public double getCost(boolean isWeekend) {
        return isWeekend ? costWeekend : costPerWeek;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("Equipment name: %s, Description: %s, Cost (Weekend): $%.2f, Cost (Per Week): $%.2f", name, description, costWeekend, costPerWeek);
    }
}

public class Loan {
    private static List<Member> members = new ArrayList<>();
    private static List<Equipment> equipmentList = new ArrayList<>();

    public static void main(String[] args) {
        initializeMembers();
        initializeEquipment();

        Scanner scanner = new Scanner(System.in);

        // Input loan dates
        System.out.print("Enter loan date (dd/MM/yyyy): ");
        String loanDate = scanner.nextLine();
        System.out.print("Enter expected return date (dd/MM/yyyy): ");
        String returnDate = scanner.nextLine();

        // Display activities
        System.out.println("Activity");
        System.out.println("1. Rock climbing");
        System.out.println("2. Hiking");
        System.out.println("3. Bush walking");
        System.out.println("4. Caving");
        System.out.println("5. Mountain biking");
        System.out.println("6. Paddling");
        System.out.print("Enter activity no: ");
        int activityChoice = scanner.nextInt();

        // Find equipment based on activity
        displayEquipment(activityChoice);

        // Input equipment choice
        System.out.print("Enter equipment no: ");
        int equipmentChoice = scanner.nextInt();

        // Find the selected equipment
        Equipment selectedEquipment = equipmentList.get(equipmentChoice - 1); // Adjust for zero-based index

        // Display members
        displayMembers();

        // Input member choice
        System.out.print("Enter member no: ");
        int memberChoice = scanner.nextInt();
        Member selectedMember = members.get(memberChoice); // Adjust for zero-based index

        // Calculate cost
        boolean isWeekend = false; // For simplicity, assume it's not a weekend
        double cost = selectedEquipment.getCost(isWeekend);

        // Display the cost
        System.out.printf("The cost of hiring the equipment is $%.2f%n", cost);
        selectedEquipment.setLoaned(true); // Mark equipment as loaned

        scanner.close();
    }

    private static void initializeMembers() {
        members.add(new Member("Siti Abdullah"));
        members.add(new Member("Abdul Rahman"));
        members.add(new Member("John Lee"));
    }

    private static void initializeEquipment() {
        equipmentList.add(new Equipment("Harness Small", "Small Harness", 2.00, 4.00));
        equipmentList.add(new Equipment("Harness Medium", "Medium Harness", 2.00, 4.00));
        equipmentList.add(new Equipment("Harness Large", "Large Harness", 2.00, 4.00));
        equipmentList.add(new Equipment("Climbing Shoes Size 10", "Rock Climbing Shoes Size 10", 3.00, 6.00));
        equipmentList.add(new Equipment("Climbing Shoes Size 11", "Rock Climbing Shoes Size 11", 3.00, 6.00));
        equipmentList.add(new Equipment("Climbing Shoes Size 12", "Rock Climbing Shoes Size 12", 3.00, 6.00));
        equipmentList.add(new Equipment("Climbing Shoes Size 8", "Rock Climbing Shoes Size 8", 3.00, 6.00));
        equipmentList.add(new Equipment("Climbing Shoes Size 9", "Rock Climbing Shoes Size 9", 3.00, 6.00));
        equipmentList.add(new Equipment("Climbing Shoes Size 6", "Rock Climbing Shoes Size 6", 3.00, 6.00));
        equipmentList.add(new Equipment("Climbing Shoes Size 7", "Rock Climbing Shoes Size 7", 3.00, 6.00));
        equipmentList.add(new Equipment("Climbing Rack", "Yellow Rack, 5 Cams, 20 Nuts, 2 Prussics, 8 Quick Draws", 10.00, 20.00));
        equipmentList.add(new Equipment("Climbing Rope Lead", "Lead Climbing Rope", 5.00, 10.00));
        equipmentList.add(new Equipment("Climbing Rope Top", "Top Rope", 5.00, 10.00));
    }

    private static void displayEquipment(int activityChoice) {
        System.out.println("Equipment List:");
        for (int i = 0; i < equipmentList.size(); i++) {
            System.out.println((i + 1) + ". " + equipmentList.get(i));
        }
    }

    private static void displayMembers() {
        System.out.println("Members:");
        for (int i = 0; i < members.size(); i++) {
            System.out.println(i + ". " + members.get(i).name);
        }
    }
}
