package org.firstinspires.ftc.team6206;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Shooter {
    private DcMotor motor;

    public Shooter(HardwareMap hardwareMap) {
        motor = hardwareMap.get(DcMotor.class, "pulley");
    }

    public  Action spinUp () {
        return new Action() {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    motor.setTargetPosition(25);
                    motor.setPower(0.8);
                    initialized = true;
                }
                return motor.isBusy();
            }
        };
    }
}