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
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.auto.Auto;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Flywheel;
import frc.robot.subsystems.InertialSensor;
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
  private final InertialSensor m_inertialSensor = new InertialSensor();

  SendableChooser<Command> m_chooser = new SendableChooser<>();

  private final XboxController controller = new XboxController(1);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {

    m_drivetrain.setDefaultCommand(
      new RunCommand(() -> m_drivetrain.drive(controller),m_drivetrain));

    m_chooser.setDefaultOption("Auto 1", new Auto(m_drivetrain));
    SmartDashboard.putData("Auto Chooser: ", m_chooser);


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

 /**
  * INTAKE RELATED COMMANDS
  */
    // Right Bumper Button - Deploy Intake and start pulling in. This is a bit of an example based upon what Luke was asking for, but I think we need to consider if this is the right combination.
    //but it serves as an example of chaining commands - with the .andThen structure. Other options include .alongWith for parallel commands
    new JoystickButton(controller, XboxController.Button.kBumperRight.value)
    .whenHeld(new InstantCommand(m_intake::deployIntake, m_intake).andThen(new InstantCommand(m_intake::intakeIn, m_intake)))
     .whenReleased(new InstantCommand(m_intake::retractIntake, m_intake).andThen(new InstantCommand(m_intake::intakeStop, m_intake)));

   //X Button -   
   new JoystickButton(controller, XboxController.Button.kBumperRight.value)
   .whenPressed(new InstantCommand(m_intake::deployIntake, m_intake).andThen(new InstantCommand(m_intake::intakeStop, m_intake)));

    // // B Button - Intake Out - runs the rollers pushing power cells out
    // new JoystickButton(controller, XboxController.Button.kB.value)
    //     .whileHeld(new InstantCommand(m_intake::intakeOut, m_intake))  //it is entirely possible these whenHeld Commands need to be made into run commands since they are longer running
    //     .whenReleased(new InstantCommand(m_intake::intakeStop, m_intake));
 
   //?? Button - Intake In, runs the rollers pulling power cells in
    // new JoystickButton(controller, XboxController.Button.??.value)
    // .whenHeld(new InstantCommand(m_intake::intakeIn, m_intake)) //it is entirely possible these whenHeld Commands need to be made into run commands since they are longer running
    // .whenReleased(new InstantCommand(m_intake::intakeStop, m_intake));
   
 /**
  * POWERCELL SHOOTER RELATED COMMANDS
  */  
    //A Button - Vision Alignment
    new JoystickButton(controller, XboxController.Button.kA.value)
    .whenHeld(new InstantCommand(() -> m_drivetrain.visionAlignment(m_limelight)));

   //B Button - Start flywheel, and run the powercells out
   new JoystickButton(controller, XboxController.Button.kB.value)
   .whenHeld(new InstantCommand(m_flyWheel::runFlywheelWithoutPID, m_flyWheel)
      .andThen(new WaitCommand(1))
      .andThen(new InstantCommand(m_conveyor::openHopperToFlyWheel, m_conveyor),new InstantCommand(m_conveyor::raiseConveyor, m_conveyor)))
   .whenReleased(new InstantCommand(m_flyWheel::stop, m_flyWheel)
      .andThen(new InstantCommand(m_conveyor::stopConveyor, m_conveyor), new InstantCommand(m_conveyor::closeHopperToFlywheel, m_conveyor)));

  //Left Stick Button - PID Shooter - Test only (NOTE: this is actually currently setup to just do Right motor onlY)
//  new JoystickButton(controller, XboxController.Button.kStickLeft.value)
//   .whenHeld(new InstantCommand(m_flyWheel::runRightFlyWheelOnly, m_flyWheel)) //TODO - need to update this to actual PID shooter 
//   .whenReleased(new InstantCommand(m_flyWheel::stop, m_flyWheel));

   //Start Button - Open / Close the hopper stopper
   new JoystickButton(controller, XboxController.Button.kStart.value)
   .whenHeld(new InstantCommand(m_conveyor::openHopperToFlyWheel, m_conveyor))
  .whenReleased(new InstantCommand(m_conveyor::closeHopperToFlywheel, m_conveyor));

/**
 * CLIMBER RELATED COMMANDS
 */
   // Climber up & Down
   new JoystickButton(controller, XboxController.Button.kBumperLeft.value)
   .whenHeld(new InstantCommand(m_climber::erectClimber, m_climber))
  .whenReleased(new InstantCommand(m_climber::retractClimber, m_climber));

  
 }
//returns the selected command to the robot.
 public Command getAutonomousCommand(){
   return m_chooser.getSelected();
 }
 /**
  * Getter for the flywheel
  * @return Flywheel
  */
 public Flywheel getFlyWheel()
 {
    return m_flyWheel;
 }

  /**
   * Getter for the InertialSensor
   */
  public InertialSensor getInertialSensor()
  {
    return m_inertialSensor;
  }
}