package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous
public class Prenut extends LinearOpMode {
    Drivetrain hazel = new Drivetrain(this, hardwareMap);
    public void runOpMode() {

        waitForStart();
        while (opModeIsActive()) {

        }
    }
}
