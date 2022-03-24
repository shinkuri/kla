package at.kurumi;

import org.apache.commons.cli.Options;

public interface ICommand {

    String getOption();

    String getLongOpt();

    boolean hasArg();

    String getDesc();

    void handle(Options options);
}
