/*MAIN TASKS: stores attributes of each item
 *			: getters and setters
 */

/** 
  * Stores and operates on item details like name, calories, standalone status, price, and stock.
  */
public class Item {
    private final String name;
    private final double calories;
    private final boolean standalone;
    private int price;
    private int stock;

	/** 
      * A constructor that creates an item instance given complete attributes.
	  * @param name 		The item's name
	  * @param calories		The decimal amount of calories the item contains
	  * @param standalone	Whether or not the item can be sold alone
	  * @param price		The cost of the item
	  * @param stock 		The quantity of the item available for sale
      */
    public Item(String name, double calories, boolean standalone, int price, int stock){ 
        this.name = name;
        this.calories = calories;
		this.standalone = standalone;
        this.price = price;
        this.stock = stock;
    }

	/** 
      * Gets and returns the item's name.
	  * @return The item's name
      */
    public String getName(){
        return name;
    }

	/** 
      * Gets and returns the item's calorie measurement.
	  * @return The item's decimal calorie measurement
      */
    public double getCalories(){
        return calories;
    }

	/** 
      * Gets and returns the item's standalone status.
	  * @return Whether or not the item can be sold alone
      */
    public boolean getStandalone(){
        return standalone;
    }

	/** 
      * Gets and returns the item's price.
	  * @return The item's price
      */
    public int getPrice(){
        return price;
    }

	/** 
      * Gets and returns the item's stock.
	  * @return The item's quantity available for sale
      */
    public int getStock(){
        return stock;
    }

	/** 
      * Changes the item's current price to a new amount
	  * @param newPrice The updated cost of the item
      */
    public void setPrice(int newPrice){
        this.price = newPrice;   
    }

	/** 
      * Increases the item's current stock by an amount and returns its success
	  * @param item			The item object to add stock to
	  * @param amountToAdd	The amount of stock to be added
	  * @return Whether the program successfully added the stock
      */
    public boolean addStock(Item item, int amountToAdd){
        if (this.stock + amountToAdd > 10)
            return false;
        else
        {
            this.stock += amountToAdd;
            return true;
        }
    }

	/** 
      * Reduces the item's current stock by an amount and returns its success
	  * @param item				The item object to remove stock from
	  * @param amountToMinus	The amount of stock to be subtracted
	  * @return Whether the program successfully reduced the stock
      */
    public boolean minusStock(Item item, int amountToMinus) {
        if (this.stock - amountToMinus < 0)
            return false;
        else
        {
            this.stock -= amountToMinus;
            return true;
        }
    }
}