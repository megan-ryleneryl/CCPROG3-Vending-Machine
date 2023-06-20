import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

public class VendingMachine {
    private String machineName;
    private boolean isSpecial;
    private ArrayList<Item> itemList;
	private int cashIn;
	private Item selectedItem;
	
	public VendingMachine(String machineName, boolean isSpecial) {
		this.machineName = machineName;
		this.isSpecial = isSpecial;
		this.cashIn = 0;
		this.selectedItem = null;
		itemList = new ArrayList<Item>();
	}
    
	public void displayMenu(VendingMachine vm) {
		int userChoice = 0;
		Scanner input = new Scanner(System.in);
		
		do {
			System.out.print("Welcome to The Founding Fathers' Vending Pantry!\n" + 
							 "(1) Build a Vending Machine\n" +
							 "(2) Test a Vending Machine\n" +
							 "(3) Leave and Exit" +
							 "Select: ");
			userChoice = input.nextInt();
			
			//Create VM
			if(userChoice == 1) {
				createMenu();
			}
			
			//Test or Maintain
			else if(userChoice == 2) {
				int menuChoice = 0;
				
				do {
					System.out.print("==============================" +
								 "Welcome to The Founding Fathers' Vending Pantry!\n" + 
								 "(1) Test Current Vending Machine Features\n" +
								 "(2) Perform Maintenance Features\n" +
								 "(3) Return to Main Menu" +
								 "Select: ");
					
					//Test
					if(menuChoice == 1) {
						testMenu();
					}
					
					//Maintain
					else if(menuChoice == 2) {
						maintainMenu();
					}
					
					//Return to main menu
					else if(menuChoice == 3) {
						System.out.println("Returning to Main Menu...");
						System.out.println("==============================");
					}
					
					//Error catch
					else {
						System.out.println("Invalid input. Please try again.");
						userChoice = 0;
					}
					
				} while (menuChoice != 3);
				
			}
			
			//Exit
			else if(userChoice == 3) {
				System.out.println("Thank you for coming!\n" +
								   "Exiting program...");
			}
			
			//Error catch
			else {
				System.out.println("Invalid input. Please try again.");
				System.out.println("==============================");
				userChoice = 0;
			}
			
		} while(userChoice != 3);
		
		input.close();
	}

    public void createMenu(){
        System.out.println("Do you want to obliterate this Vending Machine and create a new one?\n" + "Y/N");
		Scanner s = new Scanner(System.in);
		char c = s.next().charAt(0);
		Character.toLowerCase(c);
		
		//for clearing
		if(c == 'y') {
			//clear all vmHistory.txt data
			//@renzo I need help :">
			
			this.clearItemList();
			this.setCashIn(0);
			this.setSelectedItem(null);
			
			System.out.println("Name your Vending Machine: ");
			String name = s.nextLine();
			System.out.println("Great! Do you want " + name + " to be a Special Vending Machine?" +
							   "Y/N: ");
			c = s.next().charAt(0);
			Character.toLowerCase(c);
			
			boolean isSpecial = false;
			
			if(c == 'y')
				isSpecial = true;
				
			this.setMachineName(name);
			this.setIsSpecial(isSpecial);
		}
		
		s.close();
    }

    public void testMenu() {
		/* display all items and ask for an item number including input validation to see if there's stock, 
		input money amount or cancel, 
		dispense product with 1 line narration and update stock and add to transac history, 
		dispense product with 1 line narration, 
		give change and update money, 
		display receipt and calories
		*/
		
    }

    public void maintainMenu() {
		/* restock (pick item number, pick qty to add, display feedback message), 
		take money out (ask how much, update money, add to money out history), 
		change item details (pick item number, pick what to change, input changed, validate, display feedback msg)
		*/
		
    }

	public String getMachineName() {
		return this.machineName;
	}
	
	public boolean getIsSpecial() {
		return this.isSpecial;
	}
	
	public ArrayList<Item> getItemList() {
		return this.itemList;
	}
	
	public int getCashIn() {
		return this.cashIn;
	}
	
	public Item getSelectedItem() {
		return this.selectedItem;
	}
	
	public void setMachineName(String newName) {
		this.machineName = newName;
	}
	
	public void setIsSpecial(boolean newIsSpecial) {
		this.isSpecial = newIsSpecial;
	}
	
	public void clearItemList() {
		this.itemList.clear();
	}
	
	public boolean addItem(Item item) {
		boolean result = false;
		
		if(this.itemList.add(item))
			result = true;
		
		return result;
	}
	
	public void setCashIn(int newCashIn) {
		this.cashIn = newCashIn;
	}
	
	public void setSelectedItem(Item newSelectedItem) {
		this.selectedItem = newSelectedItem;
	}
	
	public static void main(String[] args) {
		VendingMachine vm = null;
		boolean exists = false;
		File vmHistory = new File("VM-History.txt");
		
		try {
			if(vmHistory.createNewFile())
				System.out.println("No previous Vending Machine exists yet.");
				
			else {
				exists = true;
				System.out.println("A previous Vending Machine already exists.");
			}
				
		} catch (IOException e) {
			System.out.println("Oops! An error occurred.");
			e.printStackTrace();
		}
		
		if(exists == true) {
			System.out.println("Loading Vending Machine history...");
			
			try {
				Scanner s = new Scanner(vmHistory);
				ArrayList<String> history = new ArrayList<>();
			
				while(s.hasNextLine()) {
				String line = s.nextLine();
				history.add(line);
				}
				s.close();
			
				//vm = new VendingMachine(...)
				//@renzo we need to figure out how vm will be initialized if history exists!
			
				System.out.println("Done!");
			} catch (FileNotFoundException e) {
				System.out.println("Oops! An error occurred.");
				e.printStackTrace();
			}
			
		}
		
		else {
			Scanner s = new Scanner(System.in);
			System.out.println("Let's initialize a Vending Machine first.\n" +
							   "Name your Vending Machine: ");
			String name = s.nextLine();
			System.out.println("Great! Do you want " + name + " to be a Special Vending Machine?" +
							   "Y/N: ");
			char c = s.next().charAt(0);
			Character.toLowerCase(c);
			
			boolean isSpecial = false;
			
			if(c == 'y')
				isSpecial = true;
				
			vm = new VendingMachine(name, isSpecial);
			
			s.close();
		}
		
		vm.displayMenu(vm);
	}
}
