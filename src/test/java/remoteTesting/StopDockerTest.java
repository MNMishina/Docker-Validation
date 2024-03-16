package remoteTesting;

import org.testng.Assert;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

public class StopDockerTest {

    public void stopBatFile() throws IOException, InterruptedException {
        boolean flag = false;
        Runtime runtime = Runtime.getRuntime();
        runtime.exec("cmd /c start docker-down.bat");

        String fileOutput = "output.txt";
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 45);
        long stopNow = calendar.getTimeInMillis();
        Thread.sleep(3000);

        while (System.currentTimeMillis() < stopNow) {
            if (flag) {
                break;
            }
            BufferedReader reader = new BufferedReader(new FileReader(fileOutput));
            String currentLine = reader.readLine();
            while (currentLine != null && !flag) {
                if (currentLine.contains("stopped: selenium-node")) {
                    System.out.println("Found the text about stopping the node in output file");
                    flag = true;
                    break;
                }
                currentLine = reader.readLine();
            }
            reader.close();
        }

        Assert.assertTrue(flag);
    }
}

