import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class MemberArray{
	private Member[] members;
		
	
	public MemberArray(String txtfile) {

		Member.setNumberOfMembers(0);
		members = new Member[300];
		int count =0;
		
		try {
			File myFile = new File (txtfile);
			Scanner input = new Scanner(myFile);
			//line.useDelimiter(";");
			while(input.hasNextLine()) {
//		
//					System.out.println("Round "+count);
					String line = input.nextLine();
					Scanner word = new Scanner(line);
					word.useDelimiter(";");
		//					System.out.println(membernumber);
//					System.out.println(firstname);
//					System.out.println(lastname);
//					System.out.println(email );
//					System.out.println(address);
//					System.out.println(studentnumber);
//					System.out.println(phonenumber);
								
					int membernumber = word.nextInt();
					String firstname = word.next();
					String lastname = word.next();
					String email = word.next();
					String address = word.next();
					int studentnumber = word.nextInt();
					int phonenumber = word.nextInt();
					

					
					Member a = new Member(membernumber,firstname,lastname,email,address,studentnumber,phonenumber);
					//System.out.println(a.getNumberOfMembers());
					members[a.getNumberOfMembers()-1]=a;
					word.close();
					
					//System.out.println(data);
				//}
			}
			input.close();
			
			for(int i=0;i<members.length;i++) {
				if (members[i] == null || members[i].equals("")) {
					break;
				}
				else {count+=1;}
				
				
			}
			System.out.println("Loaded "+count+" members");
			

		}
		catch (FileNotFoundException e) {
		System.out.println("An error has occured");
		e.printStackTrace();
		}

		
	}
	
	//for getting the members array itselft.
	public Member[] getArray() {return members;}
	
	public String getMemberNameByNumber(int membernumber) {
		
		for(int i = 0;i<members.length;i++) {
			if(membernumber == members[i].getMemberNumber()) {
				return members[i].getMemberFullname();
					
			}
			else if (members[i] == null || members[i].equals("")) {
				break;
			}
			else {
				continue;
			}

		}
		return "Null";
	}
	//Display all members in the record
	public void displayMembers() {
		for (int i=0;i<members.length;i++) {
			if (members[i] == null || members[i].equals("")) {
				break;
			}
			else {System.out.println(members[i].getMember());}
			
		}
	}
	
	//for adding to members to the array
	public void addMember(String firstname, String lastname,String email, String address,int studentnumber,int phonenumber) {
		Member a = new Member(members[0].getNumberOfMembers()+1,firstname,lastname,email,address,studentnumber,phonenumber);
		members[a.getNumberOfMembers()-1]=a;
		
	}
	
	//For saving the members array to mebers.txt
	public void saveMembers(String txtfile) {
		try {
//			File myFile = new File (txtfile);
//			FileWriter writer= new FileWriter(myFile, false);
			FileWriter writer= new FileWriter(txtfile, false);
			writer.write("");
			writer.close();
			
//			writer = new FileWriter(myFile, true);
			writer= new FileWriter(txtfile, true);
			for (int i=0;i<members.length;i++) {
				if (members[i] == null || members[i].equals("")) {
					break;
				}
				else { writer.write(members[i].toString()+"\n");}
			}
			
			writer.close();
			System.out.println("Members saved");
		}
		
		catch (IOException e) {
			System.out.println("An error has occured");
			e.printStackTrace();
			return;
		}
		

	}

}
