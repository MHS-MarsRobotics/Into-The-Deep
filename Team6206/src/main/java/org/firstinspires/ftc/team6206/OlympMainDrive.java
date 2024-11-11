
        package org.firstinspires.ftc.team6206;

        import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
        import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
        import com.qualcomm.robotcore.hardware.CRServo;
        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.hardware.DcMotorSimple;
        import com.qualcomm.robotcore.hardware.IMU;
        import com.qualcomm.robotcore.hardware.Servo;

        import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

        @TeleOp(name = "Main Olymp Drive")
        public class OlympMainDrive extends LinearOpMode {

            static final double TICKS_PER_INCH = 125;
            static final int INIT_POSITION = 80;

            private void runToPosition(DcMotor motor, double target) {
                if (Math.abs(target - motor.getCurrentPosition()) < 20) {
                    motor.setPower(0.001);
                }
                else {
                    motor.setPower(0.3);
                }

                motor.setTargetPosition((int)target);
                motor.setMode(DcMotor.RunMode.RUN_TO_POSITION); // Can't hurt to call this repeatedly
            }

            @Override
            public void runOpMode() throws InterruptedException {
                // Declare our motors
                // Make sure your ID's match your configuration
                DcMotor frontLeftMotor = hardwareMap.dcMotor.get("left motor 1");
                DcMotor backLeftMotor = hardwareMap.dcMotor.get("left motor 2");
                DcMotor frontRightMotor = hardwareMap.dcMotor.get("right motor 1");
                DcMotor backRightMotor = hardwareMap.dcMotor.get("right motor 2");
                DcMotor lift = hardwareMap.dcMotor.get("lift");
                DcMotor tilt = hardwareMap.dcMotor.get("tilt");
                Servo bucket = hardwareMap.servo.get("bucket");
                CRServo intake = hardwareMap.crservo.get("intake");
                Servo intake_angle = hardwareMap.servo.get("angle");
                // Reverse the right side motors. This may be wrong for your setup.
                // If your robot moves backwards when commanded to go forwards,
                // reverse the left side instead.
                // See the note about this earlier on this page.
                frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

                lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                tilt.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                tilt.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


                // Retrieve the IMU from the hardware map
                IMU imu = hardwareMap.get(IMU.class, "imu");
                // Adjust the orientation parameters to match your robot
                IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
                        RevHubOrientationOnRobot.UsbFacingDirection.UP));
                // Without this, the REV Hub's orientation is assumed to be logo up / USB forward
                imu.initialize(parameters);


                while (opModeInInit()) {
                    double position = tilt.getCurrentPosition();

//                    runToPosition(tilt, INIT_POSITION);
//                    bucket.setPosition(.7);
//                    intake_angle.setPosition(.6);



                    telemetry.addData("Tilt Encoder Position", position);
                    // Show the target position of the armMotor on telemetry
                    telemetry.addData("Desired Tilt Position", tilt.getTargetPosition());
                    telemetry.addData("Tilt Power", tilt.getPower());

                    telemetry.update();
                }


//                waitForStart();

                if (isStopRequested()) return;

                while (opModeIsActive()) {
                    double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
                    double x = -gamepad1.left_stick_x;
                    double rx = -gamepad1.right_stick_x;

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


                    if (gamepad1.right_trigger > 0) {
                        backLeftMotor.setPower(backLeftPower / 2);
                        backRightMotor.setPower(backRightPower / 2);
                        frontRightMotor.setPower(frontRightPower / 2);
                        frontLeftMotor.setPower(frontLeftPower / 2);
                    }

                    if (gamepad2.dpad_up) {
                        bucket.setPosition(0.8 );
                    }
                    if (gamepad2.dpad_down) {
                        bucket.setPosition(1);
                    }

                    if (gamepad2.x) {
                        intake.setDirection(DcMotorSimple.Direction.FORWARD);
                        intake.setPower(1.0);
                    } else if (gamepad2.a) {
                        intake.setDirection(DcMotorSimple.Direction.REVERSE);
                        intake.setPower(1.0);
                    } else {
                        intake.setPower(0);
                    }
                    if (gamepad2.b) {
                        lift.setTargetPosition((int)TICKS_PER_INCH * 4);
                        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        lift.setPower(0.3);
                    }
                    if (gamepad2.y) {
                        lift.setTargetPosition((int)TICKS_PER_INCH * 28);
                        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        lift.setPower(0.3);
                    }
                    if (gamepad2.left_trigger>0) {
                        tilt.setPower(0.5);
                    }
                    if (gamepad2.right_trigger>0) {
                        tilt.setPower(-0.5);
                    }

                    if (gamepad2.dpad_left) {
                        intake_angle.setPosition(0);
                    }
                    else if (gamepad2.dpad_right) {
                        intake_angle.setPosition(0.25);
                    }




                    // Show the position of the armMotor on telemetry
                    telemetry.addData("Encoder Position", tilt.getCurrentPosition());

                    // Show the target position of the armMotor on telemetry
                    telemetry.addData("Desired Position", tilt.getTargetPosition());

                    telemetry.update();
                }
            }
        }