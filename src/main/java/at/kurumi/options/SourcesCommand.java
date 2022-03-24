package at.kurumi.options;

import at.kurumi.ICommand;
import at.kurumi.KLA;
import okhttp3.Request;
import org.apache.commons.cli.Options;

import java.io.IOException;

public class SourcesCommand implements ICommand {
    @Override
    public String getOption() {
        return "s";
    }

    @Override
    public String getLongOpt() {
        return "sources";
    }

    @Override
    public boolean hasArg() {
        return false;
    }

    @Override
    public String getDesc() {
        return "List all available sources";
    }

    @Override
    public void handle(Options options) {
        final var url = String.format("%s/sources", KLA.LOG_AGGREGATOR_HOST);
        final var request = new Request.Builder().url(url).build();
        try {
            final var response = KLA.HTTP_CLIENT.newCall(request).execute();
            if(response.isSuccessful()) {
                System.out.println("list them all");
            } else {
                System.err.println("Could not receive sources");
            }
        } catch (IOException e) {
            System.out.println("Sources request failed.");
        }
    }
}
