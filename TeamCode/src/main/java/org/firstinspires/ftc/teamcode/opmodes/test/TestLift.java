package org.firstinspires.ftc.teamcode.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import static org.firstinspires.ftc.teamcode.Constants.LIFT.*;

import org.firstinspires.ftc.teamcode.subsystems.Lift;

@TeleOp(name = "TestLift")
public class TestLift extends LinearOpMode {
    private Lift lift;
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

            if (gamepad1.dpad_up) lift.setMotor(LIFT_SPEED,LIFT_SPEED);
            else if (gamepad1.dpad_down) lift.setMotor(-LIFT_SPEED,-LIFT_SPEED);
            else lift.setMotor(0,0);
        }
    }
}