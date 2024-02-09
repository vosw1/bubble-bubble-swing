package bubble.test.ex13;

public interface Moveable {
    public abstract void left();

    public abstract void right();

    public abstract void up();

    // default를 사용하면 인터페이스도 몸체가 있는 메서드를 만들 수 있음
    // 다중 상속이 안되는 것이 많기 때문에 adapter보다는 default를 쓰는 것이 좋음
    default public void down() {
    }

    // 어택은 플레이어만 할 수 있음
    default public void attack() {
    }
}