package org.firstinspires.ftc.team6206.auto;

import com.acmerobotics.dashboard.canvas.Canvas;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.team6206.MecanumDrive;

@Config
@Autonomous(name = "Red Auto", group = "Autonomous")
public class Observation extends LinearOpMode {

    double time = 0;



    @Override
    public void runOpMode() throws InterruptedException {
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(35, -62, Math.toRadians(90)));

        /*
        Action trajectoryAction1 = drive.actionBuilder(drive.pose)
                .lineToY(-10)
                .waitSeconds(1)
                .turn(Math.toRadians(90))
                .splineTo(new Vector2d(-64 ,-70), Math.toRadians(-90))
                .build();*/

        Action trajectoryAction1 = drive.actionBuilder(drive.pose)
               //.lineToX(32)
                .waitSeconds(time)
                .lineToY(-10)
                .waitSeconds(0.5)
                .turn(Math.toRadians(-90))
                .splineTo(new Vector2d(42 ,-70), Math.toRadians(-90))
                .build();
                /*.splineTo (new Vector2d(56,  -40), Math.toRadians(90))
                .waitSeconds(1)
                .splineTo (new Vector2d(57, 38), Math.toRadians(90))
                .turn(Math. toRadians(180))
                .splineTo (new Vector2d(32, -44), Math. toRadians(180))
                .splineToLinearHeading(new Pose2d(-43, -50, Math.toRadians(-110)), Math.toRadians(180))
                .build();*/

        Action trajectoryAction2 = drive.actionBuilder(drive.pose)
                .lineToY(-10)
                .waitSeconds(0.5)
                .turn(Math.toRadians(90))
                .splineTo(new Vector2d(10 ,-60), Math.toRadians(180))
                .turn(Math.toRadians(90))
                .lineToY(-10)
                .turn(Math.toRadians(-90))
                .splineTo(new Vector2d(10 ,-60), Math.toRadians(-90))
                .lineToY(-10)
                .turn(Math.toRadians(-90))
                .splineTo(new Vector2d(10 ,-60), Math.toRadians(-90))
                        .build();

        waitForStart();
        trajectoryAction2.preview(new Canvas());
        if (isStopRequested()) return;
        Actions.runBlocking(
                new SequentialAction(
                        trajectoryAction2
                )
        );
    }
}