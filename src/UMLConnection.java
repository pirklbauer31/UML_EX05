import java.awt.geom.Line2D;

public abstract class UMLConnection {
    private String mName;

    private UMLRectangle endPointA;
    private UMLRectangle endPointB;

    private Line2D mConnectionLine;

    public UMLConnection() {
        endPointA = null;
        endPointB = null;
    }

    public UMLConnection(UMLRectangle _pointA, UMLRectangle _pointB) {
        endPointA = _pointA;
        endPointB = _pointB;

        Line2D.Double line = new Line2D.Double(_pointA.getCenterX(), _pointA.getCenterY(), _pointB.getCenterX(), _pointB.getCenterY());
        mConnectionLine = line;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public UMLRectangle getEndPointA() {
        return endPointA;
    }

    public void setEndPointA(UMLRectangle endPointA) {
        this.endPointA = endPointA;
    }

    public UMLRectangle getEndPointB() {
        return endPointB;
    }

    public void setEndPointB(UMLRectangle endPointB) {
        this.endPointB = endPointB;
    }

    public Line2D getmConnectionLine() {
        return mConnectionLine;
    }

    public void setmConnectionLine(Line2D mConnectionLine) {
        this.mConnectionLine = mConnectionLine;
    }
}
