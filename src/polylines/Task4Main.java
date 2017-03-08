package polylines;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Task4Main {

    public static void sortLinesByLength(List<PolyLine> lines) {
        lines.sort(Comparator.comparingDouble(PolyLine::length));
    }

    public static void printLines(List<PolyLine> lines) {
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

    public static void main(String argv[]) {
        List<PolyLine> lines = new ArrayList<>();
        lines.add(TasksFixture.LINES[0]);
        lines.add(TasksFixture.LINES[2]);
        lines.add(TasksFixture.LINES[2]);
        lines.add(TasksFixture.LINES[3]);
        lines.add(TasksFixture.LINES[1]);
        lines.remove(1);

        System.out.println("BEFORE SORTING");
        printLines(lines);

        System.out.println("\n\nAFTER SORTING");
        sortLinesByLength(lines);
        printLines(lines);
    }

}
