/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.ColorSoli;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeSoli;
import frc.robot.subsystems.NeoSoli;
import frc.robot.subsystems.flywheel;




/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  //private final Dummy_Test_System m_testbed = new Dummy_Test_System();

  private final Drivetrain m_drivetrain = new Drivetrain();
  
  XboxController m_driver = new XboxController(1);
  NeoSoli m_Neo = new NeoSoli();
  ColorSoli m_Con = new ColorSoli(); 
  IntakeSoli m_in = new IntakeSoli();
  ColorSoli m_Color = new ColorSoli();
  Intake m_intake = new Intake();
  Conveyor m_convoy = new Conveyor();
  flywheel m_fly = new flywheel();

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    m_drivetrain.setDefaultCommand(
        new RunCommand(() -> Drivetrain.Drive(m_driver.getY(GenericHID.Hand.kLeft) * Constants.kLeftDriveScaling,
            m_driver.getY(GenericHID.Hand.kRight) * Constants.kRightDriveScaling, m_driver), m_drivetrain));

            m_fly.setDefaultCommand(
              new RunCommand(() -> flywheel.Run(m_driver), m_fly));
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    // X button - Color solenoid up
    new JoystickButton(m_driver, XboxController.Button.kX.value)
        .whenPressed(new RunCommand(() -> ColorSoli.forward(), m_Con));

    // B button - Intake out
    new JoystickButton(m_driver, XboxController.Button.kB.value)
        .whenHeld(new RunCommand(() -> m_intake.intakeOut(), m_Con));

        
    new JoystickButton(m_driver, XboxController.Button.kB.value)
    .whenHeld(new RunCommand(() -> m_intake.intakeOut(), m_Con));
 
   // Y button - PTO gearbox
    new JoystickButton(m_driver, XboxController.Button.kY.value)
    .whenReleased(new RunCommand(() -> NeoSoli.reverse(), m_Neo ));
  
    new JoystickButton(m_driver, XboxController.Button.kY.value)
    .whenHeld(new RunCommand(() -> NeoSoli.forward(), m_Neo ));
    
   //A button - Intake in
    new JoystickButton(m_driver, XboxController.Button.kA.value)
    .whenHeld(new RunCommand(() -> m_intake.intakeIn(), m_Con ));

   //Left Stick button - convey Soli fire
    new JoystickButton(m_driver, XboxController.Button.kStickLeft.value)
    .whenPressed(new RunCommand(() -> m_convoy.raiseConveyor(), m_convoy ));
  
   //Right Stick button - convey Soli retract
    new JoystickButton(m_driver, XboxController.Button.kStickRight.value)
    .whenPressed(new RunCommand(() -> m_convoy.lowerConveyor(), m_convoy ));
 
   //Start button - color soli fire
    new JoystickButton(m_driver, XboxController.Button.kStart.value)
    .whenPressed(new RunCommand(() -> m_Color.forward(),m_Color  ));
   
   //Start button - color soli retract
    new JoystickButton(m_driver, XboxController.Button.kBack.value)
    .whenPressed(new RunCommand(() -> m_Color.reverse(), m_Color ));
 
  }


  /*
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return Auto();
  }

  private Command Auto() {
    return null;
  }
}