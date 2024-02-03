package bubble.test.ex03;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

/*플레이어
 * 플레이어의 행동 방향 : 위, 아래, 왼쪽, 오른쪽인터페이스 구현 -> 가지고 있는 메서드 구현해야 함
 * 뉴할 수 있고 게임에 존재함 -> 추상메서드를 가질 수 없음 / 추상메서드를 구현해야하는 의무가 있음
 */

//private라 접근하기 위해
@Getter
@Setter
public class Player extends JLabel implements Moveable {
    // 위치 상태
    private int x;
    private int y;

    // 움직임 상태
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;
    private ImageIcon playerR, playerL;
    public Player() {
        initObject();
        initSetting();
    }

    public boolean isLeft(){
        return left;
    }

    public boolean isRight(){
        return right;
    }

    private void initObject() {
        playerR = new ImageIcon("src/_images/playerR.png");
        playerL = new ImageIcon("src/_images/playerL.png");
    }

    private void initSetting() {
        x = 55; // 오른쪽 이동
        y = 535; // 왼쪽 이동

        // 지금은 아무것도 안하고 있기 때문에 모두 false
        left = false;
        right = false;
        up = false;
        down = false;

        this.setIcon(playerR);
        setSize(50, 50);
        setLocation(x, y);
    }

    // 이벤트 핸들러
    @Override
    public void left() {
        System.out.println("left");
        left = true;
        new Thread(()-> {
            while(true) {
                setIcon(playerL); // 바라보는 방향의 이미지 설정
                x = x - 1;
                setLocation(x, y);
                //try/catch로 묶어야함
                try {
                    Thread.sleep(10); // o.01초
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void right() {
        System.out.println("right");
        right = true;
        new Thread(()-> {
            while(true) {
                setIcon(playerR); // 바라보는 방향의 이미지 설정
                x = x + 1;
                setLocation(x, y);
                //try/catch로 묶어야함
                try {
                    Thread.sleep(10); // o.01초
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void up() {
        y = y - 10;
        setLocation(x, y);
    }

    @Override
    public void down() {
        y = y + 10;
        setLocation(x, y);
    }
}
