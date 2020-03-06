/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.auto.Auto;
import frc.robot.auto.DriveOnlyAuto;
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
  private final Constants m_constants= new Constants();

  SendableChooser<Command> m_chooser = new SendableChooser<>();

  private final XboxController controller = new XboxController(1);
  private final Joystick Joystick = new Joystick(0);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {

    m_drivetrain.setDefaultCommand(
      new RunCommand(() -> m_drivetrain.drive(controller),m_drivetrain));

    

    m_chooser.setDefaultOption("Shoot Then Drive", new Auto(m_drivetrain,m_flyWheel,m_conveyor));
    m_chooser.addOption("Drive Only",new DriveOnlyAuto(m_drivetrain));
    SmartDashboard.putData("Auto Chooser: ", m_chooser);

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


 /*
  *
  * Xbox Controls
  *
  */
   
  // Right Stick Button - Deploy Intake
    new JoystickButton(controller, XboxController.Button.kStickRight.value)
      .whenHeld(new InstantCommand(m_intake::deployIntake, m_intake)  
        .andThen(new InstantCommand(m_conveyor::stopConveyor, m_conveyor))); 
       

    // Left Stick Button - Retract Intake
    new JoystickButton(controller, XboxController.Button.kStickLeft.value)
  .whenHeld(new InstantCommand(m_intake::retractIntake, m_intake)
    .andThen(new InstantCommand(m_conveyor::stopConveyor, m_conveyor))); 
   
  // left Bumper - intake in
  new JoystickButton(controller, XboxController.Button.kBumperLeft.value)
  .whenHeld(new InstantCommand(()  -> m_intake.intakeIn(),m_intake))
    .whenReleased(new InstantCommand(m_intake::intakeStop, m_intake));
   
    // right Bumper - intake out
  new JoystickButton(controller, XboxController.Button.kBumperRight.value)
  .whenHeld(new InstantCommand(()  -> m_intake.intakeOut(),m_intake))
    .whenReleased(new InstantCommand(m_intake::intakeStop, m_intake));

  // //A few test buttons for PID
  // new JoystickButton(controller, XboxController.Button.kA.value)
  // .whenHeld(new InstantCommand(m_flyWheel::runFlyWheelWithPID,m_intake))
  // .whenReleased(new InstantCommand(m_flyWheel::stop, m_flyWheel));

  // new JoystickButton(controller, XboxController.Button.kB.value)
  // .whenHeld(new InstantCommand(()->m_flyWheel.runFlyWheelWithPID(1500.00)))
  // .whenReleased(new InstantCommand(m_flyWheel::stop, m_flyWheel));
  
  // new JoystickButton(controller, XboxController.Button.kY.value)
  // .whenHeld(new InstantCommand(()->m_conveyor.closeHopperToFlywheel(), m_conveyor));
  
  

  /*
  *
  * JOYSTICK BUTTONS
  *
  */
  
  //Winch - X (NUKE!!!!!!!!)
  new JoystickButton(Joystick, m_constants.kX)
    .whenHeld(new InstantCommand(() -> m_drivetrain.winchUp(controller, Joystick)))
     .whenReleased(new InstantCommand(() -> m_drivetrain.stop())
     .andThen(new InstantCommand (() -> m_drivetrain.engageDrivePTO())));

  //Climber up & Down -A (NUKE!!!!!!!!)
  new JoystickButton(Joystick,m_constants.kA)
    .whenHeld(new InstantCommand(() -> m_climber.NukeClimb(controller),m_climber))
      .whenReleased(new InstantCommand(m_climber::retractClimber, m_climber));

  //Auto Align - LB/L1
  new JoystickButton(Joystick,m_constants.kY)
    .whileHeld(new InstantCommand(() -> m_limelight.startTracking())
    .andThen(new InstantCommand(() -> m_drivetrain.visionAlignment(m_limelight))));

  new JoystickButton(Joystick, m_constants.kY)
    .whenReleased(new InstantCommand(() -> m_limelight.stopTracking()));

  //RB Button - Start flywheel, and run the powercells out
  new JoystickButton(Joystick,m_constants.kLT)
   .whenHeld(new InstantCommand(() -> m_flyWheel.runFlywheelSector())
   .andThen(new InstantCommand(m_conveyor::stopConveyor, m_conveyor))
      .andThen(new WaitCommand(.5))
        .andThen(new InstantCommand(m_conveyor::openHopperToFlyWheel, m_conveyor)
          .andThen(new WaitCommand(.5))
            .andThen(new InstantCommand(m_conveyor::raiseConveyor, m_conveyor))))
    .whenReleased(new InstantCommand(m_conveyor::stopConveyor, m_conveyor)
    .andThen(new InstantCommand(m_flyWheel::stop, m_flyWheel))
      .andThen(new WaitCommand(1))
        .andThen(new InstantCommand(m_conveyor::closeHopperToFlywheel, m_conveyor)));

  //B Button - Start flywheel, and run the powercells out
  new JoystickButton(Joystick,m_constants.kB)
   .whenHeld(new InstantCommand(() -> m_flyWheel.runFlywheelTrench())
   .andThen(new InstantCommand(m_conveyor::stopConveyor, m_conveyor))
      .andThen(new WaitCommand(.5))
        .andThen(new InstantCommand(m_conveyor::openHopperToFlyWheel, m_conveyor)
          .andThen(new WaitCommand(.5))
            .andThen(new InstantCommand(m_conveyor::raiseConveyor, m_conveyor))))
    .whenReleased(new InstantCommand(m_conveyor::stopConveyor, m_conveyor)
    .andThen(new InstantCommand(m_flyWheel::stop, m_flyWheel))
      .andThen(new WaitCommand(1))
        .andThen(new InstantCommand(m_conveyor::closeHopperToFlywheel, m_conveyor)));

  //Rt Button - Start flywheel, and run the powercells out
new JoystickButton(Joystick,m_constants.kRB)
.whenPressed(new InstantCommand(() -> m_drivetrain.specificDrive(10)))
  .whenReleased(new InstantCommand( m_drivetrain::stop,  m_drivetrain));


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

  public Drivetrain getDrivetrain()
 {
    return m_drivetrain;
 }

 public Conveyor getConveyor()
 {
    return m_conveyor;
 }
}