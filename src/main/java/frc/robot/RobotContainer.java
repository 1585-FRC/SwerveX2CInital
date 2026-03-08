// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static edu.wpi.first.units.Units.*;

import com.ctre.phoenix6.swerve.SwerveModule.DriveRequestType;
import com.ctre.phoenix6.swerve.SwerveRequest;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.commands.FollowPathCommand;

import edu.wpi.first.math.geometry.Rotation2d;
 
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.RobotModeTriggers;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine.Direction;

import frc.robot.generated.TunerConstants;
import frc.robot.subsystems.*;
import frc.robot.commands.*;
import frc.robot.Constants;

public class RobotContainer {


        private boolean enableDriveSystem = true;
        // Declare Subsystems Varaiables
        private final IO m_controller = new IO();

        // Subsystem and command declarations (may be null if the subsystem is disabled via Constants)
        // These are intentionally not final so they can be left null when a subsystem is disabled.
        private Intake m_intake = null;
        private Shooter m_shooter = null;
        private Elevator m_elevator = null;

        private IntakeCommand m_intakeCommand = null;
        private ShooterCommand m_shooterCommand = null;
        private ElevatorCommand m_elevatorCommand = null;

        private double MaxSpeed = 1.0 * TunerConstants.kSpeedAt12Volts.in(MetersPerSecond); // kSpeedAt12Volts desired
                                                                                            // top
                                                                                            // speed
        private double MaxAngularRate = RotationsPerSecond.of(0.75).in(RadiansPerSecond); // 3/4 of a rotation per
                                                                                          // second
                                                                                          // max angular velocity

        /* Setting up bindings for necessary control of the swerve drive platform */
        private final SwerveRequest.FieldCentric drive = new SwerveRequest.FieldCentric()
                        .withDeadband(MaxSpeed * 0.1).withRotationalDeadband(MaxAngularRate * 0.1) // Add a 10% deadband
                        .withDriveRequestType(DriveRequestType.OpenLoopVoltage); // Use open-loop control for drive
                                                                                 // motors
        private final SwerveRequest.SwerveDriveBrake brake = new SwerveRequest.SwerveDriveBrake();
        private final SwerveRequest.PointWheelsAt point = new SwerveRequest.PointWheelsAt();
        private final SwerveRequest.RobotCentric forwardStraight = new SwerveRequest.RobotCentric()
                        .withDriveRequestType(DriveRequestType.OpenLoopVoltage);

        private final Telemetry logger = new Telemetry(MaxSpeed);

        private final CommandXboxController joystick = new CommandXboxController(Constants.RobotContainerConstants.DRIVE_CONTROLLER_PORT);

        // Apply a small deadzone for joystick axes (only for controller on port 0)
        // Inputs with absolute value <= 0.2 are treated as zero.
        private static double applyJoystickDeadzone(double v) {
                return Math.abs(v) > Constants.RobotContainerConstants.CONTROLLER_DEADZONE ? v : 0.0;
        }

        public final CommandSwerveDrivetrain drivetrain = enableDriveSystem ? TunerConstants.createDrivetrain() : null;

        /* Path follower */
        private SendableChooser<Command> autoChooser = null;

