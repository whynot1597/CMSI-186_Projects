/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name  : CountTheDays.java
 *  Purpose    : Count the number of days between two dates
 *  Author     : Jeremy Goldberg
 *  Date       : 2018-01-25
 *  Description: This class finds the number of days between two dates. It accepts six parameters
 *               representing month, day, and year for each date.
 *  Note       : None
 *  Exceptions : IllegalArgumentException if the number of arguments is wrong or of the wrong type
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class CountTheDays {
  public static void main ( String [] args ) {
    if (args.length != 6) {
      throw new IllegalArgumentException("Need six arguments");
    }
    try {
      System.out.println("Number Of Days Is: " + CalendarStuff.daysBetween(
        Long.parseLong(args[0]),
        Long.parseLong(args[1]),
        Long.parseLong(args[2]),
        Long.parseLong(args[3]),
        Long.parseLong(args[4]),
        Long.parseLong(args[5])
      ));
    }
    catch(NumberFormatException ex) {
      throw new IllegalArgumentException("One of the arguments is not a number");
    }
  }
}
