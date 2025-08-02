package WeekFour;
// PA4 is a method to connect GroceryItemOrder and GroceryList to the
// main method to add items into an array and print final cost
// Created by Aman Sarosh

public class PA4 {
    public static void main(String[] args) {
        // Make list and add
        GroceryItemOrder banana = new GroceryItemOrder("Banana", 5, 1.99);
        GroceryItemOrder bread  = new GroceryItemOrder("Bread",  2, 2.39);
        GroceryItemOrder orange   = new GroceryItemOrder("Orange",   1, 0.99);

        GroceryList list = new GroceryList();
        list.addItem(banana);
        list.addItem(bread);
        list.addItem(orange);

        // Print out total
        System.out.println("Grocery List:");
        list.displayItems();
        System.out.println("-");
        System.out.printf("Total: $%.2f%n", list.getTotalCost());
    }
}
