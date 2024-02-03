package bubble.test.ex01;

import javax.swing.*;

// 플레이어 만들기
public class Player extends JLabel {
    private int x;
    private int y;

    private ImageIcon playerR, playerL;

    public Player() {
        initObject();
        initSetting();
    }

    private void initObject() {
        playerR = new ImageIcon("src/_images/playerR.png");
        playerL = new ImageIcon("src/_images/playerL.png");
    }

    private void initSetting() {
        x = 55;
        y = 535;

        this.setIcon(playerR);
        setSize(50,50);
        setLocation(x, y);
    }


}
