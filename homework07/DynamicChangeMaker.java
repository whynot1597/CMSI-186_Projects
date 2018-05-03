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
   *  @see    http://bjohnson.lmu.build/cmsi186web/homework07.html
   *  @param  int[]  int array containing coin denomination
   *  @param  int    int total target amount
   *  @return Tuple that is the optimized set of coins to reach the target
   *  @throws IllegalArgumentException when input arguments are "hinky"
   */           
  public static Tuple makeChangeWithDynamicProgramming(int denoms[], int amount) throws IllegalArgumentException {
    handleInitialArguments(denoms, amount);
    Tuple table[][] = new Tuple[denoms.length][amount + 1];
    
    //Sets all elements at 0 column to 0 tuple
    for (int i = 0; i < denoms.length; i++) {
      table[i][0] = new Tuple(denoms.length);
    }
    
    //Main calculation loops
    for (int rowIndex = 0; rowIndex < denoms.length; rowIndex++) {
      for (int columnIndex = 1; columnIndex <= amount; columnIndex++) {
        if (columnIndex - denoms[rowIndex] < 0) {
          table[rowIndex][columnIndex] = Tuple.IMPOSSIBLE;
        } else {
          table[rowIndex][columnIndex] = new Tuple(denoms.length);
          table[rowIndex][columnIndex].setElement(rowIndex, 1);
          if (table[rowIndex][columnIndex - denoms[rowIndex]].isImpossible()) {
            table[rowIndex][columnIndex] = Tuple.IMPOSSIBLE;
          } else {
            table[rowIndex][columnIndex] = (table[rowIndex][columnIndex]).add(table[rowIndex][columnIndex - denoms[rowIndex]]);
          }
        }
        if (rowIndex > 0 && !(table[rowIndex - 1][columnIndex].isImpossible())) {
          if (table[rowIndex][columnIndex].isImpossible() || 
             (table[rowIndex - 1][columnIndex].total() < table[rowIndex][columnIndex].total())) {
            table[rowIndex][columnIndex] = table[rowIndex - 1][columnIndex];
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
    
    //Find the optimal solution
    String optimalSolution = "";
    try {
      optimalSolution = makeChangeWithDynamicProgramming(intInputArray, amount).toString();
    }
    catch (IllegalArgumentException iae) {
      System.out.println(iae.getMessage());
      System.exit(0);
    }
    System.out.print("The optimal solution is: " + optimalSolution);
  }
}
