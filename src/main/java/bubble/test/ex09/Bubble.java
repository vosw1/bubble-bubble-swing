package bubble.test.ex09;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

@Getter
@Setter
public class Bubble extends JLabel {

    // 의존성 컴포지션
    private Player player;

    // 위치 상태 = 플레이어의 위치와 동일해야 함 / 플레이어에 의존적
    private int x;
    private int y;

    // 움직임 상태
    private boolean left;
    private boolean right;
    private boolean up;

    // 적군을 맞춘 상태
    private int state; // 0이 물방울, 1이 적을 가둔 물방울
    private ImageIcon bubble; // 물방울
    private ImageIcon bubbled; // 적을 가둔 물방울
    private ImageIcon bomb; // 물방울이 터진 상태

    public Bubble(Player player) { // 플레이어에로부터 가져와야 함
        this.player = player; // x,y좌표를 만들어낼 수 있음
        initObject();
        initSetting();
    }

    private void initObject() {
        bubble = new ImageIcon("src/_images/bubble.png");
        bubbled = new ImageIcon("src/_images/bubbled.png");
        bomb = new ImageIcon("src/_images/bomb.png");
    }

    private void initSetting() {
        left = false;
        right = false;
        up = false;

        x = player.getX();
        y = player.getY();

        setIcon(bubble); // 초기에는 버블의 상태
        setSize(50, 50); // 버블의 크기 = 플레이어의 크기

        state = 0; // 초기 물방울의 상태는 0
    }
}