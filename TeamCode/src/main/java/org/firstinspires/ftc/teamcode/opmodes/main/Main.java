package org.firstinspires.ftc.teamcode.opmodes.main;

import static org.firstinspires.ftc.teamcode.Constants.OUTTAKE.*;
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
    private boolean trigger(float value) {
        return value > TRIGGER_SENSE;
    }

    @Override
    public void runOpMode() {
        boolean outtake_servo_state = false;
        boolean last_outtake_button_state = false;
        boolean storage_servo_state = false;
        boolean last_holder_button_state = false;

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
            // Drivebase boost control (gamepad1)
            if (gamepad1.triangle) {
                speed = BASE_BOOST;
            }

            else if (gamepad1.cross) {
                speed = BASE_DEFAULT;
            }

            // Get joystick values and set to drivebase motors
            double leftPower  = sense(gamepad1.left_stick_y * speed);
            double rightPower = sense(gamepad1.right_stick_y * speed);

            if (leftPower == 0 && rightPower == 0) {
                if (gamepad1.dpad_up) {
                    leftPower = speed;
                    rightPower = speed;
                } else if (gamepad1.dpad_down) {
                    leftPower = -speed;
                    rightPower = -speed;
                } else {
                    leftPower = 0;
                    rightPower = 0;
                }
            }

            leftMotor.setPower(leftPower);
            rightMotor.setPower(rightPower);
            
            // Lift control (gamepad2)
            double liftLeft = 0;
            double liftRight = 0;

            if (gamepad2.left_bumper) {
                liftLeft = LIFT_SPEED;
            } else if (trigger(gamepad2.left_trigger)) {
                liftLeft = -LIFT_SPEED;
            } else {
                liftLeft = 0;
            }

            if (gamepad2.right_bumper) {
                liftRight = LIFT_SPEED;
            } else if (trigger(gamepad2.right_trigger)) {
                liftRight = -LIFT_SPEED;
            } else {
                liftRight = 0;
            }

            lift.setMotor(liftLeft, liftRight);

            // Intake control (gamepad1)
            if (gamepad1.left_bumper) {
                intake.setRollSpeed(INTAKE_SPEED);
            } else if (trigger(gamepad1.left_trigger)) {
                intake.setRollSpeed(-INTAKE_SPEED);
            } else {
                intake.setRollSpeed(0);
            }

            // Outtake control (gamepad2)
            double gp2l = sense(-gamepad2.left_stick_y);
            double gp2r = sense(-gamepad2.right_stick_y);

            if (gp2l > 0 || gp2r > 0) {
                outtake.liftLeft(gp2l * OUTTAKE_SPEED);
                outtake.liftRight(gp2l * OUTTAKE_SPEED);
            } else {
                if (gamepad2.dpad_up) {
                    outtake.liftLeft(OUTTAKE_SPEED);
                    outtake.liftRight(OUTTAKE_SPEED);
                } else if (gamepad2.dpad_down) {
                    outtake.liftLeft(-OUTTAKE_SPEED);
                    outtake.liftRight(-OUTTAKE_SPEED);
                } else {
                    outtake.liftLeft(0);
                    outtake.liftRight(0);
                }
            }

            boolean current_outtake_button_state = trigger(gamepad1.right_trigger);
            if (current_outtake_button_state && !last_outtake_button_state) {
                if (outtake_servo_state) {
                    outtake.setServoPos(OUTTAKE_SERVO_OUT_POS);
                    outtake_servo_state = false;
                } else {
                    outtake.setServoPos(OUTTAKE_SERVO_IN_POS);
                    outtake_servo_state = true;
                }
            }

            last_outtake_button_state = current_outtake_button_state;

            boolean current_holder_button_state = gamepad1.right_bumper;
            if (current_holder_button_state && !last_holder_button_state) {
                storage_servo_state = !storage_servo_state;
                outtake.setStorage(storage_servo_state);
            }

            last_holder_button_state = current_holder_button_state;
        }
    }
}
