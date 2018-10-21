import java.awt.event.*;



public class Controller implements ActionListener, MouseListener, ComponentListener {

    public enum SupportedActions {
        rectangle, connection, comment
    }

    private Model mModel;
    private SupportedActions mCurrentAction;


    public Controller (Model _model) {
        mModel = _model;
        mCurrentAction = SupportedActions.rectangle;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        String eventCommand = _e.getActionCommand();

        switch (eventCommand) {
            case "r" : {
                System.out.println("Rectangle has been pressed!");
                mModel.setMouseEvent(false);
                mCurrentAction = SupportedActions.rectangle;
            } break;
            case "comment" : {
                System.out.println("Comment has been pressed!");
                mModel.setMouseEvent(false);
                mCurrentAction = SupportedActions.comment;
            } break;
            case "c" : {
                System.out.println("Line has been pressed!");
                mModel.setMouseEvent(false);
                mCurrentAction = SupportedActions.connection;
            } break;
        }


    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        mModel.setMouseEvent(true);

        switch (mCurrentAction) {
            case rectangle: {
                mModel.generateOnMousePos(_e.getX(), _e.getY(), _e.getButton(), mCurrentAction);
            } break;
            case comment: {
                mModel.generateOnMousePos(_e.getX(), _e.getY(), _e.getButton(), mCurrentAction);
            } break;
            case connection: {
                mModel.checkPosition(_e.getX(), _e.getY());
            } break;
        }


    }

    @Override
    public void mouseEntered(MouseEvent _e) {

    }

    @Override
    public void mouseExited(MouseEvent _e) {

    }

    @Override
    public void componentResized(ComponentEvent _e) {
        int compWidth = _e.getComponent().getWidth();
        int compHeight = _e.getComponent().getHeight();
        mModel.setSize(compWidth, compHeight);
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent _e) {
        /*int compWidth = _e.getComponent().getWidth();
        int compHeight = _e.getComponent().getHeight();
        mModel.setSize(compWidth, compHeight);*/
    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}
