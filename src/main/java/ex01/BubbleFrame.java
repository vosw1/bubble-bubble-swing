package ex01;

import javax.swing.*;

public class BubbleFrame extends JFrame {

    private JLabel backgroundMap;
    public BubbleFrame() {
        initObject();
        initSetting();
        setVisible(true);
    }

    public static void main(String[] args) {
        new BubbleFrame();
    }

    private void initObject() {
        backgroundMap = new JLabel(new ImageIcon("src/_images/backgroundMap.png")); // 안보임
        setContentPane(backgroundMap); // JPanel = JLabel
        //backgroundMap.setLocation(300, 300);
        //backgroundMap.setSize(100,100); //글씨 크기 조정
        //backgroundMap.setSize(1000,600);
        //add(backgroundMap); // JFrame에 JLabel 그리기 -> 위치가 없어서 안보임
    }

    private  void initSetting() {
        setSize(1000,640);
        setLayout(null); // absolute 레이아웃(자유롭게 그림을 그릴 수 있음)
        setLocationRelativeTo(null); // 창이 가운데 뜸
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
