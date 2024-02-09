package bubble.test.ex11;

import bubble.test.ex09.Bubble;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

// 백그라운드에서 버블을 확인하는 서비스
// 버블이 가는 방향에 따라 벽을 체크하는 서비스
// 버블이 생길때마다 메모리에 떠야 버블 관리가 가능함
public class BackgrundBubbleService { // 스레드가 돌면 안됨

    private final Bubble bubble; // 플레이어의 정보 가져오기
    private BufferedImage image;

    // 생성자
    public BackgrundBubbleService(Bubble bubble) { // 컴포지션
        this.bubble = bubble;
        try {
            image = ImageIO.read(new File("src/_images/BackgroundMapService.png")); // 이미지로 읽기
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 스레드 없이 boolean으로 충돌 감지하기
    public boolean leftWall() {
        return false;
    }

    public boolean rightWall() {
        return false;
    }

    public boolean topWall() {
        return false;
    }
}
