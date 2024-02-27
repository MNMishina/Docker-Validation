package remoteTesting;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class FirefoxStandAloneTest {
    public static void main(String[] args) throws MalformedURLException {
        DesiredCapabilities cap = DesiredCapabilities.firefox();
        URL url = new URL("http://localhost:4444/wd/hub");
        RemoteWebDriver driver = new RemoteWebDriver(url, cap);
        driver.get("https://www.google.com");
        driver.manage().window().maximize();
        System.out.println(driver.getTitle());
    }
}
