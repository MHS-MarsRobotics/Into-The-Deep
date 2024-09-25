package org.firstinspires.ftc.team6206;
import androidx.annotation.NonNull;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.team6206.MecanumDrive;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.ArrayList;
import java.util.List;

@Config
@Autonomous(name = "Red Auto", group = "Autonomous")
public class Auton_1 extends LinearOpMode {



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
                .lineToY(-10)
                .waitSeconds(0.5)
                .turn(Math.toRadians(-90))
                .splineTo(new Vector2d(42 ,-64), Math.toRadians(-90))
                .lineToY(-10)
                .turn(Math.toRadians(90))
                .splineTo(new Vector2d(52 ,-64), Math.toRadians(-90))
                .lineToY(-10)
                .turn(Math.toRadians(90))
                .splineTo(new Vector2d(62 ,-64), Math.toRadians(-90))
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