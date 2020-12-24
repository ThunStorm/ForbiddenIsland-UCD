package com.esr.utils;

import com.esr.gui.tools.ImageRotation;
import com.esr.service.base.ITimer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Description
 * @Author William
 * @Date 2020/12/8
 * @Version 1.0
 **/
public abstract class CommonUtils {
    // this class is a utility class of common utils

    private static final Random RANDOM = new Random();
    // get random next Int (deprecated)
    public static int nextInt(int start, int end) {
        return start == end ? start : start + RANDOM.nextInt(end - start);
    }

    // to get an img
    public static Image getImage(String imageName) {
        return new ImageIcon(Constant.RESOURCES_PATH + imageName).getImage();
    }

    // to get an img with defined dimension
    public static Image getImage(String imageName, int imageWidth, int imageHeight) {
        return new ImageIcon(Constant.RESOURCES_PATH + imageName).getImage().getScaledInstance(imageWidth, imageHeight, 4);
    }

    // to get a rotated img with defined dimension
    public static Image getImage(String imageName, int imageWidth, int imageHeight, double rotationAngle) {
        ImageRotation imageRotation = new ImageRotation();
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(Constant.RESOURCES_PATH + imageName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedImage bufferedImage = imageRotation.rotate(Objects.requireNonNull(image), rotationAngle);
        return new ImageIcon(bufferedImage).getImage().getScaledInstance(imageWidth, imageHeight, 4);
    }

    // task
    public static void task(int periodInSec, ITimer t) {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (Constant.TIMER_STOP_ON_OFF) {
                    timer.cancel();
                    return;
                }
                try {
                    t.run();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        timer.schedule(timerTask, 0, periodInSec * 1000);
    }
}
