package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IO;
import frc.robot.subsystems.Hopper;

public class HopperCommand extends Command {
    // Declaring the Hopper Subsystem
    public final Hopper m_hopperSubsystem;

    // Declaring Controller
    private final IO m_controller;

    // Declaring Variable To Finish Command For Scheduler
    private boolean isFinished = false;

    // Command Constructor
    public HopperCommand(Hopper hopperSubsystem, IO controller) {
        // Declaring Subsystem Variable
        m_hopperSubsystem = hopperSubsystem;

        // Declaring Controller
        m_controller = controller;

        // Addming Requrements for Scheduler
        addRequirements(hopperSubsystem);
    }

    // Run code when initalized for debugging
    public void initialize() {
        // System.out.println("Hopper Command initialized...");
    }

    @Override
    // Execute Command
    public void execute() {
        // Controller Mappings for Hopper buttons A and X
        if (m_controller.GetButtonA()) {
            m_hopperSubsystem.HopperDrive(.5);
        } else if (m_controller.GetButtonB()) {
            m_hopperSubsystem.HopperDrive(-.5);
        } else {
            m_hopperSubsystem.HopperDrive(0);
        }

        isFinished = true;
    }

    // setting is finished variable
    public boolean isFinished() {
        return false;
    }
}
