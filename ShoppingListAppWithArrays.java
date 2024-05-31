import java.util.Scanner;
import java.util.Arrays;

// Task: Create a shopping list app (using arrays),
// where you can add and remove items, print and delete the list.

public class ShoppingListAppWithArrays {

    private static String[] items = {"apples", "bananas", "cheese", "nuts", null};
    private static int[] quantities = {3, 5, 2, 8, 0};
    private static Scanner console = new Scanner(System.in);
    private static boolean notExit = true;
    private static int itemCount = 0;

    public static void main(String[] args) {
        System.out.println("*****\nThis is your shopping list app.\n*****");
        itemCount = listItemCount();
        while (notExit) {
            listMenu();
        }
    }

    public static void listMenu() {
        System.out.println("Choose your action by typing the number:");
        System.out.println("1 - Add item (5 items max)");
        System.out.println("2 - Remove item");
        System.out.println("3 – Print current list");
        System.out.println("4 – Delete list");
        System.out.println("5 – Exit app");
        try {
            int action = Integer.parseInt(console.nextLine());

            switch (action) {
                case 1:
                    addItem();
                    break;
                case 2:
                    removeItem();
                    break;
                case 3:
                    printList();
                    break;
                case 4:
                    deleteList();
                    break;
                case 5:
                    System.out.println("You closed the app. Goodbye.");
                    notExit = false;
            }
        } catch (
                NumberFormatException e) {
            System.out.println("Please type a number from 1 to 5.");
        }
    }

    public static void addItem() {
        if (itemCount < 5) {
            System.out.println("Add item: Type in a quantity and the item (e.g. \"3 avocados\").");
            try {
                String input = console.nextLine().trim();
                int quantity = Integer.parseInt(input.substring(0, input.indexOf(' ')));
                String item = input.substring(input.indexOf(' ') + 1);
                for (int i = 0; i < items.length; i++) {
                    if (items[i] != null && items[i].equals(item)) {
                        System.out.println("The item is already on the list.");
                        System.out.printf("Do you want to update the quantity from %d to %d? (y/n)\n", quantities[i], quantity);
                        char updateDecision = console.nextLine().charAt(0);
                        if (updateDecision == 'y') {
                            quantities[i] = quantity;
                            System.out.println("The quantity was updated.");
                        }
                        return;
                    } else if (items[i] == null) {
                        items[i] = item;
                        quantities[i] = quantity;
                        break;
                    }
                }
                System.out.printf("%d %s was added.\n", quantity, item);
                itemCount++;
            } catch (RuntimeException e) {
                System.out.println("Sorry, I couldn't read your input. The item could not be added.");
            }
        } else {
            System.out.println("The list is full. Remove other items first.");
        }
    }

    public static void removeItem() {
        if (itemCount > 0) {
            printList();
            System.out.println("Remove item: Type in the name of the item to remove.");
            String itemToRemove = console.nextLine().trim().toLowerCase();
            boolean itemFound = false;
            for (int i = 0; i < items.length; i++) {
                if (items[i] == null) {
                    break;
                }
                if (items[i].toLowerCase().equals(itemToRemove)) {
                    for (int j = i + 1; j < items.length; j++) {
                        items[j - 1] = items[j];
                        quantities[j - 1] = quantities[j];
                        if (j == items.length - 1) {
                            items[j] = null;
                            quantities[j] = 0;
                        }
                    }
                    itemFound = true;
                    break;
                }
            }
            if (itemFound) {
                System.out.printf("The %s item was removed from the list.\n", itemToRemove);
                itemCount--;
                printList();
            } else {
                System.out.printf("The item %s wasn't on the list. \n", itemToRemove);
            }
        } else {
            System.out.println("The list is empty. Add more items.");
        }
    }

    public static void printList() {
        if (itemCount > 0) {
            System.out.println("These items are on the list:");
            System.out.println("-----------------");
            for (int i = 0; i < items.length; i++) {
                if (items[i] != null) {
                    System.out.printf("%s %dx \n", items[i], quantities[i]);
                } else {
                    break;
                }
            }
            System.out.println("-----------------");
        } else {
            System.out.println("The list is empty.");
        }
    }

    public static void deleteList() {
        if (listItemCount() > 0) {
            System.out.println("Do you really want to delete all items? (y/n)");
            char deleteDecision = Character.toLowerCase(console.nextLine().charAt(0));
            if (deleteDecision == 'y') {
                Arrays.fill(items, null);
                Arrays.fill(quantities, 0);
                System.out.println("The list was deleted.");
                itemCount = 0;
            } else {
                System.out.println("Nothing was deleted.");
            }
        } else {
            System.out.println("The list is already empty.");
        }
    }

    public static int listItemCount() {
        int itemCount = 0;
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null || i == items.length - 1) {
                itemCount = i;
                break;
            }
        }
        return itemCount;
    }
}
