package Miner.gui;

import javax.swing.*;

public class Button_conteiner {
    int x;
    int y;
    JButton button;
    public Button_conteiner(int i, JButton button, int column) {
        this.button = button;
        this.x = i % column;
        this.y = i / column;
    }
}
