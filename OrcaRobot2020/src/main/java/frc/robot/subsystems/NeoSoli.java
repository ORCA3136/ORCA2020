package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.NeoSoliDefault;

/**
 *
 */
public class NeoSoli extends SubsystemBase {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    private static DoubleSolenoid doubleSolenoid;

    public NeoSoli() {
        // TODO Auto-generated constructor stub
        doubleSolenoid = new DoubleSolenoid( 6, 7);

    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new NeoSoliDefault());
    }

    public static void forward() {
        doubleSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    
    public static void reverse(){
    	doubleSolenoid.set(Value.kReverse);
    }
    
    public static void off(){
    	doubleSolenoid.set(Value.kOff);
    }
}