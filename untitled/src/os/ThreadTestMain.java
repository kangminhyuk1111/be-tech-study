package os;

public class ThreadTestMain {

  public static void main(String[] args) throws InterruptedException {
    Thread thread1 = new Thread(new Counter());
    Thread thread2 = new Thread(new Counter());

    thread1.start();
    thread2.start();

    new TestThread("test1").start();
    new TestThread("test2").start();
    new TestThread("test3").start();
    new TestThread("test4").start();
    new TestThread("test5").start();
  }

  /*
   * ex1
   * Runnable을 구현한 클래스 Counter를 만드세요
   * run() 메서드에서 1부터 10까지 출력하세요
   * 0.5초 간격으로 출력하세요
   * main에서 2개의 스레드를 동시에 실행하세요
   * 두 스레드가 동시에 돌아가는 것을 확인하세요
   * */
  static class Counter implements Runnable {

    Integer count = 0;

    @Override
    public void run() {
      for (int i = 0; i < 10; i++) {
        System.out.println(++count);
        try {
          Thread.sleep(500);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    }
  }

  /*
   * ex2
   * 스레드를 생성할 때 이름을 지정하세요 (생성자에서)
   * run() 메서드에서 현재 스레드 이름을 출력하세요
   * Thread.currentThread().getName() 사용
   * 5개의 스레드를 생성하고 모두 실행하세요
   * */
  static class TestThread extends Thread {

    private String name;

    public TestThread(String name) {
      this.name = name;
    }

    @Override
    public void run() {
      System.out.println(Thread.currentThread().getName() + ": 시작");
    }
  }
}
