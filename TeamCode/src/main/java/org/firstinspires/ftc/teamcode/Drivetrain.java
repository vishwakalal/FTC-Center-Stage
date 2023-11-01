package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
public class Drivetrain {
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor viperSlideLeft;
    private DcMotor viperSlideRight;
    private DcMotor intake;
    private Servo launcher;
    private Servo left;
    private Servo middle;
    private Servo right;
    //private Servo door;

    IMU imu;
    YawPitchRollAngles ypr;
    LinearOpMode opMode;

    public Drivetrain(LinearOpMode opMode, HardwareMap hardwareMap){
        frontLeft = hardwareMap.get(DcMotor.class,"frontLeft");
        frontRight = hardwareMap.get(DcMotor.class,"frontRight");
        backLeft = hardwareMap.get(DcMotor.class,"backLeft");
        backRight = hardwareMap.get(DcMotor.class,"backRight");
        intake = hardwareMap.get(DcMotor.class,"intake");
        launcher = hardwareMap.get(Servo.class, "launcher");
        left = hardwareMap.get(Servo.class, "left");
        middle = hardwareMap.get(Servo.class, "middle");
        right = hardwareMap.get(Servo.class, "right");
        //door = hardwareMap.get(Servo.class, "door");
        viperSlideLeft = hardwareMap.get(DcMotor.class,"viperSlideLeft");
        viperSlideRight = hardwareMap.get(DcMotor.class,"viperSlideRight");



        this.opMode = opMode;

        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);
        viperSlideLeft.setDirection(DcMotor.Direction.REVERSE);
        viperSlideRight.setDirection(DcMotor.Direction.FORWARD);
        intake.setDirection(DcMotor.Direction.FORWARD);

