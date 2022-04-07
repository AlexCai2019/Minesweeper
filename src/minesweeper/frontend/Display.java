package minesweeper.frontend;

import java.awt.*;
import java.awt.event.*;

public class Display
{
    private final Frame frame = new Frame("踩地雷");
    private final Panel controls = new Panel();
    private final Font cosmicSans = new Font("Comic Sans", Font.PLAIN, 24);
    private Button[][] map;
    private boolean gaming = false;

    public Display()
    {
        frame.setSize(960, 540);
        frame.addComponentListener(new ComponentAdapter()
        {
            @Override
            public void componentResized(ComponentEvent e)
            {
                super.componentResized(e);
                resize();
            }
        });
        frame.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                super.windowClosing(e);
                frame.dispose();
                System.exit(0);
            }
        });
        frame.setVisible(true);
    }

    public void before()
    {
        frame.setLayout(null);
        controls.setLayout(new GridLayout(3,2));

        Label rowsLabel = new Label("列數(rows)");
        rowsLabel.setFont(cosmicSans);
        TextField rowsText = new TextField();
        rowsText.setFont(cosmicSans);
        Label columnsLabel = new Label("行數(columns)");
        columnsLabel.setFont(cosmicSans);
        TextField columnsText = new TextField();
        columnsText.setFont(cosmicSans);
        Label minesLabel = new Label("地雷數(mines)");
        minesLabel.setFont(cosmicSans);
        TextField minesText = new TextField();
        minesText.setFont(cosmicSans);

        controls.add(rowsLabel);
        controls.add(rowsText);
        controls.add(columnsLabel);
        controls.add(columnsText);
        controls.add(minesLabel);
        controls.add(minesText);

        frame.add(controls);
        gaming = false;
        resize();
    }

    public void game()
    {
        gaming = true;
    }

    private void resize()
    {
        if (gaming)
        {
            ;
        }
        else
        {
            int width = frame.getWidth();
            int height = frame.getHeight();
            controls.setBounds(width >> 2, height >> 2, width >> 1, height >> 1);
        }
    }
}
