package bubble.test.ex01;

import javax.swing.*;

public class BubbleFrame extends JFrame {

    private JLabel backgroundMap;
    private Player player; // 붙여널기

    public BubbleFrame() {
        initObject();
        initSetting();
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

    private  void initSetting() {
        setSize(1000,640);
        setLayout(null); // absolute 레이아웃(자유롭게 그림을 그릴 수 있음)
        setLocationRelativeTo(null); // 창이 가운데 뜸
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
