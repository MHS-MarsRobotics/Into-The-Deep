package org.firstinspires.ftc.team6206;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class OlmpyActions {
    private DcMotor motor;
    private DcMotor lift;
    Servo bucket;
    OpMode op;

    static final double TICKS_PER_INCH = 125;

    public OlmpyActions(HardwareMap hardwareMap) {
        lift = hardwareMap.get(DcMotor.class, "lift");
         bucket = hardwareMap.servo.get("bucket");

    }

    public Action spinUp () {
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
                    lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    lift.setTargetPosition(0);
                    lift.setPower(1);
                    initialized = true;
                }

                return lift.isBusy();
            }
        };
    }

    public Action liftUp() {
        return new Action() {
            private boolean initialized = false;
            private long startTime;

            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                if (!initialized) {
                    lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    lift.setTargetPosition((int) (TICKS_PER_INCH * 31));
                    lift.setPower(1);
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
                    bucket.setPosition(0.8);
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
                    bucket.setPosition(1);
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
}