package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class Flywheel extends SubsystemBase {
    private Double exponent = 1.0;
    private CANSparkMax left = new CANSparkMax(Constants.kFlyWheel_l, MotorType.kBrushless);
    private CANSparkMax right = new CANSparkMax(Constants.kFlyWheel_R, MotorType.kBrushless);

    
    private CANEncoder encoder;
    
    private CANPIDController controller;


    private double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, setPoint;
    private boolean pidEnabled = false;

    public Flywheel() {

        right.follow(left, true);
        left.set(0.0);

        left.setIdleMode(IdleMode.kCoast);
        right.setIdleMode(IdleMode.kCoast);

        encoder = left.getEncoder();
        controller = left.getPIDController();
        controller.setFeedbackDevice(encoder);

        putPIDValuesOnDasboard();
        updateConstants();
        // set values on the controller
        controller.setP(kP);
        controller.setI(kI);
        controller.setD(kD);
        controller.setIZone(kIz);
        controller.setFF(kFF);
        controller.setOutputRange(kMinOutput, kMaxOutput);

        stop();

    }

    @Override
    public void periodic() {
        //System.out.println("Calling periodic");
        SmartDashboard.putBoolean("PID ENABLED", pidEnabled);
        double p = SmartDashboard.getNumber("P Gain", Constants.flyWheelP);
        double i = SmartDashboard.getNumber("I Gain", Constants.flyWheelI);
        double d = SmartDashboard.getNumber("D Gain", Constants.flyWheelD);
        double ff = SmartDashboard.getNumber("Feed Forward", Constants.flyWheelF);
        double max = SmartDashboard.getNumber("Max Output", 1);
        double min = SmartDashboard.getNumber("Min Output", -1);
        
        double point = SmartDashboard.getNumber("set point", 500);
        // if PID coefficients on SmartDashboard have changed, write new values to
        // controller
        if (pidEnabled) {
            if ((p != kP)) {
                controller.setP(p);
                kP = p;
            }
            if ((i != kI)) {
                controller.setI(i);
                kI = i;
            }
            if ((d != kD)) {
                controller.setD(d);
                kD = d;
            }
            // if ((iz != kIz)) {
            //     controller.setIZone(iz);
            //     kIz = iz;
            // }
            if ((ff != kFF)) {
                controller.setFF(ff);
                kFF = ff;
            }
            if ((point != setPoint)) {
                SmartDashboard.putNumber("POINT", point);
                controller.setReference(point, ControlType.kVelocity);
                setPoint = point;
            }

            if ((max != kMaxOutput) || (min != kMinOutput)) {
                controller.setOutputRange(min, max);
                kMinOutput = min;
                kMaxOutput = max;
            }

            // controller.setReference(Constants.flyWheelSetPoint, ControlType.kVelocity);
            SmartDashboard.putNumber("SetPoint", setPoint);
            SmartDashboard.putNumber("ProcessVariable", encoder.getVelocity());
        }

    }

    // manual run of flywheel
    // NOTE because the constructor has them setup as followers - setting the right
    // does not matter.
    public void runFlywheelWithoutPID() {
        pidEnabled = false;
        left.set(Constants.kFlywheelSpeed);
        right.set(Constants.kFlywheelSpeed);
    }

    public void runFlyWheelWithPID() {
        new PrintCommand(">>runFlywheelWithPID");
        pidEnabled = true;
        SmartDashboard.putNumber("Target FlyWheel Velocity: ", Constants.flyWheelSetPoint);
        setPoint = 1111;
        new PrintCommand("runFlywheeWithPID");// updateConstants();
        // controller.setReference(Constants.flyWheelSetPoint, ControlType.kVelocity);

    }

    public void runFlyWheelWithPID(double sp) {
        pidEnabled = true;
        setPoint = sp;
    }

    public void set(double setpoint) {
        controller.setReference(0, ControlType.kDutyCycle);
    }

    public void stop() {
        pidEnabled = false;
        left.set(0);
    }

    public void update() {
        SmartDashboard.putNumber("FlyWheel Velocity: ", Math.abs(encoder.getVelocity()));
    }

    public void initDefaultCommand() {
        stop();
    }

    public void updateConstants() {
        // controller.setOutputRange(-1, 0);
        kP = Constants.flyWheelP;
        kI = Constants.flyWheelI;
        kD = Constants.flyWheelD;
        kIz = 0;
        kFF = Constants.flyWheelF;
        kMaxOutput = Constants.kMaxOutput;
        kMinOutput = Constants.kMinOutput;
        setPoint = Constants.flyWheelSetPoint;

        // controller.setP(Constants.flyWheelP);
        // controller.setI(Constants.flyWheelI);
        // controller.setD(Constants.flyWheelD);
        // controller.setFF(Constants.flyWheelF);
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

    private void putPIDValuesOnDasboard()
    {
        SmartDashboard.putNumber("P Gain", kP);
        SmartDashboard.putNumber("I Gain", kI);
        SmartDashboard.putNumber("D Gain", kD);
        SmartDashboard.putNumber("Feed Forward", kFF);
        SmartDashboard.putNumber("Max Output", kMaxOutput);
        SmartDashboard.putNumber("Min Output", kMinOutput);
        SmartDashboard.putNumber("PID SetPoint", setPoint);
    }
}