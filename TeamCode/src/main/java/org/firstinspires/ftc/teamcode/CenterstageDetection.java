package org.firstinspires.ftc.teamcode;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class CenterstageDetection extends OpenCvPipeline {
    final Scalar RED = new Scalar(255, 0, 0);
    final Scalar BLACK = new Scalar(0, 0, 0);
    final Scalar WHITE = new Scalar(255, 255, 255);

    int position;

    final Point CONE_TOP_LEFT_ANCHOR_POINT = new Point(130,200);

    Point cone_pointA = new Point(CONE_TOP_LEFT_ANCHOR_POINT.x, CONE_TOP_LEFT_ANCHOR_POINT.y);
    Point cone_pointB = new Point(
            CONE_TOP_LEFT_ANCHOR_POINT.x + 20, // adding region width
            CONE_TOP_LEFT_ANCHOR_POINT.y + 20); // adding region height

    Mat coneRegion_Y;
    Mat YCrCb = new Mat();
    Mat Y = new Mat();
    int avgConeY;

    void inputToCb(Mat input) {
        Imgproc.cvtColor(input, YCrCb, Imgproc.COLOR_RGB2YCrCb);
        Core.extractChannel(YCrCb, Y, 0);
    }

    @Override
    public void init(Mat firstFrame) {
        inputToCb(firstFrame);
        coneRegion_Y = Y.submat(new Rect(cone_pointA, cone_pointB));
    }

    @Override
    public Mat processFrame(Mat input) {
        inputToCb(input);
        avgConeY = (int) Core.mean(coneRegion_Y).val[0];

        if (avgConeY < 50) {
            position = 3;
            Imgproc.rectangle(input, cone_pointA, cone_pointB, BLACK, -1);
        } else if (avgConeY < 150) {
            position = 1;
            Imgproc.rectangle(input, cone_pointA, cone_pointB, RED, -1);
        } else {
            position = 2;
            Imgproc.rectangle(input, cone_pointA, cone_pointB, WHITE, -1);
        }
        return input;
    }
    public int getAvgConeY() {
        return avgConeY;
    }
    public int getPosition() {
        return position;
    }
}