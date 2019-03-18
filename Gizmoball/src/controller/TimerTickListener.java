package controller;

import model.IModel;
import model.Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerTickListener implements ActionListener {

    IModel m;

    public TimerTickListener(IModel m){
        this.m = m;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        m.moveBall();
    }
}
