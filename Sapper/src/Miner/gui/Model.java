package Miner.gui;

public class Model {
    private int column = 9;
    private int row = 9;
    private int count = 0;
    private int bomb = 10;
    private int flag = 0;
    private int open_cell = 0;
    private int[] field;
    private int[]open_field;
    private int end_game;
    private View view;
    private Timer timer;

    public Model(View view){
        this.view = view;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setBomb(int bomb) {
        this.bomb = bomb;
    }


    public void create_timer(){
        timer = new Timer(view);
        timer.setStart_time((int) System.currentTimeMillis());
        timer.setActive(1);
        timer.start();
    }

    public void createarrfield(int x, int y){
        end_game = 0;

        open_field = new int[column * row];

        for(int i = 0; i < column * row; i += 1)
            open_field[i] = -1;

        field = new int[column * row];
        for(int i = 0; i < bomb; i += 1){
            int bomb;
            while (x + y * column == (bomb = (int)(Math.random() * (row  * column))) || field[bomb] == 9);
            field[bomb] = 9;
        }

        int num;
        for(int iy = 0; iy < row; iy += 1){
            for(int ix = 0; ix < column; ix += 1){
                num = 0;
                if(field[ix + column * iy] != 9) {
                    for (int ptry = iy - 1; ptry < iy + 2; ptry += 1) {
                        for (int ptrx = ix - 1; ptrx < ix + 2; ptrx += 1) {
                            if (ptrx >= 0 && ptry >= 0 && ptrx < column && ptry < row && ptrx + ptry * column != ix + iy * column
                                    && field[ptrx + column * ptry] == 9)
                                num += 1;


                        }

                    }

                    field[ix + column * iy] = num;
                }
            }
        }
    }

    public void open_zero_cell(int x, int y){
        open_cell += 1;
        open_field[x + y * column] = field[x + y * column];
        if( field[x + y * column] == 0){
            if(x - 1 >= 0 && open_field[x - 1 + y * column] == -1)
                open_zero_cell(x - 1, y);
            if(x + 1 < column && open_field[x + 1 + y * column] == -1)
                open_zero_cell(x + 1, y);
            if(y - 1 >= 0 && open_field[x + (y - 1) * column] == -1)
                open_zero_cell(x, y - 1);
            if(y + 1 < row && open_field[x + (y + 1) * column] == -1)
                open_zero_cell(x, y + 1);

            if(x - 1 >= 0 && y - 1 >= 0 && open_field[x - 1 + (y - 1) * column] == -1)
                open_zero_cell(x - 1, y - 1);
            if(x + 1 < column &&  y - 1 >= 0 && open_field[x + 1 + (y - 1) * column] == -1)
                open_zero_cell(x + 1, y - 1);
            if(x - 1 >= 0 && y + 1 < row && open_field[x - 1 + (y + 1) * column] == -1)
                open_zero_cell(x - 1, y + 1);
            if(x + 1 < column &&  y + 1 < row && open_field[x + 1 + (y + 1) * column] == -1)
                open_zero_cell(x + 1, y + 1);


        }
    }

    public void distributor(int x, int y, int flag){
        if(count == 0 && flag != 3) {
            createarrfield(x, y);
            count += 1;
            create_timer();
        }

        if(flag == 0 && end_game == 0 && open_field[x + column * y] != 10) {
            if (field[x + column * y] == 9) {

                end_game = 1;

                open_field[x + column * y] = 9;

                view.setPlaying_label("Try again");
                view.open_end_field(field, open_field);

                timer.setActive(0);
            }

            else if(field[x + column * y] == 0) {
                open_zero_cell(x, y);
                view.open_field(open_field);
            }
            else {
                open_field[x + column * y] = field[x + column * y];
                open_cell += 1;
                view.open_field(open_field);
            }


        }
        else if(flag == 1 && end_game == 0){
            if(open_field[x + column * y] == -1) {
                open_field[x + column * y] = 10;
                this.flag += 1;
               // bomb -= 1;

                view.setBomb_label(Integer.toString(bomb - this.flag));
                view.open_field(open_field);
            }
            else if(open_field[x + column * y] == 10) {
                open_field[x + column * y] = -1;
                this.flag -= 1;
                //bomb += 1;
                view.setBomb_label(Integer.toString(bomb - this.flag));
                view.open_field(open_field);
            }

            if(this.flag == bomb) {
                int chek = 1;
                for(int i = 0; i < column * row; i += 1)
                    if(open_field[i] == 10 && field[i] != 9)
                        chek = 0;

                if(chek == 1) {
                    view.open_end_field(field, open_field);
                    view.setPlaying_label("Win");
                    timer.setActive(0);
                    end_game = 1;

                    int time = timer.getCurrent_time();

                    String text;
                    if(bomb == 10)
                        text = "easy";
                    else if(bomb == 40)
                        text = "hard";
                    else
                        text = "nightmare";

                    view.filling_top(time, text);

                }
            }
        }
        else if(flag == 3){
            view.newGame(column, row, bomb);

            end_game = 0;
            this.flag = 0;
            open_cell = 0;
            count = 0;

            if(timer != null)
            timer.setActive(0);
        }

    }
}
