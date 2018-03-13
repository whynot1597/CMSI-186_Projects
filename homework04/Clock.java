/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Clock.java
 *  Purpose       :  Provides a class defining methods for the ClockSolver class
 *  Author        :  B.J. Johnson (prototype)
 *  Date		      :  2017-02-28 (prototype)
 *  Author		    :  Jeremy Goldberg
 *  Date		      :  2018-03-13
 *  Description   :  This class provides a bunch of methods which may be useful for the ClockSolver class
 *                   for Homework 4, part 1.  Includes the following:
 *                   tick()
 *                   validateAngleArg()
 *                   validateTimeSliceArg()
 *                   getHourHandAngle()
 *                   getMinuteHandAngle()
 *                   getHandAngle()
 *                   getTotalSeconds()
 *                   toString()                   
 *
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class Clock {
  /**
   *  Class field definitions go here
   */
  public static final double DEFAULT_TIME_SLICE_IN_SECONDS = 60.0; 
  private static final double MAX_TIME_SLICE_IN_SECONDS  = 1800.00;
  public static final double MAXIMUM_DEGREE_VALUE = 360.0;
  private static final double HOUR_HAND_DEGREES_PER_SECOND = 360.0 / 12.0 / 60.0 / 60.0;
  private static final double MINUTE_HAND_DEGREES_PER_SECOND = 360.0 / 60.0 / 60.0;
  private static final double SECONDS_PER_HOUR = 60.0 * 60.0;
  private static final double MINUTES_PER_HOUR = 60.0;
   
  private double currentMinuteAngle = 0.0;
  private double currentHourAngle = 0.0;
  private double currentSecond = 0.0;
   
  public double angle = 0;
  public double timeSlice = DEFAULT_TIME_SLICE_IN_SECONDS;

  /**
   *  Constructor goes here
   */
  public Clock() {
	 
  }

  /**
   *  Methods go here
   *
   *  Method to calculate the next tick from the time increment
   *  @return double-precision value of the current clock tick
   */
  public void tick() {
    this.currentSecond += this.timeSlice;
	  this.currentMinuteAngle += MINUTE_HAND_DEGREES_PER_SECOND * this.timeSlice;
	  if (this.currentMinuteAngle >= MAXIMUM_DEGREE_VALUE) {
		  this.currentMinuteAngle -= MAXIMUM_DEGREE_VALUE;
	  }
	  this.currentHourAngle += HOUR_HAND_DEGREES_PER_SECOND * this.timeSlice;
  }

  /**
   *  Method to validate the angle argument
   *  @param   argValue  String from the main programs args[0] input
   *  @return  double-precision value of the argument
   *  @throws  NumberFormatException
   */
  public double validateAngleArg( String input ) throws NumberFormatException, IllegalArgumentException {
	  double result = Double.parseDouble(input);
	  if (result <= 0.0 || result >= MAXIMUM_DEGREE_VALUE) {
	    throw new IllegalArgumentException("Degree must greater than 0 and less than 360");
	  }
	  return result;
  }

  /**
   *  Method to validate the optional time slice argument
   *  @param  argValue  String from the main programs args[1] input
   *  @return double-precision value of the argument or -1.0 if invalid
   *  note: if the main program determines there IS no optional argument supplied,
   *         I have elected to have it substitute the string "60.0" and call this
   *         method anyhow.  That makes the main program code more uniform, but
   *         this is a DESIGN DECISION, not a requirement!
   *  note: remember that the time slice, if it is small will cause the simulation
   *         to take a VERY LONG TIME to complete!
   */
  public double validateTimeSliceArg( String argValue ) throws NumberFormatException, IllegalArgumentException {
	  double result = Double.parseDouble(argValue);
	  if (result <= 0.0 || result > MAX_TIME_SLICE_IN_SECONDS) {
		  throw new IllegalArgumentException("Time slice must be greater than 0 and less than 1800"); 
	  }
	  return result;
  }
  
  /**
   *  Method to validate the angle window argument
   *  @param   argValue  String from the main programs args[2] input
   *  @return  double-precision value of the argument
   *  @throws  NumberFormatException, IllegalArgumentExecption
   */
  public double validateAngleWindowArg( String input ) throws NumberFormatException, IllegalArgumentException {
    double result = Double.parseDouble(input);
    if (result <= 0.0 || result > 0.5) {
      throw new IllegalArgumentException("Angle window must greater than 0 and less than 0.5");
    }
    return result;
  }

  /**
   *  Method to calculate and return the current position of the hour hand
   *  @return double-precision value of the hour hand location
   */
  public double getHourHandAngle() {
	  return this.currentHourAngle;
  }

  /**
   *  Method to calculate and return the current position of the minute hand
   *  @return double-precision value of the minute hand location
   */
  public double getMinuteHandAngle() {
    return this.currentMinuteAngle;
  }

  /**
   *  Method to calculate and return the angle between the hands
   *  @return double-precision value of the angle between the two hands
   */
  public double getHandAngle() {
    return Math.abs(this.currentMinuteAngle - this.currentHourAngle);
  }

  /**
   *  Method to fetch the total number of seconds
   *   we can use this to tell when 12 hours have elapsed
   *  @return double-precision value the total seconds private variable
   */
  public double getTotalSeconds() {
    return currentSecond;
  }

  /**
   *  Method to return a String representation of this clock
   *  @return String value of the current clock
   */
  public String toString() {
    int currentHour = (int)Math.floor(this.currentSecond / SECONDS_PER_HOUR);
	  int currentMinute = (int)Math.floor((this.currentSecond - (currentHour * SECONDS_PER_HOUR)) / MINUTES_PER_HOUR);
	  double second = this.currentSecond - (currentHour * SECONDS_PER_HOUR) - (currentMinute * MINUTES_PER_HOUR);
    return (currentHour == 0 ? "12" : currentHour) + ":" + 
			     (currentMinute < 10 ? "0" : "") + currentMinute + ":" + 
			     (second < 10 ? "0" : "") + (Math.round(second * 1000.0) / 1000.0);
  }

  /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  be sure to make LOTS of tests!!
   *  remember you are trying to BREAK your code, not just prove it works!
   */
  public static void main( String args[] ) {

    System.out.println( "\nCLOCK CLASS TESTER PROGRAM\n" +
                          "--------------------------\n" );
    System.out.println( "  Creating a new clock: " );
    Clock clock = new Clock();
    System.out.println( "    New clock created: " + clock.toString() );
    System.out.println( "    Testing validateAngleArg()....");
    System.out.print( "      sending '  0 degrees', expecting double value   0.0" );
    try { System.out.println( (0.0 == clock.validateAngleArg( "0.0" )) ? " - got 0.0" : " - no joy" ); }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
  }
}
