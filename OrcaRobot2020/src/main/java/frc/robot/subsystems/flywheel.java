package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class Flywheel extends SubsystemBase {
    Double exponent = 1.0;
    CANSparkMax left = new CANSparkMax(Constants.kFlyWheel_l, MotorType.kBrushless);
    CANSparkMax right = new CANSparkMax(Constants.kFlyWheel_R, MotorType.kBrushless);

    
    CANEncoder encoder;
    
    CANPIDController controller;

    double setpoint = 0;

public Flywheel(){

    right.follow(left, true);
    left.set(0.0);
    
    left.setIdleMode(IdleMode.kCoast);
    right.setIdleMode(IdleMode.kCoast);
    
    encoder = left.getEncoder();
    controller = left.getPIDController();
    controller.setFeedbackDevice(encoder);
    stop();
    updateConstants();
}
//manual run of flywheel
//NOTE because the constructor has them setup as followers - setting the right does not matter.
    public void runFlywheelWithoutPID() {
        left.set(Constants.kFlywheelSpeed);
        right.set(Constants.kFlywheelSpeed);
    }

    public void runFlyWheelWithPID(double setPoint)
    {
        SmartDashboard.putNumber("Target FlyWheel Velocity: ", setPoint);
        controller.setReference(setPoint, ControlType.kVelocity);
    }

    public void runLeftFlyWheelMotorOnly(){
        left.set(Constants.kFlywheelSpeed);
    
    }

    public void runRightFlyWheelOnly() {
        right.set(Constants.kFlywheelSpeed);
        
    }
    
    public void set(double setpoint) {
        controller.setReference(0, ControlType.kDutyCycle);
    }

    public void stop() {
        left.set(0);
    }

    public void update() {
        SmartDashboard.putNumber("FlyWheel Velocity: ", Math.abs(encoder.getVelocity()));
    }

    public void initDefaultCommand() {
        stop();
      }

    public void updateConstants() {
        controller.setOutputRange(-1, 0);
        controller.setP(Constants.flyWheelP);
        controller.setI(Constants.flyWheelI);
        controller.setD(Constants.flyWheelD);
        controller.setFF(Constants.flyWheelF);
    }

    /**
     * Spins the flywheel
     */

    // exponential decay of speed
    public void SlowStop() {
        boolean finish = false;
        while (finish == false) {
            if (exponent / exponent > 0) {
                exponent = 0.0;
                finish = true;
            } else if (exponent > 0) {
                // adds exponital growth to speed
                exponent = exponent / exponent;
                left.set(exponent);
                right.set(-exponent);
            } else {
                // for testing putting stop
                stop();
                finish = true;
            }

         }
    }

    // exponential growth of speed
    public void SpeedUp() {
        exponent = .00001;
        boolean finish = false;
        left.set(exponent);
        
          while (finish == false) {
            if (exponent + exponent > Constants.kFlywheelSpeed) {
                exponent = Constants.kFlywheelSpeed;
                finish = true;
            } else if (exponent > 0) {
                // adds exponital growth to speed
                exponent = exponent * exponent;
                left.set(exponent);
                right.set(-exponent);
            } else {
                stop();
                finish = true;
            }
          }
    }
}