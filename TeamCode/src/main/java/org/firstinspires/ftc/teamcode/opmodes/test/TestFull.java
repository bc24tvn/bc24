package org.firstinspires.ftc.teamcode.opmodes.test;

import static org.firstinspires.ftc.teamcode.Constants.SPEED.*;
import static org.firstinspires.ftc.teamcode.Constants.OUTTAKE.*;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;
import org.firstinspires.ftc.teamcode.subsystems.Outtake;

@TeleOp(name = "TestFull")
public class TestFull extends LinearOpMode {
    private Lift lift;
    private Intake intake;
    private Outtake outtake;
    private double speed = 0.5;
    private int isReversed = 1;
    boolean lastState = false;
    @Override
    public void runOpMode() {
        lift = new Lift(hardwareMap);

        DcMotor leftMotor = hardwareMap.get(DcMotor.class,"leftMotor") ;
        DcMotor rightMotor = hardwareMap.get(DcMotor.class,"rightMotor") ;

        leftMotor.setDirection(DcMotor.Direction.REVERSE) ;

        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        intake = new Intake(hardwareMap);

        outtake = new Outtake(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            double leftPower  = gamepad1.left_stick_y * speed;
            double rightPower = gamepad1.right_stick_y * speed;

            leftMotor.setPower(leftPower);
            rightMotor.setPower(rightPower);

            if (gamepad1.right_bumper) speed = 1;
            else if (gamepad1.left_bumper) speed = 0.5;

            if (gamepad1.dpad_up) lift.setMotor(isReversed * LIFT_SPEED,isReversed * LIFT_SPEED);
            else if (gamepad1.dpad_down) lift.setMotor(-isReversed * LIFT_SPEED,-isReversed * LIFT_SPEED);
            else lift.setMotor(0,0);

            if (gamepad1.dpad_left) intake.setRollSpeed(0.8);
            else if (gamepad1.dpad_right) intake.setRollSpeed(-0.8);
            else intake.setRollSpeed(0);

            boolean curState = (gamepad1.right_trigger > 0.1);
            if (curState && !lastState) {
                isReversed = -isReversed;
            }
            lastState = curState;

            if (gamepad1.left_bumper) outtake.liftLeft(isReversed * OUTTAKE_SPEED);
            if (gamepad1.right_bumper) outtake.liftLeft(isReversed * OUTTAKE_SPEED);

            outtake.setServoOut(gamepad1.triangle);
        }
    }
}
