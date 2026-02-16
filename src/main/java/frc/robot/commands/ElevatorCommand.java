package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IO;
import frc.robot.subsystems.Elevator;

public class ElevatorCommand extends Command {
    // Declaring The Elevator Variable
    public final Elevator m_elevatorSubsystem;

    // Declaring Variable To Finish Command For Scheduler
    private boolean isFinished = false;

    // Declaring Controller
    private final IO m_controller;

    // Command Contructor
    public ElevatorCommand(Elevator elevatorSubsystem, IO controller) {
        // Declaring Subsystem Variable
        m_elevatorSubsystem = elevatorSubsystem;

        // Declaring Controller
        m_controller = controller;

        // Adding Requirements For Scheduler
        addRequirements(elevatorSubsystem);
    }

    // Run code when initalized for debugging
    public void initialize() {
        // System.out.println("Elevator Command initialized...");
    }

    @Override
    // Execute Command
    public void execute() {

        isFinished = true;
    }

    // setting is finished variable
    public boolean isFinished() {
        return false;
    }
}