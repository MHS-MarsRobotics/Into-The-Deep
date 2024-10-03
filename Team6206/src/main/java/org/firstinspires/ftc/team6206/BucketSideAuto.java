package org.firstinspires.ftc.team6206;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Autonomous(name = "Bucket Side Auto", group = "autonomous")
public class BucketSideAuto extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        MecanumDrive mecanumDrive = new MecanumDrive(hardwareMap, new Pose2d(32, -62, Math.toRadians(90)));


        Action trajectoryAction1 = mecanumDrive.actionBuilder(mecanumDrive.pose)
                .waitSeconds(1)
                .strafeTo(new Vector2d(0,-45))
                .waitSeconds(2)
                .strafeTo(new Vector2d(0,-13))
                .waitSeconds(1)
                .strafeTo(new Vector2d(32,-24))
                .waitSeconds(1)
                .turn(Math.toRadians(-90))
                .waitSeconds(1)
                .strafeTo(new Vector2d(32,14))
                .waitSeconds(1)
                .strafeTo(new Vector2d(44,-24))
                .waitSeconds(1)
                .strafeTo(new Vector2d(44,14))
                .waitSeconds(1)
                .build();

        waitForStart();

        if (isStopRequested()) return;
        Actions.runBlocking(
                new SequentialAction(
                        trajectoryAction1
                )
        );
    }
}
