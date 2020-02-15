package frc.robot.subsystems;

// import java.util.Map;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
// import edu.wpi.first.networktables.NetworkTableEntry;
// import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
// import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
// import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class Flywheel extends SubsystemBase {
    static Double exponent = 1.0;
    static CANSparkMax left = new CANSparkMax(Constants.kFlyWheel_l, MotorType.kBrushless);
    static CANSparkMax right = new CANSparkMax(Constants.kFlyWheel_R, MotorType.kBrushless);

    public Flywheel(){


    }
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

    /**
     * Spins the flywheel
     */

    // exponential decay of speed
    public static void SlowStop() {
        boolean finish = false;
        while (finish == false) {
            if (exponent == 0) {
                left.set(exponent);
                
                finish = true;
            }
            else if (exponent / exponent < 0) {
                exponent = 0.0;
                finish = true;
            } 
            else if (exponent > 0) {
                exponent = exponent - .00002;
                left.set(exponent);
               
            }
             else {
                //Should never execute
                stop();
                finish = true;
             }
         }
       
    }

    // exponential growth of speed
    public static void SpeedUp() {
        exponent = .00001;
        boolean finish = false;
        left.set(exponent);
          while (finish == false) {
            if (exponent + exponent > Constants.kFlywheelSpeed) {
                exponent = Constants.kFlywheelSpeed;
                finish = true;
            } else if (Constants.kFlywheelSpeed > exponent) {
                exponent = exponent + .00001;
                left.set(exponent);
            } else {
                //Should never execute
                stop();
                finish = true;
            }

        }
        
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
        left.set(0);
    }

    

    // continusly rus flyWheel
    public static void Run(XboxController driver) {
        if (Constants.kFlywheelSpeed > exponent) {
            SpeedUp();
        }
       else if(exponent == Constants.kFlywheelSpeed){
            SlowStop();
        }
        else{
            SlowStop();
        }
    }

//manual run of flywheel
    public static void test(XboxController m_driver) {
        left.set(m_driver.getTriggerAxis(GenericHID.Hand.kLeft) * -1);
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