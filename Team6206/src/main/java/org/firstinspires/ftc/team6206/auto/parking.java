package org.firstinspires.ftc.team6206.auto;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.team6206.MecanumDrive;


@Autonomous(name = "Parking", group = "autonomous")
public class parking extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Pose2d action1Starting = new Pose2d(32, -62, Math.toRadians(0));
        Pose2d action2Starting = new Pose2d(-30, -64, Math.toRadians(90));
        MecanumDrive mecanumDrive = new MecanumDrive(hardwareMap, action2Starting);
        OlmpyActions oActions = new OlmpyActions(hardwareMap);

        int height1 = 31;

        Action trajectoryAction1 = mecanumDrive.actionBuilder(mecanumDrive.pose)
                .strafeTo(new Vector2d(50,-64))
                .build();

        waitForStart();

        if (isStopRequested()) return;
        Actions.runBlocking(
                new ParallelAction(
                        trajectoryAction1
                )
        );
    }
}

