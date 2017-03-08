package polylines;

import java.util.Arrays;


public class Task3Main {

    public static void main(String[] argv) {
        PolyLine lines[] = new PolyLine[]{
                new PolyLine(Arrays.asList(
                        new Point(1, 0),
                        new Point(0, 1),
                        new Point(1, 2)
                )),
                new PolyLineExt(Arrays.asList(
                        new Point(10, 0),
                        new Point(0, 10),
                        new Point(10, 20)
                )),
                new PolyLine(Arrays.asList(
                        new Point(100, 0),
                        new Point(0, 100),
                        new Point(100, 200)
                )),
                new PolyLineExt(Arrays.asList(
                        new Point(1000, 0),
                        new Point(0, 1000),
                        new Point(1000, 2000)
                ))
        };

        for(PolyLine pl : lines) {
            System.out.println("\n" + pl.getClass().getName());
            System.out.println(pl.toString() + " of length " + Double.toString(pl.length()));
            if (pl instanceof PolyLineExt) {
                System.out.println(
                        (((PolyLineExt)pl).isInRectangle(new Point(-50, 50), new Point(50, -50))
                         ? "is " : "is not ")
                        + "near the center"
                );
            }
        }
    }

}
