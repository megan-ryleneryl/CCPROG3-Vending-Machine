import java.util.Scanner;

public class MoneyHandler {
	private int[][] cashBox = {
	  {1000, 0},
	  {500, 0},
	  {200, 0},
	  {100, 0},
	  {50, 0},
	  {10, 0},
	  {5, 0},
	  {1, 0}
	};
	
	private int[][] holder = {
	  {1000, 0},
	  {500, 0},
	  {200, 0},
	  {100, 0},
	  {50, 0},
	  {10, 0},
	  {5, 0},
	  {1, 0}
	};
	
	private int[][] changeArray = {
	  {1000, 0},
	  {500, 0},
	  {200, 0},
	  {100, 0},
	  {50, 0},
	  {10, 0},
	  {5, 0},
	  {1, 0}
	};
	
	public MoneyHandler() {
		//initialize cashBox -> @megan needs to update with file
		int amount = 10;
		
		for(int i = 0; i < this.cashBox.length; i++) {
			this.cashBox[i][1] = amount;
			amount += 15;
		}
		
		this.displayDenomList();
	}
	
	public void displayDenomList() {
		for(int i = 0; i < this.cashBox.length; i++) {
			System.out.println("P" + this.cashBox[i][0] + ": " + this.cashBox[i][1] + " stored.");
		}
	}
	
	public void inputDenominations(Scanner s) {
		
	}
	
	public void cashOut() {
		for(int i = 0; i < this.cashBox.length; i++) {
			this.cashBox[i][1] = 0;
		}
	}
	
	public boolean cashOne(int denomination, int numOfDenom) {
		boolean success = false;
		
		int row = 99;
		
		for(int i = 0; i < this.cashBox.length; i++) {
			if(this.cashBox[i][0] == denomination) {
				row = i;
				success = true;
			}
		}
		
		if(success) {
			if(numOfDenom > this.cashBox[row][1])
				success = false;
			
			else
				this.cashBox[row][1] -= numOfDenom;
		}
		
		return success;
	}
	
	public void refillOne(int denomination, int denomAdd) {
		switch(denomination){
            case 1000 -> cashBox[0][1] += denomAdd;
            case 500 -> cashBox[1][1] += denomAdd;
            case 200 -> cashBox[2][1] += denomAdd;
            case 100 -> cashBox[3][1] += denomAdd;
            case 50 -> cashBox[4][1] += denomAdd;
            case 20 -> cashBox[5][1] += denomAdd;
            case 10 -> cashBox[6][1] += denomAdd;
            case 5 -> cashBox[7][1] += denomAdd;
            case 1 -> cashBox[8][1] += denomAdd;
        }
	}
	
	public boolean payment(Item selectedItem, Scanner s) {
		boolean releaseAll = false;	
		boolean enoughStock = true;
		boolean success = false;
		char c = '\0';
		int cashIn = this.getCashIn();
		int price = selectedItem.getPrice();
		int change = cashIn - price;
		int i = 0;
		
		System.out.print("Proceed with transaction? Type Y to proceed and N to cancel. Y/N\n" + "Input: ");
		
		//gets input
		if(s.hasNextLine()) {
			c = s.next().charAt(0);
			c = Character.toLowerCase(c);
			s.nextLine();
		}
		
		//payment procedures
		if(c == 'y') {
			//if the user gave enough
			if(change >= 0) {
				//transfer all holder money into cashBox
				System.out.println("Loading money into the cashbox...");
				for(i = 0; i < holder.length; i++) {
					cashBox[i][1] += holder[i][1];
					holder[i][1] = 0;
				}
				
				//break down change
				this.breakdownChange(change);
				
				//check enough
				enoughStock = checkChange();
				
				//if enough
				if(enoughStock) {
					//load money from cashBox to change
					System.out.println("Getting your change ready...");
					for(i = 0; i < changeArray.length; i++) {
						if(changeArray[i][1] > 0) 
							cashBox[i][1] -= changeArray[i][1];
					}
					
					//empty change array
					System.out.println("Ka-ching! Change has been dispensed.");
					for(i = 0; i < changeArray.length; i++) {
						changeArray[i][1] = 0;
					}
					
					success = true;
				}
				
				//not enough stock
				else {
					System.out.println("Oh no! This Vending Machine doesn't have enough change.");
					releaseAll = true;
				}
			}
			
			//user didn't give enough
			else {
				System.out.println("Oops! Not enough money was inserted.");
				releaseAll = true;
			}
		}
		
		//the user wants to cancel
		else 
			releaseAll = true;
		
		//if anything failed
		if(releaseAll) {
			System.out.println("Canceling transaction...");
			System.out.println("Releasing full change...");
			for(i = 0; i < holder.length; i++) 
				holder[i][1] = 0;
		}
		
		return success;
	}
	
	public void breakdownChange(int change) {
		System.out.println("Breaking down change...");
		int numBills = 0;
		
		for(int i = 0; i < changeArray.length; i++) {
			numBills = change / changeArray[i][0];
			changeArray[i][1] += numBills;
			change %= changeArray[i][0];
		}
	}
	
	public boolean checkChange() {
		System.out.println("Checking if there are enough bills to give change...");
		boolean enoughChange = true;
		
		for(int i = 0; i < changeArray.length; i++) {
			if(cashBox[i][1] - changeArray[i][1] < 0)
				enoughChange = false;
		}

		return enoughChange;
	}
	
	//sum of cashBox
	public int getTotal() {
		int total = 0;
		
		for(int i = 0; i < cashBox.length; i++)
			total += (cashBox[i][0] * cashBox[i][1]);
		
		return total;
	}
	
	//sum of holder
	public int getCashIn() {
		int total = 0;
		
		for(int i = 0; i < holder.length; i++)
			total += (holder[i][0] * holder[i][1]);
		
		return total;
	}
}