package org.firstinspires.ftc.team6206;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;

import org.firstinspires.ftc.robotcore.external.JavaUtil;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;



@TeleOp(name = "Olymp Testing")
public class TestOlymp extends LinearOpMode {

    static final double TICKS_PER_INCH = 125;

    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor motorFrontLeft = null;
        DcMotor motorBackLeft = null;
        DcMotor motorFrontRight = null;
        DcMotor motorBackRight = null;
        DcMotor lift = null;
        DcMotor tilt = null;
        Servo bucket = null;
        CRServo intake = null;
        Servo intake_angle = null;

        int liftarm_down =0;
        int liftarm_up = 500;
        //125 ticks = 1 inch

//        intake = hardwareMap.crservo.get("intake");
        lift = hardwareMap.dcMotor.get("lift");
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        double liftSpeed = 1.0;

        // Declare our motors
        // Make sure your ID's match your configuration
        //

           /* motorFrontLeft = hardwareMap.dcMotor.get("left motor 1");
            motorBackLeft = hardwareMap.dcMotor.get("left motor 2");
            motorFrontRight = hardwareMap.dcMotor.get("right motor 1");
            motorBackRight = hardwareMap.dcMotor.get("right motor 2");
            tilt = hardwareMap.dcMotor.get("tilt");
            bucket = hardwareMap.servo.get("bucket");
            intake_angle = hardwareMap.servo.get("angle");
        } catch(IllegalArgumentException e) {
            // Keep going, even if the hardware map doesn't exist
            telemetry.addData("Missing hardware map: ", e.getMessage());
            telemetry.update();
        }

            */

        // Reverse the right side motors
        // Reverse left motors if you are using NeveRests
   //    motorFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
   //    motorBackRight.setDirection(DcMotorSimple.Direction.REVERSE);



        // Retrieve the IMU from the hardware map
        IMU imu = hardwareMap.get(IMU.class, "imu");

        IMU.Parameters myIMUparameters;

        myIMUparameters = new IMU.Parameters(
             new RevHubOrientationOnRobot(
                  RevHubOrientationOnRobot.LogoFacingDirection.FORWARD,
                  RevHubOrientationOnRobot.UsbFacingDirection.UP
             )
        );

        imu.initialize(myIMUparameters);

        // Without this, data retrieving from the IMU throws an exception


        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {


         //   double y = -gamepad1.left_stick_y; // Remember, this is reversed!
         //   double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
         //   double rx = gamepad1.right_stick_x;
//
         //   // Read inverse IMU heading, as the IMU heading is CW positive
         //   double botHeading = -imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
//
         //   double rotX = x;
         //   double rotY = y;
//
         //   //   double rotX = x * Math.cos(botHeading) - y * Math.sin(botHeading);
         //   //   double rotY = x * Math.sin(botHeading) + y * Math.cos(botHeading);
//
         //   // Denominator is the largest motor power (absolute value) or 1
         //   // This ensures all the powers maintain the same ratio, but only when
         //   // at least one is out of the range [-1, 1]
         //   double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
         //   double frontLeftPower = (rotY + rotX + rx) / denominator;
         //   double backLeftPower = (rotY - rotX + rx) / denominator;
         //   double frontRightPower = (rotY - rotX - rx) / denominator;
         //   double backRightPower = (rotY + rotX - rx) / denominator;
//
         //   if (gamepad1.left_stick_x < -.3 && gamepad1.left_stick_x > .3) {
         //       motorFrontLeft.setPower(frontLeftPower);
         //       motorBackLeft.setPower(backLeftPower / 20);
         //       motorFrontRight.setPower(frontRightPower);
         //       motorBackRight.setPower(backRightPower / 20);
         //   } else {
         //       motorFrontLeft.setPower(frontLeftPower);
         //       motorBackLeft.setPower(backLeftPower);
         //       motorFrontRight.setPower(frontRightPower);
         //       motorBackRight.setPower(backRightPower);
         //   }
//
         //   if (gamepad1.right_trigger > 0) {
         //       motorBackLeft.setPower(backLeftPower / 2);
         //       motorBackRight.setPower(backRightPower / 2);
         //       motorFrontRight.setPower(frontRightPower / 2);
         //       motorFrontLeft.setPower(frontLeftPower / 2);
         //   }

           /*

            double position = lift.getCurrentPosition();

            // Get the target position of the armMotor
            double desiredPosition = lift.getTargetPosition();

            // Show the position of the armMotor on telemetry
            telemetry.addData("Encoder Position", position);

            // Show the target position of the armMotor on telemetry
            telemetry.addData("Desired Position", desiredPosition);

            telemetry.update();

            */

            if (gamepad2.right_bumper) {
                lift.setTargetPosition((int) (TICKS_PER_INCH * 28));
                lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                lift.setPower(liftSpeed);
            }
            else if (gamepad2.left_bumper) {
                lift.setTargetPosition(0);
                lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                lift.setPower(liftSpeed);
            }

//            if (gamepad2.x) {
//                intake.setDirection(DcMotorSimple.Direction.FORWARD);
//                intake.setPower(1.0);
//            } else if (gamepad2.a) {
//                intake.setDirection(DcMotorSimple.Direction.REVERSE);
//                intake.setPower(1.0);
//            } else {
//                intake.setPower(0);
//            }
        }
    }
}
/*            lift = hardwareMap.dcMotor.get("lift");
            tilt = hardwareMap.dcMotor.get("tilt");
            intake_angle = hardwareMap.servo.get("angle");

 */

//gobilda cpr = 537.7
// distance = (cpr * gear ratio) / (wheel diam * pi)