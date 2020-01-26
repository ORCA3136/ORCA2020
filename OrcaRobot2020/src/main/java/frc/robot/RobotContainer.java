/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.NeoSoli;
import frc.robot.subsystems.flywheel;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants;
import frc.robot.commands.Auto;




 /* This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  // private final Dummy_Test_System m_testbed = new Dummy_Test_System();

  private final Auto m_drive = new Auto();
  private final XboxController m_driver = new XboxController(1);
 
  final Intake m_intake = new Intake();
  final Conveyor m_conveyor = new Conveyor();
  final flywheel m_flywheel = new flywheel();
  final Drivetrain m_driveTrain = new Drivetrain();
  private final NeoSoli m_NeoSoli = new NeoSoli();
 

 
 
   
  public RobotContainer() {
     // Configure the button bindings:
    configureButtonBindings();

    
    new JoystickButton(m_driver, XboxController.Button.kY.value).whenHeld(new RunCommand(() -> NeoSoli.forward(),m_NeoSoli ));
    // Activate shooter (Manip A):
    
    new JoystickButton(m_driver, XboxController.Button.kA.value).whenPressed(new RunCommand(() -> AutoShoot(), m_flywheel ));


    // Intake default command (Stop intake):
    m_intake.setDefaultCommand(new RunCommand(() -> m_intake.intakeStop(), m_intake));

    // Drivetrain default command (Tank drive):
    m_driveTrain.setDefaultCommand(new RunCommand(() -> m_driveTrain.Drive(m_driver.getY(GenericHID.Hand.kLeft) * Constants.kLeftDriveScaling,
            m_driver.getY(GenericHID.Hand.kRight) * Constants.kRightDriveScaling), m_driveTrain));
  }

  private Object AutoShoot() {
    return null;
  }

  /*
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
   
    // Activate shooter (Manip A):
   
    // Move conveyor up (Manip RB):
    new JoystickButton(m_driver, XboxController.Button.kBumperRight.value) .whileActiveContinuous(new RunCommand(() -> m_conveyor.raiseConveyor(), m_conveyor));
    
    // Run intake inward (Manip LB):
    new JoystickButton(m_driver, XboxController.Button.kBumperLeft.value).whileHeld(new RunCommand(() -> m_intake.intakeIn(), m_intake));
  
  }



  
  // * Use this to pass the autonomous command to the main {@link Robot} class.
   
 //  * @return the command to run in autonomous
   
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_drive;
  }
}
