/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class Conveyor extends SubsystemBase {
  // Motor Controllers:
  private CANSparkMax leftConveyor;
  private CANSparkMax rightConveyor;
  private DoubleSolenoid ConveySoli;
  
  private CANEncoder leftEncoder;
  private CANEncoder rightEncoder;
    
  private CANPIDController leftController;
  private CANPIDController rightController;


  public Conveyor(){
    leftConveyor = new CANSparkMax(Constants.kConveyor1, MotorType.kBrushless);
    rightConveyor = new CANSparkMax(Constants.kConveyor2, MotorType.kBrushless);
    ConveySoli = new DoubleSolenoid(Constants.kHopperStopperForward, Constants.kHopperStopperReverse);
    rightConveyor.follow(leftConveyor,true);
    leftEncoder = leftConveyor.getEncoder();
    leftController = leftConveyor.getPIDController();
    leftController.setFeedbackDevice(leftEncoder);

    rightEncoder = rightConveyor.getEncoder();
    rightController = rightConveyor.getPIDController();
    rightController.setFeedbackDevice(rightEncoder);
  }


  /**
   * Runs conveyor up
   */
  public void lowerConveyor() {
    leftConveyor.set((Constants.kConveyorSpeed *-1)/2);
    
  }

  /**
   * Runs conveyor down
   */
  public void raiseConveyor() {
    leftConveyor.set(Constants.kConveyorSpeed);
   
  }

  /**
   * Stops conveyor
   */
  public void stopConveyor() {
    leftConveyor.set( 0);
    
  }

  public void toggle() {
  //Switches between fired a retracted per called
    if (Constants.ToggleSoli = true) {
      openHopperToFlyWheel();
      Constants.ToggleSoli = false;
    } else if (Constants.ToggleSoli = false) {
      closeHopperToFlywheel();
      Constants.ToggleSoli = true;
    } else {
      off();
    }
  }

    /**
   * used to set the conveyor down  a specific value....
   */  public boolean specificLower(double distance) 
   {
    boolean complete = false;
    getLeftEncoder().setPosition(0); //set the position to 0
    Double leftPosition = getLeftEncoder().getPosition();
    SmartDashboard.putNumber("Left Enc Pos: ", leftPosition);
    //really only need to get this once...
    int perRev =  getLeftEncoder().getCountsPerRevolution();
    double totalRevolutions = distance*perRev;
    double currentRevolutions = 0;
  
    //QUESTION DO WE NEED TO CHANGE THIS TO AN ABS VALUE???
    while(currentRevolutions<totalRevolutions)
    {
      //set the motors to running
      lowerConveyor();
      currentRevolutions = getLeftEncoder().getPosition() * perRev;
      SmartDashboard.putNumber("Current Revs", currentRevolutions);
    }
    complete = true;

    return complete;
  }

   /**
   * used to set the conveyor down  a specific value....
   */  public boolean specificRaise(double distance) 
   {
    boolean complete = false;
    getLeftEncoder().setPosition(0); //set the position to 0
    Double leftPosition = getLeftEncoder().getPosition();
    SmartDashboard.putNumber("Left Enc Pos: ", leftPosition);
    //really only need to get this once...
    int perRev =  getLeftEncoder().getCountsPerRevolution();
    double totalRevolutions = distance*perRev;
    double currentRevolutions = 0;
  
    //QUESTION  - DO WE NEED TO CHANGE THIS TO AN ABS value???
    while(currentRevolutions<totalRevolutions)
    {
      //set the motors to running
      raiseConveyor();
      currentRevolutions = getLeftEncoder().getPosition() * perRev;
      SmartDashboard.putNumber("Current Revs", currentRevolutions);
    }
    complete = true;

    return complete;
  }

  public CANEncoder getLeftEncoder()
  {
    return leftEncoder;
  }

  public CANEncoder getRightEncoder()
  {
    return rightEncoder;
  }

  public CANPIDController getLeftPIDController()
  {
    return leftController;
  }

  public CANPIDController getRightPIDController()
  {
    return rightController;
  }

  //Open up the hopper stopper so powercells can flow into the flywheel 
  public void openHopperToFlyWheel() {
    ConveySoli.set(DoubleSolenoid.Value.kForward);
  }
  //Close the hopper stopper so powercells don't flow into the flywheel
  public void closeHopperToFlywheel() {
    ConveySoli.set(Value.kReverse);
  }

  //TODO - find out what this method is for?
  public void off() {
    ConveySoli.set(Value.kOff);
  }
}
