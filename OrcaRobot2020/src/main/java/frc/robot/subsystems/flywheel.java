package frc.robot.subsystems;

// import java.util.Map;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
// import edu.wpi.first.networktables.NetworkTableEntry;
// import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
// import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
// import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class flywheel extends SubsystemBase {
    static Double expont = .001;

    private static CANSparkMax m_5 = new CANSparkMax(Constants.kFlyWheel5, MotorType.kBrushless);
    private static CANSparkMax m_6 = new CANSparkMax(Constants.kFlyWheel6, MotorType.kBrushless);

    private static SpeedControllerGroup m_flywheel = new SpeedControllerGroup(m_5, m_6);

    // Shuffleboard:
    // private ShuffleboardTab flywheelSpeedTab;
    // private NetworkTableEntry flywheelSpeedEntry;
    // private NetworkTableEntry currentFlywheelSpeedEntry;

    // Local Variables:
    // public double kFlywheelSpeed = 0.5;

    /**
     * Creates a new instance of this subsystem
     */
    // public Shooter() {
    // // Add a shooter tab to the Shuffleboard:
    // flywheelSpeedTab = Shuffleboard.getTab("Shooter");
    // // Add a slider to adjust the speed of the shooter:
    // flywheelSpeedEntry = flywheelSpeedTab.add("Speed",
    // 0).withWidget(BuiltInWidgets.kNumberSlider)
    // .withProperties(Map.of("min", -1, "max", 1)).getEntry();
    // // Add a space to display the flywheel's current speed setting:
    // currentFlywheelSpeedEntry = flywheelSpeedTab.add("Flywheel speed",
    // kFlywheelSpeed).getEntry();
    // }
    /*
     * spin fly reverse soli move hopper stop hopper fire soli stop fly
     */

    /**
     * Spins the flywheel
     */
    public static void FlyAcceleration() {
        if (expont < Constants.kFlywheelSpeed) {
       
        //adds exponital growth to speed
        expont = expont *expont;
        m_flywheel.set(expont);
       }
       else if (expont > Constants.kFlywheelSpeed){
            stop();
        } else {
            // for testing putting stop
            stop();
        }
    }


    public static void Run() {
        m_flywheel.set(Constants.kFlywheelSpeed);
    }



    /**
     * Sets the speed of the flywheel
     * 
     * @param flywheelSpeed The new speed for the flywheel
     */
    // public void setflywheelSpeed(double flywheelSpeed) {
    // this.kFlywheelSpeed = flywheelSpeed;
    // }

    /**
     * Stops the flywheel
     */
    public static void stop() {
        m_flywheel.set(0);
    }

    /**
     * Runs continuously
     */
    // @Override
    // public void periodic() {
    //     // Update the flywheel speed using the speed slider in Shuffleboard:
    //     kFlywheelSpeed = flywheelSpeedEntry.getDouble(0.5);

    //     // Display the current speed of the flywheel in Shuffleboard:
    //     currentFlywheelSpeedEntry.setDouble(kFlywheelSpeed);
    // }
}