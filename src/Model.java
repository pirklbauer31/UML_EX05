import org.w3c.dom.css.Rect;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Model {
    /**
     * The ArrayList containing the observers
     */
    private ArrayList<View> mObservers = new ArrayList<>();

    private ArrayList<Rectangle> mRectangles = new ArrayList<>();
    private ArrayList<Line2D> mLines = new ArrayList<>();
    private ArrayList<CommentBox> mCommentBoxes = new ArrayList<>();

    /**
     * Width of the image to draw in the panel
     */
    private int mImageWidth;
    /**
     * Height of the image to draw in the panel
     */
    private int mImageHeight;

    /**
     * Keeps track if mouse event has been triggered
     */
    private boolean mMouseEvent;
    /**
     * X Position of the mouse cursor when clicked
     */
    private int mMousePosX;
    /**
     * Y Position of the mouse cursor when clicked
     */
    private int mMousePosY;
    /**
     * Which mouse button has been clicked on event
     */
    private int mMouseButton;

    private Rectangle mRectPosition1;
    private Rectangle mRectPosition2;
    private final static float dash1[] = {10};
    private final static BasicStroke dashedStroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, dash1, 0.0f);


    public void generateOnMousePos(int _posX, int _posY, int _clickedButton, Controller.SupportedActions _action) {
        mMousePosX = _posX;
        mMousePosY = _posY;
        mMouseButton = _clickedButton;

        switch (_action) {
            case rectangle: {
                generateRectangleAtPosition();
            } break;
            case comment: {
                generateCommentAtPosition();
            } break;
        }

    }

    private void generateRectangleAtPosition() {
        Rectangle rec = new Rectangle();
        rec.setLocation(mMousePosX, mMousePosY);
        rec.setSize(80, 60);

        mRectangles.add(rec);

        updateObservers();
    }

    private void generateCommentAtPosition() {
        CommentBox comment = new CommentBox();
        comment.setLocation(mMousePosX, mMousePosY);
        comment.setSize(80, 60);

        mCommentBoxes.add(comment);

        updateObservers();
    }

    public void checkPosition(int _posX, int _posY) {
        Rectangle rectangleToFind = null;

        for (Rectangle rect: mRectangles) {
            if (rect.contains(_posX, _posY)) {
                rectangleToFind = rect;
            }
        }

        if (rectangleToFind != null && mRectPosition1 == null) {
            mRectPosition1 = rectangleToFind;
        } else if (rectangleToFind != null && mRectPosition2 == null && rectangleToFind != mRectPosition1) {
            mRectPosition2 = rectangleToFind;
            generateLineBetweenRectangles();
        }
    }




    private void generateLineBetweenRectangles() {
        Line2D.Double line = new Line2D.Double(mRectPosition1.getCenterX(), mRectPosition1.getCenterY(), mRectPosition2.getCenterX(), mRectPosition2.getCenterY());

        mLines.add(line);

        mRectPosition1 = null;
        mRectPosition2 = null;

        updateObservers();
    }

    public void addObserver(View _view) {
        mObservers.add(_view);
    }

    public void updateObservers () {
        for (View observer : mObservers) {

            BufferedImage mImage = new BufferedImage(mImageWidth, mImageHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics2D = (Graphics2D) mImage.getGraphics();
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2D.setColor(Color.white);
            graphics2D.fillRect(0,0, mImageWidth, mImageHeight);
            graphics2D.setColor(Color.black);
            for (Rectangle rec:
                 mRectangles) {
                graphics2D.draw(rec);
            }


            for (Line2D line: mLines) {
                graphics2D.draw(line);
            }

            graphics2D.setStroke(dashedStroke);
            for (Rectangle comment: mCommentBoxes) {
                graphics2D.draw(comment);
            }

            observer.update(mImage);
        }
    }

    public void setSize(int _width, int _height) {
        mImageWidth = _width;
        mImageHeight = _height;
    }

    //Getters and setters

    public boolean isMouseEvent() {
        return mMouseEvent;
    }

    public void setMouseEvent(boolean _mouseEvent) {
        this.mMouseEvent = _mouseEvent;
    }

}
