package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * autonomous program for advanced robotic movements and computer vision integration.
 * utilizes Mecanum drive, linear slide motor, servo-controlled claws, and computer vision.
 */
@Autonomous
public class AutonRight extends LinearOpMode {

    // Declare robot components
    private DcMotor linearSlideMotor;
    private Mecanum mecanum;
    private Servo claw;
    // Declare computer vision and recognition variable
    private int recognition;

    @Override
    public void runOpMode() throws InterruptedException {

        // Initialize Mecanum drivetrain
        mecanum = new Mecanum(
                hardwareMap.get(BNO055IMU.class, "imu"),
                hardwareMap.get(DcMotor.class, "frontLeft"),
                hardwareMap.get(DcMotor.class, "frontRight"),
                hardwareMap.get(DcMotor.class, "backRight"),
                hardwareMap.get(DcMotor.class, "backLeft")
        );
        mecanum.constantSpeed();

        // Initialize linear slide motor
        linearSlideMotor = hardwareMap.get(DcMotor.class, "linearSlide");
        linearSlideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearSlideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Initialize claw servos
        claw = hardwareMap.get(Servo.class, "clawServo");
        claw.setPosition(0.3);   // assuming 0.3 is an open claw

        // Initialize computer vision
        ComputerVision cv = new ComputerVision(hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName()));

        // Wait for the start button to be pressed
        waitForStart();
        recognition = cv.getRecognition();

        // Close the claws
        claw.setPosition(0.3);

        if (opModeIsActive()) {
            // pick up purple pixel
            moveLinearSlide(FIRST_SLIDE_POSITION, -0.3);

            // pick up yellow pixel
            // assuming we have a method to pick up the yellow pixel, call it here
            // pickUpPixel() ?

            // go right a little
            mecanum.drive(0, 90, 500);

            // go down a little
            mecanum.drive(-0.2, 0, 500);

            // drop purple pixel
            claw.setPosition(0.1);

            // move right a litlle
            mecanum.drive(0, 90, 500);

            // go straight a lot
            mecanum.forward(POWER_MEDIUM, 0, 1500);

            // go left a little
            mecanum.drive(0, -90, 500);

            // use prop as an indicator and drop yellow pixel onto backdrop
            // assuming we have a method to drop the yellow pixel, call it here
            // dropPixel() ?

            // go right a little
            mecanum.drive(0, 90, 500);

            // go backward a lot
            mecanum.forward(-POWER_MEDIUM, 180, 2000);

            // go left a little
            mecanum.drive(0, -90, 500);

            // pick up purple pixel
            moveLinearSlide(FIRST_SLIDE_POSITION, -0.3);

            // pick up yellow pixel
            // assuming we have a method to pick up the yellow pixel, call it here
            // pickUpPixel() ?

            // drop purple pixel on the line
            claw.setPosition(0.1);

            // go right a little
            mecanum.drive(0, 90, 500);

            // go straight a lot
            mecanum.forward(POWER_MEDIUM, 0, 1500);

            // go left a little
            mecanum.drive(0, -90, 500);

            // match and drop yellow pixel on the backdrop
            // assuming you have a method to drop the yellow pixel, call it here
            // dropPixel() ?

            // park (backstage)
            // assuming we have a method to park, call it here
        }
    }
}

// change the code to implement the changes you mentioned professionally.

/**
 * Steps
 *
 * 1. put purple pixel next to prop
 * 2. take yellow pixel to backdrop (use prop as indicator)
 * 3. come back
 * 4. pick up purple pixel, then yellow pixel
 * 5. drop purple pixel on line
 * 6. drop yellow pixel on backdrop (matched)
 * 7. park (backstage)
 */

/**
 * Cheatsheet
 *
 * mecanum.constantSpeed() - this will make the robot move at a constant speed
 * mecanum.constantPower() - this will make the robot move at a constant power
 * mecanum.getHeading() - this will return the current heading of the robot
 * mecanum.forward(double power, double angle, long interval) - this will make the robot move forward
 * mecanum.yaw(double power, double angle) - this will make the robot turn
 * mecanum.reset() - this will reset the wheel angles
 * mecanum.drift(double power, double angle, long interval) - this will make the robot drift
 * mecanum.brake() - this will make the robot brake
 * mecanum.drive(double forward, double yaw, double drift) - this will make the robot drive
 */