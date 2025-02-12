package org.firstinspires.ftc.team6206.auto;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class OlmpyActions {
    private DcMotor motor;
    private DcMotorEx lift;
    private DcMotor leftFront;
    private DcMotor leftBack;
    private DcMotor rightBack;
    private DcMotor rightFront;
    Servo bucket;
    CRServo intake;
    OpMode op;

    static final double TICKS_PER_INCH = 125;

    public OlmpyActions(HardwareMap hardwareMap) {
        lift = hardwareMap.get(DcMotorEx.class, "lift");
        bucket = hardwareMap.servo.get("bucket");
        intake =  hardwareMap.crservo.get("intake");

        leftFront = hardwareMap.get(DcMotorEx.class, "left motor 1");
        leftBack = hardwareMap.get(DcMotorEx.class, "left motor 2");
        rightBack = hardwareMap.get(DcMotorEx.class, "right motor 2");
        rightFront = hardwareMap.get(DcMotorEx.class, "right motor 1");
    }

    public Action spinUp() {
        return new Action() {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    motor.setTargetPosition(25);
                    motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    motor.setPower(0.8);
                    initialized = true;
                }
                return motor.isBusy();
            }
        };
    }

    public Action liftDown() {
        return new Action() {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                if (!initialized) {
                    lift.setTargetPosition(0);
                    lift.setTargetPositionTolerance(60);

                    lift.setPower(1);
                    lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    initialized = true;
                }

                return lift.isBusy();
            }
        };
    }

    public Action liftUp(int height) {
        return new Action() {
            private boolean initialized = false;
            private long startTime;

            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                if (!initialized) {
                    lift.setTargetPosition((int) (TICKS_PER_INCH * height));
                    lift.setPower(1);
                    lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    initialized = true;
                    startTime = telemetryPacket.addTimestamp();
                }

                if (telemetryPacket.addTimestamp() - startTime > 1000) {
                    return false;
                }

                return lift.isBusy();
            }
        };
    }

    public Action drop() {
        return new Action() {
            private boolean initialized = false;
            private long startTime;

            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                if (!initialized) {
                    bucket.setPosition(0.333);
                    initialized = true;
                    startTime = telemetryPacket.addTimestamp();
                }

                if (telemetryPacket.addTimestamp() - startTime > 1000) {
                    return false;
                }

                return true;
            }
        };
    }

    public Action up() {
        return new Action() {
            private boolean initialized = false;
            private long startTime;

            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                if (!initialized) {
                    bucket.setPosition(0);
                    initialized = true;
                    startTime = telemetryPacket.addTimestamp();
                }

                if (telemetryPacket.addTimestamp() - startTime > 1000) {
                    return false;
                }

                return true;
            }
        };
    }

    public Action in_intake() {
        return new Action() {
            private boolean initialized = false;
            private long startTime;

            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                if (!initialized) {
                    intake.setPower(1);
                    intake.setDirection(DcMotorSimple.Direction.FORWARD);
                    initialized = true;
                    startTime = telemetryPacket.addTimestamp();
                }

                if (telemetryPacket.addTimestamp() - startTime > 1000) {
                    return false;
                }

                return true;
            }
        };
    }

    public Action out_intake() {
        return new Action() {
            private boolean initialized = false;
            private long startTime;

            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                if (!initialized) {
                    intake.setPower(1);
                    intake.setDirection(DcMotorSimple.Direction.REVERSE);
                    initialized = true;
                    startTime = telemetryPacket.addTimestamp();
                }

                if (telemetryPacket.addTimestamp() - startTime > 1000) {
                    return false;
                }

                return true;
            }
        };
    }
    public Action stop_intake() {



        return new Action() {
            private boolean initialized = false;
            private long startTime;

            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                if (!initialized) {
                    intake.setPower(0);
                    initialized = true;
                    startTime = telemetryPacket.addTimestamp();
                }

                if (telemetryPacket.addTimestamp() - startTime > 1000) {
                    return false;
                }

                return true;
            }
        };
    }

    public Action tele() {
        return new Action() {
            private boolean initialized = false;
            private long startTime;

            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {

                return rightFront.isBusy() || rightBack.isBusy() || leftFront.isBusy() || leftBack.isBusy();
            }
        };
    }
}

