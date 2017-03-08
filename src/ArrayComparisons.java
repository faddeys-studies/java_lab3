import java.util.Arrays;

public class ArrayComparisons {

    private static Boolean[] compareArrays(Object[] a, Object[] b) {
        return new Boolean[]{
                b == a,
                b.equals(a),
                Arrays.equals(a, b),
                Arrays.deepEquals(a, b),
                b[0] == a[0]
        };
    }

    private static String tableToString(Object[][] objTable) {
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

    private static String ljust(String str, int width) {
        int least = width - str.length();
        if (least <= 0) {
            return str;
        }
        String suffix = new String(new char[least]).replace("\0", " ");
        return str + suffix;
    }

    private static Object[] prependArray(Object item, Object arr[]) {
        Object result[] = new Object[1+arr.length];
        for(int i = 0; i < arr.length; i++) {
            result[i+1] = arr[i];
        }
        result[0] = item;
        return result;
    }

    private static void runForArray(Object[] array, Object[] literal) {
        System.out.println(tableToString(new Object[][] {
                {"b", "b==a", "b.equals(a)", "Arrays.equals(a, b)", "Arrays.deepEquals(a, b)", "b[0] == a[0]"},
                prependArray("b=a", compareArrays(array, array)),
                prependArray("b=a.clone()", compareArrays(array, array.clone())),
                prependArray("b=Arrays.copyOf(a)", compareArrays(array, Arrays.copyOf(array, array.length))),
                prependArray("b={\"1\",\"2\",\"3\"}", compareArrays(array, literal)),
        }));
    }

    public static void main(String argv[]) {
        String  arr1d[] = {"1","2","3"};
        String  arr2d[][] = {{"1"},{"2"},{"3"}};

        System.out.println("1-D arrays:");
        runForArray(arr1d, new String[]{"1","2","3"});
        System.out.println("2-D arrays:");
        runForArray(arr2d, new String[][]{{"1"},{"2"},{"3"}});
    }

}
