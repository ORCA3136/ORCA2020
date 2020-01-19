package frc.robot.subsystems;

// import java.util.Map;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

// import edu.wpi.first.networktables.NetworkTableEntry;
// import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
// import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
// import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class flywheel extends SubsystemBase {
    Double expont = .001;
    private WPI_VictorSPX m_flywheel = new WPI_VictorSPX(Constants.kFlyWheel);

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
    //     // Add a shooter tab to the Shuffleboard:
    //     flywheelSpeedTab = Shuffleboard.getTab("Shooter");
    //     // Add a slider to adjust the speed of the shooter:
    //     flywheelSpeedEntry = flywheelSpeedTab.add("Speed", 0).withWidget(BuiltInWidgets.kNumberSlider)
    //             .withProperties(Map.of("min", -1, "max", 1)).getEntry();
    //     // Add a space to display the flywheel's current speed setting:
    //     currentFlywheelSpeedEntry = flywheelSpeedTab.add("Flywheel speed", kFlywheelSpeed).getEntry();
    // }

    /**
     * Spins the flywheel
     */
    public void FlyAcceleration() {
       if(expont < Constants.kFlywheelSpeed) {
       
        //adds exponital growth to speed
        expont = expont *expont;
        m_flywheel.set(expont);
       }
       else if (expont > Constants.kFlywheelSpeed){
           stop();
       }
       else{
           //for testing putting stop
           stop();
       }
    }

    /**
     * Sets the speed of the flywheel
     * @param flywheelSpeed The new speed for the flywheel
     */
    // public void setflywheelSpeed(double flywheelSpeed) {
    //     this.kFlywheelSpeed = flywheelSpeed;
    // }

    /**
     * Stops the flywheel
     */
    public void stop() {
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