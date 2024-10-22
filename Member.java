
public class Member {
	private String first_name, last_name, email, address;
	private int phone_number, student_role_number, member_number;
	private static int numberofmembers=0;
	
	public Member() {
		numberofmembers=0;
	}
	
	public Member (int member_number,String first_name, String last_name, 
			String email, String address,int student_role_number,int phone_number) {
		this.member_number=member_number;
		this.first_name=first_name;
		this.last_name=last_name;
		this.email=email;
		this.address=address;
		this.student_role_number=student_role_number;
		this.phone_number=phone_number;
		numberofmembers+=1;
		
	}
	
	/* getters*/
	public int getMemberNumber() {return member_number;}
	public int getPhoneNumber() {return phone_number;}
	public int getNumberOfMembers() {return numberofmembers;}
	public int getStudentRoleNumber() {return student_role_number;}
	public String getFirstName() {return first_name;}
	public String getLastName() {return last_name;}
	public String getEmail() {return email;}
	public String getAddress() {return address;}
	
	/*setters*/
	public void setMemberNumber(int member_number) {this.member_number=member_number;}
	public void setPhoneNumber(int phone_number) {this.phone_number=phone_number;}
	public static void setNumberOfMembers(int value) {numberofmembers=value;}
	public void setStudentRoleNumber(int student_role_number) {this.student_role_number=student_role_number;}
	public void setFirstName(String first_name) {this.first_name=first_name;}
	public void setLastName(String last_name) {this.last_name=last_name;}
	public void setEmail(String email) {this.email=email;}
	public void setAddress(String address) {this.address=address;}
	
	public String displayMember() {return member_number+ ", " + first_name+ ", " + last_name + ", " + email + ", " + address + ", " 
			+ student_role_number + ", " + phone_number;}
	
	public String toString() { return member_number+ ";" + first_name+ ";" + last_name + ";" + email + ";" + address + ";" 
	+ student_role_number + ";" + phone_number;}
}
