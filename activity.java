package assg2;

import java.util.Scanner;

public class activity {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose an activity:");
        System.out.println("0. Rock Climbing");
        System.out.println("1. Hiking");
        System.out.println("2. Bush Walking");
        System.out.println("3. Caving");
        System.out.println("4. Mountain Biking");
        System.out.println("5. Paddling");
        System.out.println("6. All");

        System.out.print("Enter selection: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 0:
                System.out.println("You chose Rock Climbing.");
                break;
            case 1:
                System.out.println("You chose Hiking.");
                break;
            case 2:
                System.out.println("You chose Bush Walking.");
                break;
            case 3:
                System.out.println("You chose Caving.");
                break;
            case 4:
                System.out.println("You chose Mountain Biking.");
                break;
            case 5:
                System.out.println("You chose Paddling.");
                break;
            case 6:
                System.out.println("You chose All activities.");
                break;
            default:
                System.out.println("Invalid choice. Please enter a number from 0 to 6.");
        }

        scanner.close();
    }
}