package frc.robot.subsystems;

<<<<<<< Updated upstream
// import java.util.Map;


=======
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
>>>>>>> Stashed changes
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class Flywheel extends SubsystemBase {
<<<<<<< Updated upstream
   // static Double expont = .1;
    static CANSparkMax left = new CANSparkMax(Constants.kFlyWheel_l, MotorType.kBrushless);
    static CANSparkMax right = new CANSparkMax(Constants.kFlyWheel_R, MotorType.kBrushless);

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
=======
    Double exponent = 1.0;
    CANSparkMax left = new CANSparkMax(Constants.kFlyWheel_l, MotorType.kBrushless);
    CANSparkMax right = new CANSparkMax(Constants.kFlyWheel_R, MotorType.kBrushless);

    
    CANEncoder encoder;
    
    CANPIDController controller;

    Constants constants = Constants.getConstants();

    double setpoint = 0;

public Flywheel(){
    right.follow(left, true);
    right.set(0.0);
    
    left.setIdleMode(IdleMode.kCoast);
    right.setIdleMode(IdleMode.kCoast);
    
    encoder = right.getEncoder();
    controller = right.getPIDController();
    controller.setFeedbackDevice(encoder);
    stop();
    updateConstants();
}
//manual run of flywheel
    public void test(XboxController m_driver) {
        right.set(m_driver.getTriggerAxis(GenericHID.Hand.kLeft) * -1);
        
    }
    
    public void set(double setpoint) {
        controller.setReference(0, ControlType.kVelocity);
    }

    public void stop() {
        controller.setReference(0, ControlType.kDutyCycle);
    }

    public void update() {
    }

    public void updateConstants() {
        controller.setOutputRange(-1, 0);
        controller.setP(constants.shooterP);
        controller.setI(constants.shooterI);
        controller.setD(constants.shooterD);
        controller.setFF(constants.shooterF);
    }
>>>>>>> Stashed changes

    /**
     * Spins the flywheel
     */

    // exponential decay of speed
<<<<<<< Updated upstream
    public static void SlowStop() {}
     /*   boolean finish = false;
=======
    public void SlowStop() {
        boolean finish = false;
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
    public static void SpeedUp() {}
      /*  boolean finish = false;
        while (finish == false) {
            if (expont * expont > 0) {
                expont = Constants.kFlywheelSpeed;
=======
    public void SpeedUp() {
        exponent = .00001;
        boolean finish = false;
        left.set(exponent);
        
          while (finish == false) {
            if (exponent + exponent > Constants.kFlywheelSpeed) {
                exponent = Constants.kFlywheelSpeed;
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
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
=======
   
    

    // continusly rus flyWheel
    public void Run(XboxController driver) {
        if (Constants.kFlywheelSpeed > exponent) {
            SpeedUp();
>>>>>>> Stashed changes
        }
       else if(expont == Constants.kFlywheelSpeed){
           left.set(driver.getTriggerAxis(GenericHID.Hand.kLeft)* 1);
           right.set(driver.getTriggerAxis(GenericHID.Hand.kLeft)* -1);
        }
        else{
            SlowStop();
        }
    }
<<<<<<< Updated upstream
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
=======

>>>>>>> Stashed changes
}