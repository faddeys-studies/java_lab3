import java.util.Arrays;

public class ArrayComparisons {

    public static Boolean[] compareArrays(String[] a, String[] b) {
        return new Boolean[]{
                b == a,
                b.equals(a),
                Arrays.equals(a, b),
                Arrays.deepEquals(a, b),
                b[0] == a[0]
        };
    }

    public static String tableToString(Object[][] objTable) {
        if (objTable.length == 0) {
            return "";
        }
        String table[][] = new String[objTable.length][];
        int colWidths[] = null;
        for(int row = 0; row < objTable.length; row++) {
            table[row] = new String[objTable[row].length];
            if (colWidths == null) {
                colWidths = new int[objTable[row].length];
            }
            for (int col = 0; col < objTable[row].length; col++) {
                String item = objTable[row][col].toString();
                int width = item.length();
                if (width > colWidths[col]) {
                    colWidths[col] = width;
                }
                table[row][col] = item;
            }
        }
        StringBuilder builder = new StringBuilder();
        for(int row = 0; row < table.length; row++) {
            if (row > 0) {
                builder.append("\n");
            }
            for(int col = 0; col < colWidths.length; col++) {
                if (col > 0) {
                    builder.append(" ");
                }
                int width = colWidths[col];
                builder.append(ljust(table[row][col], width));
            }
        }
        return builder.toString();
    }

    public static String ljust(String str, int width) {
        int least = width - str.length();
        if (least <= 0) {
            return str;
        }
        String suffix = new String(new char[least]).replace("\0", " ");
        return str + suffix;
    }

    public static Object[] prependArray(Object item, Object arr[]) {
        Object result[] = new Object[1+arr.length];
        for(int i = 0; i < arr.length; i++) {
            result[i+1] = arr[i];
        }
        result[0] = item;
        return result;
    }

    public static void main(String argv[]) {
        String  a[] = {"1","2","3"};
        System.out.println(tableToString(new Object[][] {
                {"b", "b==a", "b.equals(a)", "Arrays.equals(a, b)", "Arrays.deepEquals(a, b)", "b[0] == a[0]"},
                prependArray("b=a", compareArrays(a, a)),
                prependArray("b=a.clone()", compareArrays(a, a.clone())),
                prependArray("b=Arrays.copyOf(a)", compareArrays(a, Arrays.copyOf(a, a.length))),
                prependArray("b={\"1\",\"2\",\"3\"}", compareArrays(a, new String[]{"1","2","3"})),
        }));
    }

}
