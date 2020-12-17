package com.esr.utils;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * @Description
 * @Author William
 * @Date 2020/12/8
 * @Version 1.0
 **/
public enum Audio {
    BGM("Bgm.wav"),
    TTO("3-2-1.wav"),
    GO("Go.wav"),
    FLOOD("FloodTiles.wav"),
    CAPTURE("Capture.wav"),
    SHOREUP("ShoreUp.wav"),
    PASSTO("PassTo.wav"),
    MOVETO("MoveTo.wav"),
    LIFTOFF("LiftOff"),
    PLAYER1("Player 1.wav"),
    PLAYER2("Player 2.wav"),
    PLAYER3("Player 3.wav"),
    PLAYER4("Player 4.wav"),
    DISCARD("Discard.wav"),
    NEXT("Next.wav"),
    WIN("Wins!.wav"),
    FAILURE("Failure.wav");

    private String name;

    Audio(String name) {
        this.name = name;
    }

    public void Play() {
        AudioStream as;
        try {
            InputStream resourceAsStream = Audio.class.getClassLoader().getResourceAsStream("com/esr/resources/audio/" + name);
            as = new AudioStream(Objects.requireNonNull(resourceAsStream));
            AudioPlayer.player.start(as);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void LoopPlay(int sec) {
        CommonUtils.task(sec, () -> {
            try {
                InputStream resourceAsStream = Audio.class.getClassLoader().getResourceAsStream("com/esr/resources/audio/" + name);
                AudioStream as = new AudioStream(Objects.requireNonNull(resourceAsStream));
//                System.out.println(as.getLength());
                AudioPlayer.player.start(as);
//            player.getPlayerState().getGameLevel().add();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
