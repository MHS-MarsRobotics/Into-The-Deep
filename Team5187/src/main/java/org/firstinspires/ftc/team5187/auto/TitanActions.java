package org.firstinspires.ftc.team5187.auto;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class TitanActions {
    private DcMotor motor;
    private DcMotor lift;
    Servo pinch;
    OpMode op;

    static final double TICKS_PER_INCH = 125;

    public TitanActions(HardwareMap hardwareMap) {
        lift = hardwareMap.get(DcMotor.class, "lift");
         pinch = hardwareMap.servo.get("pinch");

    }

    public Action unPinch() {
        return new Action() {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                if (!initialized) {
                    pinch.setPosition(0.4);
                    initialized = true;
                }

                return Double.compare(pinch.getPosition(), 0.4) == 0;
            }
        };
    }
    public Action pinch() {
        return new Action() {
            private boolean initialized = false;
            private long startTime;

            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                if (!initialized) {
                    pinch.setPosition(1);
                    initialized = true;
                }

                return Double.compare(pinch.getPosition(), 1) == 0;
            }
        };
    }
}