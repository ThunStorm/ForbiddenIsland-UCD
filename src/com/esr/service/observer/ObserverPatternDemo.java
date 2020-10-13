package com.esr.service.observer;

public class ObserverPatternDemo {

    public static void demo() {
        Subject subject = new Subject();

        new MoveObserver(subject);

        System.out.println("head to North");
        subject.setState('N');

        System.out.println("head to West");
        subject.setState('W');
    }

}