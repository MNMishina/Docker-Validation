package remoteTesting;

import org.testng.annotations.Test;

import java.io.IOException;

public class StartDockerTest {
    @Test
    public void startBatFile() throws IOException {
        Runtime runtime = Runtime.getRuntime();
        runtime.exec("cmd /c start docker-up.bat");
    }

}
