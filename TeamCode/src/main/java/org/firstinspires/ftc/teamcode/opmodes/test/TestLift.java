package org.firstinspires.ftc.teamcode.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystems.Lift;

public class TestLift extends LinearOpMode {
    private Lift lift;
    @Override
    public void runOpMode() {
        lift = new Lift(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            lift.setMotor(gamepad1.left_stick_y, gamepad1.right_stick_y);
        }
    }
}