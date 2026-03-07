package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Elevator extends SubsystemBase {
    // Declaring Spark Max and giving variable
    private final SparkMax m_elevatorMotor;

    // Calling Controller
    private Elevator m_controller;

    // Calling Limit Switches
    private final DigitalInput m_elevatorLimitSwitchInner;
    private final DigitalInput m_elevatorLimitSwitchOuter;

    // Calling Potentiometer
    private final AnalogPotentiometer m_elevatorPotentiometer;

    // Constructor for susbsystem
    public Elevator(int ElevatorMotorOneChannelCAN, int ElevatorLimitSwitchInnerChannel, int ElevatorLimitSwitchOuterChannel, int AnalogPotentiometerChannel) {
        // Creating the elevator motor object
        m_elevatorMotor = new SparkMax(ElevatorMotorOneChannelCAN, SparkLowLevel.MotorType.kBrushed);
        m_elevatorLimitSwitchInner = new DigitalInput(ElevatorLimitSwitchInnerChannel);
        m_elevatorLimitSwitchOuter = new DigitalInput(ElevatorLimitSwitchOuterChannel);
        m_elevatorPotentiometer = new AnalogPotentiometer(AnalogPotentiometerChannel);
    }

     // Creating Command For Elevator
    public Command ElevatorCommand(double ElevatorSpeed) {
        return run(
                () -> {
                    this.ElevatorDrive(ElevatorSpeed);
                });
    }

    public double getElevatorPosition() {
        return m_elevatorPotentiometer.get();
    }

    public boolean isInnerLimitSwitchPressed() {
        return m_elevatorLimitSwitchInner.get();
    }

    public boolean isOuterLimitSwitchPressed() {
        return m_elevatorLimitSwitchOuter.get();
    }

    // Setting Parameters For Elevator Command
    public void ElevatorDrive(double ElevatorSpeed) {
        m_elevatorMotor.set(ElevatorSpeed);
    }
}
