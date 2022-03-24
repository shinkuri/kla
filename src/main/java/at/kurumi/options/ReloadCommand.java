package at.kurumi.options;

import at.kurumi.ICommand;
import at.kurumi.KLA;
import okhttp3.Request;
import org.apache.commons.cli.Options;

import java.io.IOException;

public class ReloadCommand implements ICommand {
    @Override
    public String getOption() {
        return "r";
    }

    @Override
    public String getLongOpt() {
        return "reload";
    }

    @Override
    public boolean hasArg() {
        return false;
    }

    @Override
    public String getDesc() {
        return "Tell the aggregator to reload sources.json";
    }

    @Override
    public void handle(Options options) {
        final var url = String.format("%s/reloadSources", KLA.LOG_AGGREGATOR_HOST);
        final var request = new Request.Builder().url(url).build();
        try {
            final var response = KLA.HTTP_CLIENT.newCall(request).execute();
            if(response.isSuccessful()) {
                System.out.println("Reload successful");
            } else {
                System.err.println("Reload failed");
            }
        } catch (IOException e) {
            System.out.println("Reload request failed.");
        }
    }
}
