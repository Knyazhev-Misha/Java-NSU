package Miner.gui;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

public class View {

    private JFrame frame = new JFrame();
    private JFrame difficult = new JFrame();
    private JFrame toplist = new JFrame();

    private JFrame start_frame = new JFrame();

    private JPanel field_panel = new JPanel();
    private JPanel top_panel = new JPanel();
    private JPanel main_panel = new JPanel();
    private JPanel difficult_panel = new JPanel();
    private JPanel top = new JPanel();
    private JPanel start_panel = new JPanel();
    private JPanel easy_panel = new JPanel();
    private JPanel hard_panel = new JPanel();
    private JPanel nightmare_panel = new JPanel();

    private JTextField enter = new JTextField("Enter your name",20);
    private Button ok = new Button("ok");

    private ButtonGroup buttonGroup = new ButtonGroup();

    private JRadioButton hard = new JRadioButton("hard");
    private JRadioButton nightmare = new JRadioButton("nightmare");
    private Button newgamedifficult = new Button("new game");

    private JLabel bomb_label = new JLabel();

    private ArrayList<Button_conteiner> field = new ArrayList<>();
    private JMenuBar bar = new JMenuBar();
    private JMenu menu = new JMenu("Menu");
    private JMenuItem newgame;
    private JMenuItem exit;
    private JMenuItem HighScore;
    private JMenuItem Top;

    private JLabel playing_label = new JLabel();
    private JLabel timer_label = new JLabel();

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public JFrame getStart_frame() {
        return start_frame;
    }

    public JTextField getEnter() {
        return enter;
    }

    public Button getOk() {
        return ok;
    }

    public JFrame getToplist() {
        return toplist;
    }

    public JMenuItem getTop() {
        return Top;
    }

    public JFrame getFrame() {
        return frame;
    }

    public JRadioButton getEasy() {
        return easy;
    }

    private JRadioButton easy = new JRadioButton("easy");

    public JRadioButton getHard() {
        return hard;
    }

    public JRadioButton getNightmare() {
        return nightmare;
    }

    public void setTimer_label(String text) {
        timer_label.setText("Timer: " + text);
    }

    public void setBomb_label(String text) {
        bomb_label.setText("Bomb: " + text);
    }

    public void setPlaying_label(String text) {
        playing_label.setText(text);
    }

    public void setField() {
        this.field.clear();
    }

    public JFrame getDifficult() { return difficult; }

    public JMenuItem getHighScore() { return HighScore; }

    public JPanel getField_panel() {
        return field_panel;
    }

    public JPanel getTop_panel() {
        return top_panel;
    }

    public JPanel getMain_panel() {
        return main_panel;
    }

    public Button getNewgamedifficult() {
        return newgamedifficult;
    }

    public ArrayList<Button_conteiner> getField() {
        return field;
    }

    public JMenuItem getNewgame() {
        return newgame;
    }

    public JMenuItem getExit() {
        return exit;
    }

    public View(int column, int row, int bomb){

        createbar();
        createbuttons(column, row, bomb);
        createframe();

        createDifficutButton();
        createdifficult();
        createTopPanel("easy");
        createTopPanel("hard");
        createTopPanel("nightmare");
        createTop();
        createname();
    }

    public void newGame(int column, int row, int bomb){
        top_panel.removeAll();
        field_panel.removeAll();
        main_panel.removeAll();
        field.clear();
        createbuttons(column, row, bomb);
        createframe();
    }

    public void createname(){
        frame.setVisible(false);
        start_panel.add(enter);
        start_panel.add(ok);

    start_frame.add(start_panel);
    start_frame.setVisible(true);
    start_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    start_frame.setTitle("Name");
    start_frame.pack();
    start_frame.setLocationRelativeTo(null);
    }

