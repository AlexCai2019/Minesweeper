package minesweeper.frontend;

import minesweeper.backend.Core;

import java.awt.*;
import java.awt.event.*;

public class Display
{
    private final Frame frame = new Frame("踩地雷");
    private final Panel controls = new Panel(new BorderLayout(0, 10));
    private final Font comicSans = new Font("Comic Sans", Font.PLAIN, 24);
    private final Core core = new Core();
    private boolean gaming;
    private Button[][] map;
    private final Panel mapPanel = new Panel(null);
    private final Label timeLabel = new Label("0: 0.000", Label.CENTER);
    private final Thread timer = new Thread(() ->
    {
        long start = System.currentTimeMillis();
        long duration;
        while (gaming)
        {
            duration = System.currentTimeMillis() - start;
            timeLabel.setText(duration / 60000 + ": " + (duration / 1000) % 60 + "." + String.format("%03d", duration % 1000));
        }
    });

    public Display()
    {
        reset();
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
                gaming = false;
                if (timer.isAlive())
                {
                    try
                    {
                        timer.join();
                    }
                    catch (InterruptedException ex)
                    {
                        ex.printStackTrace();
                    }
                }
                frame.dispose();
                System.exit(0);
            }
        });

        Panel edits = new Panel(new GridLayout(3,2));
        Label[] labels =
        {
            new Label("列數(rows)", Label.CENTER),
            new Label("行數(columns)", Label.CENTER),
            new Label("地雷數(mines)", Label.CENTER)
        };
        TextField[] texts = { new TextField(),new TextField(),new TextField() };
        for (int i = 0; i < 3; i++)
        {
            labels[i].setFont(comicSans);
            edits.add(labels[i]);

            texts[i].setFont(comicSans);
            edits.add(texts[i]);
        }

        Button send = new Button("開始");
        send.setFont(comicSans);
        send.addActionListener(e ->
        {
            core.acceptMapData(texts[0].getText(), texts[1].getText(), texts[2].getText());
            frame.remove(controls);
            game(); //開始遊戲
        });

        controls.add(edits, BorderLayout.CENTER);
        controls.add(send, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void game()
    {
        frame.setLayout(new BorderLayout());
        int rows = core.getRow();
        int columns = core.getColumn();
        map = new Button[rows][columns];

        mapPanel.setLayout(new GridLayout(rows, columns));
        frame.add(mapPanel, BorderLayout.CENTER);
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                map[i][j] = new Button();
                int finalI = i;
                int finalJ = j;
                map[i][j].addActionListener(e ->
                {
                    int near = core.near(finalI, finalJ);
                    if (near == -1)
                        map[finalI][finalJ].setLabel("Explosion");
                    else
                        map[finalI][finalJ].setLabel(Integer.toString(near));
                });
                mapPanel.add(map[i][j]);
            }
        }

        timeLabel.setFont(comicSans);
        frame.add(timeLabel, BorderLayout.NORTH);

        frame.revalidate();
        core.generateMap(); //後端生成地圖
        gaming = true;
        timer.start(); //開始計時
    }

    //回到設定畫面
    private void reset()
    {
        frame.setLayout(null);
        frame.add(controls);
        gaming = false;
    }

    private void resize()
    {
        if (!gaming)
        {
            int width = frame.getWidth();
            int height = frame.getHeight();
            controls.setBounds(width >> 2, height >> 2, width >> 1, height >> 1);
            controls.revalidate();
        }
    }
}
