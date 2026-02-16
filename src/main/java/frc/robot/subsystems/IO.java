package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class IO extends SubsystemBase {

    // BUTTON MAPPINGS
    // A: 
    // B: 
    // X: 
    // Y: 
    // LeftBumper: 
    // RightBumper: 

    // Calls Xbox Controller
    public CommandXboxController m_controller = new CommandXboxController(1);

    // Get 'B' button on Xbox Controller
    public boolean GetButtonB() {
        return m_controller.b().getAsBoolean();
    }

    // Get 'X' button on Xbox Controller
    public boolean GetButtonX() {
        return m_controller.x().getAsBoolean();
    }

    // Get 'A' button on Xbox Controller
    public boolean GetButtonA() {
        return m_controller.a().getAsBoolean();
    }

    // Get 'Y' button on Xbox Controller
    public boolean GetButtonY() {
        return m_controller.y().getAsBoolean();
    }

    // Get 'Left Bumper' on Xbox Controller
    public boolean GetLeftBumper() {
        return m_controller.leftBumper().getAsBoolean();
    }

    // Get 'Right Bumper' on Xbox Controller
    public boolean GetRightBumper() {
        return m_controller.rightBumper().getAsBoolean();
    }

    // Get D Pad 'Up' on Xbox Controller
    public boolean DPadUp() {
        return m_controller.povUp().getAsBoolean();
    }

    // Get D Pad 'Up Right' on Xbox Controller
    public boolean DPadUpRight() {
        return m_controller.povUpRight().getAsBoolean();
    }

    // Get D Pad 'Up Left' on Xbox Controller
    public boolean DPadUpLeft() {
        return m_controller.povUpLeft().getAsBoolean();
    }

    // Get D Pad 'Down' on Xbox Controller
    public boolean DPadDown() {
        return m_controller.povDown().getAsBoolean();
    }

    // Get D Pad 'Down Right' on Xbox Controller
    public boolean DPadDownRight() {
        return m_controller.povDownRight().getAsBoolean();
    }

    // Get D Pad 'Down Left' on Xbox Controller
    public boolean DPadDownLeft() {
        return m_controller.povDownLeft().getAsBoolean();
    }

    // Get D Pad 'Right' on Xbox Controller
    public boolean DPadRight() {
        return m_controller.povRight().getAsBoolean();
    }

    // Get D Pad 'Left' on Xbox Controller
    public boolean DPadLeft() {
        return m_controller.povLeft().getAsBoolean();
    }

    public boolean RightTrigger() {
        return m_controller.rightTrigger().getAsBoolean();
    }

    public boolean LeftTrigger() {
        return m_controller.leftTrigger().getAsBoolean();
    }
}