    public void createTopPanel(String text){

        JLabel words;

        HashMap<String, Integer> map = new HashMap<>();

        try {
            File file = new File("src/Miner/res/" + text + ".txt");

            FileReader fr = new FileReader(file);

            BufferedReader reader = new BufferedReader(fr);

            String line;
            String[] parse;
            while ((line = reader.readLine()) != null) {
                parse = line.split(" ");
                map.put(parse[0], Integer.parseInt(parse[1]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        map = sort_map(map);

        JPanel panel;

        Container container = new Container();
        container.setLayout(new BoxLayout(container ,BoxLayout.Y_AXIS));


        panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(text));

        for(HashMap.Entry entry: map.entrySet()){
            words = new JLabel();
            words.setText(entry.getKey().toString() + ": " + entry.getValue().toString() + " second");
            words.setPreferredSize(new Dimension(150, 50));
            container.add(words);
        }

        panel.add(container);

        if(text.equals("easy") == true) {
            easy_panel.removeAll();
            easy_panel.add(panel);
        }
        if(text.equals("hard") == true)  {
            hard_panel.removeAll();
            hard_panel.add(panel);
        }
        if(text.equals("nightmare") == true) {
            nightmare_panel.removeAll();
            nightmare_panel.add(panel);
        }

    }

    public void filling_top(int time, String text){

        try(FileWriter writer = new FileWriter("src/Miner/res/" + text + ".txt", true))
        {
            writer.write(name + " " + Integer.toString(time) + "\n");
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }



        createTopPanel(text);
    }

    public void createTop(){
        top.add(easy_panel);
        top.add(hard_panel);
        top.add(nightmare_panel);
        toplist.add(top);
        toplist.revalidate();
        toplist.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        toplist.setTitle("Toplist");
        toplist.pack();
        toplist.setLocationRelativeTo(null);
    }

    public HashMap<String, Integer> sort_map(HashMap<String, Integer> words){

        HashMap<String, Integer> sortedMap = words.entrySet().stream()
                .sorted(Comparator.comparingInt(e -> e.getValue()))
                .collect(Collectors.toMap(
                        HashMap.Entry::getKey,
                        HashMap.Entry::getValue,
                        (a, b) -> { throw new AssertionError(); },
                        LinkedHashMap::new));
        return sortedMap;
    }

    public void createframe() {
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Miner");
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    public void createDifficutButton() {
        buttonGroup.add(easy);
        buttonGroup.add(hard);
        buttonGroup.add(nightmare);
    }

    public void createdifficult() {
        JLabel label = new JLabel();
        easy.setName("9 9 10");
        hard.setName("16 16 40");
        nightmare.setName("16 30 99");

        GridLayout layout = new GridLayout(4, 4, 0, 0);
        difficult_panel.setLayout(layout);

        difficult_panel.add(label);

        label = new JLabel();
        label.setText("Row");
        difficult_panel.add(label);

        label = new JLabel();
        label.setText("Columns");
        difficult_panel.add(label);

        label = new JLabel();
        label.setText("Bomb");
        difficult_panel.add(label);

        difficult_panel.add(easy);

        label = new JLabel();
        label.setText("9");
        difficult_panel.add(label);

        label = new JLabel();
        label.setText("9");
        difficult_panel.add(label);

        label = new JLabel();
        label.setText("10");
        difficult_panel.add(label);

        difficult_panel.add(hard);

        label = new JLabel();
        label.setText("16");
        difficult_panel.add(label);

        label = new JLabel();
        label.setText("16");
        difficult_panel.add(label);

        label = new JLabel();
        label.setText("40");
        difficult_panel.add(label);

        difficult_panel.add(nightmare);
        label = new JLabel();
        label.setText("16");
        difficult_panel.add(label);

        label = new JLabel();
        label.setText("30");
        difficult_panel.add(label);

        label = new JLabel();
        label.setText("99");
        difficult_panel.add(label);

        JPanel panel = new JPanel();
        panel.add(difficult_panel);
        panel.add(newgamedifficult);

        difficult.add(panel);
        difficult.revalidate();
        difficult.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        difficult.setTitle("Difficult");
        difficult.pack();
        difficult.setLocationRelativeTo(null);
    }


    public void createbar() {
         newgame = menu.add(new JMenuItem("new game"));
         exit = menu.add(new JMenuItem("exit"));
         HighScore = menu.add(new JMenuItem("High Score"));
         Top = menu.add(new JMenuItem("Top"));

         bar.add(menu);
         frame.setJMenuBar(bar);

    }


   public void createbuttons(int column, int row, int bomb){
        timer_label.setText("Timer");

        bomb_label.setVerticalAlignment(JLabel.CENTER);
        bomb_label.setHorizontalAlignment(JLabel.LEFT);
        bomb_label.setPreferredSize(new Dimension((column * 50 ) / 3 - 10, 50));
        bomb_label.setFont(new Font("Verdana", Font.PLAIN, 12));
        bomb_label.setText("Bomb: " + Integer.toString(bomb));

        playing_label.setVerticalAlignment(JLabel.CENTER);
        playing_label.setHorizontalAlignment(JLabel.CENTER);
        playing_label.setPreferredSize(new Dimension((column * 50 ) / 3 , 50));
        playing_label.setFont(new Font("Verdana", Font.PLAIN, 12));
        playing_label.setText("plaing");

        timer_label.setVerticalAlignment(JLabel.CENTER);
        timer_label.setHorizontalAlignment(JLabel.RIGHT);
        timer_label.setPreferredSize(new Dimension((column * 50 ) / 3 - 10, 50));
        timer_label.setFont(new Font("Verdana", Font.PLAIN, 12));

        top_panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        top_panel.add(bomb_label);
        top_panel.add(playing_label);
        top_panel.add(timer_label);



        GridLayout layout = new GridLayout(row, column, 0, 0);
        field_panel.setLayout(layout);

        for(int i = 0; i < column * row; i += 1){
            JButton btn = new JButton();
            btn.setPreferredSize(new Dimension(50, 50));
            field_panel.add(btn);

            Button_conteiner ptr = new Button_conteiner(i, btn, column);
            field.add(ptr);

        }


        main_panel.setLayout(new VerticalLayout());
        main_panel.add(top_panel);
        main_panel.add(field_panel);
        frame.add(main_panel);
        frame.revalidate();

    }

    public void open_field(int[] open_field){
        for(int i = 0; i < open_field.length; i += 1){
            Button_conteiner ptr = field.get(i);

            if(open_field[i] == 10) {
                ptr.button.setIcon(new ImageIcon("src/Miner/res/img/flaged.png"));
            }
            else if(open_field[i] != -1) {
                ptr.button.setIcon(new ImageIcon("src/Miner/res/img/num" + Integer.toString(open_field[i]) + ".png"));
                if(open_field[i] == 0)
                ptr.button.setEnabled(false);
            }
            else {
                ptr.button.setIcon(null);
                ptr.button.setEnabled(true);
            }
        }
    }

    public void open_end_field(int[] field, int[] open_field){
        for(int i = 0; i < open_field.length; i += 1){

            Button_conteiner ptr = this.field.get(i);

            if(open_field[i] == 10 && field[i] == 9) {
                ptr.button.setIcon(new ImageIcon("src/Miner/res/img/nobomb.png"));

            }
            else if(open_field[i] != 9 && field[i] == 9) {
                ptr.button.setIcon(new ImageIcon("src/Miner/res/img/bomb.png"));

            }
            else if(open_field[i] == 9) {
                ptr.button.setIcon(new ImageIcon("src/Miner/res/img/bombed.png"));

            }
            else {
                ptr.button.setIcon(new ImageIcon("src/Miner/res/img/num" + Integer.toString(field[i]) + ".png"));
                if(field[i] == 0)
                ptr.button.setEnabled(false);
            }

        }
    }


}
