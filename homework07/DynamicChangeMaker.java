
public class DynamicChangeMaker {
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
  
  private static void handleInitialArguments(int denoms[], int amount) throws IllegalArgumentException {
    for (int i = 0; i < denoms.length; i++) {
      if (denoms[i] <= 0) {
        throw new IllegalArgumentException("Coin values must be greater than 0");
      }
      for (int j = i - 1; j >= 0; j--) {
        if (denoms[i] == denoms[j]) {
          throw new DuplicateDenominationException();
        }
      }
    }
    if (amount < 0) {
      throw new IllegalArgumentException("Target amount must be at least 0");
    }
  }
  
  public static void main( String[] args ) {
    int[] testDenoms = new int[] {1, 5, 10, 25};
    int testAmount = 99999999;
    System.out.println(makeChangeWithDynamicProgramming(testDenoms, testAmount));
  }
}
