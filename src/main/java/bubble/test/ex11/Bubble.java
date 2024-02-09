package bubble.test.ex11;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

@Getter
@Setter
public class Bubble extends JLabel implements Moveable {

    // 의존성 컴포지션
    private Player player;
    private BackgrundBubbleService backgrundBubbleService;

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

    // 생성자
    public Bubble(Player player) {
        this.player = player;
        initObject();
        initSetting();
        initThread();
    }

    private void initObject() {
        bubble = new ImageIcon("src/_images/bubble.png");
        bubbled = new ImageIcon("src/_images/bubbled.png");
        bomb = new ImageIcon("src/_images/bomb.png");

        backgrundBubbleService = new BackgrundBubbleService();
    }

    private void initSetting() {
        left = false;
        right = false;
        up = false;

        // player의 위치 정보 받아오기
        x = player.getX();
        y = player.getY();
        setLocation(x, y);

        setIcon(bubble); // 초기에는 버블의 상태
        setSize(50, 50); // 플레이어의 크기와 동일

        state = 0; // 초기 물방울의 상태는 0
    }

    private void initThread() {
        System.out.println("스레드 시작");
        // 버블은 방향이 왼쪽, 오른쪽 뿐, 동시 동작 없음 -> 스레드 하나만 필요함
        new Thread(() -> {
            if (player.getPlayerWay() == PlayerWay.LEFT) {
                left();
            } else {
                right();
            }
        }).start();
    }

    @Override
    public void left() {
        System.out.println("왼쪽으로 발사");
        // 상태
        left = true;

        // 범위를 줘야해서 for문 사용
        for (int i = 0; i < 400; i++) {
            x--; // 왼쪽 이동은 -
            setLocation(x, y);

            if (backgrundBubbleService.leftWall()) {
                break;
            }
            // 속도가 너무 빠르니까 조정해주기
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        up(); // 왼쪽으로 가다가 위로 올라가기
    }

    @Override
    public void right() {
        System.out.println("오른쪽으로 발사");
        // 상태
        right = true;

        // 범위를 줘야해서 for문 사용
        for (int i = 0; i < 400; i++) {
            x++; // 오른쪽 이동은 +
            setLocation(x, y);

            // 속도가 너무 빠르니까 조정해주기
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        up(); // 왼쪽으로 가다가 위로 올라가기
    }

    @Override
    public void up() {
        // 상태
        up = true;

        // 계속 올라가면 됨
        while (up) {
            y--; // 위로 이동은 -
            setLocation(x, y);

            // 속도가 너무 빠르니까 조정해주기
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}