package Miner.text;

import java.util.Scanner;

public class Controller {
    private Scanner in = new Scanner(System.in);
    private Miner.text.Model model;
    private int active = 1;

    public Controller(Model model, View view){
        this.model = model;

        info();
        command();
    }

    public void command(){

        while (active == 1) {
            System.out.print("Command: ");
            String command = in.nextLine();
            String[] arg = command.split(" ");

            if (arg[0].equals("exit")) {
                active = 0;
                System.exit(0);
            }
            else if (arg[0].equals("info")){
                System.out.println("here");
                info();
        }
            else if(arg.length == 2){
                if(arg[0].equals("new")){
                    model.show("new");
                }
                else
                   model.distributor(Integer.parseInt(arg[0]) - 1, Integer.parseInt(arg[1]) - 1, 0);
            }

            else if(arg.length == 3){
                if(arg[0].equals("flag"))
                model.distributor(Integer.parseInt(arg[1]) - 1, Integer.parseInt(arg[2]) - 1, 1);
                else if(arg[0].equals("field")){
                    model.setRow(Integer.parseInt(arg[2]));
                    model.setColumn(Integer.parseInt(arg[1]));
                    model.setBomb(Integer.parseInt(arg[1]) * Integer.parseInt(arg[2]) / 4);
                    model.distributor(0,0,3);
                }
            }


            else if(arg[0].equals("open")){
                model.show("open");
            }

            else if(arg[0].equals("bomb")){
                model.show("bomb");
            }

            else if(arg[0].equals("time")){
                model.show("time");
            }

            else if(arg[0].equals("top")){
                model.show("top");
            }
        }
    }

    public void info(){
        System.out.println("About command:");
        System.out.println("Enter x y coordinate:");
        System.out.println("open - open all cells and game stop");
        System.out.println("Enter flag x y coordinate:");
        System.out.println("new game");
        System.out.println("field x(size) y(size) - create new field( bomb = x * y / 4)");
        System.out.println("bomb");
        System.out.println("top");
        System.out.println("info");
        System.out.println("time");
        System.out.println("exit");
        System.out.println("");
        System.out.println("About cells:");
        System.out.println("? - not open cell");
        System.out.println("0 - 8 -how much bomb around");
        System.out.println("9 - bomb");
        System.out.println("f - flag");
    }

}
