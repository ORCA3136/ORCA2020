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
import frc.robot.subsystems.Flywheel;
import frc.robot.subsystems.Intake;






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
  
  XboxController controller = new XboxController(1);
  ColorSoli m_ColorSoli = new ColorSoli(); 
  Intake m_Intake = new Intake();
  Conveyor m_Conveyor = new Conveyor();
  Flywheel m_FlyWheel = new Flywheel();
  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    m_drivetrain.setDefaultCommand(
        new RunCommand(() -> Drivetrain.Drive(controller.getY(GenericHID.Hand.kLeft) * Constants.kLeftDriveScaling,
            controller.getY(GenericHID.Hand.kRight) * Constants.kRightDriveScaling, controller), m_drivetrain));

            m_FlyWheel.setDefaultCommand(
              new RunCommand(() -> Flywheel.test(controller), m_FlyWheel));
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
    new JoystickButton(controller, XboxController.Button.kX.value)
        .whenPressed(new RunCommand(() -> ColorSoli.forward(), m_ColorSoli));


    // B button - Intake out
    new JoystickButton(controller, XboxController.Button.kB.value)
        .whenHeld(new RunCommand(() -> m_Intake.intakeOut(), m_ColorSoli));

    new JoystickButton(controller, XboxController.Button.kB.value)
    .whenReleased(new RunCommand(() -> m_Intake.intakeStop(), m_ColorSoli));
 


   // Y button - PTO gearbox
    new JoystickButton(controller, XboxController.Button.kY.value)
    .whenReleased(new RunCommand(() -> Drivetrain.reverse(), m_drivetrain ));
  
    new JoystickButton(controller, XboxController.Button.kY.value)
    .whenPressed(new RunCommand(() -> Drivetrain.forward(), m_drivetrain ));
    
    //new JoystickButton(controller, XboxController.Button.kY.value)
    //.whenHeld(new RunCommand(() -> Drivetrain.WinchDown(controller), m_drivetrain));

  
   //A button - Intake in
    new JoystickButton(controller, XboxController.Button.kA.value)
    .whenHeld(new RunCommand(() -> m_Intake.intakeIn(), m_ColorSoli ));

    new JoystickButton(controller, XboxController.Button.kA.value)
    .whenReleased(new RunCommand(() -> m_Intake.intakeStop(), m_ColorSoli));



   //Left Stick button - convey Soli fire
    new JoystickButton(controller, XboxController.Button.kStickLeft.value)
    .whenPressed(new RunCommand(() -> m_Conveyor.raiseConveyor(), m_Conveyor ));
  


   //Right Stick button - convey Soli retract
    new JoystickButton(controller, XboxController.Button.kStickRight.value)
    .whenPressed(new RunCommand(() -> m_Conveyor.lowerConveyor(), m_Conveyor ));
 

/*
   //Start button - color soli fire
    new JoystickButton(controller, XboxController.Button.kStart.value)
    .whenPressed(new RunCommand(() -> m_ColorSoli.forward(),m_ColorSoli  ));
   


   //Start button - color soli retract
    new JoystickButton(controller, XboxController.Button.kBack.value)
    .whenPressed(new RunCommand(() -> m_ColorSoli.reverse(), m_ColorSoli ));
 */
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