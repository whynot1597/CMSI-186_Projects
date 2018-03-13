/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  ClockSolver.java
 *  Purpose       :  The main program for the ClockSolver class
 *  Author        :  B.J. Johnson (prototype)
 *  Date		      :  2017-02-28 (prototype)
 *  Author        :  Jeremy Goldberg
 *  Date		      :  2018-03-13
 *  Description   :  This class provides a bunch of methods which may be useful for the ClockSolver class
 *                   for Homework 4, part 1.  Includes the following:
 *                   handleInitialArguments()
 *
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

public class ClockSolver {
  /**
   *  Class field definitions go here
   */
  private static final double MAX_SECOND = 60.0 * 60.0 * 12.0;
   
  private double angle = 0.0;
  private double timeSlice = Clock.DEFAULT_TIME_SLICE_IN_SECONDS;
  private double angleWindow = 0.1;             // small value for double-precision comparisons
   

  /**
   *  Constructor
   *  This just calls the superclass constructor, which is "Object"
   */
  public ClockSolver() {
    super();
  }

  /**
   *  Method to handle all the input arguments from the command line
   *   this sets up the variables for the simulation
   */
  public void handleInitialArguments( String args[], Clock clock ) {
     // args[0] specifies the angle for which you are looking
     //  your simulation will find all the angles in the 12-hour day at which those angles occur
     // args[1] if present will specify a time slice value; if not present, defaults to 60 seconds
     // args[2] if present will specify an angle window value; if not present. defaults to 0.1

    System.out.println( "\n  Welcome to Clock Solver!  \n\n" ) ;
    if( 0 == args.length ) {
      System.out.println( "   Sorry you must enter at least one argument\n" +
                          "   Example: java ClockSolver <angle> [timeSlice] [angleWindow]");
      System.exit( 1 );
    }
      
    try {
    	this.angle = clock.validateAngleArg(args[0]);  
    }
    catch (NumberFormatException nfe) {
    	System.out.println("Input must be a number");
    	System.exit(1);
    }
    catch (IllegalArgumentException iae) {
    	System.out.println(iae.getMessage());
    	System.exit(1);
    }
    clock.angle = this.angle;
      
    if (args.length > 1) {
    	try {
    		this.timeSlice = clock.validateTimeSliceArg(args[1]);  
    	}
    	catch (NumberFormatException nfe) {
    		System.out.println("Input must be a number");
    		System.exit(1);
    	}
    	catch (IllegalArgumentException iae) {
    		System.out.println(iae.getMessage());
    		System.exit(1);
    	}
    	
    	if (args.length > 2) {
    	  try {
    	    this.angleWindow = clock.validateAngleWindowArg(args[2]);
    	  }
    	  catch (NumberFormatException nfe) {
          System.out.println("Input must be a number");
          System.exit(1);
        }
        catch (IllegalArgumentException iae) {
          System.out.println(iae.getMessage());
          System.exit(1);
        }
    	}
    }
    clock.timeSlice = this.timeSlice;
  }

  /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  @param  args  String array of the arguments from the command line
   *                args[0] is the angle for which we are looking
   *                args[1] is the time slice; this is optional and defaults to 60 seconds
   *                args[2] is the angle window; this is optional and defaults to 0.1 degrees
   */
  public static void main( String args[] ) {
    ClockSolver cs = new ClockSolver();
    Clock clock = new Clock();
    
    cs.handleInitialArguments(args, clock);
    
    while (clock.getTotalSeconds() < MAX_SECOND) {
      double handAngle = clock.getHandAngle();
      if (Math.abs(handAngle - cs.angle) <= cs.angleWindow ||                                 // Counterclockwise calculation
        	Math.abs(cs.angle - (Clock.MAXIMUM_DEGREE_VALUE - handAngle)) <= cs.angleWindow) {  // Clockwise calculation
        
        System.out.println(clock.toString());
        
      }
      clock.tick();
    }
    
    System.exit(0);
  }
}
