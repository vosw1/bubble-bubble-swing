package bubble.test.ex16;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

@Getter
@Setter
public class Bubble extends JLabel implements Moveable {

    // 의존성 컴포지션
    private Player player;
    private BackgrundBubbleService backgrundBubbleService;
    private BubbleFrame mContext;
    private Enemy enemy;

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
    public Bubble(BubbleFrame mContext) {
        this.mContext = mContext; // 모든 정보를 다 가지고 옴
        this.player = mContext.getPlayer(); // 플레이어 정보 가지고 오기
        this.enemy = mContext.getEnemy(); // 적군 정보 가지고 오기 - 적군이 여러명이면 컬렉션으로 받아오면
        initObject();
        initSetting();
    }

    private void initObject() {
        try {
            bubble = new ImageIcon("src/_images/bubble.png");
            bubbled = new ImageIcon("src/_images/bubbled.png");
            bomb = new ImageIcon("src/_images/bomb.png");
        } catch (Exception e) {
            e.printStackTrace();
        }

        backgrundBubbleService = new BackgrundBubbleService(this);
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

    @Override
    public void left() {
        //System.out.println("왼쪽으로 발사");
        // 상태
        left = true;

        // 범위를 줘야해서 for문 사용
        for (int i = 0; i < 400; i++) {
            x--; // 왼쪽 이동은 -
            setLocation(x, y);

            if (backgrundBubbleService.leftWall()) {
                // 상태 변동 - 쓰든 안쓰든 상태를 변동해주는 것이 좋음
                left = false;
                break;
            }

            // 40~60의 범위의 절대 값
            if ((Math.abs(x - enemy.getX()) > 45 && Math.abs(x - enemy.getX()) < 60)
                    && Math.abs(y - enemy.getY()) > 0 && Math.abs(y - enemy.getY()) < 50) {
                System.out.println("물방울이 적군과 충돌했습니다");
                if (enemy.getState() == 0) {
                    attack();
                    break;
                }
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
        //System.out.println("오른쪽으로 발사");
        // 상태
        right = true;

        // 범위를 줘야해서 for문 사용
        for (int i = 0; i < 400; i++) {
            x++; // 오른쪽 이동은 +
            setLocation(x, y);

            if (backgrundBubbleService.rightWall()) {
                right = false;
                break;
            }

            // 40~60의 범위의 절대 값
            if ((Math.abs(x - enemy.getX()) > 45 && Math.abs(x - enemy.getX()) < 60)
                    && Math.abs(y - enemy.getY()) > 0 && Math.abs(y - enemy.getY()) < 50) {
                System.out.println("물방울이 적군과 충돌했습니다");
                if (enemy.getState() == 0) {
                    attack();
                    break;
                }
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
    public void up() {
        // 상태
        up = true;

        // 계속 올라가면 됨
        while (up) {
            y--; // 위로 이동은 -
            setLocation(x, y);

            if (backgrundBubbleService.topWall()) {
                up = false;
                break; // 상태 조건을 확인하지 않고 가니까 있는게 더 효율적임
            }

            try {
                if (state == 0) { // 물방울 상태
                    // 속도가 너무 빠르니까 조정해주기
                    Thread.sleep(1);
                } else { // 적군을 가둔 상태
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        clearBubble();
    }

    // 다른데서 호출할 일이 없으면 private
    // 다른데서 호출할 일이 있으면 public
    public void clearBubble() {
        try {
            Thread.sleep(3000);
            setIcon(bomb); // bubble 부분만 다시 그려짐
            Thread.sleep(500);
            // 다시 그리려면 bubbleFrame의 정보가 필요함
            mContext.remove(this); // 메모리에서 사라지고 그림은 남아있음
            mContext.repaint(); // 메모리에 있는 것만 전체 다시 그림
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 적군을 가두는 것
    @Override
    public void attack() {
        state = 1; // 적군을 가둔 상태
        enemy.setState(1); // 적군이 갇힌 상태
        setIcon(bubbled);
        mContext.remove(enemy); // 적군을 메모리에서 지우기
        mContext.repaint(); // 다시 그리기
    }
}