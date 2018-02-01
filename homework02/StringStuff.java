/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  StringStuff.java
 *  Purpose       :  A file full of stuff to do with the Java String class
 *  Author        :  B.J. Johnson (prototype)
 *  Date          :  2017-01-19 (prototype)
 *  Author        :  Jeremy Goldberg
 *  Date          :  2018-02-08
 *  Description   :  This file presents a bunch of String-style helper methods.  Although pretty much
 *                   any and every thing you'd want to do with Strings is already made for you in the
 *                   Java String class, this exercise gives you a chance to do it yourself [DIY] for some
 *                   of it and get some experience with designing code that you can then check out using
 *                   the real Java String methods [if you want]
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
import java.util.Set;
import java.util.LinkedHashSet;

public class StringStuff {

  public static String evensOnlyString = "bdfhjlnprtvxzBDFHJLNPRTVXZ";

  public static String oddsOnlyString = "acegikmoqsuwyACEGIKMOQSUWY";


  /**
  * Method to determine if a string contains one of the vowels: A, E, I, O, U, and sometimes Y.
  * Both lower and upper case letters are handled.  In this case, the normal English rule for Y means
  * it gets included.
  *
  * @param s String containing the data to be checked for &quot;vowel-ness&quot;
  * @return  boolean which is true if there is a vowel, or false otherwise
  */
  public static boolean containsVowel( String s ) {
    s = s.toLowerCase();
    return s.contains("a") || s.contains("e") || s.contains("i") || s.contains("o") || s.contains("u") || s.contains("y");
  }

  /**
  * Method to determine if a string is a palindrome.  Does it the brute-force way, checking
  * the first and last, second and last-but-one, etc. against each other.  If something doesn't
  * match that way, returns false, otherwise returns true. In this case, the empty string is treated
  * as a palindrome.
  *
  * @param s String containing the data to be checked for &quot;palindrome-ness&quot;
  * @return  boolean which is true if this a palindrome, or false otherwise
  */
  public static boolean isPalindrome( String s ) {
    for (int testPosition1 = 0, testPosition2 = s.length() - 1; testPosition1 <= testPosition2; testPosition1++, testPosition2--) {
      if (s.charAt(testPosition1) != s.charAt(testPosition2)) {
        return false;
      }
    }
    return true;
  }

  /**
  * Method to return the characters in a string that correspond to the &quot;EVEN&quot; index
  * numbers of the alphabet or the &quot;ODD&quot; index numbers of the alphabet.
  * The letters B, D, F, H, J, L, N, P, R, T, V, X, and Z are even,
  * corresponding to the numbers 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, and 26.
  * The letters A, C, E, G, I, K, M, O, Q, S, U, W, and Y are odd,
  * corresponding to the numbers 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, and 25.
  *
  * @param s String containing the data to be parsed for &quot;even&quot; or &quot;odd&quot; letters
  * @param testString String containing all possible the &quot;even&quot; or &quot;odd&quot; letters
  * @param allowDupes boolean true if duplicates are allowed, otherwise false
  * @return  String containing the &quot;even&quot; or &quot;odd&quot; letters from the input
  */
  private static String oddsOrEvensOnly( String s, String testString, boolean allowDupes ) {
    String resultString = "";
    for (int testPosition = 0; testPosition < s.length(); testPosition++) {
      char testCharacter = s.charAt(testPosition);
      if (testString.indexOf(testCharacter) >= 0 && (allowDupes || resultString.indexOf(testCharacter) < 0)) {
        resultString = resultString + testCharacter;
      }
    }
    return resultString;
  }

  /**
  * Method to return the characters in a string that correspond to the &quot;EVEN&quot; index
  * numbers of the alphabet.  The letters B, D, F, H, J, L, N, P, R, T, V, X, and Z are even,
  * corresponding to the numbers 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, and 26.
  *
  * @param s String containing the data to be parsed for &quot;even&quot; letters
  * @return  String containing the &quot;even&quot; letters from the input
  */
  public static String evensOnly( String s ) {
    return oddsOrEvensOnly( s, evensOnlyString, true );
  }

  /**
  * Method to return the characters in a string that correspond to the &quot;ODD&quot; index
  * numbers of the alphabet.  The letters A, C, E, G, I, K, M, O, Q, S, U, W, and Y are odd,
  * corresponding to the numbers 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, and 25.
  *
  * @param s String containing the data to be parsed for &quot;odd&quot; letters
  * @return  String containing the &quot;odd&quot; letters from the input
  */
  public static String oddsOnly( String s ) {
    return oddsOrEvensOnly( s, oddsOnlyString, true );
  }

  /**
  * Method to return the characters in a string that correspond to the &quot;EVEN&quot; index
  * numbers of the alphabet, but with no duplicate characters in the resulting string.
  *
  * @param s String containing the data to be parsed for &quot;even&quot; letters
  * @return  String containing the &quot;even&quot; letters from the input without duplicates
  */
  public static String evensOnlyNoDupes( String s ) {
    return oddsOrEvensOnly( s, evensOnlyString, false );
  }

  /**
   * Method to return the characters in a string that correspond to the &quot;ODD&quot; index
   * numbers of the alphabet, but with no duplicate characters in the resulting string.
   *
   * @param s String containing the data to be parsed for &quot;odd&quot; letters
   * @return  String containing the &quot;odd&quot; letters from the input without duplicates
   */
   public static String oddsOnlyNoDupes( String s ) {
     return oddsOrEvensOnly( s, oddsOnlyString, false );
   }

  /**
   * Method to return the reverse of a string passed as an argument
   *
   * @param s String containing the data to be reversed
   * @return  String containing the reverse of the input string
   */
   public static String reverse( String s ) {
      String[] stringArray = s.split("");
      String resultString = "";
      for(int position = stringArray.length - 1; position >= 0; position--) {
        resultString = resultString + stringArray[position];
      }
      return resultString;
   }

  /**
   * Main method to test the methods in this class
   *
   * @param args String array containing command line parameters
   */
   public static void main( String args[] ) {
      String blah = new String( "Blah blah blah" );
      String woof = new String( "BCDBCDBCDBCDBCD" );
      String pal1 = new String( "a" );
      String pal2 = new String( "ab" );
      String pal3 = new String( "aba" );
      String pal4 = new String( "amanaplanacanalpanama" );
      String pal5 = new String( "abba" );
      System.out.println( containsVowel( blah ) );
      System.out.println( containsVowel( woof ) );
      System.out.println( isPalindrome( pal1 ) );
      System.out.println( isPalindrome( pal2 ) );
      System.out.println( isPalindrome( pal3 ) );
      System.out.println( isPalindrome( pal4 ) );
      System.out.println( isPalindrome( pal5 ) );
      System.out.println( "evensOnly()        returns: " + evensOnly( "REHEARSALSZ" ) );
      System.out.println( "evensOnly()        returns: " + evensOnly( "REhearSALsz" ) );
      System.out.println( "evensOnlyNoDupes() returns: " + evensOnlyNoDupes( "REhearSALsz" ) );
      System.out.println( "oddsOnly()         returns: " + oddsOnly( "xylophones" ) );
      System.out.println( "oddsOnly()         returns: " + oddsOnly( "XYloPHonES" ) );
      System.out.println( "oddsOnlyNoDupes()  returns: " + oddsOnlyNoDupes( "XYloPHonES" ) );
      System.out.println( "reverse()          returns: " + reverse( "REHEARSALSZ" ) );
   }
}
