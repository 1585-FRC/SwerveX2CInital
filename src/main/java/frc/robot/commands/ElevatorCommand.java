package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IO;
import frc.robot.subsystems.Elevator;
import frc.robot.Constants;

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
        // Elevator Drive Command Mapped To Controller D Pad Up And Down

        boolean innerLimitPressed = m_elevatorSubsystem.isInnerLimitSwitchPressed();
        boolean outerLimitPressed = m_elevatorSubsystem.isOuterLimitSwitchPressed();
        if (m_controller.DPadUp()) {
            if (!outerLimitPressed || !innerLimitPressed) {
                m_elevatorSubsystem.ElevatorDrive(Constants.ElevatorConstants.ELEVATOR_SPEED_UP);
            } else {
                m_elevatorSubsystem.ElevatorDrive(Constants.ElevatorConstants.SPEED_ZERO);
            }
        } else if (m_controller.DPadDown()) {
            m_elevatorSubsystem.ElevatorDrive(Constants.ElevatorConstants.ELEVATOR_SPEED_DOWN);
        } else {
            m_elevatorSubsystem.ElevatorDrive(Constants.ElevatorConstants.SPEED_ZERO);
        }

        isFinished = true;
    }

    // setting is finished variable
    public boolean isFinished() {
        return false;
    }
}