package FInal_project;

public class Weapon extends Item {
    private int damage;

    // Constructor
    public Weapon(String name, int damage) {
        super(name);  // Call the constructor of the Item class
        this.damage = damage;
    }

    
    public int getDamage() {
        return damage;
    }
}