package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Hopper extends SubsystemBase {
    // Declaring Spark Max and giving variable
    private final SparkMax m_hopperMotor;

    // Calling controller
    private Hopper m_controller;

    public Hopper(int HopperMotorChannelCAN) {
        m_hopperMotor = new SparkMax(HopperMotorChannelCAN, SparkLowLevel.MotorType.kBrushed);
    }

     // Creating Command For Hopper
    public Command HopperCommand(double HopperSpeed) {
        return run(
                () -> {
                    this.HopperDrive(HopperSpeed);
                });
    }

    // Setting Parameters For Hopper Command
    public void HopperDrive(double IntakeSpeed) {
        m_hopperMotor.set(IntakeSpeed);
    }
}
