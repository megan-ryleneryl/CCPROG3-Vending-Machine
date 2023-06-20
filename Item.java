public class Item {
    private String name;
    private double calories;
    private boolean standalone = false;
    private double price;
    private int stock;

    public String getName() {
        return name;
    }

    public double getCalories() {
        return calories;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setPrice(double newPrice) {
        this.price = newPrice;   
    }

    public boolean addStock(Item item, int amountToAdd) {
        if (this.stock + amountToAdd > 10)
            return false;
        else
        {
            this.stock += amountToAdd;
            return true;
        }
    }

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