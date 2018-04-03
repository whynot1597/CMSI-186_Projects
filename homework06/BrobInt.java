/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  BrobInt.java
 * Purpose    :  Learning exercise to implement arbitrarily large numbers and their operations
 * @author    :  B.J. Johnson (prototype)
 * Date       :  2017-04-04(prototype)
 * Author     :  Jeremy Goldberg
 * Date       :  2018-04-19
 * Description:  @see <a href='http://bjohnson.lmu.build/cmsi186web/homework06.html'>Assignment Page</a>
 * Notes      :  None
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
import java.util.Arrays;

public class BrobInt {

  public static final BrobInt ZERO = new BrobInt("0");  // Constant for "zero"
  public static final BrobInt ONE = new BrobInt("1");  // Constant for "one"
  public static final BrobInt TWO = new BrobInt("2" );  // Constant for "two"
  public static final BrobInt THREE = new BrobInt("3");  // Constant for "three"
  public static final BrobInt FOUR = new BrobInt("4");  // Constant for "four"
  public static final BrobInt FIVE = new BrobInt("5");  // Constant for "five"
  public static final BrobInt SIX = new BrobInt("6");  // Constant for "six"
  public static final BrobInt SEVEN = new BrobInt("7");  // Constant for "seven"
  public static final BrobInt EIGHT = new BrobInt("8");  // Constant for "eight"
  public static final BrobInt NINE = new BrobInt("9");  // Constant for "nine"
  public static final BrobInt TEN = new BrobInt("10");  // Constant for "ten"

  /// Some constants for other intrinsic data types
  ///  these can help speed up the math if they fit into the proper memory space
  public static final BrobInt MAX_INT  = new BrobInt( new Integer( Integer.MAX_VALUE ).toString() );
  public static final BrobInt MIN_INT  = new BrobInt( new Integer( Integer.MIN_VALUE ).toString() );
  public static final BrobInt MAX_LONG = new BrobInt( new Long( Long.MAX_VALUE ).toString() );
  public static final BrobInt MIN_LONG = new BrobInt( new Long( Long.MIN_VALUE ).toString() );

  /// These are the internal fields
  private String internalValue = "";        // internal String representation of this BrobInt
  private byte sign = 0;         // "0" is positive, "1" is negative
  private String reversed = "";        // the backwards version of the internal String representation
  private int[] intVersion = null;      // int array for storing the string values; uses the reversed string

