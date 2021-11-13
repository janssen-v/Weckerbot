import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

class timerSet{
    public void createTimer(int setTime) {

        JFrame jframe = new JFrame();
        JLabel jLabel = new JLabel();
        jframe.setLayout(new FlowLayout());
        jframe.setBounds(500, 300, 400, 100);

        jframe.add(jLabel);
        jframe.setVisible(true);

    Timer timer = new Timer();
    timer.scheduleAtFixedRate(new TimerTask() {
        int time = setTime;

        public void run() {

                jLabel.setText("Time left: " + time);
                time--;
                if (time < 0) {
                    timer.cancel();
                    jLabel.setText("Time Over");
                }
        }
    },0,1000);
    }
}

public class clocker {

    public static void main(String[] args) {
    timerSet time = new timerSet();
    time.createTimer(3600);
    }
}
