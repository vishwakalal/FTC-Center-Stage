package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp

public class Postnut extends LinearOpMode {
    Drivetrain hazel = new Drivetrain(this, hardwareMap);
    public void runOpMode(){

        waitForStart();

        while (opModeIsActive()){
            hazel.teleop(gamepad1, gamepad2);
        }
    }
}
