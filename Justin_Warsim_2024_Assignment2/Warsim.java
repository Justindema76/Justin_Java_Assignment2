import warrior.*;
import weapon.*;
import armour.*;
import utility.*;

import java.util.Scanner;
import java.util.Random;

public class Warsim {
  // Objects
  public static Scanner input = new Scanner(System.in);
  public static Random randNum = new Random();
  public static Ink ink = new Ink();
  
  public static Warrior player; // player
  public static Weapon pWeapon; // player weapon
  public static Armour pArmour; // player armour

  public static Warrior enemy; // enemy
  public static Weapon eWeapon; // enemy weapon
  public static Armour eArmour; // enemy armour

  // Game Vars
  public static boolean gameOver = false;
  public static boolean playerTurn = true; // player starts
  public static String who = "Player";
  public static String winner = "";
  public static int choice = 0;
  public static int attackType = 0;
  public static int pDamage = 0;
  public static int eDamage = 0;
  
  //================>>
  // main method
  public static void main(String[] args) {

    // Prints welcome message w/ASCII art ;)
    ink.welcome();

    //=====================>>
    // Player Creation
    // Warrior
    ink.printWarriorMenu();
    choice = input.nextInt();
    createWarrior(who, choice);
    
    // Weapon
    ink.printWeaponMenu();
    choice = input.nextInt();
    createWeapon(who, choice);
    
    // Armour
    ink.printArmourMenu();
    choice = input.nextInt();
    createArmour(who, choice);

    // player is all setup
    // switch 'who' to Enemy
    who = "Enemy";

    //=====================>>
    // Enemy Creation
    // Warrior
    choice = randNum.nextInt(3) + 1;
    createWarrior(who, choice);

    // Weapon
    choice = randNum.nextInt(3) + 1; 
    createWeapon(who, choice);

    // Armour
    choice = randNum.nextInt(3) + 1; 
    createArmour(who, choice);

    ink.printWarriorStats(player, enemy);
    
    ink.printWeaponType(pWeapon, eWeapon);

    ink.printArmourType(pArmour, eArmour);
    //==================>>
    // Main Game Loop
    while(!gameOver) {
      if(playerTurn) {
        // player code
        ink.printAttackMenu();
        attackType = input.nextInt();
        eDamage = pWeapon.strike(attackType, 
          player.getDexterity(),
          player.getStrength());

        // take the damage and pass it into the enemy's armour
        // to reduce the damage
        if(eDamage > 0) { // if there is damage at all
          eDamage = eArmour.getFinalDamage(eDamage);
          
          // assign the damage amount to the enemy
          enemy.takeDamage(eDamage);

          System.out.println("Player dealt: " + eDamage  + " damage to enemy!"); // testing

          // check to see if the enemy is dead!
          if(enemy.getHealth() <= 0) { // enemy is dead!
            winner = "Player";
            gameOver = !gameOver;
          }
        }
        else { // missed!
          System.out.println("Missed no damage!");
        }
      }
      else {
        // enemy code
        System.out.println(); // blank line
        System.out.println("Enemy Turn!");
        attackType = randNum.nextInt(3) + 1;

        pDamage = eWeapon.strike(attackType, enemy.getDexterity(), enemy.getStrength());
        player.takeDamage(pDamage);

        System.out.println("Enemy dealt: " + pDamage + " damage to you!"); // testing

        if(player.getHealth() <= 0){
          winner = "Enemy";
          gameOver = !gameOver;
        }
      }
      playerTurn = !playerTurn; // toggles turn each iteration
    
    } // while()
    ink.printGameOver(winner);

  } // main()

  //========================>>
  // Helper Methods
  public static void createWarrior(String who, int choice) {
    if(who.equals("Player")) { // player warrior creation
      switch (choice) {
        case 1: // Human
          player = new Human();
          player.setWarriorType("Human");
          break;
        case 2: // Elf
          player = new Elf();
          player.setWarriorType("Elf");
          break;
        case 3: // Orc
          player = new Orc();
          player.setWarriorType("Orc");
          break;
        default:
          System.out.println("Oops!");
          break;
      } // switch
    }
    else { // enemy warrior creation
      switch (choice) {
        case 1: // Human
          enemy = new Human();
          enemy.setWarriorType("Human");
          break;
        case 2: // Elf
          enemy = new Elf();
          enemy.setWarriorType("Elf");
          break;
        case 3: // Orc
          enemy = new Orc();
          enemy.setWarriorType("Orc");
          break;
        default:
          System.out.println("Oops!");
          break;
      } // switch
    }
  } // createWarrior()

  public static void createWeapon(String who, int choice) {
    switch (choice) {
      case 1: // Dagger
        if(who.equals("Player")) {
          pWeapon = new Dagger();
          pWeapon.setWeaponType("Dagger");
        }
        else {
          eWeapon = new Dagger();
          eWeapon.setWeaponType("Dagger");
        }
        break;
      case 2: // Sword
        if(who.equals("Player")) {
          pWeapon = new Sword();
          pWeapon.setWeaponType("Sword");
        }
        else {
          eWeapon = new Sword();
          eWeapon.setWeaponType("Sword");
        }
        break;
      case 3: // Axe
        if(who.equals("Player")) {
          pWeapon = new Axe();
          pWeapon.setWeaponType("Axe");
        }
        else {
          eWeapon = new Axe();
          eWeapon.setWeaponType("Axe");
        }
        break;
      default:
        System.out.println("Oops!");
        break;
    } // switch

  } // createWeapon()

  public static void createArmour(String who, int choice) {
    switch (choice) {
      case 1: // Leather
        if(who.equals("Player")) {
          pArmour = new Leather();
          pArmour.setArmourType("Leather");
        }
        else {
          eArmour = new Leather();
          eArmour.setArmourType("Leather");
        }
        break;
      case 2: // Chainmail
        if(who.equals("Player")) {
          pArmour = new Chainmail();
          pArmour.setArmourType("Chainmail");
        }
        else {
          eArmour = new Chainmail();
          eArmour.setArmourType("Chainmail");
        }
        break;
      case 3: // Platemail
        if(who.equals("Player")) {
          pArmour = new Platemail();
          pArmour.setArmourType("Platemail");
        }
        else {
          eArmour = new Platemail();
          eArmour.setArmourType("Platemail");
        }
        break;
      default:
        System.out.println("Oops!");
        break;
    } // switch
  } // createArmour()

} // class