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
  private CANSparkMax m_conveyor1;
  private CANSparkMax m_conveyor2;
  private DoubleSolenoid ConveySoli;


  public Conveyor(){
    m_conveyor1 = new CANSparkMax(Constants.kConveyor1, MotorType.kBrushless);
    m_conveyor2 = new CANSparkMax(Constants.kConveyor2, MotorType.kBrushless);
    ConveySoli = new DoubleSolenoid(Constants.kHopperStopperForward, Constants.kHopperStopperReverse);
  }


  /**
   * Runs conveyor up
   */
  public void lowerConveyor() {
    m_conveyor1.set( Constants.kConveyorSpeed *-1);
    m_conveyor2.set( Constants.kConveyorSpeed);
  }

  /**
   * Runs conveyor down
   */
  public void raiseConveyor() {
    m_conveyor1.set(Constants.kConveyorSpeed);
    m_conveyor2.set(Constants.kConveyorSpeed *-1);
  }

  /**
   * Stops conveyor
   */
  public void stopConveyor() {
    m_conveyor1.set( 0);
    m_conveyor2.set( 0);
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
