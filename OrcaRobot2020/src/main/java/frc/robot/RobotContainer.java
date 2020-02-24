/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.auto.Auto;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Flywheel;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Limelight;






/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer
 {
  // The robot's subsystems
  private final Drivetrain m_drivetrain = new Drivetrain(); 
  private final Conveyor m_conveyor= new Conveyor();
  private final Intake m_intake =  new Intake(m_conveyor);
  private final Flywheel m_flyWheel = new Flywheel();
  private final Limelight m_limelight = new Limelight();
  private final Climber m_climber = new Climber();

  SendableChooser<Command> m_chooser = new SendableChooser<>();

  private final XboxController controller = new XboxController(1);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {

   
    m_chooser.setDefaultOption("Auto 1", new Auto(m_drivetrain));
    SmartDashboard.putData("Auto Chooser: ", m_chooser);

    m_drivetrain.setDefaultCommand(
        new RunCommand(() -> m_drivetrain.drive(controller),m_drivetrain));

    //m_FlyWheel.setDefaultCommand(
      //  new RunCommand(() -> m_FlyWheel.test(controller),m_FlyWheel));

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

    // Left Bumper Button - Deploy Intake
    new JoystickButton(controller, XboxController.Button.kBumperLeft.value)
    .whenPressed(new InstantCommand(m_intake::toggle, m_intake));

  


   // Right Bumper Button - Flywheel
   new JoystickButton(controller, XboxController.Button.kBumperRight.value)
   .whenHeld(new RunCommand(() -> m_flyWheel.test1()));

   new JoystickButton(controller, XboxController.Button.kBumperRight.value)
  .whenReleased(new RunCommand(() -> m_flyWheel.stop()));


    // B Button - Intake Out
    new JoystickButton(controller, XboxController.Button.kB.value)
        .whenHeld(new RunCommand(() -> m_intake.intakeOut()));

    new JoystickButton(controller, XboxController.Button.kB.value)
    .whenReleased(new RunCommand(() -> m_intake.intakeStop()));
 

  
   //A Button - Intake In
    new JoystickButton(controller, XboxController.Button.kA.value)
    .whenHeld(new RunCommand(() -> m_intake.intakeIn()));

    new JoystickButton(controller, XboxController.Button.kA.value)
    .whenReleased(new RunCommand(() -> m_intake.intakeStop()));



   //Left Stick Button - PID Shooter
   new JoystickButton(controller, XboxController.Button.kStickLeft.value)
   .whenHeld(new RunCommand(() -> m_flyWheel.test2()));

   new JoystickButton(controller, XboxController.Button.kStickLeft.value)
  .whenReleased(new RunCommand(() -> m_flyWheel.stop()));

   


   //Right Stick Button  - Stop Shooter
   new JoystickButton(controller, XboxController.Button.kStickRight.value)
   .whenHeld(new RunCommand(() -> m_flyWheel.test1()));

   new JoystickButton(controller, XboxController.Button.kStickRight.value)
  .whenReleased(new RunCommand(() -> m_flyWheel.stop()));

   
 
  

    //X Button - Conveyor Soli fire
    new JoystickButton(controller, XboxController.Button.kX.value)
    .whenPressed(new RunCommand(() -> m_intake.retractIntake()));


    //Y Button - Conveyor Soli retact
     new JoystickButton(controller, XboxController.Button.kY.value)
   .whenHeld(new RunCommand(() -> m_drivetrain.visionAlignment(m_limelight)));
   


   //Start Button - climb up
   new JoystickButton(controller, XboxController.Button.kStart.value)
   .whenHeld(new RunCommand(() -> m_conveyor.forward()));


   new JoystickButton(controller, XboxController.Button.kStart.value)
  .whenReleased(new RunCommand(() ->m_conveyor.reverse()));


   // Back Button
   new JoystickButton(controller, XboxController.Button.kBack.value)
   .whenHeld(new RunCommand(() -> m_climber.retractClimber()));

   new JoystickButton(controller, XboxController.Button.kBack.value)
  .whenReleased(new RunCommand(() -> m_climber.erectClimber()));

  
 }
//returns the selected command to the robot.
 public Command getAutonomousCommand(){
   return m_chooser.getSelected();
 }

}