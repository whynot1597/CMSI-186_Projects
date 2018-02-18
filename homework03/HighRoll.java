/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  HighRoll.java
 *  Purpose       :  User based high roll game
 *  Author        :  Jeremy Goldberg
 *  Date          :  2018-02-22
 *  Description   :
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class HighRoll{

  private static String getUserInput(String prompt) throws IOException {
    System.out.println(prompt);

    // This line uses the two classes to assemble an "input stream" for the user to type text into the program
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    System.out.print(">> ");
    String inputLine = input.readLine();
    return inputLine;
  }

  public static void main(String args[]) {
    try {
      System.out.println("\n  This is High Roll!");

      String inputLine = null;
      int count = 0;
      while (true) {
        inputLine = getUserInput("\n  How many dice would you like?");
        try {
          count = Integer.parseInt(inputLine);
          if (count > 0) {
            break;
          } else {
            System.out.println("\n  Input must be bigger than 0");
          }
        }
        catch (NumberFormatException nfe) {
          System.out.println("\n  Input must be a number");
        }
      }

      int sides = 4;
      while (true) {
        inputLine = getUserInput("\n  How many sides should the dice have?");
        try {
          sides = Integer.parseInt(inputLine);
          if (sides >= 4) {
            break;
          } else {
            System.out.println("\n  Input must be at least 4");
          }
        }
        catch (NumberFormatException nfe) {
          System.out.println("\n  Input must be a number");
        }
      }

      DiceSet ds = new DiceSet(count, sides);
      int highscore = 0;
      String mainPrompt = "\n  Choose an option :)\n" +
                          "\n  1: Roll all the dice\n" +
                          "\n  2: Reroll a single die\n" +
                          "\n  3: Calculate a score for this set\n" +
                          "\n  4: Save this score as a high score\n" +
                          "\n  5: Display the high score\n" +
                          "\n  Q: Quit";
      while (true) {
        inputLine = getUserInput(mainPrompt);
        int inputLineInt = 0;

        if('q' == inputLine.toLowerCase().charAt(0)) {
           break;
        }
        try {
          inputLineInt = Integer.parseInt(inputLine);
        }
        catch (NumberFormatException nfe) {
          inputLineInt = 0;
        }
        switch (inputLineInt) {
          case 1:
            ds.roll();
            System.out.println("\n  Your roll is: " + ds.toString());
            break;

          case 2:
            inputLine = getUserInput("\n  Which die should be rerolled?");
            int diceIndex = Integer.parseInt(inputLine) - 1;
            try {
              ds.rollIndividual(diceIndex);
              System.out.println("\n  The new die value is: " + ds.getIndividual(diceIndex));
              System.out.println("  Your roll is: " + ds.toString());
            }
            catch (IllegalArgumentException iae) {
              System.out.println("\n  All dice must be rolled first and the input must be between 1 and " + count);
            }
            break;

          case 3:
            try {
              System.out.println("\n  Your score is: " + ds.sum());
            }
            catch (IllegalArgumentException iae) {
              System.out.println("\n  All dice must be rolled first");
            }
            break;

          case 4:
            try {
              highscore = ds.sum();
              System.out.println("\n  Saved!");
            }
            catch (IllegalArgumentException iae) {
              System.out.println("\n  All dice must be rolled first");
            }
            break;

          case 5:
            System.out.println("\n  Your highscore is: " + highscore);
            break;

          default:
            System.out.println("\n  Input must be 1-5 or Q");
        }
      }
    }
    catch (IOException ioe) {
      System.out.println( "Caught IOException" );
    }
  }
}
