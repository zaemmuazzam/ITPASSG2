
import java.util.Scanner;

public class Activity {
    public static String selectActivity() {
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
        String activity = null;

        switch (choice) {
            case 0:
                System.out.println("You chose Rock Climbing.");
                activity="rock climbing";
                break;
            case 1:
                System.out.println("You chose Hiking.");
                activity="hiking";
                break;
            case 2:
                System.out.println("You chose Bush Walking.");
                activity="bush walking";
                break;
            case 3:
                System.out.println("You chose Caving.");
                activity="caving";
                break;
            case 4:
                System.out.println("You chose Mountain Biking.");
                activity="biking";
                break;
            case 5:
                System.out.println("You chose Paddling.");
                activity="paddling";
                break;
            case 6:
                System.out.println("You chose All activities.");
                activity="all";
                break;
            default:
                System.out.println("Invalid choice. Please enter a number from 0 to 6.");
        }

        scanner.close();
        return activity;
    }
}