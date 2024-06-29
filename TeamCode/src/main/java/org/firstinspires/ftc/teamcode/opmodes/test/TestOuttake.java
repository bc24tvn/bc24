package org.firstinspires.ftc.teamcode.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Outtake;

import static org.firstinspires.ftc.teamcode.Constants.OUTTAKE.*;

@TeleOp(name="outtake")
public class TestOuttake extends LinearOpMode {
    /**
     * Override this method and place your code here.
     * <p>
     * Please do not catch {@link InterruptedException}s that are thrown in your OpMode
     * unless you are doing it to perform some brief cleanup, in which case you must exit
     * immediately afterward. Once the OpMode has been told to stop, your ability to
     * control hardware will be limited.
     *
     * @throws InterruptedException When the OpMode is stopped while calling a method
     *                              that can throw {@link InterruptedException}
     */
    @Override
    public void runOpMode() throws InterruptedException {
        // Create outtake object
        Outtake outtake = new Outtake(this);

        // Wait for opMode to start
        waitForStart();

        // Main loop
        while(opModeIsActive()) {
            // Outtake lift
            if (gamepad1.left_bumper) {
                outtake.lift(OUTTAKE_SPEED);
            } else if (gamepad1.right_bumper) {
                outtake.lift(-OUTTAKE_SPEED);
            } else {
                outtake.lift(0);
            }

            // Outtake servo
            if (gamepad1.cross) {
                outtake.setServoOut(true);
            } else if (gamepad1.circle) {
                outtake.setServoOut(false);
            }
        }
    }
}
