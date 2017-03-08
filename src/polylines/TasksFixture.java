package polylines;


import java.util.Arrays;

public class TasksFixture {

    public static final PolyLine[] LINES = new PolyLine[]{
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

}
