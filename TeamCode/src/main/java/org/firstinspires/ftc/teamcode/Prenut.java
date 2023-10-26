package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous
public class Prenut extends LinearOpMode {
    public void runOpMode() {
        Drivetrain hazel = new Drivetrain(this, hardwareMap);


        waitForStart();
        while (opModeIsActive()) {
            hazel.drive(1170,0.5);
            break;

        }
    }
}
