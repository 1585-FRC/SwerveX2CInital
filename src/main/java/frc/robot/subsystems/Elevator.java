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

    // Calling Potentiometer
    private final AnalogPotentiometer m_elevatorPotentiometer;

    // Constructor for susbsystem
    public Elevator(int ElevatorMotorOneChannelCAN, int AnalogPotentiometerChannel) {
        // Creating the elevator motor object
        m_elevatorMotor = new SparkMax(ElevatorMotorOneChannelCAN, SparkLowLevel.MotorType.kBrushed);
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

    // Setting Parameters For Elevator Command
    public void ElevatorDrive(double ElevatorSpeed) {
        m_elevatorMotor.set(ElevatorSpeed);
    }
}
