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
    // Hardware Ports:
    // -- Drivetrain:
    public static final int kLFDrive = 1;
    public static final int kRFDrive = 3;
    public static final int kLBDrive = 2;
    public static final int kRBDrive = 4;

  
    public static double DEADZONE = .04;
    // Constant Variables:

    public static final double kLeftDriveScaling = 1.0 / 1.3;
    public static final double kRightDriveScaling = 1.0 / 1.4;
}
