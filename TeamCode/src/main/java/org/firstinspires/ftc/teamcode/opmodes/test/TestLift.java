package org.firstinspires.ftc.teamcode.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.subsystems.Lift;

public class TestLift extends LinearOpMode {
    private Lift lift;
    private int mode = 0;
    @Override
    public void runOpMode() {
        lift = new Lift(hardwareMap);

        DcMotor leftMotor = hardwareMap.get(DcMotor.class,"leftMotor") ;
        DcMotor rightMotor = hardwareMap.get(DcMotor.class,"rightMotor") ;

        leftMotor.setDirection(DcMotor.Direction.REVERSE) ;

        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        while (opModeIsActive()) {
            double leftPower  = -gamepad1.left_stick_y ;
            double rightPower = -gamepad1.right_stick_y ;

            leftMotor.setPower(leftPower);
            rightMotor.setPower(rightPower);

            if (gamepad1.left_bumper) mode = 1;
            else if (gamepad1.right_bumper) mode = 2;
            else mode = 0;

            if (mode == 1) lift.setMotor(-gamepad1.left_stick_y, -gamepad1.right_stick_y);
            else if (mode == 2) lift.setMotor(-gamepad1.left_stick_y, -gamepad1.left_stick_y);
            else lift.setMotor(0,0);

            telemetry.addData("Current Mode: ", mode);
            telemetry.update();
        }
    }
}