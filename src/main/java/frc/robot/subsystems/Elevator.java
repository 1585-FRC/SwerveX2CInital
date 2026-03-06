package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Elevator extends SubsystemBase {
    // Declaring Spark Max and giving variable
    private final SparkMax m_elevatorMotorOne;
    private final SparkMax m_elevatorMotorTwo;

    // Calling Controller
    private Elevator m_controller;

    // Calling Limit Switches
    private final DigitalInput m_elevatorLimitSwitchInner;
    private final DigitalInput m_elevatorLimitSwitchOuter;

    // Constructor for susbsystem
    public Elevator(int ElevatorMotorOneChannelCAN, int ElevatorMotorTwoChannelCAN, int ElevatorLimitSwitchInnerChannel, int ElevatorLimitSwitchOuterChannel) {
        // Creating the elevator motor object
        m_elevatorMotorOne = new SparkMax(ElevatorMotorOneChannelCAN, SparkLowLevel.MotorType.kBrushed);
        m_elevatorLimitSwitchInner = new DigitalInput(ElevatorLimitSwitchInnerChannel);
        m_elevatorLimitSwitchOuter = new DigitalInput(ElevatorLimitSwitchOuterChannel);
        m_elevatorMotorTwo = new SparkMax(ElevatorMotorTwoChannelCAN, SparkLowLevel.MotorType.kBrushed);
    }

     // Creating Command For Elevator
    public Command ElevatorCommand(double ElevatorSpeed) {
        return run(
                () -> {
                    this.ElevatorDrive(ElevatorSpeed);
                });
    }

    public boolean isInnerLimitSwitchPressed() {
        return m_elevatorLimitSwitchInner.get();
    }

    public boolean isOuterLimitSwitchPressed() {
        return m_elevatorLimitSwitchOuter.get();
    }

    // Setting Parameters For Elevator Command
    public void ElevatorDrive(double ElevatorSpeed) {
        m_elevatorMotorOne.set(ElevatorSpeed);
        m_elevatorMotorTwo.set(ElevatorSpeed);
    }
}
