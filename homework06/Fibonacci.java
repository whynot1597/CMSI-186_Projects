/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  Fibonacci.java
 * Purpose    :  Find the "nth" Fibonacci number given an argument, using BrobInt class
 * @author    :  B.J. Johnson
 * Date       :  2017-04-17
 * Description:  @see <a href='http://bjohnson.lmu.build/cmsi186web/homework06.html'>Assignment Page</a>
 * Notes      :  None
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

public class Fibonacci {

   private static final String usageMessage = "\n  You must enter an integer number....." +
                                              "\n    Please try again!" +
                                              "\n  USAGE: java Fibonacci <required_integer>\n\n";
   private static int maxCount = 0;
   private static int working = 10000;
   private static String end1 = "st";
   private static String end2 = "nd";
   private static String end3 = "rd";
   private static String endRest = "th";
   private static String cardinality = "";
   private static String resultCount = "";

   private static final int NO_CMD_LINE_ARGS = -1;
   private static final int BAD_CMD_LINE_ARG = -2;

   public Fibonacci() {
      super();
   }

   public static void main( String[] args ) {
      System.out.println( "\n\n   Welcome to the Fibonacci sequence number finder!\n" );
      if( 0 == args.length ) {
         System.out.println( usageMessage );
         System.exit( NO_CMD_LINE_ARGS );
      }
      try {
         maxCount = Integer.parseInt( args[0] );
      }
      catch( NumberFormatException nfe ) {
         System.out.println( "\n   Sorry, that does not compute!!" + usageMessage );
         System.exit( BAD_CMD_LINE_ARG );
      }
      if( 2 == args.length ) {
         try {
            working = Integer.parseInt( args[1] );
         }
         catch( NumberFormatException nfe ) {
            System.out.println( "\n   Sorry, that does not compute!!" + usageMessage );
            System.exit( BAD_CMD_LINE_ARG );
         }
      }

     // this is just for making the output pretty... no real "fibonacci" functionality
      int lastIndex = args[0].length() - 1;
      switch(args[0].charAt(lastIndex - 1)) {
        case '1': cardinality = endRest;
        break;
        default: switch(args[0].charAt(lastIndex)) {
                   case '1': cardinality = end1;
                   break;
                   case '2': cardinality = end2;
                   break;
                   case '3': cardinality = end3;
                   break;
                   default : cardinality = endRest;
                   break;
        }
        break;
      }

      if (maxCount == 1) {
        resultCount = "0";
      } else if (maxCount == 2) {
        resultCount = "1";
      } else {
        if( maxCount >= working ) {
          System.out.println( "\n   This may take me a while; please be patient!!\n" );
        }
        
        // NOTE: you WILL need to initialize your BrobInts to keep track of things....
        BrobInt firstBrobInt = BrobInt.ZERO;
        BrobInt secondBrobInt = BrobInt.ONE;
        BrobInt resultBrobInt = null;
        
        for (int i = 2; i < maxCount; i++) {
          resultBrobInt = new BrobInt(firstBrobInt.add(secondBrobInt).toString());
          firstBrobInt = new BrobInt(secondBrobInt.toString());
          secondBrobInt = new BrobInt(resultBrobInt.toString());
        }
        resultCount = resultBrobInt.toString();
      }

      System.out.println( "\n   Starting from zero, the " + maxCount + cardinality + " Fibonacci number is: " + resultCount);

      System.exit( 0 );
   }
}
