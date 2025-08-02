package WeekFour;
// File edited to make writing PA4 Easier

public class GroceryList {
    public static final int MAX_ITEMS = 10;

    private GroceryItemOrder[] items;
    private int numItems;

    public GroceryList() {
        items = new GroceryItemOrder[MAX_ITEMS];
        numItems = 0;
    }

    public void addItem(GroceryItemOrder item) {
        if (numItems < MAX_ITEMS) {
            items[numItems++] = item;
        }
    }

    public double getTotalCost() {
        double total = 0;
        for (int i = 0; i < numItems; i++) {
            total += items[i].getCost();
        }
        return total;
    }

    public void displayItems() {
        for (int i = 0; i < numItems; i++) {
            System.out.printf("%s: $%.2f%n",
                    items[i].getItemName(),
                    items[i].getCost());
        }
    }
}
