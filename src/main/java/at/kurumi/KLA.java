package at.kurumi;

import at.kurumi.options.HelpCommand;
import at.kurumi.options.ReloadCommand;
import at.kurumi.options.SourcesCommand;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.apache.commons.cli.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Entrypoint for the application. Builds command line options from available operations and dispatches to them if
 * the user selects one.
 */
public class KLA {

    public static final String LOG_AGGREGATOR_HOST = "localhost:8080";
    public static final OkHttpClient HTTP_CLIENT = new OkHttpClient();

    private static final Map<String, ICommand> commands = new HashMap<>();

    public static void main(String[] args) throws ParseException {
        final var options = new Options();
        fromCommand(new HelpCommand(), options);
        fromCommand(new SourcesCommand(), options);
        fromCommand(new ReloadCommand(), options);

        final var input = new DefaultParser()
                .parse(options, args, true)
                .getOptions();

        for(Option o : input) {
            commands.get(o.getOpt()).handle(options);
        }

        // Is there a non-option argument?
        if(args.length > input.length) {
            tail(args[input.length]);
        }
    }

    private static void fromCommand(ICommand command, Options options) {
        commands.put(command.getOption(), command);
        options.addOption(Option.builder()
                .option(command.getOption())
                .longOpt(command.getLongOpt())
                .hasArg(command.hasArg())
                .desc(command.getDesc())
                .build());
    }

    private static void tail(String source) {

    }
}
