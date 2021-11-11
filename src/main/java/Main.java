import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;

public class Main extends ListenerAdapter {
    public static void main(String[] args) throws LoginException {
        String token = "INSERT TOKEN HERE";
        JDABuilder builder = JDABuilder.createDefault(token);
        builder.setToken(token);
        builder.setActivity(Activity.watching("the time"));
        builder.addEventListeners(new Main());
        builder.build();
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        super.onMessageReceived(event);
        if(event.getMessage().getContentRaw().equals("$ping")) {
            event.getChannel().sendMessage("Pong!").queue();
        } else if(event.getMessage().getContentRaw().equals("$hodl")) {
            event.getChannel().sendMessage(":gem: :open_hands: :rocket: :full_moon:").queue();
        }
    }
}
