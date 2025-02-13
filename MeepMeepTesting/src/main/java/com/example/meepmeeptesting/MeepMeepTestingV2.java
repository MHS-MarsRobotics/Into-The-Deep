package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MeepMeepTestingV2 {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(700);

          RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-32, -62, Math.toRadians(180)))
                                /*.lineTo(new Vector2d(-32,-10))
                                .waitSeconds(0.5)
                                .turn(Math.toRadians(90))
                                .splineTo(new Vector2d(-32 ,-60), Math.toRadians(180))
                                .turn(Math.toRadians(90))
                                .back(60)
                                .turn(Math.toRadians(-90))
                                .splineTo(new Vector2d(-42 ,-60), Math.toRadians(-90))
                                .back(60)
                                .turn(Math.toRadians(-90))
                                .splineTo(new Vector2d(-52 ,-60), Math.toRadians(-90))

                               */

                               /* .lineTo(new Vector2d(0,-37))
                                .waitSeconds(0.5)
                                .lineTo(new Vector2d(0,-39))
                                .lineTo(new Vector2d(48, -39))
                                .lineTo(new Vector2d(48,-36))
                                .waitSeconds(0.5)
                                .turn(Math.toRadians(90))
                                .waitSeconds(0.5)
                                .lineTo(new Vector2d(48,-39))
                                .lineTo(new Vector2d(-58, -55))
                                .waitSeconds(0.5)
                                .lineToLinearHeading(new Pose2d(58, -36, Math.toRadians(90)))
                                .waitSeconds(0.5)
                                .lineToLinearHeading(new Pose2d(-58,-55, Math.toRadians(180)))

                                */

                               /* .strafeTo(new Vector2d(8,-34 ))
                                .back(10)
                                .strafeTo(new Vector2d(38,-62))
                                .strafeTo(new Vector2d(34,-10))
                                .waitSeconds(0.5)
                                .turn(Math.toRadians(-90))
                                .splineTo(new Vector2d(42 ,-60), Math.toRadians(-90))
                                .back(50)
                                .turn(Math.toRadians(90))
                                .splineTo(new Vector2d(52 ,-60), Math.toRadians(-90))
                                .back(50)
                                .turn(Math.toRadians(90))
                                .splineTo(new Vector2d(62 ,-60), Math.toRadians(-90))

                                */

                                .splineTo(new Vector2d(8,-34 ), Math.toRadians(90))
                                .splineToConstantHeading(new Vector2d(8,-38 ), Math.toRadians(90))
                                .splineToLinearHeading(new Pose2d(38, -48 , Math.toRadians(90))  ,Math.toRadians(0))
                                .splineToConstantHeading(new Vector2d(46, -6), Math.toRadians(0) )

                                .strafeTo(new Vector2d(60, -61))

 .build());


        /*RoadRunnerBotEntity mySecondBot = new DefaultBotBuilder(meepMeep)
                // We set this bot to be red
                .setColorScheme(new ColorSchemeRedDark()).setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(35, -62, Math.toRadians(90)))

                        .splineTo (new Vector2d(56,  -40), Math.toRadians(90))
                        .waitSeconds(1)
                        .splineTo (new Vector2d(57, 38), Math.toRadians(90))
                        .turn(Math. toRadians(180))
                        .splineTo (new Vector2d(32, -44), Math. toRadians(180))
                        .splineToLinearHeading(new Pose2d(-43, -50, Math.toRadians(-110)), Math.toRadians(180))
                        .build());*/




        Image img = null;
        try {
            img = ImageIO.read(new File("MeepMeepTesting/src/main/java/com/example/meepmeeptesting/field-2024-juice-dark.png"));
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }

        meepMeep.setBackground(img)
//  <following code you were using previously>
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)//.addEntity(mySecondBot).start()
                .start();
    }
}