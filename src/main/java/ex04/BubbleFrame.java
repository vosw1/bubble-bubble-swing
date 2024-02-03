package ex04;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BubbleFrame extends JFrame {

    private JLabel backgroundMap;
    private Player player; // 플레이어 객체

    public BubbleFrame() {
        initObject();
        initSetting();
        initListner();
        setVisible(true);
    }

    public static void main(String[] args) {
        new BubbleFrame();
    }

    private void initObject() {
        //Label 안에 이미지 넣기
        backgroundMap = new JLabel(new ImageIcon("src/_images/backgroundMap.png")); // 안보임
        setContentPane(backgroundMap); // JPanel = JLabel

        player = new Player(); // 플레이어 띄우기
        add(player); // 붙이기
    }

    private void initSetting() {
        setSize(1000, 640);
        setLayout(null); // absolute 레이아웃(자유롭게 그림을 그릴 수 있음)
        setLocationRelativeTo(null); // 창이 가운데 뜸
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initListner() {
        addKeyListener(new KeyAdapter() {// 키 리스너 등록
            /*
            3가지 메서드중 keyPressed만 필요함
            모두 다 사용하지 않으면 오류가 터짐 -> 어댑터 패턴 사용
            * */


            //키보드 이벤트 핸들러
            @Override
            public void keyPressed(KeyEvent e) {
                //System.out.println(e.getKeyCode());
                // switch문을 사용한 가정법
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        if (!player.isLeft()) {
                            player.left();
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (!player.isRight()) {
                            player.right();
                        }
                        break;
                    case KeyEvent.VK_UP:
                        if(!player.isUp() && !player.isDown()) { // up만 하면 down이 안됨
                            player.up();
                        }
                        break;
                }
            }
            //키보드 해제 이벤트 핸들러
            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        if (!player.isLeft()) {
                            player.left();
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (!player.isRight()) {
                            player.right();
                        }
                        break;
                    case KeyEvent.VK_UP:
                        player.up();
                        break;
                }
            }
        });
    }

}
