import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Timer;
import java.util.TimerTask;


public class Main extends ListenerAdapter {
    String prefix = "$";

    private String clean_input(String command, String input){
        return input.substring(command.length());
    }


    private static void thread_init() {
        String token = "TOKEN";
        JDABuilder builder = JDABuilder.createDefault(token);
        builder.setToken(token);
        builder.setActivity(Activity.watching("the time"));
        builder.addEventListeners(new Main());
        try {
            builder.build();
        } catch (Exception LoginException) {
            throw new java.lang.Error("Login Failure: " + LoginException);
        }
    }

    public static void main(String[] args) {

        // Config Inputs
        thread_init();

    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        // Ignore messages sent by bots
        if(event.getAuthor().isBot()) {
            return;
        }

        Message msg = event.getMessage();
        String read = msg.getContentRaw();
        // Separate read into the command and payload (prefix + command + payload)
        String [] tmp = read.split(" ", 2);
        String cmd = tmp[0];
        String arg = tmp[1]; //Doesn't work with payload-free commands



        if(read.equals(prefix + "alarm")) {
            String input = clean_input(prefix + "alarm", read);
            // $alarm 7:30

        }


        if(cmd.equals(prefix + "timer")) {
            int setTime = Integer.parseInt(arg);
            // $timer 10 (seconds)

            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask(){
                int time = setTime;
                public void run() {
                    time--;
                    if (time < 0) {
                        timer.cancel();
                        event.getChannel().sendMessage
                                ("It's been " + setTime + " seconds. Time's up!")
                                .queue();
                    }

                }

            }, 0, 1000);

        }





        // Ping Test
        if(read.equals(prefix + "ping")) {
            event.getChannel().sendMessage
                    ("Pong!")
                    .queue();

        } else if(event.getMessage().getContentRaw().equals(prefix + "hodl")) {
            event.getChannel().sendMessage
                    (":gem: :open_hands: :rocket: :full_moon:")
                    .queue();
        }
    }
}
