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
@Autonomous(name = "Observation-Side Auto", group = "Autonomous")
public class ObSideAuto extends LinearOpMode {
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
                .waitSeconds(time)
                .turn(Math.toRadians(-90))
                .splineTo(new Vector2d(42 ,-60), Math.toRadians(-90))
                .lineToY(-10)
                .turn(Math.toRadians(90))
                .splineTo(new Vector2d(62 ,-58), Math.toRadians(-90))
                .build();


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


        waitForStart();

        if (isStopRequested()) return;
        Actions.runBlocking(
                new SequentialAction(
                        trajectoryAction1
                )
        );
    }
}