  /**
   *  Constructor takes a string and assigns it to the internal storage, checks for a sign character
   *   and handles that accordingly;  it then checks to see if it's all valid digits, and reverses it
   *   for later use
   *  @param  value  String value to make into a BrobInt
   *  @throws  IllegalArgumentException if something is hinky
   */
  public BrobInt(String value) throws IllegalArgumentException {
    if (value == null || value.isEmpty()) {
      throw new IllegalArgumentException("Not a valid representation");
    }
    if (value.charAt(0) == Character.valueOf('-')) {
      this.sign = 1;
      value = value.substring(1);
    } else if (value.charAt(0) == Character.valueOf('+')) {
      value = value.substring(1);
    }
    
    this.internalValue = value;
    this.reversed = new String(new StringBuffer(this.internalValue).reverse());
     
    this.intVersion = new int[this.reversed.length()];
    for (int i = 0; i < this.reversed.length(); i++) {
      char testChar = this.reversed.charAt(i);
      if(!Character.isDigit(testChar)) {
        throw new IllegalArgumentException("Not a valid representation");
      }
      this.intVersion[i] = testChar - '0';       //converts char to int
    }    
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to validate that all the characters in the value are valid decimal digits
   *  @return  boolean  true if all digits are good
   *  @throws  IllegalArgumentException if something is hinky
   *  note that there is no return false, because of throwing the exception
   *  note also that this must check for the '+' and '-' sign digits
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public boolean validateDigits() {
     return true;
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to reverse the value of this BrobInt
   *  @return BrobInt that is the reverse of the value of this BrobInt
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public BrobInt reverser() {
     throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to reverse the value of a BrobIntk passed as argument
   *  Note: static method
   *  @param  gint         BrobInt to reverse its value
   *  @return BrobInt that is the reverse of the value of the BrobInt passed as argument
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public static BrobInt reverser( BrobInt gint ) {
     throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to add the value of a BrobIntk passed as argument to this BrobInt using byte array
   *  @param  gint         BrobInt to add to this
   *  @return BrobInt that is the sum of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public BrobInt addByte(BrobInt gint) {
     return this.add(gint);
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to add the value of a BrobIntk passed as argument to this BrobInt using int array
   *  @param  gint         BrobInt to add to this
   *  @return BrobInt that is the sum of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public BrobInt addInt(BrobInt gint) {
     return this.add(gint);
  }
   
  public BrobInt add(BrobInt gint) {     
    /*try {
      int first = Integer.parseInt(this.toString());
      int second = Integer.parseInt(gint.toString());     
      int result = first + second;
      //TODO first and second add to higher than MAX_INT
      return new BrobInt(Integer.toString(result));
    }
    catch (NumberFormatException nfe) {*/
      BrobInt resultBrobInt = null;
      
      //TODO test if one is ZERO (use abs)
      
      if (gint.sign == 1 && this.sign == 0) {
        resultBrobInt = this.subtract(gint.abs());
      } else if (gint.sign == 1 && this.sign == 1) {
        resultBrobInt = this.abs().add(gint.abs());
        resultBrobInt.sign = 1;
      } else if (gint.sign == 0 && this.sign == 1) {
        resultBrobInt = gint.subtract(this.abs());
      } else {
        int compareTest = this.compareTo(gint);
        BrobInt biggerBrobInt = null;
        BrobInt smallerBrobInt = null;
         
        if (compareTest == 0) {
          this.multiply(TWO);
        } else if (compareTest < 0) {
          biggerBrobInt = new BrobInt(gint.toString());
          smallerBrobInt = new BrobInt(this.toString());
        } else {
          biggerBrobInt = new BrobInt(this.toString());
          smallerBrobInt = new BrobInt(gint.toString());
        }
        int[] intVersionOutput = new int[biggerBrobInt.intVersion.length + 1];
         
        for (int i = 0; i < intVersionOutput.length; i++) {
          if (i < biggerBrobInt.intVersion.length) {
            if (i < smallerBrobInt.intVersion.length) {
              int value = biggerBrobInt.intVersion[i] + smallerBrobInt.intVersion[i] + intVersionOutput[i];
              if (value >= 10) {
                intVersionOutput[i] = value - 10;
                intVersionOutput[i + 1] = 1;
              } else {
                intVersionOutput[i] = value;
              }
              continue;
            }
            int value = biggerBrobInt.intVersion[i] + intVersionOutput[i];
            if (value >= 10) {
              intVersionOutput[i] = value - 10;
              intVersionOutput[i + 1] = 1;
            } else {
              intVersionOutput[i] = value;
            }
          }
        }
        resultBrobInt = new BrobInt(intArrayToString(intVersionOutput));
      }
      return resultBrobInt;
    //s}
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to subtract the value of a BrobIntk passed as argument to this BrobInt using bytes
   *  @param  gint         BrobInt to subtract from this
   *  @return BrobInt that is the difference of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public BrobInt subtractByte( BrobInt gint ) {
     return this.subtract(gint);
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to subtract the value of a BrobIntk passed as argument to this BrobInt using int array
   *  @param  gint         BrobInt to subtract from this
   *  @return BrobInt that is the difference of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public BrobInt subtractInt( BrobInt gint ) {
     return this.subtract(gint);
  }
   
  public BrobInt subtract( BrobInt gint ) {
    try {
      int first = Integer.parseInt(this.toString());
      int second = Integer.parseInt(gint.toString());      
      int result = first - second;
      return new BrobInt(Integer.toString(result));
    }
    catch (NumberFormatException nfe) {
      BrobInt resultBrobInt = null;
      
      if (this.sign == 1 && gint.sign == 0) {
        resultBrobInt = this.abs().add(gint);
        resultBrobInt.sign = 1;
      } else if (this.sign == 1 && gint.sign == 1) {
        resultBrobInt = this.add(gint.abs());
      } else if (this.sign == 0 && gint.sign == 1) {
        resultBrobInt = this.add(gint.abs());
      } else {
        int compareTest = this.compareTo(gint);
        BrobInt biggerBrobInt = null;
        BrobInt smallerBrobInt = null;
        
        if (compareTest == 0) {
          return ZERO;
        } else if (compareTest < 0) {
          biggerBrobInt = new BrobInt(gint.toString());
          smallerBrobInt = new BrobInt(this.toString());
        } else {
          biggerBrobInt = new BrobInt(this.toString());
          smallerBrobInt = new BrobInt(gint.toString());
        }
        int[] intVersionOutput = new int[biggerBrobInt.intVersion.length];
        
        if (biggerBrobInt.compareTo(gint) == 0) {
          resultBrobInt = gint.subtract(this);
          resultBrobInt.sign = 1;
        } else {
          for (int i = 0; i < biggerBrobInt.intVersion.length; i++) {
            if (i < smallerBrobInt.intVersion.length) {
              int value = biggerBrobInt.intVersion[i] - smallerBrobInt.intVersion[i] + intVersionOutput[i];
              if (value < 0) {
                intVersionOutput[i] = 10 - value;
                intVersionOutput[i + 1] -= 1;
              } else {
                intVersionOutput[i] = value;
              }
            } else {
              intVersionOutput[i] += biggerBrobInt.intVersion[i];
            }
            resultBrobInt = new BrobInt(intArrayToString(intVersionOutput));
          }
        }
      }
      return resultBrobInt;
    }
  }
  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to multiply the value of a BrobIntk passed as argument to this BrobInt
   *  @param  gint         BrobInt to multiply by this
   *  @return BrobInt that is the product of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public BrobInt multiply( BrobInt gint ) {
    BrobInt resultBrobInt = null;
    BrobInt biggerBrobInt = null;
    BrobInt smallerBrobInt = null;
    int compareTest = this.compareTo(gint);
    
    if (gint.equals(ZERO) || this.equals(ZERO)) {
      return ZERO;
    }
    
    if (gint.abs().equals(ONE)) {
      resultBrobInt = new BrobInt(this.toString());
      if (gint.sign == 1 && this.sign == 0) {
        resultBrobInt.sign = 1;
      }
      return resultBrobInt;
    }
    
    if (this.abs().equals(ONE)) {
      resultBrobInt = new BrobInt(this.toString());
      if (this.sign == 1 && gint.sign == 0) {
        resultBrobInt.sign = 1;
      }
      return resultBrobInt;
    }
   
    if (compareTest <= 0) {
      biggerBrobInt = new BrobInt(gint.toString());
      smallerBrobInt = new BrobInt(this.toString());
    } else {
      biggerBrobInt = new BrobInt(this.toString());
      smallerBrobInt = new BrobInt(gint.toString());
    }
    
    BrobInt[] brobIntArray = new BrobInt[smallerBrobInt.intVersion.length];
    int[] intVersionOutput = null;
    int startIndex = 0;
    for (int i = 0; i < smallerBrobInt.intVersion.length; i++) {
      intVersionOutput = new int[biggerBrobInt.intVersion.length + 1 + startIndex];
      for (int j = startIndex; j < biggerBrobInt.intVersion.length + startIndex; j++) {
        int value = (smallerBrobInt.intVersion[i] * biggerBrobInt.intVersion[j - startIndex]) + intVersionOutput[j];
        intVersionOutput[j] = value - ((int)Math.floor(value / 10) * 10);
        intVersionOutput[j + 1] = (int)Math.floor(value / 10);
      }
      brobIntArray[i] = new BrobInt(intArrayToString(intVersionOutput));
      startIndex += 1;
    }
 
    resultBrobInt = new BrobInt(ZERO.toString());
    for (int i = 0; i < brobIntArray.length; i++) {
      resultBrobInt = resultBrobInt.add(brobIntArray[i]);
    }
    if (biggerBrobInt.sign != smallerBrobInt.sign) {
      resultBrobInt.sign = 1;
    }
    return resultBrobInt;
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to divide the value of this BrobIntk by the BrobInt passed as argument
   *  @param  gint         BrobInt to divide this by
   *  @return BrobInt that is the dividend of this BrobInt divided by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public BrobInt divide( BrobInt gint ) {
    BrobInt resultBrobInt = null;
    BrobInt firstBrobInt = null;
    BrobInt secondBrobInt = null;
    int compareTest = this.compareTo(gint);
    
    if (gint.abs().equals(ONE)) {
      resultBrobInt = new BrobInt(this.toString());
      if (gint.sign == 1 && this.sign == 0) {
        resultBrobInt.sign = 1;
      }
      return resultBrobInt;
    }
    
    if (compareTest == 0) {
      resultBrobInt = new BrobInt(ONE.toString());
      return resultBrobInt;
    }
    if (compareTest < 0) {
      return ZERO;
    } else {
      firstBrobInt = new BrobInt(this.toString());
      secondBrobInt = new BrobInt(gint.toString());
    }
    throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to get the remainder of division of this BrobInt by the one passed as argument
   *  @param  gint         BrobInt to divide this one by
   *  @return BrobInt that is the remainder of division of this BrobInt by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public BrobInt remainder( BrobInt gint ) {
     throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to compare a BrobInt passed as argument to this BrobInt
   *  @param  gint  BrobInt to add to this
   *  @return int   that is one of neg/0/pos if this BrobInt precedes/equals/follows the argument
   *  NOTE: this method performs a lexicographical comparison using the java String "compareTo()" method
   *        THAT was easy.....
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public int compareTo( BrobInt gint ) {
     return (internalValue.toString().compareTo(gint.toString()));
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to check if a BrobInt passed as argument is equal to this BrobInt
   *  @param  gint     BrobInt to compare to this
   *  @return boolean  that is true if they are equal and false otherwise
   *  NOTE: this method performs a similar lexicographical comparison as the "compareTo()" method above
   *        also using the java String "equals()" method -- THAT was easy, too..........
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public boolean equals( BrobInt gint ) {
     return (internalValue.toString().equals(gint.toString()));
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a BrobInt given a long value passed as argument
   *  @param  value         long type number to make into a BrobInt
   *  @return BrobInt  which is the BrobInt representation of the long
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public static BrobInt valueOf(long value) throws NumberFormatException {
     BrobInt gi = null;
     try {
        gi = new BrobInt(new Long(value).toString());
     }
     catch(NumberFormatException nfe) {
        System.out.println("\n  Sorry, the value must be numeric of type long.");
     }
     return gi;
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a String representation of this BrobInt
   *  @return String  which is the String representation of this BrobInt
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public String toString() {
     String intVersionOutput = intArrayToString(this.intVersion);
     if(this.sign == 1) {
       intVersionOutput = "-" + intVersionOutput;
     }
     return intVersionOutput;
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to turn an array of integers into a string
   *  @param array   array of integers
   *  @return String which is the String representation of the array
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  private static String intArrayToString(int[] array) {
    String arrayOutput = "";
    int endIndex = array.length;
    if (array[array.length - 1] == 0 && array.length > 1) {
      endIndex = array.length - 1;
    }
    for(int i = 0; i < endIndex; i++) {
       arrayOutput = array[i] + arrayOutput;
    }
    return arrayOutput;
  }
   
  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to perform absolute value equation on BrobInt type
   *  @return BrobInt which is this BrobInt with positive sign
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public BrobInt abs() {
    BrobInt resultBrobInt = new BrobInt(this.toString());
    resultBrobInt.sign = 0;
    return resultBrobInt;
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  the main method redirects the user to the test class
   *  @param  args  String array which contains command line arguments
   *  note:  we don't really care about these
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public static void main( String[] args ) {
     System.out.println( "\n  Hello, world, from the BrobInt program!!\n" );
     System.out.println( "\n   You should run your tests from the BrobIntTester...\n" );
      
     
     BrobInt test1 = new BrobInt(MAX_INT.toString());
     BrobInt test2 = new BrobInt("10");
     System.out.println(MAX_INT.toString());
     System.out.println(test1.add(test2));
     
     System.exit( 0 );
  }
}
