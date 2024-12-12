
package org.firstinspires.ftc.team5187.teleop;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp(name = "Titan main drive")
public class TitanDrive extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        // Make sure your ID's match your configuration
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("left motor 1");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("left motor 2");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("right motor 1");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("right motor 2");
        DcMotorEx basearm = (DcMotorEx) hardwareMap.dcMotor.get("base");
        DcMotorEx midjoint = (DcMotorEx) hardwareMap.dcMotor.get("mid");
        Servo pinch = hardwareMap.servo.get("pinch");
        Servo angle = hardwareMap.servo.get("angle");

        // Reverse the right side motors. This may be wrong for your setup.
        // If your robot moves backwards when commanded to go forwards,
        // reverse the left side instead.
        // See the note about this earlier on this page.
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        basearm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        midjoint.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        // Set motors to RUN_USING_ENCODER for velocity control
        basearm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        midjoint.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // Retrieve the IMU from the hardware map
        IMU imu = hardwareMap.get(IMU.class, "imu");
        // Adjust the orientation parameters to match your robot
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.BACKWARD,
                RevHubOrientationOnRobot.UsbFacingDirection.UP));
        // Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        imu.initialize(parameters);


        while (opModeInInit()) {
            telemetry.addData("base current Position", basearm.getCurrentPosition());

            telemetry.addData("mid current Position", midjoint.getCurrentPosition());

            telemetry.update();
        }
        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
            double x = gamepad1.left_stick_x;
            double rx = gamepad1.right_stick_x;

            // This button choice was made so that it is hard to hit on accident,
            // it can be freely changed based on preference.
            // The equivalent button is start on Xbox-style controllers.
            if (gamepad1.options) {
                imu.resetYaw();
            }

            double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

            // Rotate the movement direction counter to the bot's rotation
            double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
            double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

            rotX = rotX * 1.1;  // Counteract imperfect strafing

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio,
            // but only if at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
            double frontLeftPower = (rotY + rotX + rx) / denominator;
            double backLeftPower = (rotY - rotX + rx) / denominator;
            double frontRightPower = (rotY - rotX - rx) / denominator;
            double backRightPower = (rotY + rotX - rx) / denominator;

            frontLeftMotor.setPower(frontLeftPower);
            backLeftMotor.setPower(backLeftPower);
            frontRightMotor.setPower(frontRightPower);
            backRightMotor.setPower(backRightPower);

            /*DCmotor
                        lift.setTargetPosition(0);
                        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        lift.setPower(0.3);


                    frontLeftMotor.setPower(frontLeftPower);
                    backLeftMotor.setPower(backLeftPower);
                    frontRightMotor.setPower(frontRightPower);
                    backRightMotor.setPower(backRightPower);


              Servo
              Continous

                        intake.setDirection(DcMotorSimple.Direction.FORWARD);
                        intake.setPower(1.0);

              Limits
                bucket.setPosition(.5);


             */


            int basetargtop = -1006;
            int basetargbottom = 372;
            int midtargtop = 2057;
            int midtagbottom = 637;

            // Initialize motors as DcMotorEx for velocity control




            // Read joystick inputs
            double arm1Joystick = -gamepad2.left_stick_y; // Joystick for arm 1
            double arm2Joystick = -gamepad2.right_stick_y; // Joystick for arm 2

            // Scale joystick inputs to velocity range
            double maxVelocity1 = 20;
            double maxVelocity2= 600; // Maximum velocity in ticks/sec
            double arm1Velocity = arm1Joystick * maxVelocity1;
            double arm2Velocity = arm2Joystick * maxVelocity2;

            int arm1Position = basearm.getCurrentPosition();
            if ((arm1Position <= basetargtop && arm1Joystick > 0) ||
                    (arm1Position >= basetargbottom && arm1Joystick < 0)) {
                arm1Velocity = 0; // Stop movement if limit is reached
            }

            // Check and enforce limits for arm segment 2
            int arm2Position = midjoint.getCurrentPosition();
            if ((arm2Position >= midtargtop && arm2Joystick > 0) ||
                    (arm2Position <= midtagbottom && arm2Joystick < 0)) {
                arm2Velocity = 0; // Stop movement if limit is reached
            }

            // Apply velocity to motors
            basearm.setVelocity(arm1Velocity);
            midjoint.setVelocity(arm2Velocity);

            basearm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            midjoint.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            // Telemetry for monitoring
//            telemetry.addData("Arm1 Encoder", arm1Position);
//            telemetry.addData("Arm2 Encoder", arm2Position);
            telemetry.addData("Arm1 Velocity", arm1Velocity);
            telemetry.addData("Arm2 Velocity", arm2Velocity);
            telemetry.update();


