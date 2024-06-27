package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Lift {
    private DcMotor motorLeft, motorRight;
    public Lift(HardwareMap hardwareMap) {
        motorLeft = hardwareMap.get(DcMotor.class,"leftLift");
        motorRight = hardwareMap.get(DcMotor.class, "rightLift");

        motorLeft.setPower(0);
        motorRight.setPower(0);

        motorLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motorLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        motorRight.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    public void setMotor(double pwLeft, double pwRight) {
        motorLeft.setPower(pwLeft);
        motorRight.setPower(pwRight);
    }
}
