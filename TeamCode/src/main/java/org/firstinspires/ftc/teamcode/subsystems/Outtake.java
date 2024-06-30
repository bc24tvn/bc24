package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PwmControl;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;

import static org.firstinspires.ftc.teamcode.Constants.OUTTAKE.*;

public class Outtake {
    private DcMotor leftLift, rightLift;
    private ServoImplEx outtakeServoLeft, outtakeServoRight, storageHolderServo;

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
        outtakeServoLeft = (ServoImplEx) hardwareMap.get(Servo.class, "outtakeServoLeft");
        outtakeServoRight = (ServoImplEx) hardwareMap.get(Servo.class, "outtakeServoRight");
        storageHolderServo = (ServoImplEx) hardwareMap.get(Servo.class, "storageHolderServo");
        outtakeServoLeft.setPwmRange(new PwmControl.PwmRange(500, 2500));
        outtakeServoRight.setPwmRange(new PwmControl.PwmRange(500, 2500));
        storageHolderServo.setPwmRange(new PwmControl.PwmRange(500, 2500));

        // Reset servo to default location
        outtakeServoLeft.setPosition(OUTTAKE_SERVO_START_POS);
        outtakeServoRight.setPosition(1 - OUTTAKE_SERVO_START_POS);
        storageHolderServo.setPosition(STORAGE_SERVO_START_POS);
    }

    public void liftLeft(double speed) {
        leftLift.setPower(speed);
    }
    public void liftRight(double speed) {
        rightLift.setPower(speed);
    }

    public void setServoPos(double pos) {
        outtakeServoLeft.setPosition(pos);
        outtakeServoRight.setPosition(1 - pos);
    }

    public void setStorage(boolean open) {
        storageHolderServo.setPosition(open ? STORAGE_SERVO_START_POS : STORAGE_SERVO_RELEASE_POS);
    }
}
