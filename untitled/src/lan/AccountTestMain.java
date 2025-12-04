package lan;

import java.util.ArrayList;
import java.util.List;

public class AccountTestMain {

  public static void main(String[] args) {
    System.out.println("=== 은행 계좌 멀티스레드 테스트 시작 ===\n");

    BankAccount instance1 = BankAccount.getInstance();
    BankAccount instance2 = BankAccount.getInstance();
    BankAccount instance3 = BankAccount.getInstance();

    // 싱글톤 검증
    System.out.println("싱글톤 검증:");
    System.out.println("instance1 == instance2: " + (instance1 == instance2));
    System.out.println("instance2 == instance3: " + (instance2 == instance3));
    System.out.println("instance1 hashCode: " + System.identityHashCode(instance1));
    System.out.println("instance2 hashCode: " + System.identityHashCode(instance2));
    System.out.println("instance3 hashCode: " + System.identityHashCode(instance3));
    System.out.println();

    // 초기 잔액
    System.out.println("초기 잔액: " + instance1.getBalance() + "원\n");

    List<Thread> threads = new ArrayList<>();

    // 입금 스레드 3개
    threads.add(new Thread(() -> {
      System.out.println("[Thread-" + Thread.currentThread().getId() + "] 입금 1000원 시도");
      instance1.deposit(1000);
      System.out.println("[Thread-" + Thread.currentThread().getId() + "] 입금 완료");
    }, "Deposit-1"));

    threads.add(new Thread(() -> {
      System.out.println("[Thread-" + Thread.currentThread().getId() + "] 입금 1000원 시도");
      instance2.deposit(1000);
      System.out.println("[Thread-" + Thread.currentThread().getId() + "] 입금 완료");
    }, "Deposit-2"));

    threads.add(new Thread(() -> {
      System.out.println("[Thread-" + Thread.currentThread().getId() + "] 입금 1000원 시도");
      instance3.deposit(1000);
      System.out.println("[Thread-" + Thread.currentThread().getId() + "] 입금 완료");
    }, "Deposit-3"));

    // 출금 스레드 3개
    threads.add(new Thread(() -> {
      System.out.println("[Thread-" + Thread.currentThread().getId() + "] 출금 1000원 시도");
      boolean result = instance1.withDraw(1000);
      System.out.println("[Thread-" + Thread.currentThread().getId() + "] 출금 " + (result ? "성공" : "실패"));
    }, "Withdraw-1"));

    threads.add(new Thread(() -> {
      System.out.println("[Thread-" + Thread.currentThread().getId() + "] 출금 1000원 시도");
      boolean result = instance2.withDraw(1000);
      System.out.println("[Thread-" + Thread.currentThread().getId() + "] 출금 " + (result ? "성공" : "실패"));
    }, "Withdraw-2"));

    threads.add(new Thread(() -> {
      System.out.println("[Thread-" + Thread.currentThread().getId() + "] 출금 1000원 시도");
      boolean result = instance3.withDraw(1000);
      System.out.println("[Thread-" + Thread.currentThread().getId() + "] 출금 " + (result ? "성공" : "실패"));
    }, "Withdraw-3"));

    System.out.println("=== 모든 스레드 시작 ===\n");
    threads.forEach(Thread::start);

    // 모든 스레드가 완료될 때까지 대기
    threads.forEach(thread -> {
      try {
        thread.join();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });

    System.out.println("\n=== 모든 스레드 완료 ===\n");

    // 최종 결과
    System.out.println("최종 잔액: " + instance1.getBalance() + "원");
    System.out.println("예상 잔액: 0원 (입금 3000원 - 출금 3000원)");
    System.out.println();

    // 검증
    System.out.println();
    if (instance1.getBalance() == 0) {
      System.out.println("✅ 테스트 성공: 동시성 제어가 올바르게 작동했습니다!");
    } else {
      System.out.println("❌ 테스트 실패: 잔액이 예상과 다릅니다!");
    }
  }
}