        encoders();
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        viperSlideLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        viperSlideRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);



        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        viperSlideLeft.setPower(0);
        viperSlideRight.setPower(0);
        intake.setPower(0);


    }

    public void encoders(){
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        intake.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        viperSlideLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        viperSlideRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        intake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        viperSlideLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        viperSlideRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void teleop(Gamepad gamepad1, Gamepad gamepad2) {
        launcher.setPosition(1);

        //driving
        double drive = (-1*(gamepad1.left_stick_y));
        double strafe = (gamepad1.left_stick_x);
        double rotate = (gamepad1.right_stick_x);
        double FL = drive + strafe + rotate;
        double FR = drive - strafe - rotate;
        double BL = drive - strafe + rotate;
        double BR = drive + strafe - rotate;
        frontLeft.setPower(FL);
        frontRight.setPower(FR);
        backLeft.setPower(BL);
        backRight.setPower(BR);



        //viper slides
        double up = (gamepad2.left_stick_y);
        double viperLeft = up;
        double viperRight = up;
        viperSlideLeft.setPower(viperLeft);
        viperSlideRight.setPower(viperRight);

        //intake
        double pull = (gamepad2.right_trigger);
        double eject = (-gamepad2.left_trigger);
        intake.setPower(pull + eject);
       // if (gamepad2.y) {

        //launcher
        if(gamepad2.y){
            launcher.setPosition(0.6);
            wait(200);
            launcher.setPosition(1);
        }
        //door
        if(gamepad2.left_bumper){
            middle.setPosition(0.45);
        }
        if(gamepad2.right_bumper){
            middle.setPosition(0.4);
        }

        if(gamepad2.dpad_up){
            left.setPosition(0.5);
            right.setPosition(0.5);
        }





    }
    public void testTele(Gamepad gamepad1, Gamepad gamepad2){

        opMode.telemetry.addData("door current", middle.getPosition());

        opMode.telemetry.update();
        if(gamepad2.y){
            middle.setPosition(0.5);
        }


    }
    public void drive(int forward, double power){
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        frontLeft.setTargetPosition(forward);
        frontRight.setTargetPosition(forward);
        backLeft.setTargetPosition(forward);
        backRight.setTargetPosition(forward);


        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);


        while(frontLeft.isBusy()){
            opMode.telemetry.addData("frontLeft current", frontLeft.getCurrentPosition());
            opMode.telemetry.addData("frontLeft target", frontLeft.getTargetPosition());

            opMode.telemetry.addData("frontRight current", frontRight.getCurrentPosition());
            opMode.telemetry.addData("frontRight target", frontRight.getTargetPosition());

            opMode.telemetry.addData("backLeft current", backLeft.getCurrentPosition());
            opMode.telemetry.addData("backLeft target", backLeft.getTargetPosition());

            opMode.telemetry.addData("backRight current", backRight.getCurrentPosition());
            opMode.telemetry.addData("backRight target", backRight.getTargetPosition());
            opMode.telemetry.update();
        }
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);

    }

    public void strafe(int slide, double power, String direction){
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        if(direction.equals("left")){
            frontLeft.setTargetPosition(-slide);
            frontRight.setTargetPosition(slide);
            backLeft.setTargetPosition(slide);
            backRight.setTargetPosition(-slide);

        } else if(direction.equals("right")){
            frontLeft.setTargetPosition(slide);
            frontRight.setTargetPosition(-slide);
            backLeft.setTargetPosition(-slide);
            backRight.setTargetPosition(slide);
        }


        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);

        while(frontLeft.isBusy()){
            opMode.telemetry.addData("frontLeft current", frontLeft.getCurrentPosition());
            opMode.telemetry.addData("frontLeft target", frontLeft.getTargetPosition());

            opMode.telemetry.addData("frontRight current", frontRight.getCurrentPosition());
            opMode.telemetry.addData("frontRight target", frontRight.getTargetPosition());

            opMode.telemetry.addData("backLeft current", backLeft.getCurrentPosition());
            opMode.telemetry.addData("backLeft target", backLeft.getTargetPosition());

            opMode.telemetry.addData("backRight current", backRight.getCurrentPosition());
            opMode.telemetry.addData("backRight target", backRight.getTargetPosition());
            opMode.telemetry.update();
        }

        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);

    }

    public void turn(double angle, double power, String direction){
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        imu.resetYaw();
        ypr = imu.getRobotYawPitchRollAngles();

        if (direction.equals("left")) { power *= -1; }

        frontLeft.setPower(power);
        frontRight.setPower(-power);
        backLeft.setPower(power);
        backRight.setPower(-power);

        while (opMode.opModeIsActive() && Math.abs(ypr.getYaw(AngleUnit.DEGREES)) < angle) {
            opMode.telemetry.addData("Current Angle", ypr.getYaw(AngleUnit.DEGREES));
            opMode.telemetry.addData("Target Angle", angle );
            opMode.telemetry.addData("Direction", direction);
            opMode.telemetry.update();

            ypr = imu.getRobotYawPitchRollAngles(); // Keep updating YPR until we reach ideal angle
        }

        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        encoders();

    }

   public void slideVipers(int position, double power) {
       viperSlideLeft.setTargetPosition(position);
        viperSlideRight.setTargetPosition(position);

        viperSlideLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        viperSlideRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        viperSlideLeft.setPower(power);
        viperSlideRight.setPower(power);

        while (viperSlideLeft.isBusy()) {
            opMode.telemetry.addData("viperSlideLeft current", viperSlideLeft.getCurrentPosition());
            opMode.telemetry.addData("viperSlideLeft target", viperSlideLeft.getTargetPosition());

            opMode.telemetry.addData("viperSlideRight current", viperSlideRight.getCurrentPosition());
            opMode.telemetry.addData("viperSlideRight target", viperSlideRight.getTargetPosition());
            opMode.telemetry.update();
        }

    }
    public void wait(int ms) {
        ElapsedTime timer = new ElapsedTime();
        timer.startTime();
        while (timer.milliseconds() < ms) {
            opMode.telemetry.update();
        }
    }


}
