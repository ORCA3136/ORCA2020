/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;


/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // Subsystems:
 
  private final DriveTrain m_driveTrain = new DriveTrain();

  // Controllers:
  private final XboxController m_driver = new XboxController(1);
  

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings:
    configureButtonBindings();

    // Intake default command (Stop intake):
    

    // Drivetrain default command (Arcade drive):
    m_driveTrain.setDefaultCommand(new RunCommand(() -> m_driveTrain.Drive(
      m_driver.getY(GenericHID.Hand.kLeft) * Constants.kLeftDriveScaling,
      m_driver.getY(GenericHID.Hand.kRight) * Constants.kRightDriveScaling),m_driveTrain
    ));

   
  }

  /**
   * Configures the bindings for the controller buttons
   */
  private void configureButtonBindings() {
    // Change forward direction (Drive Y):
    new JoystickButton(m_driver, XboxController.Button.kY.value);
    // Activate shooter (Manip A):
   
  }

  /**
   * Sets the command to run during autonomous
   * @return The autonomous command
   */
  public Command getAutonomousCommand() {
    return null;
  }

  /**
   * Trigger to return true when up is pressed on manip dpad
   */
  // private class ManipDPadUp extends Trigger {
  //   @Override
  //   public boolean get() {
  //     int pov = m_manip.getPOV();
  //     return pov == 315 || (pov <= 45 && pov >= 0);
  //   }
  // }

  // /**
  //  * Trigger to return true when down is pressed on manip dpad
  //  */
  // private class ManipDPadDown extends Trigger {
  //   @Override
  //   public boolean get() {
  //     int pov = m_manip.getPOV();
  //     return pov >= 135 && pov <= 225;
  //   }
  // }

  /**
   * Trigger to return true when the right trigger of the manip controller
   * is pressed in 3/4 of the way or more 
   */
  
  }

