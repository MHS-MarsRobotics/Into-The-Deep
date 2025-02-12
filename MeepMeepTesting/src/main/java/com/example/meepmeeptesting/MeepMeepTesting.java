package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(700);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep).setDimensions(14.5, 13).setColorScheme(new ColorSchemeRedDark())
                //.Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-30, -64, Math.toRadians(90)))
//                                .lineTo(new Vector2d(36,-10))
//                                .waitSeconds(0.5)
//                                .turn(Math.toRadians(-90))
//                                .splineTo(new Vector2d(42 ,-60), Math.toRadians(-90))
//                                .back(50)
//                                .turn(Math.toRadians(90))
//                                .splineTo(new Vector2d(52 ,-60), Math.toRadians(-90))
//                                .back(50)
//                                .turn(Math.toRadians(90))
//                                .splineTo(new Vector2d(62 ,-60), Math.toRadians(-90))
//                                .build());

                                .waitSeconds(1)
                                .strafeTo(new Vector2d(-6, -34))
                                .waitSeconds(1)
                                .build()

//                                .splineTo(new Vector2d(30, 30), Math.PI / 2)
//                                .splineTo(new Vector2d(0, 60), Math.PI)
//                                .build()
                );



//                        RoadRunnerBotEntity myBot2 = new DefaultBotBuilder(meepMeep).setDimensions(14.5, 13).setColorScheme(new ColorSchemeBlueDark())
                                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
 //                               .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
 //                               .followTrajectorySequence(drive ->
 //                                       drive.trajectorySequenceBuilder(new Pose2d(-36, -62, Math.toRadians(0)))
//                                                .lineTo(new Vector2d(-36,-10))
//                                                .waitSeconds(0.5)
//                                                .turn(Math.toRadians(90))
//                                                .splineTo(new Vector2d(-42 ,-60), Math.toRadians(-90))
//                                                .back(50)
//                                                .turn(Math.toRadians(-90))
//                                                .splineTo(new Vector2d(-52 ,-60), Math.toRadians(-90))
//                                                .back(50)
//                                                .turn(Math.toRadians(-90))
//                                                .splineTo(new Vector2d(-62 ,-60), Math.toRadians(-90))

  //                                              .lineTo(new Vector2d(-52,-62))
  //                                              .build()
  //              );



       /* RoadRunnerBotEntity mySecondBot = new DefaultBotBuilder(meepMeep)
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

        RoadRunnerBotEntity myBot2 = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-30, -62, Math.toRadians(90)))
                                .lineTo(new Vector2d(-36,-10))
                                                .waitSeconds(0.5)
                                                .turn(Math.toRadians(90))
                                                .splineTo(new Vector2d(-42 ,-60), Math.toRadians(-90))
                                                .back(50)
                                                .turn(Math.toRadians(-90))
                                                .splineTo(new Vector2d(-52 ,-60), Math.toRadians(-90))
                                                .back(50)
                                                .turn(Math.toRadians(-90))
                                                .splineTo(new Vector2d(-62 ,-60), Math.toRadians(-90))
                                .build()
                );




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
               // .addEntity(myBot)
                .addEntity(myBot2).start()
                .start();
    }
}