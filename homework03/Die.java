/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Die.java
 *  Purpose       :  Provides a class describing a single die that can be rolled
 *  Author        :  B.J. Johnson (prototype)
 *  Date          :  2017-02-06 (prototype)
 *  Author        :  Jeremy Goldberg
 *  Date          :  2018-02-22
 *  Description   :  This class provides the data fields and methods to describe a single game die.  A
 *                   die can have "N" sides.  Sides are randomly assigned sequential pip values, from 1
 *                   to N, with no repeating numbers.  A "normal" die would thus has six sides, with the
 *                   pip values [spots] ranging in value from one to six.  Includes the following:
 *                   public Die( int nSides );                  // Constructor for a single die with "N" sides
 *                   public int roll();                         // Roll the die and return the result
 *                   public int getValue()                      // get the value of this die
 *                   public void setSides()                     // change the configuration and return the new number of sides
 *                   public String toString()                   // Instance method that returns a String representation
 *                   public static String toString()            // Class-wide method that returns a String representation
 *                   public static void main( String args[] );  // main for testing porpoises
 *
 *  Notes         :  Restrictions: no such thing as a "two-sided die" which would be a coin, actually.
 *                   Also, no such thing as a "three-sided die" which is a physical impossibility without
 *                   having it be a hollow triangular prism shape, presenting an argument as to whether
 *                   the inner faces are faces which then should be numbered.  Just start at four for
 *                   minimum number of faces.  However, be aware that a four-sided die dosn't have a top
 *                   face to provide a value, since it's a tetrahedron [pyramid] so you'll have to figure
 *                   out a way to get the value, since it won't end up on its point.
 *
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the number of sides or pips is out of range
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class Die {
  /**
  * private instance data
  */
  private final int MINIMUM_SIDES = 4;
  private int sides = MINIMUM_SIDES;
  private int pips = 0;

  // public constructor:
  /**
  * constructor
  * @param nSides int value containing the number of sides to build on THIS Die
  * @throws       IllegalArgumentException
  * Note: parameter must be checked for validity; invalid value must throw "IllegalArgumentException"
  */
  public Die( int nSides ) {
    if (nSides < MINIMUM_SIDES) {
      throw new IllegalArgumentException("Number of sides is too low");
    }
    this.sides = nSides;
  }

  /**
  * Roll THIS die and return the result
  * @return  integer value of the result of the roll, randomly selected
  */
  public int roll() {
    this.pips = 1 + (int)(Math.random() * ((this.sides - 1) + 1));
    return this.pips;
  }

  /**
  * Get the value of THIS die to return to the caller; note that the way
  *  the count is determined is left as a design decision to the programmer
  *  For example, what about a four-sided die - which face is considered its
  *  "value"?
  * @return the pip count of THIS die instance
  */
  public int getValue() {
    if (this.pips == 0) {
      throw new IllegalArgumentException("Die must be rolled first");
    }
    return this.pips;
  }

  /**
  * @param  int  the number of sides to set/reset for this Die instance
  * @return      The new number of sides, in case anyone is looking
  * @throws      IllegalArgumentException
  */
  public void setSides( int sides ) {
    if (sides < MINIMUM_SIDES) {
      throw new IllegalArgumentException("Number of sides is too low");
    }
    this.sides = sides;
    this.pips = 0;
  }

  /**
  * Public Instance method that returns a String representation of THIS die instance
  * @return String representation of this Die
  */
  public String toString() {
    return Integer.toString(this.getValue());
  }

  /**
  * Class-wide method that returns a String representation of THIS die instance
  * @return String representation of this Die
  */
  public static String toString( Die d ) {
    return d.toString();
  }

  /**
  * A little test main to check things out
  */
  public static void main( String[] args ) {
    Die testDie = new Die(11);
    try {
      for(int i = 10; i > 0; i--) {
        System.out.println("Your roll is: " + testDie.roll());
        System.out.println("The value is: " + testDie.getValue());
        System.out.println("The number of sides is: " + testDie.sides);
        System.out.println("The string representation is: " + testDie.toString());
        testDie.setSides(i);
      }
    }
    catch (IllegalArgumentException iae) {
      System.out.println("Caught small number of sides");
    }
  }
}
