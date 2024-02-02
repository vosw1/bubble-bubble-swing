package ex02;

import javax.swing.*;

/*플레이어
* 플레이어의 행동 방향 : 위, 아래, 왼쪽, 오른쪽인터페이스 구현 -> 가지고 있는 메서드 구현해야 함
* 뉴할 수 있고 게임에 존재함 -> 추상메서드를 가질 수 없음 / 추상메서드를 구현해야하는 의무가 있음
*/

public class Player extends JLabel implements Moveable {
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


    @Override
    public void left() {

    }

    @Override
    public void right() {

    }

    @Override
    public void up() {

    }

    @Override
    public void down() {

    }
}
