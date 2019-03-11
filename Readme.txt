Requirements:
    - Java JDK & JRE
    - Project Jar file

Running the Program:
    Open command line and run:
        java -jar %PATH_TO_JAR% -i input.txt -o output.txt -h on

Usage:
    -h on|off -i input.txt -o output.txt [-d path] [-l log.txt] [-v verify.txt]
Notes:
    There are two types of flags, required (-i, -o, -h) and  optional (-d, -l, -v). If any of the required flags are
    missing, the program will print out the usage and terminate gracefully. By default the -d flag is set to the
    working directory, -l is set at logging to the console and -v is disabled.