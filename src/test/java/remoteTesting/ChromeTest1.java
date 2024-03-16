package remoteTesting;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ChromeTest1 {
    @BeforeTest
    public void startDockerScale() throws IOException, InterruptedException {
        File file = new File("output.txt");
        if (file.delete()) {
            System.out.println("Output file deleted successfully !");
        }
        StartDockerTest sd = new StartDockerTest();
        sd.startBatFile();
    }

    @Test
    public void test1() throws MalformedURLException {

        DesiredCapabilities cap = DesiredCapabilities.chrome();
        URL url = new URL("http://localhost:4444/wd/hub");
        RemoteWebDriver driver = new RemoteWebDriver(url, cap);
        driver.get("https://www.google.com");
        System.out.println(driver.getTitle());
    }
    @AfterTest
    public void stopDockerDeleteFile() throws IOException, InterruptedException {
        StopDockerTest sdt = new StopDockerTest();
        sdt.stopBatFile();
    }
}
