import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.*;
import java.util.Scanner;

public class GraphUtils {

    // // TODO:
    // public static int getLongestField(Vertex[] vArray, int[][] mat) {
    //
    //     int longest = 0;
    //     int result = 0;
    //
    //     for (int i = 0; i < vArray.length; i++) {
    //         result = vArray[i] != null ? vArray[i].label.length() : 0;
    //
    //         if (result > longest) {
    //             longest = result;
    //         }
    //     }
    //
    //     return longest;
    // }

    /*
      A B C D
    A 0 2 1 4
    B 2 0 7 3
    C 1 7 0 5
    D 4 3 5 0

    Vertex:
        - index
        - label
    */
    public static String matrixToString(Vertex[] vArray, int[][][] mat) {
        String result = "  ";

        if (vArray == null) {
            return "vArray == null\n";
        }
        // Print header
        for (int i = 0; i < vArray.length; i++) {
            result += vArray[i] != null ? vArray[i].label + " " : "null ";
        }
        result += '\n';

        // Print Rows
        for (int i = 0; i < vArray.length; i++) {
            result += vArray[i] != null ? vArray[i].label + " " : "null ";

            for (int j = 0; j < vArray.length; j++) {

                for (int k = 0; k < mat[i][j].length; k++) {
                    result += mat[i][j][k];

                    if (! (k + 1 == mat[i][j].length)) {
                        result += ",";
                    } else {
                        result += " ";
                    }
                }

                if (mat[i][j].length == 0) {
                    result += 0 + " ";
                }
            }



            result += '\n';
        }

        return result;

    }

    public static String getDotFromGraphFile(String fileName) {
        String result = "";

        result += "graph{splines=curved;sep=\"+25,+25\"; nodesep=\"1\";overlap=scalexy;";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineCount = 1;

            while ((line = br.readLine()) != null) {
                if (lineCount == 1) {
                    lineCount++;
                } else {
                    line = line.trim();
                    line = line.replace(" ", "");
                    line = line.replace("\n", "").replace("\r", "");

                    if (!line.equals("")) {

                        // Add to the array
                        String[] elements = line.split(",");

                        if (elements.length == 3) {
                            result += elements[0] + "--" + elements[1] + "[ label= \"" + elements[2] + "\"];";
                        }
                    }
                }
            }
        } catch (IOException e) {

        }

        result += "}";

        return result;
    }

    public static void drawGraphFromFile(String inputFile, String outputFile) {
        String dot = getDotFromGraphFile(inputFile);
        drawGraphFromDot(dot, outputFile);
    }

    public static void drawGraphFromDot(String dot, String outputFile) {

        JavaFitchLogger jLog = new JavaFitchLogger("graphviz", JavaFitchLogger.DEBUG);

        ProcessBuilder builder = new ProcessBuilder("neato", "-T", "png", "-o", outputFile);
        builder.redirectErrorStream(true);

        try {

            Process process = builder.start();

            OutputStream stdin = process.getOutputStream(); // Our OUT is the process IN
            InputStream stdout = process.getInputStream();

            // stdin and stdout is now from thier perspective
            BufferedReader reader = new BufferedReader(new InputStreamReader(stdout));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin));

            writer.write(dot);
            writer.flush();
            writer.close();

            Scanner scanner = new Scanner(stdout);
            while (scanner.hasNextLine()) {
                jLog.log(JavaFitchLogger.ERROR, scanner.nextLine());
            }

        } catch (IOException e) {
            jLog.log(JavaFitchLogger.ERROR, e.toString());
            System.exit(1);
        }

    }

}
