package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import static org.firstinspires.ftc.teamcode.Constants.OUTTAKE.*;

public class Outtake {
    private DcMotor leftLift, rightLift;
    private Servo outtakeServo;

    public Outtake(HardwareMap hardwareMap) {
        // Get motor objects from hardwareMap
        leftLift = hardwareMap.get(DcMotor.class, "leftOuttakeMotor");
        rightLift = hardwareMap.get(DcMotor.class, "rightOuttakeMotor");

        // Reverse one motor
        rightLift.setDirection(DcMotorSimple.Direction.REVERSE);

        // Reset motor
        leftLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftLift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightLift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Set brake
        leftLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Get servo object from hardwareMap
        outtakeServo = hardwareMap.get(Servo.class, "outtakeServo");

        // Reset servo to default location
        outtakeServo.setPosition(OUTTAKE_SERVO_NORM_POS);
    }

    public void liftLeft(double speed) {
        leftLift.setPower(speed);
    }
    public void liftRight(double speed) {
        rightLift.setPower(speed);
    }

    public void setServoOut(boolean out) {
        if (out) {
            outtakeServo.setPosition(OUTTAKE_SERVO_OUT_POS);
        } else {
            outtakeServo.setPosition(OUTTAKE_SERVO_NORM_POS);
        }
    }
}