        public RobotContainer() {

                configureBindings();

        

                // Create Object for Subystems

                // Initialize Subsystems and Commands based on Constant Value
                if (Constants.IntakeConstants.INTAKE_ENABLED) {
                        m_intake = new Intake(Constants.IntakeConstants.FEEDER_MOTOR_ID, Constants.IntakeConstants.WINCH_MOTOR_ID, Constants.IntakeConstants.INTAKE_LIMIT_SWITCH_CHANNEL);
                        m_intakeCommand = new IntakeCommand(m_intake, m_controller);
                        m_intake.setDefaultCommand((m_intakeCommand));
                }

                 if (Constants.ShooterConstants.SHOOTER_ENABLED) {
                        m_shooter = new Shooter(Constants.ShooterConstants.TOP_SHOOTER_MOTOR_ID, Constants.ShooterConstants.BOTTOM_SHOOTER_MOTOR_ID);   
                        m_shooterCommand = new ShooterCommand(m_shooter, m_controller);
                        m_shooter.setDefaultCommand((m_shooterCommand));
                }

                 if (Constants.ElevatorConstants.ELEVATOR_ENABLED) {
                        m_elevator = new Elevator(Constants.ElevatorConstants.ELEVATOR_MOTOR_ID, Constants.ElevatorConstants.ANALOG_POTENTIOMETER_CHANNEL);
                        m_elevatorCommand = new ElevatorCommand(m_elevator, m_controller);
                        m_elevator.setDefaultCommand((m_elevatorCommand));
                }

                // Register PathPlanner named command(s) after subsystems are created.
                // This maps the "Score" named event in PathPlanner autos to a command
                // that spins up the shooter using the existing shooter constants.
                if (m_shooter != null) {
                        // Register a command returned by the Shooter subsystem that
                        // runs the shooter at the configured speed. PathPlanner will
                        // schedule/cancel this command based on named events and waits.
                        NamedCommands.registerCommand(
                                        "Score",
                                        m_shooter.ShooterCommand(Constants.ShooterConstants.BOTTOM_SHOOTER_SPEED_FWD, Constants.ShooterConstants.TOP_SHOOTER_SPEED_FWD)
                        );
                }

                if (m_intake != null) {
                        // Register the Intake subsystem's command for feeding. PathPlanner
                        // will schedule/cancel it using its named events and waits.
                        NamedCommands.registerCommand(
                                        "Intake",
                                        m_intake.IntakeFeeder(Constants.IntakeConstants.FEEDER_SPEED_FWD)
                        );
                }

                // Now that named commands are registered and subsystems are created,
                // build the AutoChooser so PathPlanner resolves named events correctly.
                if (enableDriveSystem)
                {
                        autoChooser = AutoBuilder.buildAutoChooser("Intake-Score");
                        SmartDashboard.putData("Auto Mode", autoChooser);

                         // Warmup PathPlanner to avoid Java pauses
                        FollowPathCommand.warmupCommand().schedule();
                }
        }

        private void configureBindings() {

                if(!enableDriveSystem)
                {
                        return;
                }

                // Note that X is defined as forward according to WPILib convention,
                // and Y is defined as to the left according to WPILib convention.
                drivetrain.setDefaultCommand(
                                // Drivetrain will execute this command periodically
                                drivetrain
                                                .applyRequest(() -> drive.withVelocityX(
                                                                -applyJoystickDeadzone(joystick.getLeftY()) * MaxSpeed) // Drive
                                                                                                                        // forward
                                                                                                                        // with
                                                                                                                        // negative
                                                                                                                        // Y
                                                                                                                        // (forward)
                                                                .withVelocityY(-applyJoystickDeadzone(
                                                                                joystick.getLeftX()) * MaxSpeed) // Drive
                                                                                                                 // left
                                                                                                                 // with
                                                                                                                 // negative
                                                                                                                 // X
                                                                                                                 // (left)
                                                                .withRotationalRate(-applyJoystickDeadzone(
                                                                                joystick.getRightX()) * MaxAngularRate) // Drive
                                                                                                                        // counterclockwise
                                                                                                                        // with
                                                                                                                        // negative
                                                                                                                        // X
                                                                                                                        // (left)
                                                ));

                // Idle while the robot is disabled. This ensures the configured
                // neutral mode is applied to the drive motors while disabled.
                final var idle = new SwerveRequest.Idle();
                RobotModeTriggers.disabled().whileTrue(
                                drivetrain.applyRequest(() -> idle).ignoringDisable(true));

                joystick.a().whileTrue(drivetrain.applyRequest(() -> brake));
                joystick.b().whileTrue(drivetrain.applyRequest(() -> point.withModuleDirection(new Rotation2d(
                                -applyJoystickDeadzone(joystick.getLeftY()),
                                -applyJoystickDeadzone(joystick.getLeftX())))));

                joystick.povUp().whileTrue(
                                drivetrain.applyRequest(() -> forwardStraight.withVelocityX(0.5).withVelocityY(0)));
                joystick.povDown()
                                .whileTrue(drivetrain.applyRequest(
                                                () -> forwardStraight.withVelocityX(-0.5).withVelocityY(0)));

                // Run SysId routines when holding back/start and X/Y.
                // Note that each routine should be run exactly once in a single log.
                joystick.back().and(joystick.y()).whileTrue(drivetrain.sysIdDynamic(Direction.kForward));
                joystick.back().and(joystick.x()).whileTrue(drivetrain.sysIdDynamic(Direction.kReverse));
                joystick.start().and(joystick.y()).whileTrue(drivetrain.sysIdQuasistatic(Direction.kForward));
                joystick.start().and(joystick.x()).whileTrue(drivetrain.sysIdQuasistatic(Direction.kReverse));

                // Reset the field-centric heading on left bumper press.
                joystick.leftBumper().onTrue(drivetrain.runOnce(drivetrain::seedFieldCentric));

                drivetrain.registerTelemetry(logger::telemeterize);
        }

        public Command getAutonomousCommand() {
                /* Run the path selected from the auto chooser */
                return autoChooser.getSelected();
        }
}
