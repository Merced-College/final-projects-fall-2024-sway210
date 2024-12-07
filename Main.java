/* Jose Arellano
 * Final project 
 * 12/6/24
 * cpsc-39
 */




package FInal_project;

import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final String itemToEquip = null;
	static Inventory inventory = new Inventory();

    public static void main(String[] args) {
        

        Scanner scanner = new Scanner(System.in);
        System.out.println("Ready to play? (Y/N)");
        String response = scanner.next().toLowerCase();

        if (response.equals("y")) {
            System.out.println("Alright, then enjoy the game!!");
        } else {
            System.out.println("Till next time then?");
            scanner.close();
            return;
        }

        // Ask for and capture the player's name
        scanner.nextLine(); // Consume the newline left by next()
        System.out.println("Please enter a name before you start.");
        String PlayerName = scanner.nextLine().trim();

        Player player = new Player(PlayerName, 100); // Create the player object with 100 health

        // Beginning of the game
        System.out.println(player.getName() + " finds themselves running from a mysterious figure...");
        System.out.println("While " + player.getName() + " is running away, they come across a building...");

        // Choice to enter the building
        System.out.println("Do you want to enter the building? (Y/N)");
        String Response_1 = scanner.next().toUpperCase();

        if (Response_1.equals("Y")) {
            System.out.println("You enter the building");
        } else {
            System.out.println("Mysterious figure catches up to you and kills you....");
            System.out.println("Game over...");
            scanner.close();
            return;
           
        }

        // Calling Rooms.java - room descriptions
        Rooms room1 = new Rooms("You find yourself in a dimly lit room, the air cold and musty...");
        Rooms room2 = new Rooms("Another room, this time it gave off a more eerie vibe then the last one..");
        Rooms room3 = new Rooms("This is the last room.. ");
        
        
        
        // Start the game in room1
        System.out.println(room1.getDescription());
        scanner.nextLine(); // Pause before continuing

        System.out.println("You also realize you are not alone...");
        
        // Defining the enemies
        Enemy[] enemies = new Enemy[3];
        enemies[0] = new Enemy("Watcher", 150);   // Watcher with 100 health
        enemies[1] = new Enemy("Jumper", 60);     // Jumper with 60 health
        enemies[2] = new Enemy("Rabid Rodent", 40); // Rodent with 40 health
       

        System.out.println("You hear quick steps approaching you...");
        System.out.println("A " + enemies[2].getName() + " attacks you.");
        System.out.println(enemies[2].getName() + " doesn't want to let go.");
        scanner.nextLine(); // Pause before continuing

        System.out.println("You must fight back... (You tell yourself)");
        scanner.nextLine();

        System.out.println("While you are getting attacked, you find a sharp piece of wood next to you."); 
        inventory.addItem("Sharp Piece of Wood");
        
        scanner.nextLine();

        System.out.println(player.getName() + " thinks on what to do...?");

        // Player makes a decision on how to handle the attack
        System.out.println("(Push enemy off / stab on the side)");
        String Response_2 = scanner.nextLine().toLowerCase();

        if (Response_2.contains("push") || Response_2.contains("push off")) {
            System.out.println("You push the " + enemies[2].getName() + " off and decide to fight.");
            combat(scanner, player, enemies[2]);
        } else if (Response_2.contains("stab") || Response_2.contains("stab on the side")) {
            System.out.println("You stab the " + enemies[2].getName() + " and successfully make them immobile.");
            continueStory(player, enemies[2]);
        } else {
            System.out.println("Invalid choice, the enemy attacks you!");
            System.out.println("Game over!!");
            scanner.close();
            return;
        }

        
		// Navigation system to move to the next room
        // After combat in room1, ask if the player wants to move to room 2
        int roomCounter = 1;  // Start from room 1
        while (player.getHealth() > 0) {
            System.out.println("Do you want to move to the next room? (Y/N)");
            String moveChoice = scanner.nextLine().toUpperCase();

            if (moveChoice.equals("Y")) {
                // Determine which story method to call based on the roomCounter
                if (roomCounter == 1) {
                    // First room transition
                    continueStory2(player, enemies[1]);
                } else if (roomCounter == 2) {
                    // Second room transition
                	continueStory3(player, enemies);
                    
                 
                } else {
                    // No more rooms available
                    System.out.println("There are no more rooms to explore. The game ends here.");
                    break;
                }
                roomCounter++;  // Move to the next room
            } else {
                // If the player decides to stay
                System.out.println("You decide to stay in the room you are currently in, but feel an ominous presence lurking behind you...");
                System.out.println("They finally catch up to you... and they call themselves the \"Watcher\".");
                scanner.nextLine();  // Wait for the player to read the message
                System.out.println("...");
                System.out.println("Trembling in fear, you decide to fight...");
                scanner.nextLine();  // Wait before starting combat
                combat(scanner, player, enemies[0]);  
                
                
                if(player.getHealth() > 0) {
                	scanner.nextLine();
                	System.out.println("You escape the bulding, but these mysteries are far from over...");
                	scanner.nextLine();
                	
                	System.err.println("Thanks for playing!!!!");
                	return;
                }
                	else {
                		 System.out.println("The Watcher pins you to the ground, its glowing red eyes piercing through your soul.");
                         System.out.println("It leans in close and whispers, 'You will make a perfect subject for my next experiment...'");
                         scanner.nextLine();  // Pause for effect
                         System.out.println("Your vision fades to black as the Watcher begins its dreadful work...");
                         System.out.println("GAME OVER.");
                         return;
                	}
                	
                }
                
                return;
               
            }
        }
    

    // Combat system Algorithm
    public static void combat(Scanner scanner, Player player, Enemy enemy) {
        Random random = new Random();
        int playerHealth = player.getHealth(); // Player's health from the Player class
        int enemyHealth = enemy.getHealth();
        int playerAttack, enemyAttack;

        System.out.println("Combat begins!");

        // Loop for the combat turns
        while (playerHealth > 0 && enemyHealth > 0) {
            // Player's turn
            playerAttack = random.nextInt(20) + 10;  // Example attack range for player
            enemyHealth -= playerAttack;
            System.out.println(player.getName() + " attacks " + enemy.getName() + " for " + playerAttack + " damage!");

            // Check if enemy is defeated
            if (enemyHealth <= 0) {
                System.out.println("You defeated the " + enemy.getName() + "!");
                break;
            }

            // Enemy's turn
            enemyAttack = random.nextInt(15) + 5;  // Example attack range for enemy
            playerHealth -= enemyAttack;
            System.out.println(enemy.getName() + " attacks " + player.getName() + " for " + enemyAttack + " damage!");

            // Check if player is defeated
            if (playerHealth <= 0) {
                System.out.println(player.getName() + " has been defeated by the " + enemy.getName() + "!");
                System.out.println("");
                break;
            }

            // Print the current health status
            System.out.println("Your health: " + playerHealth);
            System.out.println(enemy.getName() + "'s health: " + enemyHealth);

            // Pause for the player to continue
            System.out.println("Press Enter to continue...");
            scanner.nextLine();
        }

        if (playerHealth > 0) {
            System.out.println("You survived the battle!");
            
        } else {
        	System.out.println("You fall to the ground, your vision fading to black.");
            System.out.println("Game over...");
            		            System.exit(0);
            
            
        }
    }

    // Continue the story after combat (only if you win)
    public static void continueStory(Player player, Enemy enemy) {
    	Scanner scanner = new Scanner(System.in);
    		if (player.getHealth() > 0){
            System.out.println("\nThe room falls silent. The " + enemy.getName() + " collapses at your feet.");
            System.out.println("You catch your breath, exhausted but free from the " + enemy.getName() + "." );
            System.out.println("You look around the room and see a door leading down..");
            
            
            
            String choice = scanner.nextLine().toUpperCase();

            if (choice.equals("Y")) {
                System.out.println("You cautiously approach the door, ready for whatever lies beyond.");
                System.out.println("You ponder on what you should do next");
                scanner.nextLine();
                System.out.println("\"If i stay here the person that was following me will attack me...");
            
            }
        } else {
            System.out.println("You fall to the ground, your vision fading to black.");
            System.out.println("The last thing see is a mysterious figure looking down at you with a creppy smile....");
            System.out.println("Game over....");
            System.exit(0);
            
            
            	
        }
            
    }
    
    public static void continueStory2(Player player, Enemy enemies2) {
    	
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("\"This room feels differen then the last one..\"");
        scanner.nextLine();
        System.out.println("Papers scattered across the floor...");
        System.out.println("Written on these papers it says");
        scanner.nextLine();
       
        System.out.println("\"Project J-47: Evolutionary Jump. Failed. Subject 2: Rat Model—Subject Failed to Adapt.\"");
        System.out.println("\"Attempt 4: Genetic Modification... Results Unpredictable. Proceed with Caution.\"");
        System.out.println("\"The experiment shows potential for hyper-evolution. Further tests are required.\"");
        scanner.nextLine();
       
        System.out.println("\nA cold shiver runs down your spine as you piece together the disturbing truth. This was not just some ordinary research project.");       
        System.out.println("The creature you encountered earlier, the Rabid Rodent... it was only the beginning.");
        scanner.nextLine();
       
        System.out.println("\nYou hear a soft scuttling sound behind you, and as you turn, the room grows darker.");
        System.out.println("From the shadows, something stirs—its form grotesque, its movements unnatural.");
        System.out.println("It’s the Jumper, the failed result of the experiment.");
        scanner.nextLine();
       
        System.out.println("\nThis creature, a twisted evolution of the rat, was created through reckless scientific ambition...."); 
        System.out.println("Once a rat, now it’s something worse, something born from mistakes and forced mutations.");
        scanner.nextLine();
       
        System.out.println("\nIts body is elongated, its limbs stretching in ways that should not be possible. Its face is distorted, with eyes that glow faintly, full of primal hunger.");
        System.out.println("The Jumper’s erratic movements suggest it is no longer fully in control of its own body, a result of the failed experiments.");
        System.out.println("The papers on the floor were warnings, but they were too late—this thing was already created.");
        scanner.nextLine();
       
        System.out.println("\nIt lunges at you with terrifying speed, its mouth open, revealing rows of sharp, jagged teeth.");
        System.out.println("You dodge and realize someone that was here before left a sharp knife behind...");
        inventory.addItem("Sharp Knife");
        inventory.displayItems();
        scanner.nextLine();
        System.out.println("You realize now—this is no ordinary creature. It’s the culmination of a desperate, disastrous attempt to play god.");
        scanner.nextLine();
        
        System.out.println("what do you decide to do...");
        System.out.println("(Fight/Run)");
        
        String Response_3 = scanner.nextLine().toUpperCase();

        // Initialize the enemies array properly
        Enemy[] enemies = new Enemy[3];
        enemies[0] = new Enemy("Watcher", 150);
        enemies[1] = new Enemy("Jumper", 60);  // The Jumper you are fighting here
        enemies[2] = new Enemy("Rabid Rodent", 40);

        if (Response_3.equals("FIGHT")) {
        	System.out.println("Which item would you like to equip?");
            String itemName= scanner.nextLine().toUpperCase();  // Player selects an item to equip

            // Equip the selected item
            inventory.useItem(itemToEquip, player);  

            // After equipping the item, you can proceed with combat
            System.out.println("You use " + itemName);
            scanner.nextLine();  
            combat(scanner, player, enemies[1]);
            continueStory3(player, enemies);  
            
            
        } else {
        	System.out.println("You forgot you cant run away, because there is someone is following you...");
        	System.out.println();
        	
        	System.out.println("Which item would you like to equip?");
            String itemToEquip = scanner.nextLine().toUpperCase();  // Player selects an item to equip

            // Equip the selected item
            inventory.useItem(itemToEquip, player);  

            // After equipping the item, you can proceed with combat
            System.out.println("You use " + itemToEquip);
            scanner.nextLine();  // Wait for the player to read the message

            // Now, the player proceeds to fight the Jumper
            combat(scanner, player, enemies[1]);
            continueStory3(player, enemies);
            
        }
       
    }public static void continueStory3(Player player, Enemy[] enemies) {
    	
    	Enemy[] enemies1 = new Enemy[3];
        enemies1[0] = new Enemy("Watcher", 150);
        enemies1[1] = new Enemy("Jumper", 60);  // The Jumper you are fighting here
        enemies1[2] = new Enemy("Rabid Rodent", 40);
    	
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("\nYou step into the next room, and an icy chill immediately washes over you.");
        System.out.println("The air feels sterile and lifeless, carrying a faint metallic scent.");
        scanner.nextLine();

        System.out.println("Around you, the room is filled with old, broken-down science equipment.");
        System.out.println("Glass beakers, test tubes, and vials filled with mysterious, murky liquids line the counters.");
        scanner.nextLine();

        System.out.println("In the center of the room is a large, ominous operating table, stained and rusted.");
        System.out.println("Scattered papers and files indicate that this was a laboratory of some sort.");
        scanner.nextLine();

        System.out.println("You pick up one of the documents and begin to read...");
        System.out.println("\"Project Alpha-W: Subject Watcher. Goal: Enhance intelligence and create a self-sustaining prototype.\"");
        System.out.println("\"Current results: Subject achieved sentience. Began replicating itself. Termination protocols failed.\"");
        scanner.nextLine();

        System.out.println("A sinking realization dawns upon you—the Watcher was the one creating the Jumpers.");
        System.out.println("This was its lab, and it had been conducting experiments here to create its own army.");
        scanner.nextLine();

        System.out.println("Suddenly, the Watcher emerges from the shadows, its glowing eyes locking onto you.");
        System.out.println("It lets out a guttural snarl, its twisted form illuminated by the dim, flickering lights.");
        scanner.nextLine();
        
        
        System.out.println("What will you do? (Fight/Run)");
        String choice = scanner.nextLine().toUpperCase();

        switch (choice) {
            case "FIGHT":
                // Call combat method
                combat(scanner, player, enemies1[0]);  // Assuming enemies[0] is the Watcher

                if (player.getHealth() > 0) {
                    // Player wins the fight
                    System.out.println("The Watcher collapses at your feet. Its menacing form now lies motionless.");
                    System.out.println("You catch your breath and cautiously approach the Watcher's body...");
                    System.out.println("Removing its hood, you reveal its grotesque, unnatural face—part human, part machine.");
                    System.out.println("It whispers in a broken voice, 'You... were never supposed to escape...'");
                    scanner.nextLine();  // Pause for effect
                    System.out.println("You realize the experiments were more sinister than you imagined. Who created these monsters? And why?");
                    System.out.println("You escape the room, but the mysteries are far from over...");
                    System.out.println("Thanks for playing!!!");
                    System.exit(0);
                } else {
                    // Player loses the fight
                    System.out.println("The Watcher pins you to the ground, its glowing red eyes piercing through your soul.");
                    System.out.println("It leans in close and whispers, 'You will make a perfect subject for my next experiment...'");
                    scanner.nextLine();  // Pause for effect
                    System.out.println("Your vision fades to black as the Watcher begins its dreadful work...");
                    System.out.println("GAME OVER.");
                    System.exit(0);
                }
		case "RUN":
                System.out.println("You decide to run, but the Watcher blocks your path with impossible speed...");
                System.out.println("The only option is to fight!");
                combat(scanner, player, enemies1[0]);  // Forced combat after attempting to run

                if (player.getHealth() > 0) {
                    // Player wins forced fight
                    System.out.println("After a grueling battle, you manage to defeat the Watcher and escape!");
                    System.exit(0);
                } else {
                    // Player loses forced fight
                    System.out.println("The Watcher overpowers you as you desperately try to resist...");
                    System.out.println("GAME OVER.");
                    System.exit(0);
                }
		default:
                System.out.println("Invalid choice. The Watcher doesn't wait for your indecision...");
                combat(scanner, player, enemies1[0]);  // Forced combat due to hesitation

                if (player.getHealth() > 0) {
                    // Player wins forced fight
                    System.out.println("You narrowly survive and defeat the Watcher. Its terrifying visage haunts your thoughts as you escape.");
                    System.exit(0);
                } else {
                    // Player loses forced fight
                    System.out.println("Your hesitation costs you dearly as the Watcher overpowers you...");
                    System.out.println("GAME OVER.");
                    System.exit(0);
                   
                }
        }
    }
    
}
                
  
 
                	
