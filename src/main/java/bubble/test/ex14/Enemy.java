package bubble.test.ex14;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

/*적군의 방향
 * 플레이어의 행동 방향 : 위, 아래, 왼쪽, 오른쪽인터페이스 구현 -> 가지고 있는 메서드 구현해야 함
 * 뉴할 수 있고 게임에 존재함 -> 추상메서드를 가질 수 없음 / 추상메서드를 구현해야하는 의무가 있음
 */

//private라 접근하기 위해
@Getter
@Setter
public class Enemy extends JLabel implements Moveable {

    private BubbleFrame mContext;

    // 위치 상태
    private int x;
    private int y;

    // 적군의 방향
    private EnemyWay enemyWay;

    // 움직임 상태
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;

    // 적군의 속도 상태
    private final int SPEED = 3; // 상수명은 대문자로
    private final int JUMPSPEED = 1; // up, down의 스피드

    private ImageIcon enemyR, enemyL;

    public Enemy(BubbleFrame mContext) {
        this.mContext = mContext;
        initObject();
        initSetting();
        initBackgrundEnemyService();
    }

    private void initObject() {
        enemyR = new ImageIcon("src/_images/enemyR.png");
        enemyL = new ImageIcon("src/_images/enemyL.png");
    }

    private void initSetting() {
        // 천장에서 시작
        x = 480; // 오른쪽 이동
        y = 178; // 왼쪽 이동

        // 움직임 상태 : 지금은 아무것도 안하고 있기 때문에 모두 false
        left = false;
        right = false;
        up = false;
        down = false;

        enemyWay = EnemyWay.RIGHT; // 초기설정 : 오른쪽 바라보기
        this.setIcon(enemyR);
        setSize(50, 50);
        setLocation(x, y);
    }

    private void initBackgrundEnemyService() {

        //new Thread(new BackgrundEnemyService(this)).start();
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    // 이벤트 핸들러
    @Override
    public void left() {
        //System.out.println("left");
        enemyWay = enemyWay.LEFT;
        left = true;
        new Thread(() -> {
            while (left) {
                setIcon(enemyL); // 바라보는 방향의 이미지 설정
                x = x - SPEED;
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
        //System.out.println("right");
        enemyWay = enemyWay.RIGHT;
        right = true;
        new Thread(() -> {
            while (right) {
                setIcon(enemyR); // 바라보는 방향의 이미지 설정
                x = x + SPEED;
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

    // 두가지 이상의 상태를 동시에 가질 수 있음
    // up + left or up + right
    // 스레드가 필요함
    @Override
    public void up() {
        //System.out.println("up");
        up = true;
        new Thread(() -> {
            for (int i = 0; i < 120 / JUMPSPEED; i++) {
                y = y - JUMPSPEED; // 너무 빠르면 안되니까 sleep이 필요함
                setLocation(x, y);
                try {
                    Thread.sleep(5);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            up = false; // up의 상태가 끝났음
            down(); // up과 down이 이어짐
            // up과 down의 메서드를 합쳐서 jump라고 만들어버리면 down만 못함
            //down이 없으면 윗층에서 떨어지지 못함
        }).start();
    }

    @Override
    public void down() { // 하강 : 올라간 만큼 내려오기
        //System.out.println("down");
        down = true;
        new Thread(() -> {
            while (down) {
                y = y + JUMPSPEED; // 너무 빠르면 안되니까 sleep이 필요함
                setLocation(x, y);
                try {
                    Thread.sleep(3);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            down = false;
        }).start();
    }
}
