/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    
    
//DriveTrain Motors
    public static int fl_motor_id = 1;
    public static int fr_motor_id = 4;
    public static int rr_motor_id = 3;
    public static int rl_motor_id = 2;
    
    
 //Motors ID's
    public static final int kIntake1 = 5;
    public static final int kIntake2 = 10;
    
    public static final int kConveyor1 = 8;
    public static final int kConveyor2 = 9;

    public static final int kFlyWheel_l = 7;
    public static final int kFlyWheel_R = 6;
 
 //Solinoids
    public static final int kIntakeForward = 3;
    public static final int kIntakeReverse = 0;
    
    public static final int kHopperStopperForward = 7;
    public static final int kHopperStopperReverse = 4;
    
    public static final int kClimberForward = 5;
    public static final int kClimberReverse = 6;
    
    public static final int kPTOForward = 2;
    public static final int kPTOReverse = 1;
   
    //Speeds for Motors
    public static final double kIntakeSpeed =.2;

    //Speeds for conveyor motors
    public static final double kConveyorSpeed = 1;
    
    //Speeds for FlyWheel motors
    public static final double kFlywheelSpeed = 1;

    public static final double flyWheelP = 0.0011;
    public static final double flyWheelI = 0;
    public static final double flyWheelD = 4;
    public static final double flyWheelF = 0.00017;
    
    //Speeds for Drivetrain motors
    public static final double kLeftDriveScaling = 0.7;
    public static final double kRightDriveScaling = 0.7;
    public static final double kWinchSpeed = 0.5;
    public static final double kAutoSpeed = -0.5;

    
    public static final double  kMaxOutput = 1; 
    public static final double kMinOutput = -1;

    public static final double flyWheelSetPoint = -5000;

    public static boolean ToggleSoli = false;

    //JoyStick buttons
    public final int kY = 0;
    public final int kX = 1;
    public final int kA = 2;
    public final int kB = 3;
    public final int kLB = 4;
    public final int kRB = 5;
    public final int kRT = 6;
    public final int kLT = 7;
    public final int kSelect = 8;
    public final int kStart = 9;
    //JoyStick Axis
    public final int kAxisX =0;
    public final int kAxisY =1;




}
