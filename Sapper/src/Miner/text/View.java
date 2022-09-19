package Miner.text;

import Miner.gui.Button_conteiner;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.stream.Collectors;

public class View {
    private String name;

    public View(){
        System.out.print("Enter your name: ");
        Scanner in = new Scanner(System.in);
        name = in.nextLine();
    }

    public void message(String text){
        System.out.println(text);
    }

    public void top(){
        HashMap<String, Integer> map = new HashMap<>();

        try {
            File file = new File("src/Miner/res/top.txt");

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


        for(HashMap.Entry entry: map.entrySet()){
            System.out.println(entry.getKey().toString() + ": " + entry.getValue().toString() + " second");
        }
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

    public void filling_top(int time, String text){

        try(FileWriter writer = new FileWriter("src/Miner/res/" + text + ".txt", true))
        {
            writer.write(name + " " + Integer.toString(time) + "\n");
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }

    public void open_field(int[] open_field, int row, int column) {

        for (int i = 0; i < column + 1; i += 1)
            System.out.print(i + " ");

        System.out.print("\n");

        for (int iy = 0; iy < row; iy += 1) {
            for (int ix = 0; ix < column + 1; ix += 1) {
                if(ix == 0)
                    System.out.print((iy + 1) + " ");
                else {
                    if(open_field[ix - 1 + iy * column] == -1)
                        System.out.print("? ");
                    else if(open_field[ix - 1 + iy * column] == 10)
                        System.out.print("f ");
                    else
                    System.out.print(open_field[ix - 1 + iy * column] + " ");
                }
            }
            System.out.print("\n");
        }
    }
}