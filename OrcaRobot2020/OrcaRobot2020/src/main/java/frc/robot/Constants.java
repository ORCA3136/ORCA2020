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
    public static int fl_motor_id = 10;
    public static int fr_motor_id = 3;
    public static int rr_motor_id = 2;
    public static int rl_motor_id = 1;
    //Motors ID's
    public static final int kIntake = 5;
    public static final int kConveyor = 1;
    public static final int kFlyWheel = 2;
  //Speeds for Motors
    public static final double kIntakeSpeed = 1.0;
    public static final double kConveyorSpeed = 0.5;
    public static final double kFlywheelSpeed = 0.45;
    public static final double kLeftDriveScaling = 1.0 / 1.3;
    public static final double kRightDriveScaling = 1.0 / 1.4;
}