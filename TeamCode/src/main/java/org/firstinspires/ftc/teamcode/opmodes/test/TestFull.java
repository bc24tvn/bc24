package org.firstinspires.ftc.teamcode.opmodes.test;

import static org.firstinspires.ftc.teamcode.Constants.LIFT.LIFT_SPEED;

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
    @Override
    public void runOpMode() {
        lift = new Lift(hardwareMap);

        DcMotor leftMotor = hardwareMap.get(DcMotor.class,"leftMotor") ;
        DcMotor rightMotor = hardwareMap.get(DcMotor.class,"rightMotor") ;

        leftMotor.setDirection(DcMotor.Direction.REVERSE) ;

        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        intake = new Intake(hardwareMap);

        outtake = new Outtake(this);

        waitForStart();

        while (opModeIsActive()) {
            double leftPower  = -gamepad1.left_stick_y ;
            double rightPower = -gamepad1.right_stick_y ;

            leftMotor.setPower(leftPower);
            rightMotor.setPower(rightPower);

            if (gamepad1.dpad_up) lift.setMotor(LIFT_SPEED,LIFT_SPEED);
            else if (gamepad1.dpad_down) lift.setMotor(-LIFT_SPEED,-LIFT_SPEED);
            else lift.setMotor(0,0);

            if (gamepad1.left_bumper) intake.deployIntake();
            else if (gamepad1.right_bumper) intake.resetIntake();

            if (gamepad1.square) intake.setRollSpeed(0.8);
            else intake.setRollSpeed(0);

            if (gamepad1.dpad_right) outtake.lift(0.5);
            else if (gamepad1.dpad_left) outtake.lift(-0.5);
            else outtake.lift(0);

            outtake.setServoOut(gamepad1.triangle);
        }
    }
}
