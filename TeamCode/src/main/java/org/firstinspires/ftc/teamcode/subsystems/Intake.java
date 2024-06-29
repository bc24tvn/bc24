package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Intake {
    private DcMotor rollerMotor1, rollerMotor2;
    private Servo leftDeployServo, rightDeployServo;

    public Intake(HardwareMap hardwareMap) {
        this.rollerMotor1 = hardwareMap.get(DcMotor.class, "rollerMotor1");
        this.rollerMotor2 = hardwareMap.get(DcMotor.class, "rollerMotor2");
        this.rollerMotor1.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void setRollSpeed(double speed) {
        rollerMotor1.setPower(speed);
        rollerMotor2.setPower(speed);
    }
    public double getRollSpeed() {
        return rollerMotor1.getPower();
    }
}
