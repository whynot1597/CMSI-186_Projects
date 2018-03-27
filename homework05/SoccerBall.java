/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  SoccerBall.java
 *  Purpose       :  Provides a class defining methods for the SoccerSim class
 *  Author        :  Jeremy Goldberg
 *  Date          :  2018-03-27
 *  Description   :  This class defines a ball with an x and y start location and x and y start velocity.
 *                   It also can find the next location and velocity of the ball
 *                   Includes the following:
 *                   tick()
 *                   getXCurrentLocation()
 *                   getYCurrentLocation()
 *                   getXCurrentVelocity()
 *                   getYCurrentVelocity()    
 *                   main()        
 *
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class SoccerBall {
  
  public final static double radius = 0.3708; // 4.45 Inches
  public final static double MINIMUM_SPEED = 0.08333333333333; // 1/12 which is 1 inch in terms of feet per second
  
  private double xCurrentLocation = 0;
  private double yCurrentLocation = 0;
  private double xCurrentVelocity = 0;
  private double yCurrentVelocity = 0;
  
  //public constructor:
  /**
   * constructor
   * @param xStartLocation double value containing the x start location of this ball
   * @param yStartLocation double value containing the y start location of this ball
   * @param xStartVelocity double value containing the x start velocity of this ball
   * @param yStartVelocity double value containing the y start velocity of this ball
   */
  public SoccerBall(double xStartLocation,
                    double yStartLocation,
                    double xStartVelocity,
                    double yStartVelocity) {
    
    this.xCurrentLocation = xStartLocation;
    this.yCurrentLocation = yStartLocation;
    this.xCurrentVelocity = xStartVelocity;
    this.yCurrentVelocity = yStartVelocity;    
  }
  
  /**  
   *  Method to calculate the ball location and velocity from the time slice
   */  
  public void tick(double timeSlice) {
    double xInitialVelocity = this.xCurrentVelocity;
    double yInitialVelocity = this.yCurrentVelocity;
    double multiplier = Math.pow(0.99, timeSlice);
    this.xCurrentVelocity *= multiplier;
    this.yCurrentVelocity *= multiplier;
    
    if (Math.abs(this.xCurrentVelocity) < MINIMUM_SPEED) {
      this.xCurrentVelocity = 0.0;
    }
    if (Math.abs(this.yCurrentVelocity) < MINIMUM_SPEED) {
      this.yCurrentVelocity = 0.0;
    }
    
    this.xCurrentLocation += timeSlice * (xInitialVelocity + this.xCurrentVelocity) / 2.0;
    this.yCurrentLocation += timeSlice * (yInitialVelocity + this.yCurrentVelocity) / 2.0;
  }
  
  /**
   *  Method to return the current x location of the ball
   *  @return double-precision value of the x location
   */
  public double getXCurrentLocation() {
    return this.xCurrentLocation;
  }
  
  /**
   *  Method to return the current y location of the ball
   *  @return double-precision value of the y location
   */
  public double getYCurrentLocation() {
    return this.yCurrentLocation;
  }
  
  /**
   *  Method to return the current x velocity of the ball
   *  @return double-precision value of the y velocity
   */
  public double getXCurrentVelocity() {
    return this.xCurrentVelocity;
  }
  
  /**
   *  Method to return the current y velocity of the ball
   *  @return double-precision value of the y velocity
   */
  public double getYCurrentVelocity() {
    return this.yCurrentVelocity;
  }

  /**
   * Main method to test SoccerBall class
   * @param args
   */
  public static void main(String[] args) {
    System.out.println("   Soccer Ball Tests");
    
    System.out.println("\nCreating a new soccer ball at (0, 0) with inital velocity (0, 0)");
    SoccerBall testBall = new SoccerBall(0, 0, 0, 0);
    
    System.out.println("The current x location is: " + testBall.getXCurrentLocation());
    System.out.println("The current y location is: " + testBall.getYCurrentLocation());
    System.out.println("The current x velocity is: " + testBall.getXCurrentVelocity());
    System.out.println("The current y velocity is: " + testBall.getYCurrentVelocity());
    
    System.out.println("\nCreating a new soccer ball at (-10, 10) with inital velocity (-10, 10)");
    SoccerBall testBall2 = new SoccerBall(-10, 10, -10, 10);
    
    System.out.println("The current x location is: " + testBall2.getXCurrentLocation());
    System.out.println("The current y location is: " + testBall2.getYCurrentLocation());
    System.out.println("The current x velocity is: " + testBall2.getXCurrentVelocity());
    System.out.println("The current y velocity is: " + testBall2.getYCurrentVelocity());
    
    System.out.println("\nMoving the ball 10 seconds forward in time\n");
    testBall2.tick(10);
    
    System.out.println("The current x location is: " + testBall2.getXCurrentLocation());
    System.out.println("The current y location is: " + testBall2.getYCurrentLocation());
    System.out.println("The current x velocity is: " + testBall2.getXCurrentVelocity());
    System.out.println("The current y velocity is: " + testBall2.getYCurrentVelocity());
  }
}
