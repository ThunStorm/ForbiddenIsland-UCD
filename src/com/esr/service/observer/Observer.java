package com.esr.service.observer;


//Observer Pattern
public abstract class Observer {
    protected Subject subject;

    public abstract void update();
}
