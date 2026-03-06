package frc.robot.subsystems;

import frc.robot.subsystems.IO;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
    // Declaring Spark Max and giving variable
    private final SparkMax m_feedingMotor;
    private final SparkMax m_winchMotor;
    private final DigitalInput m_intakeLimitSwitch;

    // Calling controller
    private Intake m_controller;

    // Contructor for subsystem
    public Intake(int FeedingMotorChannelCAN, int WinchMotorChannelCAN, int IntakeLimitSwitchChannelDIO) {
        // Creating the intake motor object
        m_feedingMotor = new SparkMax(FeedingMotorChannelCAN, SparkLowLevel.MotorType.kBrushless);
        m_winchMotor = new SparkMax(WinchMotorChannelCAN, SparkLowLevel.MotorType.kBrushed);
        m_intakeLimitSwitch = new DigitalInput(IntakeLimitSwitchChannelDIO);
    }

    // Creating Command For Intake Drop
    public Command IntakeDrops(double DropSpeed) {
        return run(
                () -> {
                    this.IntakeDrop(DropSpeed);
                });
    }

    // Setting Parameters For Intake Drop Command
    public void IntakeDrop(double DropSpeed) {
        m_winchMotor.set(DropSpeed);
    }

    /**
     * Returns the raw value of the intake limit switch.
     * Note: the polarity depends on wiring (normally-open vs closed).
     */
    public boolean isLimitPressed() {
        return m_intakeLimitSwitch.get();
    }

    // Creating Command For Intake Feed
    public Command IntakeFeeder(double FeedSpeed) {
        return run(
                () -> {
                    this.IntakeFeed(FeedSpeed);
                });
    }

    // Setting Parameters For Intake Drop Command
    public void IntakeFeed(double FeedSpeed) {
        m_feedingMotor.set(FeedSpeed);
    }
}
