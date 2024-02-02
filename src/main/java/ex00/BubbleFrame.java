package ex00;

import javax.swing.*;

public class BubbleFrame extends JFrame {
    public BubbleFrame() {
        setSize(1000, 640); // 화면 크기
        setVisible(true); // 화면에 보여주기
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 종료버튼 누르면 자동 종료 설정
    }

    public static void main(String[] args) {
        new BubbleFrame();
    }
}
