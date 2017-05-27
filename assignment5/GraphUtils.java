import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.*;
import java.util.Scanner;
import java.util.*;
import java.lang.Object.*;

public class GraphUtils {

    public static final String GRAPH_CONFIG = "splines=curved;sep=\"+25,+25\"; nodesep=\"1\";overlap=scalexy";

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

    // WARNING: white-box
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
        String result = "graph {labelloc=\"t\";label=\"Expected " + fileName + "\";";

        result += GRAPH_CONFIG + ";";

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

    public static void graphFileToImage(String inputFile, String outputFile) {
        String dot = getDotFromGraphFile(inputFile);
        drawGraphFromDot(dot, outputFile);
    }

    public static int getIndexByString(String[] arr, String str) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null && arr[i].equals(str)) {
                return i;
            }
        }

        return -1;
    }

    public static boolean arrayContainsString(String[] arr, String str) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null && arr[i].equals(str)) {
                return true;
            }
        }

        return false;
    }

    /*
    Need to give two arrays if nodes in the graph have been renamed to make node discovery possible
    */
    public static String[] getVerticesFromGraphFile(String fileName, String[] srcName, String[] targetName) {
        String[] result = new String[1];
        int resultCount = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            int lineCount = 1;

            while ((line = br.readLine()) != null) {
                if (lineCount == 1) {
                    int count = Integer.parseInt(line);
                    result = new String[count];

                    lineCount++;
                } else {
                    line = line.trim();
                    line = line.replace(" ", "");
                    line = line.replace("\n", "").replace("\r", "");

                    if (!line.equals("")) {

                        // Add to the array
                        String[] elements = line.split(",");

                        if (elements.length == 3) {
                            for (int i = 0; i < 2; i++) {

                                // Rename element
                                if (arrayContainsString(srcName, elements[i])) {
                                    elements[i] = targetName[getIndexByString(srcName, elements[i])];
                                }

                                if (!arrayContainsString(result, elements[i])) {
                                    result[resultCount++] = elements[i];
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {

        }

        return result;
    }


    public static void graphToImage(Graph g, String graphFile, String imageFile, String[] srcName, String[] targetName) {
        String dot = graphToDot(g, graphFile, srcName, targetName, "", null);
        drawGraphFromDot(dot, imageFile);
    }

    public static void graphToImage(Graph g, String graphFile, String imageFile, String activeVertex, String[][] path) {
        String dot = graphToDot(g, graphFile, new String[0], new String[0], activeVertex, path);
        drawGraphFromDot(dot, imageFile);
    }

    public static void graphToImage(Graph g, String graphFile, String imageFile) {
        String dot = graphToDot(g, graphFile, new String[0], new String[0], "", null);
        drawGraphFromDot(dot, imageFile);
    }

    public static String[][] pathToEdgeArray(String path) {
        String[] elems = path.split(",");

        String[][] master = new String[elems.length - 1][2];

        for (int i = 0; i < elems.length - 1; i++) {
            master[i][0] = elems[i];
            master[i][1] = elems[i+1];
        }

        return master;
    }

    public static boolean edgeInTraveled(String[][] path, String vertA, String vertB) {

        if (path != null) {
            for (int i = 0; i < path.length; i++) {
                // Check if one of the vertices are in the current entry
                if (path[i][0] != null) {
                    if (path[i][0].equals(vertA)) {
                        if (path[i][1].equals(vertB)) {
                            return true;
                        }
                    } else if (path[i][0].equals(vertB)) {
                        if (path[i][1].equals(vertA)) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;

    }

    public static String graphToDot(Graph g, String graphFile, String[] srcName, String[] targetName, String activeVertex, String[][] jumps) {
        String result = "graph {labelloc=\"t\";label=\"Receieved " + graphFile + "\";";
        result += GRAPH_CONFIG + ";";

        String[] vertices = getVerticesFromGraphFile(graphFile, srcName, targetName);
        ArrayList<String> checked = new ArrayList<String>();

        // Take every node
        for ( int i = 0; i < vertices.length; i++) {

            // Compare with other nodes
            for ( int j = 0; j < vertices.length; j++) {
                if (!checked.contains(vertices[j])) {
                    int edges = g.numEdges(vertices[i], vertices[j]);
                    // Record if there are edges
                    for (int k = 0; k < edges; k++) {


                        if (edgeInTraveled(jumps, vertices[i], vertices[j])) {
                            result += vertices[i] + "--" + vertices[j] + " [dir=none; color=blue, penwidth=2];";
                        } else {
                            result += vertices[i] + "--" + vertices[j] + ";";
                        }


                    }
                }
            }

            // Mark node as visted
            checked.add(vertices[i]);
        }

        // NOTE: This function created a lot of duplicate data
        if (jumps != null) {
            for (int i = 0; i < jumps.length; i++) {
                if (jumps[i][0] != null && jumps[i][1] != null) {
                    result += jumps[i][0] + " [style=filled, fillcolor=green];";
                    result += jumps[i][1] + " [style=filled, fillcolor=green];";
                }

            }
        }

        if (activeVertex != "") {
            // node [shape=box, color=blue]
            result += activeVertex + " [style=filled, fillcolor=firebrick1];";
        }

        result += "}";

        return result;
    }

    public static void drawGraphFromDot(String dot, String outputFile) {

        File file = new File(outputFile);
        File parentDir = file.getParentFile(); // to get the parent dir
        //String parentDirName = file.getParent(); // to get the parent dir name
        parentDir.mkdirs();

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
                JLogger.log(JLogger.ERROR, scanner.nextLine());
            }

        } catch (IOException e) {
            JLogger.log(JLogger.ERROR, e.toString());
            JLogger.log(JLogger.ERROR, "Please install graphviz for the ability to draw graphs.");
            JLogger.log(JLogger.ERROR, "$ sudo pacman -S graphviz");
            JLogger.log(JLogger.ERROR, "$ sudo apt-get install graphviz");
        }

    }

}
