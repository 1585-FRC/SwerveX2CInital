package frc.robot;

public class Constants {
    private Constants() {
    }

    public static final class IntakeConstants {
        public static final boolean INTAKE_ENABLED = true;
        public static final double FEEDER_SPEED_FWD = .3;
        public static final double FEEDER_SPEED_BWD = -.3;
        public static final double DROP_SPEED_FWD = .3;
        public static final double DROP_SPEED_BWD = -.85;
        public static final double SPEED_ZERO = 0.0;
        public static final int FEEDER_MOTOR_ID = 51;
        public static final int WINCH_MOTOR_ID = 52;
    }

    public static final class ShooterConstants {
        public static final boolean SHOOTER_ENABLED = false;
        public static final double SHOOTER_SPEED_FWD = .5;
        public static final double SHOOTER_SPEED_BWD = -.5;
        public static final double SPEED_ZERO = 0.0;
        public static final int TOP_SHOOTER_MOTOR_ID = 54;
        public static final int BOTTOM_SHOOTER_MOTOR_ID = 55;
    }

    public static final class HopperConstants {
        public static final boolean HOPPER_ENABLED = false;
        public static final double HOPPER_SPEED_FWD = .3;
        public static final double HOPPER_SPEED_BWD = -.3;
        public static final double SPEED_ZERO = 0.0;
        public static final int HOPPER_MOTOR_ID = 58;
    }

    public static final class ElevatorConstants {
        public static final boolean ELEVATOR_ENABLED = false;
        public static final double ELEVATOR_SPEED_FWD = .3;
        public static final double ELEVATOR_SPEED_BWD = -.3;
        public static final double SPEED_ZERO = 0.0;
        public static final int ELEVATOR_MOTOR_ID_1 = 61;
        public static final int ELEVATOR_MOTOR_ID_2 = 62;
    }
}