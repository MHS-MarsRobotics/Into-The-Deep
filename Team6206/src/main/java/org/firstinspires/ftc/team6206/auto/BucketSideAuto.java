package org.firstinspires.ftc.team6206.auto;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.team6206.MecanumDrive;


@Autonomous(name = "Bucket Side Auto", group = "autonomous")
public class BucketSideAuto extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Pose2d action1Starting = new Pose2d(32, -62, Math.toRadians(90));
        Pose2d action2Starting = new Pose2d(-30, -64, Math.toRadians(90));
        MecanumDrive mecanumDrive = new MecanumDrive(hardwareMap, action2Starting);


        Action trajectoryAction1 = mecanumDrive.actionBuilder(action2Starting)
                .waitSeconds(1)
                .strafeTo(new Vector2d(0,-45))
                .waitSeconds(2)
                .strafeTo(new Vector2d(32,-45))
                .waitSeconds(1)
                .strafeTo(new Vector2d(32,-24))
                .waitSeconds(1)
                .turn(Math.toRadians(-90))
                .waitSeconds(1)
                .strafeTo(new Vector2d(32,-62))
                .waitSeconds(1)
                .strafeTo(new Vector2d(44,-24))
                .waitSeconds(1)
                .strafeTo(new Vector2d(44,-62))
                .waitSeconds(1)
                .build();

        Action trajectoryAction2 = mecanumDrive.actionBuilder(mecanumDrive.pose)
                .waitSeconds(1)
                .strafeTo(new Vector2d(-6,-34))
                .waitSeconds(1)
                .strafeTo(new Vector2d(-6,-50))
                .waitSeconds(1)
                .splineTo(new Vector2d(-37,-22),Math.toRadians(90))
                .waitSeconds(1)
                .turn(Math.toRadians(90))
                .strafeTo(new Vector2d(-37,-24))
                .waitSeconds(3)
                .turn(Math.toRadians(45))
                .strafeTo(new Vector2d(-52,-52))
                .turn(Math.toRadians(-45))
                .strafeTo(new Vector2d(-47,-52))
                .strafeTo(new Vector2d(-47,-24))
                .waitSeconds(3)
                .turn(Math.toRadians(45))
                .strafeTo(new Vector2d(-52,-52))
                .turn(Math.toRadians(-45))
                .strafeTo(new Vector2d(-57,-24))
                .waitSeconds(3)
                .turn(Math.toRadians(45))
                .strafeTo(new Vector2d(-52,-52))
                .build();

        waitForStart();

        if (isStopRequested()) return;
        Actions.runBlocking(
                new SequentialAction(
                        trajectoryAction2
                )
        );
    }
}
