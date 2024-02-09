package bubble.test.ex11;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

// 백그라운드에서 물방울을 확인하는 서비스
// 물방울이 생길 때마다 메모리에 떠야 물방울 관리가 가능함

public class BackgrundBubbleService {
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


    // 벽에 충돌을 체크하기
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