package org.firstinspires.ftc.team6206.auto;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.team6206.MecanumDrive;


@Autonomous(name = "Marker", group = "autonomous")
public class MarkerTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Pose2d action1Starting = new Pose2d(32, -62, Math.toRadians(0));
        Pose2d action2Starting = new Pose2d(-30, -64, Math.toRadians(0));
        MecanumDrive mecanumDrive = new MecanumDrive(hardwareMap, action2Starting);
        OlmpyActions oActions = new OlmpyActions(hardwareMap);

        Action trajectoryAction2 = mecanumDrive.actionBuilder(mecanumDrive.pose)
                .waitSeconds(1)
                .strafeTo(new Vector2d(-52,-62))
                .build();

        Action trajectoryAction1 = mecanumDrive.actionBuilder(mecanumDrive.pose)
                .strafeTo(new Vector2d(-30,-64))
                .waitSeconds(1)
                .build();

        waitForStart();

        if (isStopRequested()) return;
        Actions.runBlocking(
                new SequentialAction(
                        trajectoryAction2,
                        oActions.liftUp(),
                        oActions.drop(),
                        trajectoryAction1,
                        oActions.liftDown(),
                        oActions.up()

                )
        );
    }
}

