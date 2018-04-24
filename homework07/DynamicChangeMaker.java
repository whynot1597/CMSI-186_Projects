/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  DynamicChangeMaker.java
 * Purpose    :  @see http://bjohnson.lmu.build/cmsi186web/homework07.html
 * @author    :  Jeremy Goldberg
 * Date       :  2018-05-03
 * Description:  Creates a table of tuples and finds the optimal solution for a target amount given a set
 *               of coin denominations
 * Notes      :  None
 * Warnings   :  None
 * Exceptions :  IllegalArgumentException when the input arguments are "hinky"
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class DynamicChangeMaker {
  /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see    http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  @param  int[]  int array containing coin denomination
   *  @param  int    int total target amount
   *  @return Tuple that is the optimized set of coins to reach the target
   *  @throws IllegalArgumentException when input arguments are "hinky"
   */           
  public static Tuple makeChangeWithDynamicProgramming(int denoms[], int amount) throws IllegalArgumentException {
    handleInitialArguments(denoms, amount);
    Tuple table[][] = new Tuple[denoms.length][amount + 1];
    for (int i = 0; i < denoms.length; i++) {
      table[i][0] = new Tuple(denoms.length);
    }
    for (int j = 0; j < denoms.length; j++) {
      for (int i = 1; i <= amount; i++) {
        if (i - denoms[j] < 0) {
          table[j][i] = Tuple.IMPOSSIBLE;
        } else {
          table[j][i] = new Tuple(denoms.length);
          table[j][i].setElement(j, 1);
          if (table[j][i - denoms[j]].isImpossible()) {
            table[j][i] = Tuple.IMPOSSIBLE;
          } else {
            table[j][i] = (table[j][i]).add(table[j][i - denoms[j]]);
          }
        }
        if (j > 0 && !(table[j - 1][i].isImpossible())) {
          if (table[j][i].isImpossible() || (table[j - 1][i].total() < table[j][i].total())) {
            table[j][i] = table[j - 1][i];
          }
        }
      }
    }
    return table[denoms.length - 1][amount];
  }
  
  /**
   *  Method to check that the initial arguments fit the constraints of the assignment
   *  @see    http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  @param  denoms[]  int array containing coin denomination
   *          amount    total target amount
   *  @return Tuple that is the optimized set of coins to reach the target
   *  @throws IllegalArgumentException when input arguments are "hinky"
   */
  private static void handleInitialArguments(int denoms[], int amount) throws IllegalArgumentException {
    for (int i = 0; i < denoms.length; i++) {
      if (denoms[i] <= 0) {
        throw new NonpositiveDenominationException();
      }
      for (int j = i - 1; j >= 0; j--) {
        if (denoms[i] == denoms[j]) {
          throw new DuplicateDenominationException();
        }
      }
    }
    if (amount < 0) {
      throw new NegativeTargetValueException();
    }
  }
  
  /**
   *  @see    http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  @param  String[] args
   *          args[0]  Coin denominations
   *          args[1]  Target value
   *  @throws IllegalArgumentException when input arguments are "hinky"
   */  
  public static void main( String[] args ) {
    if (args.length != 2) {
      System.out.println("Invalid Usage\n"
          + "Please use: java DynamicChangeMaker <coin denominations> <target value>");
      System.exit(0);
    }
    
    //Test each denomination is a number and make an int array
    String[] stringInputArray = args[0].split(",");
    int[] intInputArray = new int[stringInputArray.length];
    for (int i = 0; i < stringInputArray.length; i++) {
      try {
        intInputArray[i] = Integer.parseInt(stringInputArray[i]);
      }
      catch (NumberFormatException nfe) {
        System.out.println("Coin denominations must be a number\n"
            + "Correct usage is each coin denomination separated by a comma\n"
            + "ie. 3 cents and 6 cents would be 3,6");
        System.exit(0);
      }
    }
    
    //Test the amount is a number
    int amount = 0;
    try {
      amount = Integer.parseInt(args[1]);
    }
    catch (NumberFormatException nfe) {
      System.out.println("Target value must be a number");
      System.exit(0);
    }
    
    System.out.print("The optimal solution is: " + makeChangeWithDynamicProgramming(intInputArray, amount).toString());
  }
}
