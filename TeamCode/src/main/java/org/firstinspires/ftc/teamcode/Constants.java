package org.firstinspires.ftc.teamcode;

public class Constants {
    public static class SENSE {
        public static final double JOYSTICK_SENSE = 0.05;
        public static final double TRIGGER_SENSE = 0.3;
    }

    public static class OUTTAKE {
        // Outtake servo positions
        public static final double OUTTAKE_SERVO_START_POS = 0.0;
        public static final double OUTTAKE_SERVO_IN_POS = 80.0 / 270;
        public static final double OUTTAKE_SERVO_OUT_POS = 170.0 / 270;

        // Storage holder servo positions
        public static final double STORAGE_SERVO_START_POS = 0.0;
        public static final double STORAGE_SERVO_RELEASE_POS = 120.0 / 270;
    }

    public static class SPEED {
        public static final double LIFT_SPEED = 0.7;
        public static final double BASE_DEFAULT = 0.5;
        public static final double BASE_BOOST = 1;
        public static final double OUTTAKE_SPEED = 0.5;
        public static final double INTAKE_SPEED = 0.8;
    }
}
