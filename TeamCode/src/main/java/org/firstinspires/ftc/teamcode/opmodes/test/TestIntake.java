package org.firstinspires.ftc.teamcode.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.subsystems.Intake;

public class TestIntake extends LinearOpMode {
    private Intake intake;
    @Override
    public void runOpMode() throws InterruptedException {
        intake = new Intake(hardwareMap);

        waitForStart();
        if(opModeIsActive()) {
            intake.setRollSpeed(1);
            while (opModeIsActive()) {
                if (gamepad1.left_bumper) {
                    if (intake.getRollSpeed() == 0) intake.setRollSpeed(1);
                    else if (intake.getRollSpeed() == 1) intake.setRollSpeed(0);
                }
            }
        }
    }
}
