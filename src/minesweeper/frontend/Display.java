package minesweeper.frontend;

import java.awt.*;
import java.awt.event.*;

public class Display
{
    private final Frame frame;
    private final Panel controls;
    private Button[][] map;
    private boolean gaming = false;

    public Display()
    {
        frame = new Frame("踩地雷");
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

        controls = new Panel();
    }

    public void before()
    {
        frame.setLayout(new BorderLayout());
        controls.setLayout(new GridLayout(3,2));
        Label label = new Label("test");
        controls.add(label);
        frame.add(new Button("北"), BorderLayout.NORTH);
        frame.add(new Button("西"), BorderLayout.WEST);
        frame.add(controls, BorderLayout.CENTER);
        gaming = false;
    }

    private void resize()
    {
        if (gaming)
        {
            ; //git config --global user.email "you@example.com"
        }
        else
        {
            int widthDiv2 = frame.getWidth() >> 1;
            int heightDiv2 = frame.getHeight() >> 1;
            controls.setBounds(widthDiv2 >> 1, heightDiv2 >> 1, widthDiv2, heightDiv2);
        }
    }
}
