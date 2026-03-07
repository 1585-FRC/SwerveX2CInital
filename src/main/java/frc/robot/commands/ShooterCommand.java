package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IO;
import frc.robot.subsystems.Shooter;
import frc.robot.Constants;

public class ShooterCommand extends Command {
    // Declaring the Shooter Subsystem
    public final Shooter m_shooterSubsystem;

    // Declaring Controller
    private final IO m_controller;

    // Declaring Variable To Finis Command For Scheduler
    private boolean isFinished = false;

    // Command Constructor
    public ShooterCommand(Shooter shooterSubsystem, IO controller) {
        // Declaring Subsystem Variable
        m_shooterSubsystem = shooterSubsystem;

        // Declaring Controller
        m_controller = controller;

        // Adding Requirements For Scheduler
        addRequirements(shooterSubsystem);
    }

    // Run code when initalized for debugging
    public void initialize() {
        // System.out.println("Shooter Command initialized...");
    }

    @Override
    // Execute Command
    public void execute() {

        // Controller Mappings On Left and Right triggers for Shooter

        if (m_controller.GetRightTrigger()) {
            m_shooterSubsystem.ShooterDrive(Constants.ShooterConstants.BOTTOM_SHOOTER_SPEED_FWD, Constants.ShooterConstants.TOP_SHOOTER_SPEED_FWD);
        } else if (m_controller.GetLeftTrigger()) {
            m_shooterSubsystem.ShooterDrive(Constants.ShooterConstants.BOTTOM_SHOOTER_SPEED_BWD, Constants.ShooterConstants.TOP_SHOOTER_SPEED_BWD);
        } else {
            m_shooterSubsystem.ShooterDrive(Constants.ShooterConstants.SPEED_ZERO, Constants.ShooterConstants.SPEED_ZERO);
        }

        isFinished = true;
    }

    // setting is finished variable
    public boolean isFinished() {
        return false;
    }
}