//            if (gamepad2.left_stick_y >0 && basearm.getCurrentPosition() > basetargtop){
//                telemetry.addLine("Going up");
//                basearm.setTargetPosition(basetargtop);
//                basearm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                basearm.setVelocity(25);
//            }
//            if (gamepad2.left_stick_y<0  && basearm.getCurrentPosition() < basetargbottom){
//                telemetry.addLine("Going down");
//
//                basearm.setTargetPosition(basetargbottom);
//                basearm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                basearm.setVelocity(25);
//            }
//            else{
//                telemetry.addLine("Going nowhere");
//                basearm.setVelocity(0);
//                basearm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//            }
//
//            if (gamepad2.right_stick_y >0 && midjoint.getCurrentPosition() < midtargtop){
//                telemetry.addLine("Going up");
//
//                midjoint.setTargetPosition(midtargtop);
//                midjoint.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                midjoint.setVelocity(55);
//            }
//            if (gamepad2.right_stick_y<0  && midjoint.getCurrentPosition() < midtagbottom){
//                telemetry.addLine("Going down");
//
//                midjoint.setTargetPosition(midtagbottom);
//                midjoint.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                midjoint.setVelocity(55);
//            }
//            else{
//                telemetry.addLine("Going nowhere");
//
//                midjoint.setVelocity(0);
//            }
//
//            double baseposition = basearm.getCurrentPosition();
//            double midpos = midjoint.getCurrentPosition();
//
//            // Show the position of the armMotor on telemetry
//            telemetry.addData("Encoder Position", baseposition);
//            telemetry.addData("midpos", midpos);
//            telemetry.update();


//            if  (gamepad2.right_stick_y != 0) {
//                basearm.setTargetPosition((int) (basetarg*(Math.abs(gamepad2.right_stick_y))));
//                basearm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                if((basearm.getTargetPosition()-basearm.getCurrentPosition()) > -100) {
//                    basearm.setVelocity(40);
//                }
//                else {
//                    basearm.setVelocity(90);
//                }
//            }
//            else if (gamepad2.right_stick_y == 0) {
//                basearm.setTargetPosition(-10);
//                basearm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                basearm.setVelocity(40);
//                if((basearm.getTargetPosition()-basearm.getCurrentPosition()) > -100) {
//                    basearm.setVelocity(40);
//                }
//                else {
//                    basearm.setVelocity(0);
//                    basearm.setPower(0);
//                    basearm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//                }
//            }

//            if (gamepad2.left_stick_y > 0) {
//                midjoint.setTargetPosition((int) (midtarg*(Math.abs(gamepad2.left_stick_y))));
//                midjoint.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                if((midjoint.getTargetPosition()-midjoint.getCurrentPosition()) > -100) {
//                    midjoint.setVelocity(30);
//                }
//                else {
//                    midjoint.setVelocity(70);
//                }
//            }
//
//            else if (gamepad2.left_stick_y == 0) {
//                midjoint.setTargetPosition(-10);
//                midjoint.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                midjoint.setVelocity(40);
//                if((midjoint.getTargetPosition()-midjoint.getCurrentPosition()) > -100) {
//                    midjoint.setVelocity(30);
//                }
//                else {
//                    midjoint.setVelocity(70);
//                }
//            }


//            if (gamepad2.right_trigger > 0) {
//                basearm.setTargetPosition(-390);
//                basearm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                basearm.setPower(armpower);
//            } else if (gamepad2.right_bumper) {
//                basearm.setTargetPosition(-130);
//                basearm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                basearm.setPower(armpower);
//            } else {
//                basearm.setTargetPosition(0);
//                basearm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                basearm.setPower(armpower);
//            }
//
//            if (gamepad2.left_trigger > 0) {
//                midjoint.setTargetPosition(-350);
//                midjoint.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                midjoint.setPower(armpower);
//            } else if (gamepad2.left_bumper) {
//                midjoint.setTargetPosition(-184);
//                midjoint.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                midjoint.setPower(armpower);
//            } else {
//                midjoint.setTargetPosition(0);
//                midjoint.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                midjoint.setPower(armpower);
//            }

   /*Targets for arm

    top -  -390
    bottom - 0

    top - -700
    bottom - 0


     */
//            if (gamepad2.left_stick_y!=0) {
//                basearm.setPower(gamepad2.left_stick_y);
//            }
//            else {
//                basearm.setPower(0);
//            }
//            if (gamepad2.right_stick_y!=0) {
//                midjoint.setPower(gamepad2.right_stick_y);
//            }
//            else {
//                midjoint.setPower(0);
//            }

            if (gamepad2.right_bumper) {
                pinch.setPosition(0.4);
            } else if (gamepad2.right_trigger > 0) {
                pinch.setPosition(1);
            }

            if (gamepad2.left_trigger > 0) {
                angle.setPosition(0.5);
            } else if (gamepad2.left_bumper) {
                angle.setPosition(0.6);

            }
            // Show the position of the armMotor on telemetr
        }
    }
}

        