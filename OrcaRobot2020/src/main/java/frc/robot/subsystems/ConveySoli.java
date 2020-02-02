package frc.robot.subsystems;



import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.ConveySoliDefault;

/**
 *
 */
public class ConveySoli extends SubsystemBase {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    private static DoubleSolenoid doubleSolenoid;
    static boolean flip = false;

    public ConveySoli() {
        // TODO Auto-generated constructor stub
       doubleSolenoid = new DoubleSolenoid(0, 1);

    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ConveySoliDefault());
    }

    public static void toggle() {
        if (flip = true) {
            forward();
            flip = false;
        }
        else if (flip = false){
            reverse();
            flip = true;
        }
        else{
            off();
        }
    }

    public static void forward() {
        doubleSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    public static void reverse() {
        doubleSolenoid.set(Value.kReverse);
    }

    public static void off() {
    	doubleSolenoid.set(Value.kOff);
    }
}