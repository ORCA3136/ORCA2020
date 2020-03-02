/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class Conveyor extends SubsystemBase {
  // Motor Controllers:
  private CANSparkMax leftConveyor;
  private CANSparkMax rightConveyor;
  private DoubleSolenoid ConveySoli;


  public Conveyor(){
    leftConveyor = new CANSparkMax(Constants.kConveyor1, MotorType.kBrushless);
    rightConveyor = new CANSparkMax(Constants.kConveyor2, MotorType.kBrushless);
    ConveySoli = new DoubleSolenoid(Constants.kHopperStopperForward, Constants.kHopperStopperReverse);
    rightConveyor.follow(leftConveyor,true);
  }


  /**
   * Runs conveyor up
   */
  public void lowerConveyor() {
    leftConveyor.set( Constants.kConveyorSpeed *-1);
    
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
