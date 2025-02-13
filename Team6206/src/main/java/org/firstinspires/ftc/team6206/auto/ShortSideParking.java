package org.firstinspires.ftc.team6206.auto;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.team6206.MecanumDrive;


@Autonomous(name = "Short Side Parking", group = "autonomous")
public class ShortSideParking extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Pose2d action1Starting = new Pose2d(32, -62, Math.toRadians(0));
        Pose2d action2Starting = new Pose2d(-30, -64, Math.toRadians(180));
        MecanumDrive mecanumDrive = new MecanumDrive(hardwareMap, action1Starting);
        OlmpyActions oActions = new OlmpyActions(hardwareMap);

        int height1 = 31;



        Action trajectoryAction1 = mecanumDrive.actionBuilder(mecanumDrive.pose)
                .strafeTo(new Vector2d(60, -61))
                .build();

       /* Action trajectoryAction2 = mecanumDrive.actionBuilder(mecanumDrive.pose)
                .turn(Math.toRadians(45))
                .strafeTo(new Vector2d(-52, -52))
                .waitSeconds(3)
                .build();

        Action trajectoryAction3 = mecanumDrive.actionBuilder(mecanumDrive.pose)
                .turn(Math.toRadians(-45))
                .strafeTo(new Vector2d(-47, -52))
                .strafeTo(new Vector2d(-47, -24))
                .waitSeconds(3)
                .build();

        Action trajectoryAction4 = mecanumDrive.actionBuilder(mecanumDrive.pose)
                .turn(Math.toRadians(45))
                .strafeTo(new Vector2d(-52, -52))
                .waitSeconds(3)
                .build();

        Action trajectoryAction5 = mecanumDrive.actionBuilder(mecanumDrive.pose)
                .turn(Math.toRadians(-45))
                .strafeTo(new Vector2d(-57, -24))
                .waitSeconds(3)
                .build();

        Action trajectoryAction6 = mecanumDrive.actionBuilder(mecanumDrive.pose)
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
        SequentialAction armChamberUpActions = new SequentialAction(
                oActions.liftDown(),
                oActions.up(),
                oActions.drop()
                );
*/        waitForStart();

        if (isStopRequested()) return;
        Actions.runBlocking(
                new SequentialAction(
                        trajectoryAction1 
//                        armUpActions,
//                        trajectoryAction2,
//                        armBasketDropActions,
//                        trajectoryAction3,
//                        armUpActions,
//                        trajectoryAction4,
//                        armBasketDropActions,
//                        trajectoryAction5,
//                        armUpActions,
//                        trajectoryAction6,
//                        armBasketDropActions
                )
        );
    }
}

