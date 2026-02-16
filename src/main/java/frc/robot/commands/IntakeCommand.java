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
            m_intakeSubsystem.IntakeFeed(.5);
        } else if (m_controller.GetButtonB()) {
            m_intakeSubsystem.IntakeFeed(-.5);
        } else {
            m_intakeSubsystem.IntakeFeed(0);
        }

        // Controller Mappings for Drop on left Bumper and Right Bumper
        if (m_controller.GetLeftBumper()) {
            m_intakeSubsystem.IntakeDrop(-.5);
        } else if (m_controller.GetRightTrigger()) {
            m_intakeSubsystem.IntakeDrop(.5);
        } else {
            m_intakeSubsystem.IntakeDrop(0);
        }

        isFinished = true;
    }

    // setting is finished variable
    public boolean isFinished() {
        return false;
    }
}
