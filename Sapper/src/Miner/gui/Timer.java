package Miner.gui;

public class Timer extends Thread {

    private int current_time = 0;
    private int start_time;
    private int active;
    private View view;

    public Timer(View view){
    this.view = view;
    }

    public void setStart_time(int start_time) {
        this.start_time = start_time;
    }

    public int getCurrent_time() {
        return current_time;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public void run() {
        while (active == 1){
                current_time = (((int)System.currentTimeMillis() - start_time)/ 1000);
                view.setTimer_label(Integer.toString(current_time));

            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
