package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IO;
import frc.robot.subsystems.Intake;
import frc.robot.Constants;

public class IntakeCommand extends Command {
    // Delclaring The Intake Subsystem
    public final Intake m_intakeSubsystem;

    // Delcaring Controller
    private final IO m_controller;

    // Limit switch is read via the Intake subsystem


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
            m_intakeSubsystem.IntakeFeed(Constants.IntakeConstants.FEEDER_SPEED_FWD);
        } else if (m_controller.GetButtonB()) {
            m_intakeSubsystem.IntakeFeed(Constants.IntakeConstants.FEEDER_SPEED_BWD);
        } else {
            m_intakeSubsystem.IntakeFeed(Constants.IntakeConstants.SPEED_ZERO);
        }

        // Limit switch logic: when the limit is triggered, disable the lift
        // action. The polarity of DigitalInput#get() depends on wiring
        // (normally-open vs normally-closed). Here we treat get() == true
        // as "limit is pressed"; invert the check if your switch reports
        // the opposite.
    boolean limitPressed = m_intakeSubsystem.isLimitPressed();

        if (m_controller.GetLeftBumper()) {
            // Always allow dropping (winch down)
            m_intakeSubsystem.IntakeDrop(Constants.IntakeConstants.DROP_SPEED);
        } else if (m_controller.GetRightBumper()) {
            // Only allow lifting if the limit is NOT pressed
            if (!limitPressed) {
                m_intakeSubsystem.IntakeDrop(Constants.IntakeConstants.LIFT_SPEED);
            } else {
                m_intakeSubsystem.IntakeDrop(Constants.IntakeConstants.SPEED_ZERO);
            }
        } else {
            m_intakeSubsystem.IntakeDrop(Constants.IntakeConstants.SPEED_ZERO);
        }
    }

    // setting is finished variable
    public boolean isFinished() {
        return false;
    }
}
