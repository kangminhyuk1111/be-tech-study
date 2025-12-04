package lan;

import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {

  private static class Holder {
    private static final BankAccount INSTANCE = new BankAccount();
  }

  private final ReentrantLock lock = new ReentrantLock();

  private Integer balance = 0;

  public static BankAccount getInstance() {
    return Holder.INSTANCE;
  }

  public void deposit(Integer amount) {
    lock.lock();
    try {
      balance += amount;
    } finally {
      lock.unlock();
    }
  }

  public boolean withDraw(Integer amount) {
    lock.lock();
    try {
      if (balance >= amount) {
        balance -= amount;
        return true;
      }

      return false;
    } finally {
      lock.unlock();
    }
  }

  public Integer getBalance() {
    return balance;
  }
}
