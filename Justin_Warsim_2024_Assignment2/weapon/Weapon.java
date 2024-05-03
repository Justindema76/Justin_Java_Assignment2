
package weapon;


import java.util.Random;

public abstract class Weapon {
  private int damageAmount;
  private int dexterityCost;
  protected Random randNum = new Random();
  private String weaponType = "";
  private int pDamage;
  private int eDamage;
  

  public Weapon() {
    // do nothing for now
  }

  //==============>>
  // GETTERS
  public int getDamageAmount() {
    return this.damageAmount;
  }
  public int getDexterityCost() {
    return this.dexterityCost;
  }
  public String getWeaponType() {
    return this.weaponType;
  }
  public int getPdamage(){
    return this.pDamage;
  }
  public int getEdamage(){
    return this.eDamage;
  }
  //==============>>
  // SETTERS
  public void setDamageAmount(int damageAmount) {
    this.damageAmount = damageAmount;
  }
  public void setDexterityCost(int dexterityCost) {
    this.dexterityCost = dexterityCost;
  }
  public void setWeaponType(String weaponType) {
    this.weaponType = weaponType;
  }
  public void setPdamage(int pDamage){
    this.pDamage = pDamage;
  }
  public void setEdamage(int eDamage){
    this.eDamage = eDamage;
  }
  // an abstract method is polymorphic
  // meaning the children determine how
  // this method will be implemented
  public abstract int strike(int attackType, int dexterity, int strength);

} // class
