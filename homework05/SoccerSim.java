/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  SoccerSim.java
 *  Purpose       :  The main program for the SoccerSim class
 *  Author        :  Jeremy Goldberg
 *  Date          :  2018-03-27
 *  Description   :  This class provides a bunch of methods which may be useful for the ClockSolver class
 *                   for Homework 5.  Includes the following:
 *                   handleInitialArguments()
 *                   isCollisionDetected()
 *                   isMovingBall()
 *                   printInitialReport()
 *                   printProgressReport()
 *                   printCollisionReport()      
 *                   main()    
 *
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  NumberFormetException when arguments are not numbers
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class SoccerSim {
  
  private double timeSlice = 1.0;
  private SoccerBall[] soccerBallSet = null;
  private final static String inputError = "All inputs must be numbers";
  public double currentTime = 0.0;
  private int firstBallCollided = 0;
  private int secondBallCollided = 0;
  private int numberOfBalls = 0;
  private final double poleXLocation = 10.0;
  private final double poleYLocation = 10.0;
  private final double poleRadius = 0.25;
  private final int poleIndex = -1;

  public SoccerSim() {}
  
  /**
   *  Method to check validity of arguments and assign them to variables
   *  @throws NumberFormatExecption when arguments are not numbers
   */
  public void handleInitialArguments(String args[]) {
    // Every group of four arguments is a ball
    // args[0] is the starting x position
    // args[1] is the starting y position
    // args[2] is the starting x velocity
    // args[3] is the starting y velocity
    // if present the arg after all the groups of four is the time slice

    System.out.println( "\n  Welcome to Soccer Simulator!\n" ) ;
    if(args.length < 4) {
      System.out.println( "You must enter at least four arguments");
      System.exit(1);
    }
    
    if(args.length % 4 == 1 || args.length % 4 == 0) {
      this.numberOfBalls = (int)Math.floor(args.length / 4);
      this.soccerBallSet = new SoccerBall[this.numberOfBalls];
      try {
        for (int i = 0; i < this.numberOfBalls; i++) {
          double xStartLocation = Double.parseDouble(args[4 * i]);
          double yStartLocation = Double.parseDouble(args[4 * i + 1]);
          double xStartVelocity = Double.parseDouble(args[4 * i + 2]);
          double yStartVelocity = Double.parseDouble(args[4 * i + 3]);
          this.soccerBallSet[i] = new SoccerBall(xStartLocation, yStartLocation, xStartVelocity, yStartVelocity);
        }
      }
      catch (NumberFormatException nfe) {
        System.out.println(inputError);
        System.exit(1);
      }
     
      if (args.length % 4 == 1) {
        try {
          this.timeSlice = Double.parseDouble(args[args.length - 1]);
          if (this.timeSlice <= 0) {
            System.out.println("Time sclice must be greater than 0");
            System.exit(1);
          }
        }
        catch (NumberFormatException nfe) {
          System.out.println(inputError);
          System.exit(1);
        }
      }
    } else {
      System.out.println("Not correct amount of arguments");
      System.exit(1);
    }
    
  }
  
  /**
   *  Method to check if any two balls have collided
   *  @return boolean corresponding to collision detection
   */
  public boolean isCollisionDetected() {
    for (int i = 0; i < this.numberOfBalls - 1; i++) {
      for (int t = i + 1; t < this.numberOfBalls; t++) {
        if (Math.abs(this.soccerBallSet[i].getXCurrentLocation() - this.soccerBallSet[t].getXCurrentLocation()) < (2 * SoccerBall.radius) &&
            Math.abs(this.soccerBallSet[i].getYCurrentLocation() - this.soccerBallSet[t].getYCurrentLocation()) < (2 * SoccerBall.radius)) {
          this.firstBallCollided = i;
          this.secondBallCollided = t;
          return true;
        }
      }
    }
    for (int j = 0; j < this.numberOfBalls; j++) {
      if (Math.abs(this.soccerBallSet[j].getXCurrentLocation() - this.poleXLocation) < (SoccerBall.radius + this.poleRadius) &&
          Math.abs(this.soccerBallSet[j].getYCurrentLocation() - this.poleYLocation) < (SoccerBall.radius + this.poleRadius)) {
        this.firstBallCollided = j;
        this.secondBallCollided = poleIndex;
        return true;
      }
    }
    return false;
  }
  
  /**
   *  Method to check if any ball is moving
   *  @return boolean corresponding to if a ball is moving
   */
  public boolean isMovingBall() {
    for (int i = 0; i < this.numberOfBalls; i++) {
      if (Math.abs(soccerBallSet[i].getXCurrentVelocity()) > 0 || 
          Math.abs(soccerBallSet[i].getYCurrentVelocity()) > 0)
        return true;
    }
    return false;
  }
  
  /**
   *  Method to print the initial x and y locations and the initial x and y velocities of each ball
   */
  public void printInitialReport() {
    System.out.println("INITIAL REPORT AT " + this.currentTime + " seconds");
    System.out.println("Pole:     <" + this.poleXLocation + ", " + this.poleYLocation + ">");
    for(int i = 0; i < this.soccerBallSet.length; i++) {
      System.out.println(i + ":      position <" + 
                         this.soccerBallSet[i].getXCurrentLocation() + ", " +
                         this.soccerBallSet[i].getYCurrentLocation() + ">     velocity <" +
                         this.soccerBallSet[i].getXCurrentVelocity() + ", " +
                         this.soccerBallSet[i].getYCurrentVelocity() + ">");
    }
  }
  
  /**
   *  Method to print the current x and y locations and the current x and y velocities of each ball
   */
  public void printProgressReport() {
    System.out.println("\nPROGRESS REPORT AT " + this.currentTime + " seconds");
    for(int i = 0; i < this.soccerBallSet.length; i++) {
      System.out.println(i + ":      position <" + 
                         this.soccerBallSet[i].getXCurrentLocation() + ", " +
                         this.soccerBallSet[i].getYCurrentLocation() + ">     velocity <" +
                         this.soccerBallSet[i].getXCurrentVelocity() + ", " +
                         this.soccerBallSet[i].getYCurrentVelocity() + ">");
    }
  }
  
  /**
   *  Method to print time at which two balls collided and which balls collided
   */
  public void printCollisionReport() {
    System.out.println("\nCollision Detected");
    System.out.println("Time: " + this.currentTime );
    System.out.println("First Ball: " + this.firstBallCollided);
    System.out.println("AND");
    if (this.secondBallCollided != poleIndex) {
      System.out.println("Second Ball: " + this.secondBallCollided);
    } else {
      System.out.println("Pole");
    }
    System.exit(1);
  }
  
  /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework05.html
   *  @param  args  String array of the arguments from the command line
   *                Every group of 4 arguments is a ball
   *                args[0] is the starting x position
                    args[1] is the starting y position
                    args[2] is the starting x velocity
                    args[3] is the starting y velocity
                    if present the arg after all the groups of four is the time slice 
   */
  public static void main(String[] args) {
    SoccerSim ss = new SoccerSim();
    
    ss.handleInitialArguments(args);
    ss.printInitialReport();
    if (ss.isCollisionDetected()) {
      ss.printCollisionReport();
    }
    
    while (ss.isMovingBall()) {
      if (ss.isCollisionDetected()) {
        ss.printCollisionReport();
      }
      
      for(int i = 0; i < ss.soccerBallSet.length; i++) {
        ss.soccerBallSet[i].tick(ss.timeSlice);
      }
      ss.currentTime += ss.timeSlice;
      ss.printProgressReport();
    }
    System.out.println("No Collision Detected");
  }

}
