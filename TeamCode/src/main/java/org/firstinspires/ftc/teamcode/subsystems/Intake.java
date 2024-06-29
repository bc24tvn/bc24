package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Intake {
    private DcMotor rollerMotor;
    private Servo leftDeployServo, rightDeployServo;

    public Intake(HardwareMap hardwareMap) {
        this.rollerMotor = hardwareMap.get(DcMotor.class, "rollerMotor");
        this.leftDeployServo = hardwareMap.get(Servo.class, "leftDeployServo");
        this.rightDeployServo = hardwareMap.get(Servo.class, "rightDeployServo");
    }

    public void setRollSpeed(double speed) {
        rollerMotor.setPower(speed);
    }
    public double getRollSpeed() {
        return rollerMotor.getPower();
    }
    public void deployIntake() {
        leftDeployServo.setPosition(1);
        rightDeployServo.setPosition(1);
    }
    public void resetIntake() {
        leftDeployServo.setPosition(0);
        rightDeployServo.setPosition(0);
    }
}
