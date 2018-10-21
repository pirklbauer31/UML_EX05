import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class View implements DataObserver {

    /**
     * The Drawing Panel to paint images in.
     */
    private DrawingPanel mPanel = new DrawingPanel();

    public View (Controller _controller) {
        JFrame mFrame = new JFrame();
        mFrame.setSize(1000,800);
        mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //mFrame.addWindowListener(_controller);

        JButton mDrawRectButton = new JButton();
        mDrawRectButton.setText("Rectangle");
        mDrawRectButton.setActionCommand("r");
        mDrawRectButton.addActionListener(_controller);

        JButton mDrawCommentBoxButton = new JButton();
        mDrawCommentBoxButton.setText("Comment");
        mDrawCommentBoxButton.setText("comment");
        mDrawCommentBoxButton.addActionListener(_controller);

        JButton mDrawConnectionButton = new JButton();
        mDrawConnectionButton.setText("Line");
        mDrawConnectionButton.setActionCommand("c");
        mDrawConnectionButton.addActionListener(_controller);

        mPanel.setBackground(Color.white);
        mPanel.addComponentListener(_controller);
        mPanel.addMouseListener(_controller);

        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new FlowLayout());
        actionPanel.setBackground(Color.lightGray);

        mFrame.setLayout(new BorderLayout());
        mFrame.add(mPanel, BorderLayout.CENTER);
        mFrame.add(actionPanel, BorderLayout.SOUTH);
        actionPanel.add(mDrawRectButton);
        actionPanel.add(mDrawCommentBoxButton);
        actionPanel.add(mDrawConnectionButton);

        mFrame.setVisible(true);
    }

    @Override
    public void update(BufferedImage _data) {
        mPanel.setmImage(_data);
        mPanel.repaint();
    }

    public DrawingPanel getmPanel() {
        return mPanel;
    }

    public void setmPanel(DrawingPanel _mPanel) {
        this.mPanel = mPanel;
    }
}
