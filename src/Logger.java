import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Logger {
    private FileWriter fileWriter;
    private boolean toConsole = true;
    Logger(){

    }

    Logger(String logFileName){
        toConsole = false;

        File logFile = null;
        try {
            logFile = new File(logFileName).getCanonicalFile();
            fileWriter = new FileWriter(logFile, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void log(String actionType, String data){
        String output = "LOG: Action: " + actionType + " Timestamp: " + new Date() + " Item: " + data;
        if(toConsole){
            System.out.print(output);
        } else {
            try {
                fileWriter.write(output);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void closeLogger(){
        if(!toConsole){
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
