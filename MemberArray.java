// Team:
// Fa’izah B20240050
// Hidayat b20240386
// zaem b20240387
// Asyraf B20240436
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class MemberArray{
	private Member[] members;
		
	
	public MemberArray(String txtfile) {
    	File myFile;
		String path;
		Scanner input;
		path = txtfile;
		Member.setNumberOfMembers(0);
		members = new Member[300];
		int count =0;
		
		while(true) {
			try {
				myFile = new File (path);
				input = new Scanner(myFile);
				//line.useDelimiter(";");
				while(input.hasNextLine()) {

						String line = input.nextLine();
						Scanner word = new Scanner(line);
						word.useDelimiter(";");
									
						int membernumber = word.nextInt();
						String firstname = word.next();
						String lastname = word.next();
						String email = word.next();
						String address = word.next();
						int studentnumber = word.nextInt();
						int phonenumber = word.nextInt();
						
	
						
						Member a = new Member(membernumber,firstname,lastname,email,address,studentnumber,phonenumber);

						members[a.getNumberOfMembers()-1]=a;
						word.close();
						

				}
				input.close();
				
				for(int i=0;i<members.length;i++) {
					if (members[i] == null || members[i].equals("")) {
						break;
					}
					else {count+=1;}
					
					
				}
				System.out.println("Loaded "+count+" members");
				return;
				
	
			}
			catch (FileNotFoundException e) {

			System.out.println("member.txt not found");
			Scanner pathtxt = new Scanner(System.in);
			System.out.println("Please state the file path to equipment.txt (Example = C:\\Users\\USER\\Desktop\\folderexample\\member.txt): ");
			String x=pathtxt.nextLine();

			x.replace("\\","/");
			x.replace("C:","");
			path = x;

			continue;
			}
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
		Member a = new Member(members[0].getNumberOfMembers(),firstname,lastname,email,address,studentnumber,phonenumber);
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
