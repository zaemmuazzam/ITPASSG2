
public class Equipment {
	private String name, description, date_of_purchased,activity;
	private int equipment_number, purchase_cost, hire_cost_per_weekend, hire_cost_per_week;
	private Boolean returned;
	private static int numberofequipment=0;// for keeping track of records loaded
	private Boolean isLoaned;
	
	//each record of equipment.txt is saved as an member object called Equipment
	public Equipment(int equipment_number, String name, String description, String date_of_purchased, int purchase_cost, 
			int hire_cost_per_weekend, int hire_cost_per_week, String activity, Boolean returned) {
		this.equipment_number=equipment_number;
		this.name=name;
		this.description=description;
		this.date_of_purchased=date_of_purchased;
		this.purchase_cost=purchase_cost;
		this.hire_cost_per_weekend=hire_cost_per_weekend;
		this.hire_cost_per_week=hire_cost_per_week;
		this.activity=activity;
		this.returned=returned;
		
		numberofequipment++;
	}
	
	
	
	//Getters
	public String getName() {return name;}
	public String getDescription() {return description;}
	public String getDateOfPurchased() {return date_of_purchased;}
	public String getActivity() {return activity;}
	public int getEquipmentNumber() {return equipment_number;}
	public int getPurchaseCost() {return purchase_cost;}
	public int getHireCostPerWeekend() {return hire_cost_per_weekend;}
	public int getHireCostPerWeek() {return hire_cost_per_week;}
	public Boolean getReturned() {return returned;}
	public int getNumberOfEquipment() {return numberofequipment;}

	// Setters 
	public void setName(String name) {this.name = name;}
	public void setDescription(String description) {this.description = description;}
	public void setDateOfPurchased(String date_of_purchased) {this.date_of_purchased = date_of_purchased;}
	public void setActivity(String activity) {this.activity = activity;}
	public void setEquipmentNumber(int equipment_number) {this.equipment_number = equipment_number;}
	public void setPurchaseCost(int purchase_cost) {this.purchase_cost = purchase_cost;}
	public void setHireCostPerWeekend(int hire_cost_per_weekend) {this.hire_cost_per_weekend = hire_cost_per_weekend;}
	public void setHireCostPerWeek(int hire_cost_per_week) {this.hire_cost_per_week = hire_cost_per_week;}
	public void setReturned(Boolean returned) {this.returned = returned;}
	public static void setNumberOfEquipment(int value) {numberofequipment=value;}
	public void setLoaned(boolean loaned) {
	    this.isLoaned = !loaned;
	}
	
	//for showing the equipment record spaced and saparated by ,
	public String displayEquipmentDetail() {return equipment_number+ ", " + name+ ", " + description + ", " + date_of_purchased + ", " + purchase_cost + ", " 
			+ hire_cost_per_weekend + ", " + hire_cost_per_week + ", " + activity + ", " + returned;}
	//for showing what the equipment record looks like in the txt files
	public String toString() { return equipment_number+ ";" + name+ ";" + description + ";" + date_of_purchased + ";" + purchase_cost + ";" 
			+ hire_cost_per_weekend + ";" + hire_cost_per_week + ";" + activity + ";" + returned;}
	

}
