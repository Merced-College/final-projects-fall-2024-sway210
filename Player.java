package FInal_project;

public class Player {
    private String name;
    private int health;
    private Weapon equippedWeapon;  // Now this is a Weapon

    // Constructor
    public Player(String name, int health) {
        this.name = name;
        this.health = health;
        this.equippedWeapon = null;  // No weapon equipped by default
    }

    // Getter for health
    public int getHealth() {
        return health;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for health
    public void setHealth(int health) {
        this.health = health;
    }

    // Method to equip a weapon
    public void equipWeapon(Weapon weapon) {
        this.equippedWeapon = weapon;
        System.out.println("You have equipped the " + weapon.getName() + "!");
    }

    // Getter for equipped weapon
    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    // Method to attack with the equipped weapon
    public void attack(Enemy enemy) {
        if (equippedWeapon != null) {
            System.out.println(name + " attacks " + enemy.getName() + " with " + equippedWeapon.getName() + " for " + equippedWeapon.getDamage() + " damage.");
            enemy.takeDamage(equippedWeapon.getDamage());
        } else {
            System.out.println(name + " has no weapon equipped!");
        }
    }
}

