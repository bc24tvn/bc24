package org.firstinspires.ftc.teamcode.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystems.Lift;

public class TestLift extends LinearOpMode {
    private Lift lift;
    private int mode = 1;
    @Override
    public void runOpMode() {
        lift = new Lift(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.left_bumper) mode = 1;
            else if (gamepad1.right_bumper) mode = 2;
            if (mode == 1) lift.setMotor(-gamepad1.left_stick_y, -gamepad1.right_stick_y);
            else lift.setMotor(-gamepad1.left_stick_y, -gamepad1.left_stick_y);
        }
    }
}