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


       /* Action trajectoryAction1 = drive.actionBuilder(drive.pose)
                .lineToX(32)
                .waitSeconds(time)
                .lineToY(-10)
                .waitSeconds(time)
                .turn(Math.toRadians(-90))
                .splineTo(new Vector2d(42 ,-60), Math.toRadians(-90))
                .lineToY(-10)
                .turn(Math.toRadians(90))
                .splineTo(new Vector2d(62 ,-58), Math.toRadians(-90))
                .build();

        */

        Action trajectoryAction2 = drive.actionBuilder(new Pose2d(32, -62, Math.toRadians(90)))
                .waitSeconds(1)
                .strafeTo(new Vector2d(6,-34))
                .waitSeconds(1)
                .strafeTo(new Vector2d(6,-50))
                .waitSeconds(1)
                .splineTo(new Vector2d(37,-20),Math.toRadians(90))
                .waitSeconds(1)
                .turn(Math.toRadians(-90))
                .strafeTo(new Vector2d(37,-20))
                .waitSeconds(3)
                .turn(Math.toRadians(-45))
                .strafeTo(new Vector2d(52,-52))
                .turn(Math.toRadians(45))
                .strafeTo(new Vector2d(47,-52))
                .strafeTo(new Vector2d(47,-20))
                .waitSeconds(3)
                .turn(Math.toRadians(-45))
                .strafeTo(new Vector2d(52,-52))
                .turn(Math.toRadians(45))
                .strafeTo(new Vector2d(57,-20))
                .waitSeconds(3)
                .strafeTo(new Vector2d(52,-60))
                .waitSeconds(2)
                .turn(Math.toRadians(90))
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
                        trajectoryAction2
                )
        );
    }
}