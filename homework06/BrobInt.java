/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  BrobInt.java
 * Purpose    :  Learning exercise to implement arbitrarily large numbers and their operations
 * @author    :  B.J. Johnson (prototype)
 * Date       :  2017-04-04 (prototype)
 * @author    :  Jeremy Goldberg
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
    
    //TODO Strip 0s
    while (value.charAt(0) == Character.valueOf('0') && value.length() > 1) {
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
   *  Method to add the value of a BrobIntk passed as argument to this BrobInt using int array
   *  @param  gint         BrobInt to add to this
   *  @return BrobInt that is the sum of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */   
  public BrobInt add(BrobInt gint) {     
    try {
      int first = Integer.parseInt(this.toString());
      int second = Integer.parseInt(gint.toString()); 
      int result = first + second;
      if ((first > 0 && second > 0 && result < 0) ||
          (first < 0 && second < 0 && result > 0)) {
        throw new NumberFormatException ("Result is too large so must use BrobInt");
      }  
      return new BrobInt(Integer.toString(result));
    }
    catch (NumberFormatException nfe) {
      if (this.abs().compareTo(ZERO) == 0) {
        return gint;
      }
      if (gint.abs().compareTo(ZERO) == 0) {
        return this;
      }
      
      BrobInt resultBrobInt = null;
      
      if (gint.sign == 1 && this.sign == 0) {
        return this.subtract(gint.abs());
      } else if (gint.sign == 1 && this.sign == 1) {
        resultBrobInt = this.abs().add(gint.abs());
        resultBrobInt.sign = 1;
        return resultBrobInt;
      } else if (gint.sign == 0 && this.sign == 1) {
        return gint.subtract(this.abs());
      } else {
        int compareTest = this.compareTo(gint);
        BrobInt biggerBrobInt = null;
        BrobInt smallerBrobInt = null;
         
        if (compareTest <= 0) {
          biggerBrobInt = new BrobInt(gint.toString());
          smallerBrobInt = new BrobInt(this.toString());
        } else {
          biggerBrobInt = new BrobInt(this.toString());
          smallerBrobInt = new BrobInt(gint.toString());
        }
        int[] intVersionOutput = new int[biggerBrobInt.intVersion.length + 1];
         
        for (int i = 0; i < intVersionOutput.length; i++) {
          if (i < biggerBrobInt.intVersion.length) {
            int addend = 0;
            if (i < smallerBrobInt.intVersion.length) {
              addend = smallerBrobInt.intVersion[i];
            }
            int value = biggerBrobInt.intVersion[i] + addend + intVersionOutput[i];
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
    }
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to subtract the value of a BrobInt passed as argument to this BrobInt using int array
   *  @param  gint         BrobInt to subtract from this
   *  @return BrobInt that is the difference of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   
  public BrobInt subtract( BrobInt gint ) {
    try {
      int first = Integer.parseInt(this.toString());
      int second = Integer.parseInt(gint.toString());      
      int result = first - second;
      if ((first > 0 && second < 0 && result < 0) ||
          (first < 0 && second > 0 && result > 0)) {
        throw new NumberFormatException ("Result is too large so must use BrobInt"); 
      }
      return new BrobInt(Integer.toString(result));
    }
    catch (NumberFormatException nfe) {
      if (this.abs().compareTo(ZERO) == 0) {
        return gint;
      }
      if (gint.abs().compareTo(ZERO) == 0) {
        return this;
      }
      
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
                int nonZeroIndex = i + 1;
                for (int j = nonZeroIndex; (biggerBrobInt.intVersion[j] + intVersionOutput[j]) == 0; j++, nonZeroIndex++) {
                  intVersionOutput[j] = 10;
                }
                for (int k = nonZeroIndex; k > i; k--) {
                  intVersionOutput[k] -= 1;
                }
                intVersionOutput[i] = biggerBrobInt.intVersion[i] - smallerBrobInt.intVersion[i] + 10;
              } else {
                intVersionOutput[i] = value;
              }
            } else {
              intVersionOutput[i] += biggerBrobInt.intVersion[i];
            }
          }
          resultBrobInt = new BrobInt(intArrayToString(intVersionOutput));
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
    
    if (gint.abs().equals(ZERO) || this.abs().equals(ZERO)) {
      return ZERO;
    }
    
    if (gint.abs().equals(ONE)) {
      resultBrobInt = new BrobInt(this.toString()).abs();
      if (gint.sign != this.sign) {
        resultBrobInt.sign = 1;
      }
      return resultBrobInt;
    }
    
    if (this.abs().equals(ONE)) {
      resultBrobInt = new BrobInt(this.toString()).abs();
      if (this.sign != gint.sign) {
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
        int carryTensPlace = (int)Math.floor(value / 10);
        intVersionOutput[j] = value - (carryTensPlace * 10);
        intVersionOutput[j + 1] = carryTensPlace;
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
   *  @throws IllegalArgumentException when argument is 0
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public BrobInt divide( BrobInt gint ) throws IllegalArgumentException {
    if (gint.abs().equals(ZERO) ) {
      throw new IllegalArgumentException("Divide by 0");
    }
    if (this.abs().equals(ZERO) ) {
      return ZERO;
    }
    BrobInt resultBrobInt = null;
    BrobInt firstBrobInt = null;
    BrobInt secondBrobInt = null;
    BrobInt testBrobInt = null;
    int[] testIntVersionOutputReversed = null;
    int absCompareTest = this.abs().compareTo(gint.abs());
    
    if (gint.abs().equals(ONE)) {
      resultBrobInt = new BrobInt(this.toString()).abs();
      if (gint.sign != this.sign) {
        resultBrobInt.sign = 1;
      }
      return resultBrobInt;
    }
    
    if (absCompareTest == 0) {
      return ONE;
    }
    if (absCompareTest < 0) {
      return ZERO;
    }
    
    firstBrobInt = new BrobInt(this.abs().toString());
    secondBrobInt = new BrobInt(gint.abs().toString());
    int[] firstIntVersionReversed = reverse(firstBrobInt.intVersion);
    int[] secondIntVersionReversed = reverse(secondBrobInt.intVersion);
    
    int[] intVersionOutputReversed = new int[firstBrobInt.intVersion.length];
    int i = secondBrobInt.intVersion.length - 1;
    //TODO code main divide method
    
    testIntVersionOutputReversed = new int[secondBrobInt.intVersion.length + 1];
    for (int j = 0; j < secondBrobInt.intVersion.length; j++) {
      testIntVersionOutputReversed[j + 1] = firstIntVersionReversed[j];
    }
    testBrobInt = new BrobInt(intArrayToString(reverse(testIntVersionOutputReversed)));
    
    if (testBrobInt.compareTo(secondBrobInt) < 0) {
      for (int j = 0; j < testIntVersionOutputReversed.length - 1; j++) {
        testIntVersionOutputReversed[j] = testIntVersionOutputReversed[j + 1];
      }
      testIntVersionOutputReversed[testIntVersionOutputReversed.length - 1] = firstIntVersionReversed[testIntVersionOutputReversed.length - 1];
      testBrobInt = new BrobInt(intArrayToString(reverse(testIntVersionOutputReversed)));
      i += 1;
    }
     
    do {
      intVersionOutputReversed[i] += 1;
      testBrobInt = testBrobInt.subtract(secondBrobInt);
    } while (testBrobInt.compareTo(secondBrobInt) > 0);
    
    
    resultBrobInt = new BrobInt(intArrayToString(reverse(intVersionOutputReversed)));
    return resultBrobInt;
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to get the remainder of division of this BrobInt by the one passed as argument
   *  @param  gint         BrobInt to divide this one by
   *  @return BrobInt that is the remainder of division of this BrobInt by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public BrobInt remainder( BrobInt gint ) {
     BrobInt flooredDivision = new BrobInt(this.divide(gint).toString());
     return new BrobInt(this.subtract(flooredDivision).toString());
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to compare a BrobInt passed as argument to this BrobInt
   *  @param  gint  BrobInt to add to this
   *  @return int   that is one of neg/0/pos if this BrobInt precedes/equals/follows the argument
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public int compareTo( BrobInt gint ) {
    if (this.sign == 0 && gint.sign == 1) {
      return 1;
    }
    if (this.sign == 1 && gint.sign == 0) {
      return -1;
    }
    if (this.sign == 0) {
      if (this.intVersion.length > gint.intVersion.length) {
        return 1;
      }
      if (this.intVersion.length < gint.intVersion.length) {
        return -1;
      }
      for (int i = this.intVersion.length - 1; i >= 0; i--) {
        if (this.intVersion[i] > gint.intVersion[i]) {
          return 1;
        }
        if (this.intVersion[i] < gint.intVersion[i]) {
          return -1;
        }
      }
     return 0;
    }
    if (this.intVersion.length > gint.intVersion.length) {
      return -1;
    }
    if (this.intVersion.length < gint.intVersion.length) {
      return 1;
    }
    for (int i = this.intVersion.length - 1; i >= 0; i--) {
      if (this.intVersion[i] > gint.intVersion[i]) {
        return -1;
      }
      if (this.intVersion[i] < gint.intVersion[i]) {
        return 1;
      }
    }
    return 0;
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to check if a BrobInt passed as argument is equal to this BrobInt
   *  @param  gint     BrobInt to compare to this
   *  @return boolean  that is true if they are equal and false otherwise
   *  NOTE: this method uses the compareTo() method above
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public boolean equals( BrobInt gint ) {
     return this.compareTo(gint) == 0;
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
    //TODO possibly use Array.toString()
    String arrayOutput = "";
    int endIndex = array.length;
    for(int i = 0; i < endIndex; i++) {
       arrayOutput = array[i] + arrayOutput;
    }
    return arrayOutput;
  }
  
  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to reverse an int array
   *  @param array   array of integers
   *  @return array  array of int that is the reverse of the parameter
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  private static int[] reverse(int[] array) {
    int[] returnArray = new int[array.length];
    for (int i = 0; i < array.length; i++) {
      returnArray[i] = array[array.length - (1 + i)];
    }
    return returnArray;
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
      
     
     BrobInt test1 = new BrobInt("0000099999999999999999992");
     BrobInt test2 = new BrobInt("0000099999999999999999990");
     BrobInt test3 = new BrobInt("159");
     BrobInt test4 = new BrobInt("15");
     BrobInt test5 = new BrobInt("74");
     System.out.println(test1.subtract(test2));
     
     System.exit( 0 );
  }
}
