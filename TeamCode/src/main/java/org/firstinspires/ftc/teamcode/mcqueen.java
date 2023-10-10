package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous
public class McQueen extends LinearOpMode{


    @Override
    public void runOpMode(){
        DcMotor lightning = hardwareMap.get(DcMotor.class, "lightning");
        lightning.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lightning.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        while (opModeIsActive()) {
            double power = 1.0;

            lightning.setPower(power);
            wait(5000);
            lightning.setPower(0);

            break;
        }
    }

    public void wait(int ms) {
        ElapsedTime timer = new ElapsedTime();
        timer.startTime();
        while (timer.milliseconds() < ms) {
            telemetry.update();
        }
    }
}
