package org.firstinspires.ftc.teamcode.opmodes.main;

import static org.firstinspires.ftc.teamcode.Constants.SPEED.*;
import static org.firstinspires.ftc.teamcode.Constants.SENSE.*;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;
import org.firstinspires.ftc.teamcode.subsystems.Outtake;

@TeleOp(name = "Main")
public class Main extends LinearOpMode {
    private Lift lift;
    private Intake intake;
    private Outtake outtake;
    private double speed = 0.5;
    private int isReversed = 1;
    boolean lastState = false;

    // Joystick sensitivity filter
    private double sense(double value) {
        return Math.abs(value) > JOYSTICK_SENSE ? value : 0;
    }

    // Trigger
    private boolean trigger(double value) {
        return value > TRIGGER_SENSE;
    }

    @Override
    public void runOpMode() {
        // Get drivebase motor objects
        DcMotor leftMotor = hardwareMap.get(DcMotor.class,"leftMotor");
        DcMotor rightMotor = hardwareMap.get(DcMotor.class,"rightMotor");

        // Reverse left motor
        leftMotor.setDirection(DcMotor.Direction.REVERSE);

        // Set brake
        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Initialize subsystems
        lift = new Lift(hardwareMap);
        intake = new Intake(hardwareMap);
        outtake = new Outtake(hardwareMap);

        // Wait for start
        waitForStart();

        // Main loop
        while (opModeIsActive()) {
            // Drivebase boost control
            if (gamepad1.right_bumper) {
                speed = BASE_BOOST;
            }

            else if (gamepad1.left_bumper) {
                speed = BASE_DEFAULT;
            }

            // Get joystick values and set to drivebase motors
            double leftPower  = sense(gamepad1.left_stick_y * speed);
            double rightPower = sense(gamepad1.right_stick_y * speed);

            leftMotor.setPower(leftPower);
            rightMotor.setPower(rightPower);

            // Toggle reverse mode
            boolean curState = trigger(gamepad1.right_trigger);
            if (curState && !lastState) {
                isReversed = -isReversed;
            }
            lastState = curState;

            // Lift control
            if (gamepad1.dpad_up) {
                lift.setMotor(isReversed * LIFT_SPEED,isReversed * LIFT_SPEED);
            }

            else if (gamepad1.dpad_down) {
                lift.setMotor(-isReversed * LIFT_SPEED,-isReversed * LIFT_SPEED);
            }

            else {
                lift.setMotor(0,0);
            }

            // Intake control
            if (gamepad1.dpad_left) {
                intake.setRollSpeed(INTAKE_SPEED);
            }

            else if (gamepad1.dpad_right) {
                intake.setRollSpeed(-INTAKE_SPEED);
            }

            else {
                intake.setRollSpeed(0);
            }

            // Outtake control
            if (gamepad1.left_bumper) outtake.liftLeft(isReversed * OUTTAKE_SPEED);
            if (gamepad1.right_bumper) outtake.liftLeft(isReversed * OUTTAKE_SPEED);

            outtake.setServoOut(gamepad1.triangle);
        }
    }
}
