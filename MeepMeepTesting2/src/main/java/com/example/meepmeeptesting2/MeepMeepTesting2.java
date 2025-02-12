package com.example.meepmeeptesting2;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueLight;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MeepMeepTesting2 {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep).setDimensions(14.5, 13).setColorScheme(new ColorSchemeRedDark())
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        RoadRunnerBotEntity testOlympianBot = new DefaultBotBuilder(meepMeep).setDimensions(14.5, 13).setColorScheme(new ColorSchemeBlueDark())
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(32, -62, Math.toRadians(90)))
                .splineTo(new Vector2d(37,-22),Math.toRadians(90))
                .waitSeconds(1)
                .turn(Math.toRadians(-90))
                .strafeTo(new Vector2d(37,-24))
                .waitSeconds(3)
                .turn(Math.toRadians(-45))
                .strafeTo(new Vector2d(52,-52))
                .turn(Math.toRadians(45))
                .strafeTo(new Vector2d(47,-52))
                .strafeTo(new Vector2d(47,-24))
                .waitSeconds(3)
                .turn(Math.toRadians(-45))
                .strafeTo(new Vector2d(52,-52))
                .turn(Math.toRadians(45))
                .strafeTo(new Vector2d(57,-24))
                .waitSeconds(3)
                .strafeTo(new Vector2d(52,-60))
                .waitSeconds(2)
                .turn(Math.toRadians(90))
                .build());

        testOlympianBot.runAction(testOlympianBot.getDrive().actionBuilder(new Pose2d(-30, -64, Math.toRadians(90)))

                .strafeTo(new Vector2d(-37,-22))
                .waitSeconds(1)
                .turn(Math.toRadians(90))
                .strafeTo(new Vector2d(-37,-24))
                .waitSeconds(3)
                .turn(Math.toRadians(45))
                .strafeTo(new Vector2d(-52,-52))
                .turn(Math.toRadians(-45))
                .strafeTo(new Vector2d(-47,-52))
                .strafeTo(new Vector2d(-47,-24))
                .waitSeconds(3)
                .turn(Math.toRadians(45))
                .strafeTo(new Vector2d(-52,-52))
                .turn(Math.toRadians(-45))
                .strafeTo(new Vector2d(-57,-24))
                .waitSeconds(3)
                .turn(Math.toRadians(45))
                .strafeTo(new Vector2d(-52,-52))
                .build());

        Image img = null;
        try {
            img = ImageIO.read(new File("MeepMeepTesting/src/main/java/com/example/meepmeeptesting/field-2024-juice-dark.png"));
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }

        assert img != null;
        meepMeep.setBackground(img)
//  <following code you were using previously>
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .addEntity(testOlympianBot)
                .start();
    }
}