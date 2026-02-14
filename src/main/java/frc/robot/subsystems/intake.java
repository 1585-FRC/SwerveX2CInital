package frc.robot.subsystems;

import frc.robot.subsystems.IO;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
    // Declaring Spark Max and giving variable
    private final SparkMax m_intakeMotor;

    // Calling controller
    private Intake m_controller;

    // Contructor for subsystem
    public Intake (int IntakeMotorChannelCAN) {
        // Creating the intake motor object
        m_intakeMotor = new SparkMax(IntakeMotorChannelCAN, SparkLowLevel.MotorType.kBrushless);
    }
}
