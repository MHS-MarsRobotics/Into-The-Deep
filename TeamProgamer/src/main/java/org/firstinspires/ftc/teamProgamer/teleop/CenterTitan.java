package org.firstinspires.ftc.teamProgamer.teleop;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;



@TeleOp(name = "Titan Drive")
public class CenterTitan extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        // Declare our motors
        // Make sure your ID's match your configuration
        DcMotor motorFrontLeft = hardwareMap.dcMotor.get("left motor 1");
        DcMotor motorBackLeft = hardwareMap.dcMotor.get("left motor 2");
        DcMotor motorFrontRight = hardwareMap.dcMotor.get("right motor 1");
        DcMotor motorBackRight = hardwareMap.dcMotor.get("right motor 2");




        // Reverse the right side motors
        // Reverse left motors if you are using NeveRests
        motorFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBackRight.setDirection(DcMotorSimple.Direction.REVERSE);

        // Retrieve the IMU from the hardware map
        IMU imu = hardwareMap.get(IMU.class, "imu");

IMU.Parameters myIMUparameters;

myIMUparameters = new IMU.Parameters(
     new RevHubOrientationOnRobot(
          RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
          RevHubOrientationOnRobot.UsbFacingDirection.FORWARD
     )
);

imu.initialize(myIMUparameters);

        // Without this, data retrieving from the IMU throws an exception


        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y; // Remember, this is reversed!
            double x =  gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x;

            // Read inverse IMU heading, as the IMU heading is CW positive
            double botHeading = -imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

            double rotX = x;
            double rotY = y;

         //   double rotX = x * Math.cos(botHeading) - y * Math.sin(botHeading);
         //   double rotY = x * Math.sin(botHeading) + y * Math.cos(botHeading);

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio, but only when
            // at least one is out of the range [-1, 1]                                                                      
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (rotY + rotX + rx) / denominator;
            double backLeftPower = (rotY - rotX + rx) / denominator;
            double frontRightPower = (rotY - rotX - rx) / denominator;
            double backRightPower = (rotY + rotX - rx) / denominator;

            if (gamepad1.left_stick_x  < -.3 && gamepad1.left_stick_x >.3 ) {
            motorFrontLeft.setPower(frontLeftPower);
            motorBackLeft.setPower(backLeftPower/20);
            motorFrontRight.setPower(frontRightPower);
            motorBackRight.setPower(backRightPower/20);
        }
            else {
                 motorFrontLeft.setPower(frontLeftPower);
                motorBackLeft.setPower(backLeftPower);
                motorFrontRight.setPower(frontRightPower);
                motorBackRight.setPower(backRightPower);
            }

            if (gamepad1.right_trigger>0){
                motorBackLeft.setPower(backLeftPower/2);
                motorBackRight.setPower(backRightPower/2);
                motorFrontRight.setPower(frontRightPower/2);
                motorFrontLeft.setPower(frontLeftPower/2);
            }
        }
    }
}