import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class EquipmentArray {
	private Equipment[] equipments;
	private ArrayList<Equipment> equipmentList;
	
	    public EquipmentArray(String txtfile) {

		Equipment.setNumberOfEquipment(0);
		equipments = new Equipment[300];
		equipmentList = new ArrayList<>();
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
								
					int equipment_number = word.nextInt();
					String name = word.next();
					String description = word.next();
					String date_of_purchased = word.next();
					int purchase_cost = word.nextInt();
					int hire_cost_per_weekend = word.nextInt();
					int hire_cost_per_week = word.nextInt();
					String activity = word.next();
					Boolean returned = Boolean.parseBoolean(word.next());
					
					
//					System.out.println(equipment_number);
//					System.out.println(name);
//					System.out.println(description);
//					System.out.println(date_of_purchased);
//					System.out.println(purchase_cost);
//					System.out.println(hire_cost_per_weekend);
//					System.out.println(hire_cost_per_week);
//					System.out.println(activity);
//					System.out.println(returned);
					
					
					Equipment a = new Equipment(equipment_number,name,description,date_of_purchased,purchase_cost,hire_cost_per_weekend, hire_cost_per_week,activity,returned);
					//System.out.println(a.getNumberOfEquipment());
					equipments[a.getNumberOfEquipment()-1]=a;
					equipmentList.add(a);
					word.close();
					
					//System.out.println(data);
				//}
			}
			input.close();
			
			for(int i=0;i<equipments.length;i++) {
				if (equipments[i] == null || equipments[i].equals("")) {
					break;
				}
				else {count+=1;}
				
				
			}
			System.out.println("Loaded "+count+" equipment");
			

		}
		catch (FileNotFoundException e) {
		System.out.println("An error has occured");
		e.printStackTrace();
		}

		
	}
	
	public Equipment[] getArray() {return equipments;}
	
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
	
	public Equipment getEquipmentByNumber(int number) {
		return equipments[number];	
	}
	
	
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
	
	public void displayEquipments() {
		for (int i=0;i<equipments.length;i++) {
			if (equipments[i] == null || equipments[i].equals("")) {
				break;
			}
			else {System.out.println(equipments[i].displayEquipmentDetail());}
			
		}
	}
	
	public void addEquipment(String name, String description, String date_of_purchased, int purchase_cost, 
			int hire_cost_per_weekend, int hire_cost_per_week, String activity, Boolean returned) {
		Equipment a = new Equipment(equipments[0].getNumberOfEquipment()+1,name,description,date_of_purchased,purchase_cost,hire_cost_per_weekend, hire_cost_per_week,activity,returned);
		equipments[a.getNumberOfEquipment()-1]=a;
		
	}
	
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
		}
		
		catch (IOException e) {
			System.out.println("An error has occured");
			e.printStackTrace();
			return;
		}
		

	}




public Equipment findLoanedEquipmentByNameOrNumber(String nameOrNumber) {
    for (Equipment equipment : equipmentList) {
        if (equipment.getName().equalsIgnoreCase(nameOrNumber) || 
            String.valueOf(equipment.getEquipmentNumber()).equals(nameOrNumber)) {
            return equipment;
        }
    }
    return null; // Return null if no matching equipment is found
}

// Method to add equipment to the list (for testing and usage purposes)
public void addEquipment(Equipment equipment) {
    equipmentList.add(equipment);
}
}


