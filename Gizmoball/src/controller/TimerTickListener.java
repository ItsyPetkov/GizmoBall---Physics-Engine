package controller;

import model.Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerTickListener implements ActionListener {

    Model m;

    public TimerTickListener(Model m){
        this.m = m;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        m.moveBall();
    }
}
