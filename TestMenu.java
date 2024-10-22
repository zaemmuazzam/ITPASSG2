
public class TestMenu {
	//private static Member[] members;

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		MemberArray memarray= new MemberArray("member.txt");
		memarray.displayMember();
		System.out.println();
		System.out.println();

		memarray.addMember("Haruhi", "Suzumiya", "HaruSu@utb.edu.bn", "No 34,Jln 190, Spg 10", 32301451, 4519012);
		memarray.displayMember();
		System.out.println();
		System.out.println();

		memarray.saveMembers("member.txt");
		System.out.println();
		System.out.println();

		//Thread.sleep(5000);
		memarray= new MemberArray("member.txt");
		memarray.displayMember();


	}

}
