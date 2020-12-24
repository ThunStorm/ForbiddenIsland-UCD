package com.esr.service.base;

/**
 * @Description
 * @Author William
 * @Date 2020/12/8
 * @Version 1.0
 **/
public interface IUpdater {
    // update graphical interface
    void guiUpdate();

    // disable all gui when game finish
    void gameOver();
}
