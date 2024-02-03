package bubble.test.ex05;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

// 백그라운드에서 플레이어를 확인하는 서비스
// 백그라운드에서 계속 플레이어(메인스레드-키보드 이벤트 처리)를 관찰
public class BackgrundPlayerService implements Runnable{

    private final Player player; // 플레이어의 정보 가져오기
    private BufferedImage image;
    // 생성자
    public BackgrundPlayerService(Player player) { // 컴포지션
        this.player = player;
        try {
            image = ImageIO.read(new File("src/_images/BackgroundMapService.png")); // 이미지로 읽기
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        // 플레이어의 위치(x좌표)에 따른 색상 확인 -> 플레이어의 정보가 필요함
        while (true) {
            // 플레이어의 좌표에 따른 생상
            Color leftcolor = new Color(image.getRGB(player.getX()-10, player.getY()+25));
            Color rightcolor = new Color(image.getRGB(player.getX()+50 +15, player.getY()+25));
            //System.out.println("leftcolor:" + leftcolor); //색상 확인하기
            //System.out.println("rightcolor:" + rightcolor); //색상 확인하기

            if(leftcolor.getRed() == 255 && leftcolor.getGreen() == 0 && leftcolor.getBlue() == 0) {
                System.out.println("왼쪽 벽에 충돌함");
            } else if (rightcolor.getRed() == 255 && rightcolor.getGreen() == 0 && rightcolor.getBlue() == 0) {
                System.out.println("오른쪽 벽에 충돌함");
            }
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
