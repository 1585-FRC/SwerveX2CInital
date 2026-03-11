package frc.robot;

public class Constants {
    private Constants() {
    }

    public static final class RobotContainerConstants {
        public static final double CONTROLLER_DEADZONE = 0.2;
        public static final int DRIVE_CONTROLLER_PORT = 0;
        public static final boolean DRIVE_ENABLED = true;
        public static final int OPERATOR_CONTROLLER_PORT = 1;
    }

    public static final class IntakeConstants {
        public static final boolean INTAKE_ENABLED = true;
        public static final double FEEDER_SPEED_FWD = .47;
        public static final double FEEDER_SPEED_BWD = -.45;
        public static final double DROP_SPEED = -.3;
        public static final double LIFT_SPEED = 1.0;
        public static final double SPEED_ZERO = 0.0;
        public static final int FEEDER_MOTOR_ID = 51;
        public static final int WINCH_MOTOR_ID = 52;
    }

    public static final class ShooterConstants {
        public static final boolean SHOOTER_ENABLED = true;
        public static final double TOP_SHOOTER_SPEED_FWD = .65;
        public static final double BOTTOM_SHOOTER_SPEED_FWD = .80;
        public static final double TOP_SHOOTER_SPEED_BWD = -.65;
        public static final double BOTTOM_SHOOTER_SPEED_BWD = -.80;
        public static final double SPEED_ZERO = 0.0;
        public static final int TOP_SHOOTER_MOTOR_ID = 54;
        public static final int BOTTOM_SHOOTER_MOTOR_ID = 55;
    }

    public static final class ElevatorConstants {
        public static final boolean ELEVATOR_ENABLED = true;
        public static final double ELEVATOR_SPEED_DOWN = .3;
        public static final double ELEVATOR_SPEED_UP = -.3;
        public static final double SPEED_ZERO = 0.0;
        public static final int ELEVATOR_MOTOR_ID = 61;
        public static final int ANALOG_POTENTIOMETER_CHANNEL = 0;
        public static final double ELEVATOR_LIMIT_SWITCH_THRESHOLD = 0.19;
    }
}