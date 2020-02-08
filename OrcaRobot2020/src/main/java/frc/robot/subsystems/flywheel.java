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


public class Flywheel extends SubsystemBase {
   // static Double expont = .1;
    static CANSparkMax left = new CANSparkMax(5, MotorType.kBrushless);
    static CANSparkMax right = new CANSparkMax(6, MotorType.kBrushless);

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
    public static void SlowStop() {}
     /*   boolean finish = false;
        while (finish == false) {
            if (expont / expont > 0) {
                expont = 0.0;
                finish = true;
            } else if (expont > 0) {
                // adds exponital growth to speed
                expont = expont / expont;
                left.set(expont);
                right.set(-expont);
            } else {
                // for testing putting stop
                stop();
                finish = true;
            }

         }
        return expont;
    }
*/
    // exponential growth of speed
    public static void SpeedUp() {}
      /*  boolean finish = false;
        while (finish == false) {
            if (expont * expont > 0) {
                expont = Constants.kFlywheelSpeed;
                finish = true;
            } else if (expont > 0) {
                // adds exponital growth to speed
                expont = expont * expont;
                left.set(expont);
                right.set(-expont);
            } else {
                stop();
                finish = true;
            }

        }*/
      //  return expont;
   // }

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
        right.set(0);
        left.set(0);
    }

    // continusly rus flyWheel
    public static void Run(XboxController driver) {}
   /*     if (Constants.kFlywheelSpeed > expont) {
            //SpeedUp();
        }
       else if(expont == Constants.kFlywheelSpeed){
           left.set(driver.getTriggerAxis(GenericHID.Hand.kLeft)* 1);
           right.set(driver.getTriggerAxis(GenericHID.Hand.kLeft)* -1);
        }
        else{
            SlowStop();
        }
    }
*/
//manual run of flywheel
    public static void test(XboxController m_driver) {
        left.set(m_driver.getTriggerAxis(GenericHID.Hand.kLeft) * -1);
        right.set(m_driver.getTriggerAxis(GenericHID.Hand.kLeft) * -1);
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