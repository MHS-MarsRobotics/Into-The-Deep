package org.firstinspires.ftc.team6206.auto;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.team6206.MecanumDrive;


@Autonomous(name = "Bucket Side Auto 2", group = "autonomous")
public class BucketSide2 extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Pose2d action1Starting = new Pose2d(32, -62, Math.toRadians(0));
        Pose2d action2Starting = new Pose2d(-30, -64, Math.toRadians(180));
        MecanumDrive mecanumDrive = new MecanumDrive(hardwareMap, action2Starting);
        OlmpyActions oActions = new OlmpyActions(hardwareMap);

        int height1 = 31;

        Action trajectoryAction1 = mecanumDrive.actionBuilder(mecanumDrive.pose)
                .waitSeconds(1)
                .strafeTo(new Vector2d(-6, -34))
                .waitSeconds(1)
                .build();

        Action trajectoryAction2 = mecanumDrive.actionBuilder(mecanumDrive.pose)
                .strafeTo(new Vector2d(-6, -50))
                .waitSeconds(1)
                .splineTo(new Vector2d(-37, -22), Math.toRadians(90))
                .waitSeconds(1)
                .turn(Math.toRadians(90))
                .strafeTo(new Vector2d(-37, -24))
                .waitSeconds(3)
                .build();

        Action trajectoryAction3 = mecanumDrive.actionBuilder(mecanumDrive.pose)
                .turn(Math.toRadians(45))
                .strafeTo(new Vector2d(-52, -52))
                .waitSeconds(3)
                .build();

        Action trajectoryAction4 = mecanumDrive.actionBuilder(mecanumDrive.pose)
                .turn(Math.toRadians(-45))
                .strafeTo(new Vector2d(-47, -52))
                .strafeTo(new Vector2d(-47, -24))
                .waitSeconds(3)
                .build();

        Action trajectoryAction5 = mecanumDrive.actionBuilder(mecanumDrive.pose)
                .turn(Math.toRadians(45))
                .strafeTo(new Vector2d(-52, -52))
                .waitSeconds(3)
                .build();

        Action trajectoryAction6 = mecanumDrive.actionBuilder(mecanumDrive.pose)
                .turn(Math.toRadians(-45))
                .strafeTo(new Vector2d(-57, -24))
                .waitSeconds(3)
                .build();

        Action trajectoryAction7 = mecanumDrive.actionBuilder(mecanumDrive.pose)
                .turn(Math.toRadians(45))
                .strafeTo(new Vector2d(-52, -52))
                .build();



        waitForStart();

        SequentialAction armBasketDropActions = new SequentialAction(
                oActions.liftDown(),
                oActions.drop(),
                oActions.liftUp(height1)
        );
        SequentialAction armChamberDropActions = new SequentialAction(
                oActions.liftDown(),
                oActions.drop(),
                oActions.liftUp(height1)
        );
        SequentialAction armUpActions = new SequentialAction(
                oActions.liftDown(),
                oActions.up(),
                oActions.liftUp(height1)
        );

        if (isStopRequested()) return;
        Actions.runBlocking(
                new SequentialAction(
                        trajectoryAction1
//                        armChamberDropActions
//                        trajectoryAction2,
//                        armUpActions,
//                        trajectoryAction3,
//                        armBasketDropActions,
//                        trajectoryAction4,
//                        armUpActions,
//                        trajectoryAction5,
//                        armBasketDropActions,
//                        trajectoryAction6,
//                        armUpActions,
//                        trajectoryAction7,
//                        armBasketDropActions
                )
        );
    }
}

