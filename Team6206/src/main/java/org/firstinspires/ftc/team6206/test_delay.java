package org.firstinspires.ftc.team6206;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Config
@Autonomous(name = "Delayed Auto", group = "Autonomous")
public class test_delay extends LinearOpMode {

    double time = 0;
    
    @Override
    public void runOpMode() throws InterruptedException {
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(35, -62, Math.toRadians(90)));

        long waitTime = 250;


        while (opModeInInit()){
            telemetry.addData("Delay",time + "sec(s)");
            if (gamepad1.left_trigger>.5){
                time += .5;
                telemetry.update();

                sleep(waitTime);
            } else if (gamepad1.right_trigger>.5){
                time -= .5;

                time = Math.max(0, time);

                telemetry.update();

                sleep(waitTime);
            } else {
                telemetry.update();
            }

        }
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




        waitForStart();

        if (isStopRequested()) return;
        Actions.runBlocking(
                new SequentialAction(
                        trajectoryAction1
                )
        );
    }
}