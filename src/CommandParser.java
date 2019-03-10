import java.util.*;

/**
 * the CommandParser class parses the arguments passed in from the command line.
 * Possible flags:
 * -h on: use hashmap; off: use database
 * -d resource directory
 * -i input file path
 * -o output file path
 * -l log file path
 * -v very data path
 */
public class CommandParser {

    private String[] REQUIRED_FLAGS = {"-h", "-i", "-o"};

    private Map<String, String> options;

    public CommandParser() {
        options = new HashMap<>();
    }

    public Map<String, String> parse(String[] args) throws IllegalArgumentException {

        for (int i = 0; i < args.length; i += 2) {
            if (args[i].charAt(0) == '-') {
                options.put(args[i].toLowerCase(), args[i + 1]);
            } else {
                throw new IllegalArgumentException("Not a valid argument " + args[i]);
            }
        }

        for (String flag : REQUIRED_FLAGS) {
            if (!options.containsKey(flag)) {
                throw new IllegalArgumentException("Missing Required Flags");
            }
        }
        return options;
    }

    public Map<String, String> getOptions() {
        return options;
    }

    public List<String> getFlags() {
        return new ArrayList<String>(options.keySet());
    }
}
