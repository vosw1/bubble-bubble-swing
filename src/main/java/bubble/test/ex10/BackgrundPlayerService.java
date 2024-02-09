package bubble.test.ex10;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

// 백그라운드에서 플레이어를 확인하는 서비스
// 백그라운드에서 계속 플레이어(메인스레드-키보드 이벤트 처리)를 관찰
public class BackgrundPlayerService implements Runnable {

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
            // 플레이어의 좌표에 따른 색상
            Color leftColor = new Color(image.getRGB(player.getX() - 10, player.getY() + 25));
            Color rightColor = new Color(image.getRGB(player.getX() + 50 + 15, player.getY() + 25));
            // -2가 나온다는 뜻은 바닥에 색깔이 없이 흰색이라는 뜻
            int bottomColor = image.getRGB(player.getX() + 10, player.getY() + 50 + 5) // -1
                    + image.getRGB(player.getX() + 50, player.getY() + 50 + 5); // -1

            // 바닥 충돌 확인
            if (bottomColor != -2) {
                //System.out.println("bottom:" + bottomColor);
                //System.out.println("바닥에 충돌함");
                player.setDown(false);
            } else if (!player.isUp() && !player.isDown()) {
                player.down();
            }

            // 외벽 충돌 확인
            if (leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0) {
                //System.out.println("왼쪽 벽에 충돌함");
                player.setLeftWallCrash(true);
                player.setLeft(false); // 더이상 움직이지 않음
            } else if (rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0) {
                //System.out.println("오른쪽 벽에 충돌함");
                player.setRightWallCrash(true);
                player.setRight(false); // 더이상 움직이지 않음
            } else { // 둘 다 충돌한 것이 아닐 때 이동 가능
                player.setLeftWallCrash(false);
                player.setRightWallCrash(false);
            }
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
