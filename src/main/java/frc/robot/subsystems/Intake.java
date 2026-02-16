package frc.robot.subsystems;

import frc.robot.subsystems.IO;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
    // Declaring Spark Max and giving variable
    private final SparkMax m_feedingMotor;
    private final SparkMax m_winchMotor;

    // Calling controller
    private Intake m_controller;

    // Contructor for subsystem
    public Intake(int IntakeMotorChannelCAN) {
        // Creating the intake motor object
        m_feedingMotor = new SparkMax(IntakeMotorChannelCAN, SparkLowLevel.MotorType.kBrushless);
        m_winchMotor = new SparkMax(IntakeMotorChannelCAN, SparkLowLevel.MotorType.kBrushed);
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
        m_feedingMotor.set(DropSpeed);;
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
        m_feedingMotor.set(FeedSpeed);;
    }
}
