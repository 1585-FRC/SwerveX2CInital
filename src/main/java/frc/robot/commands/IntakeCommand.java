package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IO;
import frc.robot.subsystems.Intake;

public class IntakeCommand extends Command {
    // Delclaring The Intake Subsystem
    public final Intake m_intakeSubsystem;

    // Delcaring Controller
    private final IO m_controller;

    // Declaring Variable To Finish Command For Scheduler
    private boolean isFinished = false;

    private final double FEEDER_SPEED_FWD = .3;
    private final double FEEDER_SPEED_BWD = -.3;
    private final double DROP_SPEED_FWD = .3;
    private final double DROP_SPEED_BWD = -.85;
    private final double SPEED_ZERO = 0.0;

    // Command Contructor
    public IntakeCommand(Intake intakeSubsystem, IO controller) {
        // Declaring Subsystem Variable
        m_intakeSubsystem = intakeSubsystem;

        // Declaring Controller
        m_controller = controller;

        // Adding Requirements For Scheduler
        addRequirements(intakeSubsystem);
    }

    // Run code when initalized for debugging
    public void initialize() {
        // System.out.println("Intake Command initialized...");
    }

    @Override
    // Execute Command
    public void execute() {
        // Controller Mappings for feeder on X and Y Button
        if (m_controller.GetButtonY()) {
            m_intakeSubsystem.IntakeFeed(FEEDER_SPEED_FWD);
        } else if (m_controller.GetButtonB()) {
            m_intakeSubsystem.IntakeFeed(FEEDER_SPEED_BWD);
        } else {
            m_intakeSubsystem.IntakeFeed(SPEED_ZERO);
        }

        // Controller Mappings for Drop on left Bumper and Right Bumper
        if (m_controller.GetLeftBumper()) {
            m_intakeSubsystem.IntakeDrop(DROP_SPEED_BWD);
        } else if (m_controller.GetRightBumper()) {
            m_intakeSubsystem.IntakeDrop(DROP_SPEED_FWD);
        } else {
            m_intakeSubsystem.IntakeDrop(SPEED_ZERO);
        }

        isFinished = true;
    }

    // setting is finished variable
    public boolean isFinished() {
        return false;
    }
}
