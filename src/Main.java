import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private static final String USAGE = "Usage: -h on|off -i input.txt -o output.txt " +
            "[-d path] [-l log.txt] [-v verify.txt]";

    public static void main(String[] args) {
        /* -Initialization- */
        System.out.println("Initializing...........");
        CommandParser commandParser = new CommandParser();

        Map<String, String> options = new HashMap<>();
        try {
            options = commandParser.parse(args);
        } catch (IllegalArgumentException e){
            System.out.println(USAGE);
            System.out.println(e.getMessage());
        } catch (Exception e){
            e.printStackTrace();
        }

        // Retrieve flags
        boolean useHashTable = options.get("-h").equals("on");
        String inputFileName = options.get("-i");
        String outputFileName = options.get("-o");
        String directory = options.containsKey("-d") ? options.get("-d") : System.getProperty("user.dir");

        // Get proper file paths
        File inputFile = null;
        File outputFile = null;
        try {
            inputFile = new File(directory + File.separator + inputFileName).getCanonicalFile();
            outputFile = new File(directory + File.separator + outputFileName).getCanonicalFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set up inventory type
        Inventory inventory;
        if(useHashTable)
            inventory = new CachedInventory();
        else
            inventory = new CachedInventory(); // TODO: Change to DB inventory

        /* -Input- */
        System.out.println("Reading from file: " + inputFile);
        assert inputFile != null;
        List<ItemModel> items =  getInputData(inputFile);

        /* -Insertion- */
        System.out.println("Inserting data into " + (useHashTable ? "hashtable" : "database"));
        for(ItemModel item : items){
            inventory.insert(item);
        }

        /* -Output Dump- */
        System.out.println("Dumping data to " + outputFile);
        dumpOutput(outputFile, items);
    }

    private static List<ItemModel> getInputData(File inputFile){
        if(!inputFile.exists() || !inputFile.isFile()){
            System.out.println("Error: Invalid input file");
            System.exit(0);
        }

        List<ItemModel> inputData = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(inputFile))){
            String st;
            while((st = br.readLine()) != null){
                ItemModel tempItem = new ItemModel(st);
                inputData.add(tempItem);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return inputData;
    }

    private static void dumpOutput(File outputFile, List<ItemModel> inventory){

        try(FileWriter fileWriter = new FileWriter(outputFile)) {
            fileWriter.write(new ItemModel().getItemDescriptor());
            for(ItemModel item : inventory){
                fileWriter.write(item.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
