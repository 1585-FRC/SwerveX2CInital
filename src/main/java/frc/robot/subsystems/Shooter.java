package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
    // Declaring Spark Max and giving variable
    private final SparkMax m_shooterMotorTop;
    private final SparkMax m_shooterMotorBottom;

    // Calling Controller
    private Shooter m_controller;

    // Constructor for subsystem
    public Shooter (int ShooterMotorTopChannelCAN, int ShooterMotorBottomChannelCAN) {
        // Creating spark max objects
        m_shooterMotorTop = new SparkMax(ShooterMotorTopChannelCAN, SparkLowLevel.MotorType.kBrushless);
        m_shooterMotorBottom = new SparkMax(ShooterMotorBottomChannelCAN, SparkLowLevel.MotorType.kBrushless);
    }

    // Creating Command For Shooter
    public Command Shooter(double ShooterSpeed) {
        return run(
                () -> {
                    this.ShooterDrive(ShooterSpeed);
                });
    }

    // Setting Parameters For Shooter Command
    public void ShooterDrive(double ShooterSpeed) {
        m_shooterMotorTop.set(ShooterSpeed);
        m_shooterMotorBottom.set(ShooterSpeed);
    }
}
