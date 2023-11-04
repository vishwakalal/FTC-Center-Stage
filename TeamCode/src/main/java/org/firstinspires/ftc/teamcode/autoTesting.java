package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous
public class autoTesting extends LinearOpMode {
    public void runOpMode() {
       DcMotor viperSlideLeft = hardwareMap.get(DcMotor.class,"viperSlideLeft");
       DcMotor viperSlideRight = hardwareMap.get(DcMotor.class,"viperSlideRight");
       DcMotor frontLeft = hardwareMap.get(DcMotor.class,"frontLeft");
       DcMotor frontRight = hardwareMap.get(DcMotor.class,"frontRight");
       DcMotor backLeft = hardwareMap.get(DcMotor.class,"backLeft");
       DcMotor backRight = hardwareMap.get(DcMotor.class,"backRight");
        Drivetrain hazel = new Drivetrain(this, hardwareMap);


        waitForStart();
        while (opModeIsActive()) {
            telemetry.addData("left", viperSlideLeft.getCurrentPosition());
            telemetry.addData("right", viperSlideRight.getCurrentPosition());

            telemetry.addData("frontLeft current", frontLeft.getCurrentPosition());
            //telemetry.addData("frontLeft target", frontLeft.getTargetPosition());

            telemetry.addData("frontRight current", frontRight.getCurrentPosition());
            //telemetry.addData("frontRight target", frontRight.getTargetPosition());

            telemetry.addData("backLeft current", backLeft.getCurrentPosition());
            //telemetry.addData("backLeft target", backLeft.getTargetPosition());

            telemetry.addData("backRight current", backRight.getCurrentPosition());
            //telemetry.addData("backRight target", backRight.getTargetPosition());
            telemetry.update();



            frontLeft.setPower(0.2);
            frontRight.setPower(0.2);
            backLeft.setPower(0.2);
            backRight.setPower(0.2);

            //hazel.drive(1170,0.5);
            //break;

        }
    }
}
