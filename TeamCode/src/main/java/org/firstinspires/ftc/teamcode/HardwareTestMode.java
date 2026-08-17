package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Hardware Test", group = "Test")
public class HardwareTestMode extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor[] motors = new DcMotor[]{
                getMotor("motor0"),
                getMotor("motor1"),
                getMotor("motor2"),
                getMotor("motor3"),
                getMotor("motor4"),
                getMotor("motor5"),
                getMotor("motor6"),
                getMotor("motor7")
        };

        double motorPowerLevel = 0.25;
        double motorTimeMultiplier = 2;

        waitForStart();

        ElapsedTime t = new ElapsedTime();
        while (opModeIsActive()) {
            double motorPower = motorPowerLevel * Math.sin(motorTimeMultiplier * t.seconds());
            telemetry.addData("motorPower", motorPower);

            for (int i = 0; i < motors.length; i++) {
                if (motors[i] != null) {
                    motors[i].setPower(motorPower);
                    telemetry.addData("motor" + i, motors[i].getCurrentPosition());
                } else {
                    telemetry.addData("motor" + i, "missing");
                }
            }

            telemetry.update();
        }
    }

    private DcMotor getMotor(String name) {
        try {
            return hardwareMap.get(DcMotor.class, name);
        } catch (Exception e) {
            return null;
        }
    }
}
