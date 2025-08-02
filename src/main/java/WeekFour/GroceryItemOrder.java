package WeekFour;


// This class stores information about a single grocery item being ordered.

public class GroceryItemOrder 
{
    private String name;
    private int quantity;
    private double pricePerUnit;

    public GroceryItemOrder(String name, int quantity, double pricePerUnit) 
    {
        this.name = name;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
    }

    public double getCost() 
    {
        return quantity * pricePerUnit;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public String toString() 
    {
        return quantity + " of " + name;
    }
    
    // Joe - Added this method
    public String getItemName()
    {
       return this.name;	
    }	
    	
}
