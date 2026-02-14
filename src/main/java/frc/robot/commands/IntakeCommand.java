package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IO;
import frc.robot.subsystems.Intake;

public class IntakeCommand extends Command {
    // Delclaring The Intake Subsystem
    public final Intake m_intakeSubsystem;
    
    // Delcaring Controller
    private final IO m_controller;

    // Delaring Variable To Finish Command For Scheduler
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
}
