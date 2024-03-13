import java.util.Scanner;

class Item {
    int itemId;
    String itemName;
    float price;

    Item(int id, String name, float price) {
        this.itemId = id;
        this.itemName = name;
        this.price = price;
    }

    void display() {
        System.out.println("Item ID: " + itemId + "\tItem Name: " + itemName + "\tPrice: " + price);
    }
}

interface Customer {
    void showItems(Item[] items);

    void addToCart(Item[] items);
}

class Shop {
    static Scanner scanner = new Scanner(System.in);

    static void showItems(Item[] items) {
        System.out.println("Items Available:");
        for (Item item : items) {
            if (item != null) {
                item.display();
            }
        }
    }

    static void addItem(Item[] items, int id) {
        System.out.println("Enter the item name:");
        String name = scanner.next();
        System.out.println("Enter the price:");
        float price = scanner.nextFloat();
        items[id] = new Item(id, name, price);
        System.out.println("Item added successfully.");
    }

    static void deleteItem(Item[] items, int id) {

        if (items[id] != null) {
            items[id] = null;
            System.out.println("Item deleted successfully.");
            showItems(items);
        } else {
            System.out.println("No item found with the given ID.");
        }
    }

    static void start(Item[] items) {
        while (true) {
            System.out.println("\nWelcome to GCET Online Store\n1. Shopkeeper\n2. Customer\n3. Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    shopkeeperOptions(items);
                    break;
                case 2:
                    customerOptions(items);
                    break;
                case 3:
                    System.out.println("Thank you. Visit again!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    static void shopkeeperOptions(Item[] items) {
        while (true) {
            System.out.println("\nShopkeeper Options\n1. Add Item\n2. Delete Item\n3. Main Menu");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    for (int i = 0; i < items.length; i++) {
                        if (items[i] == null) {
                            addItem(items, i);
                            break;
                        }
                    }
                    break;
                case 2:
                    showItems(items);
                    System.out.println("Enter the ID of the item to delete:");
                    int id = scanner.nextInt();
                    deleteItem(items, id);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    static void customerOptions(Item[] items) {
        while (true) {
            System.out.println("\nCustomer Options\n1. Show All Items\n2. Buy Items\n3. Main Menu");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    showItems(items);
                    break;
                case 2:
                    buyItems(items);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    static void buyItems(Item[] items) {
        float total = 0;
        boolean found = false;
        System.out.println("Enter the item IDs you want to buy (Enter -1 to stop):");
        while (true) {
            int itemId = scanner.nextInt();
            if (itemId == -1) {
                break;
            }
            for (Item item : items) {
                if (item != null && item.itemId == itemId) {
                    found = true;
                    total += item.price;
                    System.out.println("Item added to cart: " + item.itemName + " - $" + item.price);
                    break;
                }
            }
            if (!found) {
                System.out.println("Item with ID " + itemId + " not found.");
            }
        }
        System.out.println("Total amount: $" + total);
    }

    public static void main(String[] args) {
        final int MAX_ITEMS = 10;
        Item[] items = new Item[MAX_ITEMS];
        start(items);
    }
}
