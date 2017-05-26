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
}
