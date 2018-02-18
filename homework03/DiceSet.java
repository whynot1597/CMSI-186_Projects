/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  DiceSet.java
 *  Purpose       :  Provides a class describing a set of dice
 *  Author        :  B.J. Johnson (prototype)
 *  Date          :  2017-02-09 (prototype)
 *  Author        :  Jeremy Goldberg
 *  Date          :  2018-02-22
 *  Description   :  This class provides everything needed (pretty much) to describe a set of dice.  The
 *                   idea here is to have an implementing class that uses the Die.java class.  Includes
 *                   the following:
 *                   public DiceSet( int k, int n );                  // Constructor for a set of k dice each with n-sides
 *                   public int sum();                                // Returns the present sum of this set of dice
 *                   public void roll();                              // Randomly rolls all of the dice in this set
 *                   public void rollIndividual( int i );             // Randomly rolls only the ith die in this set
 *                   public int getIndividual( int i );               // Gets the value of the ith die in this set
 *                   public String toString();                        // Returns a stringy representation of this set of dice
 *                   public static String toString( DiceSet ds );     // Classwide version of the preceding instance method
 *                   public boolean isIdentical( DiceSet ds );        // Returns true iff this set is identical to the set ds
 *                   public static void main( String[] args );        // The built-in test program for this class
 *
 *  Notes         :  Stolen from Dr. Dorin pretty much verbatim, then modified to show some interesting
 *                   things about Java, and to add this header block and some JavaDoc comments.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the number of sides or pips is out of range
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
import java.util.Arrays;

public class DiceSet {
  /**
  * private instance data
  */
  private int count;
  private int sides;
  private Die[] ds = null;

  // public constructor:
  /**
  * constructor
  * @param  count int value containing total dice count
  * @param  sides int value containing the number of pips on each die
  * @throws IllegalArgumentException if one or both arguments don't make sense
  * @note   parameters are checked for validity; invalid values throw "IllegalArgumentException"
  */
  public DiceSet( int count, int sides ) {
    if (count < 0) {
      throw new IllegalArgumentException("Count is too low");
    }
    if (sides < 4) {
      throw new IllegalArgumentException("Not enough sides");
    }
    ds = new Die[count];
    for (int i = 0; i < count; i++) {
      ds[i] = new Die(sides);
    }
    this.count = count;
    this.sides = sides;
  }

  /**
  * @return the sum of all the dice values in the set
  */
  public int sum() {
    int sumOfValues = 0;
    for (int i = 0; i < ds.length; i++) {
      sumOfValues += ds[i].getValue();
    }
    return sumOfValues;
  }

  /**
  * Randomly rolls all of the dice in this set
  *  NOTE: you will need to use one of the "toString()" methods to obtain
  *  the values of the dice in the set
  */
  public void roll() {
    for (int i = 0; i < ds.length; i++) {
      ds[i].roll();
    }
  }

  /**
  * Randomly rolls a single die of the dice in this set indexed by 'dieIndex'
  * @param  dieIndex int of which die to roll
  * @return the integer value of the newly rolled die
  * @throws IllegalArgumentException if the index is out of range
  */
  public int rollIndividual( int dieIndex ) {
    if (dieIndex < 0 || dieIndex >= ds.length) {
      throw new IllegalArgumentException("Index is out of range");
    }
    return ds[dieIndex].roll();
  }

  /**
  * Gets the value of the die in this set indexed by 'dieIndex'
  * @param  dieIndex int of which die to roll
  * @throws IllegalArgumentException if the index is out of range
  */
  public int getIndividual( int dieIndex ) {
    if (dieIndex < 0 || dieIndex >= ds.length) {
      throw new IllegalArgumentException("Index is out of range");
    }
    return ds[dieIndex].getValue();
  }

  /**
  * @return Public Instance method that returns a String representation of the DiceSet instance
  */
  public String toString() {
    String[] stringArray = new String[ds.length];
    for (int i = 0; i < ds.length; i++) {
      stringArray[i] = ds[i].toString();
    }
    String resultString = "";
    for (int i = 0; i < stringArray.length; i++) {
      resultString += (stringArray[i] + ", ");
    }
    resultString = resultString.substring(0, resultString.length() - 2);
    return resultString;
  }

  /**
  * @return Class-wide version of the preceding instance method
  */
  public static String toString( DiceSet ds ) {
    return ds.toString();
  }

  /**
  * @return  tru iff this set is identical to the set passed as an argument
  */
  public boolean isIdentical( DiceSet newDiceSet ) {
    if (newDiceSet.count != this.count || newDiceSet.sides != this.sides) {
      return false;
    }
    int[] thisValuedDiceSetArray = new int[this.count];
    int[] newValuedDiceSetArray = new int[newDiceSet.count];
    for (int i = 0; i < this.count; i++) {
      thisValuedDiceSetArray[i] = this.getIndividual(i);
      newValuedDiceSetArray[i] = newDiceSet.getIndividual(i);
    }
    Arrays.sort(thisValuedDiceSetArray);
    Arrays.sort(newValuedDiceSetArray);
    for (int i = 0; i < newValuedDiceSetArray.length; i++) {
      if (thisValuedDiceSetArray[i] != newValuedDiceSetArray[i]) {
        return false;
      }
    }
    return true;
  }
  /**
  * A little test main to check things out
  */
  public static void main( String[] args ) {
    DiceSet testDiceSet = new DiceSet(5, 6);
    testDiceSet.roll();
    System.out.println("The ds is: " + testDiceSet.toString());
    System.out.println("The sum of the dice values is: " + testDiceSet.sum());
    System.out.println("Rerolling 4th die");
    testDiceSet.rollIndividual(3);
    System.out.println("The ds is: " + testDiceSet.toString());
    System.out.println("The 4th dice value is: " + testDiceSet.getIndividual(3));
    System.out.println("The number of dice is: " + testDiceSet.count);
    DiceSet testDiceSet2 = new DiceSet(5, 6);
    testDiceSet2.roll();
    System.out.println("The new ds is: " + testDiceSet2.toString());
    System.out.println("Are the sets equal: " + testDiceSet.isIdentical(testDiceSet2));
  }

}
