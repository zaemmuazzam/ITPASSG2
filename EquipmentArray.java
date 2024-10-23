import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
public class EquipmentArray {
	private Equipment[] equipments;
		
	
	public EquipmentArray(String txtfile) {

		Equipment.setNumberOfEquipment(0);
		equipments = new Equipment[300];
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
	
	public void displayEquipments() {
		for (int i=0;i<equipments.length;i++) {
			if (equipments[i] == null || equipments[i].equals("")) {
				break;
			}
			else {System.out.println(equipments[i].displayEquipment());}
			
		}
	}
	
	public void addEquipment(String name, String description, String date_of_purchased, int purchase_cost, 
			int hire_cost_per_weekend, int hire_cost_per_week, String activity, Boolean returned) {
		Equipment a = new Equipment(equipments[0].getNumberOfEquipment()+1,name,description,date_of_purchased,purchase_cost,hire_cost_per_weekend, hire_cost_per_week,activity,returned);
		equipments[a.getNumberOfEquipment()-1]=a;
		
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


}
