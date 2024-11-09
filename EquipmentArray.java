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

public class EquipmentArray {
	private Equipment[] equipments;
		
	//When Equipment array is instantiated an array of equipment members is created
	    public EquipmentArray(String txtfile) {
	    	File myFile;
			String path;
			Scanner input;
			path = txtfile;
			Equipment.setNumberOfEquipment(0);
			equipments = new Equipment[300];
			int count =0;
	    	while(true) {
			
				try {//Equipment.txt is opened 
					myFile = new File (path);
					input = new Scanner(myFile);
					while(input.hasNextLine()) { //and the records are loaded one line at a time into the array as equipment objects

						String line = input.nextLine();
						Scanner word = new Scanner(line);
						word.useDelimiter(";");
									
						int equipment_number = word.nextInt();
						String name = word.next();
						String description = word.next();
						String date_of_purchased = word.next();
						int purchase_cost = word.nextInt();
						int hire_cost_per_weekend = word.nextInt();
						int hire_cost_per_week = word.nextInt();
						String activity = word.next();
						Boolean returned = Boolean.parseBoolean(word.next());
						
						
						
						Equipment a = new Equipment(equipment_number,name,description,date_of_purchased,purchase_cost,hire_cost_per_weekend, hire_cost_per_week,activity,returned);

						equipments[a.getNumberOfEquipment()-1]=a;
						word.close();
			
					}

					for(int i=0;i<equipments.length;i++) {
						if (equipments[i] == null || equipments[i].equals("")) {
							break;
						}
						else {count+=1;}
						
						
					}
					System.out.println("Loaded "+count+" equipment");
					return;
			    }

				catch (FileNotFoundException e) { //in case equipment.txt is not found the in  same directory as the java files, the user can state the location of the text file
					System.out.println("equipment.txt not found");
					Scanner pathtxt = new Scanner(System.in);
					System.out.println("Please state the file path to equipment.txt (Example = C:\\Users\\USER\\Desktop\\folderexample\\equipment.txt): ");
					String x=pathtxt.nextLine();

		
					x.replace("\\","/");
					x.replace("C:","");
					path = x;

					continue;
				}
	    	}
	    }



	
	    //For getting a equipments array itself 
	public Equipment[] getArray() {return equipments;}
	
	//For getting getting the equipment number by number of 
	public String[] getEquipmentNumberByNumber(int number) {
		String[] result = new String[3];
		for (int i=0;i<equipments.length;i++) {
			if (equipments[i] == null || equipments[i].equals("")) {
				break;
			}
			else if (equipments[i].getEquipmentNumber()==number&&equipments[i].getReturned()==true) {
				result[0]=Integer.toString(equipments[i].getEquipmentNumber());
				result[1]=Integer.toString(equipments[i].getHireCostPerWeekend());
				result[2]=Integer.toString(equipments[i].getHireCostPerWeek());
		
				return result;
			}	
		}
		return result;
		
	}
	
	//For getting a specific equipment according to the equipment number
	public Equipment getEquipmentByNumber(int number) {
		return equipments[number];	
	}
	
	//For getting a loaned equipment by their equipment number
	public Equipment getLoanedEquipmentByNumber(int number) {
		if (equipments[number] == null || equipments[number].equals("")) {
		
			return null;
		} else {
			if (equipments[number].getReturned()==false) { 
				return equipments[number];
				
			} else {
				System.out.println("Invalid equipment number. Please try again.");
			
				return null;
			}
			
		}
	
	}
	
	//Display equipment available for loan
	public void displayEquipmentForLoan() {
		System.out.println("Equipment no, Equipment name, Description Cost weekend, Cost per week");
		for (int i=0;i<equipments.length;i++) {
			if (equipments[i] == null || equipments[i].equals("")) {
				break;
			}
			else if (equipments[i].getReturned() == true) {
				System.out.println(equipments[i].getEquipmentNumber()+", "+equipments[i].getName()
						+", "+equipments[i].getDescription()+", $"+equipments[i].getHireCostPerWeekend()
						+", $"+equipments[i].getHireCostPerWeek());}
			}
		
	}
	
