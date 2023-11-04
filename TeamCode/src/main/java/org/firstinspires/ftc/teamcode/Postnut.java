package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp

public class Postnut extends LinearOpMode {
    public void runOpMode(){
        Servo left = hardwareMap.get(Servo.class, "left");
        Servo middle = hardwareMap.get(Servo.class, "middle");
        Drivetrain hazel = new Drivetrain(this, hardwareMap);
        //left.setPosition(0.42);
       // middle.setPosition(0.35);
        waitForStart();

        while (opModeIsActive()){
            hazel.teleop(gamepad1, gamepad2);

        }
    }
}
