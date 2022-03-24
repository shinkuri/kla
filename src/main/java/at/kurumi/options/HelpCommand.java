package at.kurumi.options;

import at.kurumi.ICommand;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

public class HelpCommand implements ICommand {
    @Override
    public String getOption() {
        return "h";
    }

    @Override
    public String getLongOpt() {
        return "help";
    }

    @Override
    public boolean hasArg() {
        return false;
    }

    @Override
    public String getDesc() {
        return "Show help";
    }

    @Override
    public void handle(Options options) {
        new HelpFormatter().printHelp("kla", options);
    }
}
