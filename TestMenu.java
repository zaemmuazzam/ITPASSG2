import java.util.Scanner;

public class TestMenu {
	//private static Member[] members;

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Scanner input= new Scanner(System.in);
		int equipmentNumber;
		MemberArray memarray= new MemberArray("member.txt");
		EquipmentArray eqarray= new EquipmentArray("equipment.txt");
		
//		memarray.displayMembers();
		System.out.println();
		System.out.println();
		
		String activity=Activity.selectActivity();
		System.out.println(activity);
		eqarray.displayEquipmentByActivity(activity);
//		String activity="paddling";
		equipmentNumber = input.nextInt();
		//equipmentNumber=8;
		System.out.println(eqarray.checkActivityEquipmentNumberMatch(activity, equipmentNumber));
		String[] equipment=eqarray.getEquipmentNumberByNumber(equipmentNumber);
		System.out.println(equipment[0]);
		System.out.println(equipment[1]);
		System.out.println(equipment[2]);
        int eqnumber=Integer.parseInt(equipment[0]);
        int costperweekend=Integer.parseInt(equipment[1]);
        int costperweek=Integer.parseInt(equipment[2]);
        System.out.println( eqnumber);
        System.out.println(costperweekend);
        System.out.println(costperweek);
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

}