	//Display equipment that already have been loaned
	public void displayLoanedEquipment() {
		System.out.println("Equipment no, Equipment name, Description, Activity, Cost weekend, Cost per week");
		for (int i=0;i<equipments.length;i++) {
			if (equipments[i] == null || equipments[i].equals("")) {
				break;
			}
			else if (equipments[i].getReturned() == false) {
				System.out.println(equipments[i].getEquipmentNumber()+", "+equipments[i].getName()
						+", "+equipments[i].getDescription()+", "+equipments[i].getActivity()+", $"+equipments[i].getHireCostPerWeekend()
						+", $"+equipments[i].getHireCostPerWeek());}
			}
		
	}

	//Returning an equipment according to the specificed equipment number
	public void returnEquipmentByNumber(int equipmentNumber) {
	    for (int i = 0; i < equipments.length; i++) {
	        if (equipments[i] == null || equipments[i].equals("")) {
	            break;
	        } else if (equipments[i].getEquipmentNumber() == equipmentNumber) {
	            equipments[i].setReturned(true); // Mark as returned
	            System.out.println("Equipment " + equipmentNumber + " has been marked as returned.");
	            return;
	        }
	    }
	    System.out.println("Equipment number " + equipmentNumber + " not found.");
	}
	//For showing all equipments available
	public void displayEquipments() {
		for (int i=0;i<equipments.length;i++) {
			if (equipments[i] == null || equipments[i].equals("")) {
				break;
			}
			else {System.out.println(equipments[i].displayEquipmentDetail());}
			
		}
	}
	//This method adds equipment after taking a specified name, description, date of purchased, purchase_cost,hire cost per week,activity and returned.
	public void addEquipment(String name, String description, String date_of_purchased, int purchase_cost, 
			int hire_cost_per_weekend, int hire_cost_per_week, String activity, Boolean returned) {
		Equipment a = new Equipment(equipments[0].getNumberOfEquipment(),name,description,date_of_purchased,purchase_cost,hire_cost_per_weekend, hire_cost_per_week,activity,returned);
		equipments[a.getNumberOfEquipment()-1]=a;
		
	}
	//Display all equipments which matches the scpecified
	public void displayEquipmentByActivity(String activity) {
		System.out.println("Equipment no, Equipment name, Description Cost weekend, Cost per week");
		for (int i=0;i<equipments.length;i++) {
			if (equipments[i] == null || equipments[i].equals("")) {
				break;
			}
			else if ((equipments[i].getActivity()).equalsIgnoreCase(activity)&&equipments[i].getReturned() == true) {
				System.out.println(equipments[i].getEquipmentNumber()+", "+equipments[i].getName()
						+", "+equipments[i].getDescription()+", $"+equipments[i].getHireCostPerWeekend()
						+", $"+equipments[i].getHireCostPerWeek());}
			}
			
		}
		//checks equipment according to activit
	public boolean checkActivityEquipmentNumberMatch(String activity, int equipmentnumber) {
		for (int i=0;i<equipments.length;i++) {
			if (equipments[i] == null || equipments[i].equals("")) {
				break;
			}
			else if (equipments[i].getEquipmentNumber()==equipmentnumber&&(equipments[i].getActivity()).equalsIgnoreCase(activity)) {
				return true;
			}
		}
		return false;	
		
		
	}
	//Method for loaning the equipment by number
	public void loaningEquipmentByNumber(int equipmentnumber) {
		for (int i=0;i<equipments.length;i++) {
			if (equipments[i] == null || equipments[i].equals("")) {
				break;
			}
			else if (equipments[i].getEquipmentNumber()==equipmentnumber) {
				equipments[i].setReturned(false);
				return;
			}
			
			
		}
		System.out.println("Equipment number not found!");
		
	}
	
//Saving array to equipments.txt 
	public void saveEquipments(String txtfile) {
		try {
//			File myFile = new File (txtfile);
//			FileWriter writer= new FileWriter(myFile, false);
			FileWriter writer= new FileWriter(txtfile, false);
			writer.write("");
			writer.close();
			
//			writer = new FileWriter(myFile, true);
			writer= new FileWriter(txtfile, true);
			for (int i=0;i<equipments.length;i++) {
				if (equipments[i] == null || equipments[i].equals("")) {
					break;
				}
				else { writer.write(equipments[i].toString()+"\n");}
			}
			
			writer.close();
			System.out.println("Equipments saved");
		}
		
		catch (IOException e) {
			System.out.println("An error has occured");
			e.printStackTrace();
			return;
		}
		

	}
}






