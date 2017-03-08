package polylines;


import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class Task4Main {

    public void main(String argv[]) {
        List<PolyLine> lines = new ArrayList<>();
        lines.add(new PolyLine(Arrays.asList(
                new Point(1, 0),
                new Point(0, 1),
                new Point(1, 2)
        )));
        lines.add(new PolyLineExt(Arrays.asList(
                new Point(10, 0),
                new Point(0, 10),
                new Point(10, 20)
        )));
        lines.add(new PolyLine(Arrays.asList(
                new Point(100, 0),
                new Point(0, 100),
                new Point(100, 200)
        )));
        lines.add(new PolyLineExt(Arrays.asList(
                new Point(1000, 0),
                new Point(0, 1000),
                new Point(1000, 2000)
        )));
        lines.remove(2);

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
