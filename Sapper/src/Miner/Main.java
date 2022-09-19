package Miner;

import java.util.Scanner;

public class Main  {

    public static void main(String[] args) {

System.out.print("Start gui ot text miner?\n1 - gui\n2 - text\nEnter num: ");
        Scanner in = new Scanner(System.in);
        int start = in.nextInt();

        while (start != 1 && start != 2) {
            System.out.println("Enter correct num: ");
            start = in.nextInt();
        }

        if(start == 1) {
            Miner.gui.View view = new Miner.gui.View(9, 9, 10);
            Miner.gui.Model model = new Miner.gui.Model(view);
            Miner.gui.Controller controller = new Miner.gui.Controller(view, model);
        }
        else if(start == 2){
            Miner.text.View view = new Miner.text.View();
            Miner.text.Model model = new Miner.text.Model(view);
            Miner.text.Controller controller = new Miner.text.Controller(model, view);
        }


    }

}
