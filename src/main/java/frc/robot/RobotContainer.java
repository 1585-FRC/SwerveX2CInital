// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static edu.wpi.first.units.Units.*;

import java.lang.ModuleLayer.Controller;

import com.ctre.phoenix6.swerve.SwerveModule.DriveRequestType;
import com.fasterxml.jackson.databind.util.Named;
import com.ctre.phoenix6.swerve.SwerveRequest;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.commands.FollowPathCommand;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.RobotModeTriggers;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine.Direction;

import frc.robot.generated.TunerConstants;
import frc.robot.subsystems.*;
import frc.robot.commands.*;

public class RobotContainer {


        private boolean enableDriveSystem = true;
        // Declare Subsystems Varaiables
        private final IO m_controller = new IO();
        private final Intake m_intake;

        // Declare Commands Variables
        private final IntakeCommand m_intakeCommand;

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

        private final CommandXboxController joystick = new CommandXboxController(0);

        // Apply a small deadzone for joystick axes (only for controller on port 0)
        // Inputs with absolute value <= 0.2 are treated as zero.
        private static double applyJoystickDeadzone(double v) {
                return Math.abs(v) > 0.2 ? v : 0.0;
        }

        public final CommandSwerveDrivetrain drivetrain = enableDriveSystem ? TunerConstants.createDrivetrain() : null;

        /* Path follower */
        private SendableChooser<Command> autoChooser = null;

        public RobotContainer() {
                // NamedCommands.registerCommand("Score", new ScoreCommand());

                if(enableDriveSystem)
                {
                        autoChooser = AutoBuilder.buildAutoChooser("Tests");
                        SmartDashboard.putData("Auto Mode", autoChooser);

                         // Warmup PathPlanner to avoid Java pauses
                        FollowPathCommand.warmupCommand().schedule();
                }

                configureBindings();

        

                // Create Object for Subystems

                m_intake = new Intake(51, 52);
                // m_shooter = new Shooter(61, 62);
                // m_hopper = new Hopper(71);
                // m_elevator = new Elevator(81, 82);

                // Create Object for Commands

                m_intakeCommand = new IntakeCommand(m_intake, m_controller);
                // m_shooterCommand = new ShooterCommand(m_shooter, m_controller);
                // m_hopperCommand = new HopperCommand(m_hopper, m_controller);
                // m_elevatorCommand = new ElevatorCommand(m_elevator, m_controller);
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
