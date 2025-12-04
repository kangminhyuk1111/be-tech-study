package lan;

public class StringTestMain {

  public static void main(String[] args) {
    StringBuilder sb = new StringBuilder();
    StringBuffer sf = new StringBuffer();

    sb.append(1);
    sf.append(1);

    // StringBuilder는 메서드에 동기화 로직이 없음
    // StringBuffer는 메서드에 동기회 로직인 synchronized 구문이 추가 되어 있어서 멀티스레딩에서 안전함
  }
}
