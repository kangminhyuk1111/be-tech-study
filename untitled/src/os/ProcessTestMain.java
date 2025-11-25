package os;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/*
* 프로세스의 정보를 얻을 수 있다.
* */
public class ProcessTestMain {
  public static void main(String[] args) throws Exception {
    String command = System.getProperty("os.name").toLowerCase().contains("win")
        ? "cmd /c dir"
        : "ls -la";

    System.out.println("=== 프로세스 실행 ===");
    System.out.println("명령어: " + command);
    System.out.println();

    Process p = Runtime.getRuntime().exec(command);

    System.out.println("프로세스 ID: " + p.pid());
    System.out.println("프로세스 상태: 실행 중");
    System.out.println();

    // ★ 중요: Windows는 CP949 인코딩 사용
    Charset charset = System.getProperty("os.name").toLowerCase().contains("win")
        ? Charset.forName("CP949")
        : StandardCharsets.UTF_8;

    BufferedReader reader = new BufferedReader(
        new InputStreamReader(p.getInputStream(), charset)
    );

    String line;
    System.out.println("=== 명령어 실행 결과 ===");
    while ((line = reader.readLine()) != null) {
      System.out.println(line);
    }

    int exitCode = p.waitFor();
    System.out.println();
    System.out.println("종료 코드: " + exitCode);
    System.out.println("프로세스 상태: 종료됨");
  }
}
