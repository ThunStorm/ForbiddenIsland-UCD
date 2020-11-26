package com.esr.utils;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.IOException;
import java.io.InputStream;

public enum Audio {
    BGM("bgm.wav"),
    Start("start.wav");

    private String name;

    Audio(String name) {
        this.name = name;
    }

    public void Play() {
        AudioStream as = null;
        try {
            InputStream resourceAsStream = Audio.class.getClassLoader().getResourceAsStream("com/esr/resources/audio/" + name);
            as = new AudioStream(resourceAsStream);
            AudioPlayer.player.start(as);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void LoopPlay(int sec) {
        CommonUtils.task(sec, () -> {
            try {
                InputStream resourceAsStream = Audio.class.getClassLoader().getResourceAsStream("com/esr/resources/audio/" + name);
                AudioStream as = new AudioStream(resourceAsStream);
//                System.out.println(as.getLength());
                AudioPlayer.player.start(as);
//            player.getPlayerState().getGameLevel().add();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
