package polylines;


public class Task3Main {

    public static void main(String[] argv) {

        for(PolyLine pl : TasksFixture.LINES) {
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
