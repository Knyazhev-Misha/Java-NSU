package Miner.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Controller {
    private View view;
    private Model model;
    private String[] args;


public Controller(View view, Model model){
    this.view = view;
    this.model = model;
    setViewMenu();
    setButton();
}

public void setButton(){
    for(Button_conteiner ptr : view.getField()){
        ptr.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.distributor(ptr.x, ptr.y, 0);
            }
        });



        ptr.button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3){

                    model.distributor(ptr.x, ptr.y, 1);
                }
            }
        });
    }
}

public void setViewMenu(){
    view.getNewgame().addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.distributor(0,0,3);;
            setButton();
        }
    });

    view.getExit().addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    });

    view.getHighScore().addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.getDifficult().setVisible(true);
        }
    });

    view.getEasy().addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JRadioButton button = (JRadioButton)e.getSource();
            String NameButton = button.getName();
            args = NameButton.split(" ");
        }
    });

    view.getHard().addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JRadioButton button = (JRadioButton)e.getSource();
            String NameButton = button.getName();
            args = NameButton.split(" ");

        }
    });

    view.getNightmare().addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JRadioButton button = (JRadioButton)e.getSource();
            String NameButton = button.getName();
            args = NameButton.split(" ");
        }
    });



    view.getNewgamedifficult().addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.setRow(Integer.parseInt(args[0]));
            model.setColumn(Integer.parseInt(args[1]));
            model.setBomb(Integer.parseInt(args[2]));

            model.distributor(0,0, 3);

            view.getDifficult().setVisible(false);
            setButton();
        }
    });

    view.getTop().addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.getToplist().setVisible(true);
        }
    });

    view.getOk().addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.getStart_frame().setVisible(false);
            view.getFrame().setVisible(true);
            view.setName(view.getEnter().getText());
        }
    });

}



}
