package FInal_project;

public class Inventory {
    private Item head;  // Start of the linked list

    // Add item to the inventory
    public void addItem(String itemName) {
        Item newItem = new Item(itemName);
        if (head == null) {
            head = newItem;
        } else {
            Item current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newItem;
        }
        System.out.println(itemName + " has been added to your inventory.");
    }

    // Display items in the inventory
    public void displayItems() {
        if (head == null) {
            System.out.println("Your inventory is empty.");
            return;
        }
        System.out.println("Inventory:");
        Item current = head;
        while (current != null) {
            System.out.println("- " + current.name);
            current = current.next;
        }
    }

    // Use an item from the inventory
    public void useItem(String itemName, Player player) {
        if (itemName == itemName.toUpperCase() || itemName.isEmpty()) {
            System.out.println("Invalid item name. Please select a valid item.");
            return;
        }

        if (head == null) {
            System.out.println("Your inventory is empty.");
            return;
        }

        if (head.name.equals(itemName)) {
            System.out.println("You used " + itemName + ".");
            equipOrRemove(itemName, player);  // Equip if weapon, remove from inventory
            head = head.next;  // Remove the item from inventory
            return;
        }

        Item current = head;
        while (current.next != null) {
            if (current.next.name.equals(itemName)) {
                System.out.println("You used " + itemName + ".");
                equipOrRemove(itemName, player);  // Equip if weapon, remove from inventory
                current.next = current.next.next;  // Remove the item from inventory
                return;
            }
            current = current.next;
        }

        System.out.println(itemName + " is in your inventory.");
    }

    // Equip the weapon or remove item from inventory
    private void equipOrRemove(String itemName, Player player) {
        if (itemName.equalsIgnoreCase("Sharp Knife")) {
            Weapon sharpKnife = new Weapon("Sharp Knife", 30);  // Example damage value
            player.equipWeapon(sharpKnife);  // Equip the weapon to the player
        } else if (itemName.equalsIgnoreCase("Sharp Piece of Wood")) {
            Weapon sharpWood = new Weapon("Sharp Piece of Wood", 10);  // Example damage value
            player.equipWeapon(sharpWood);  // Equip the weapon to the player
        } 
        }
    }

