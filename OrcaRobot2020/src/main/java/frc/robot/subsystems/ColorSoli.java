package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 *
 */

public class ColorSoli extends SubsystemBase {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private DoubleSolenoid doubleSolenoid;

    public ColorSoli() {
        // TODO Auto-generated constructor stub
      doubleSolenoid = new DoubleSolenoid(2, 3);


    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    public void forward() {
        doubleSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    
    public void reverse(){
    	doubleSolenoid.set(Value.kReverse);
    }
    
    public void off(){
    	doubleSolenoid.set(Value.kOff);
    }
}