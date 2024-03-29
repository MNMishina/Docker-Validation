package remoteTesting;

import org.testng.Assert;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

public class StartDockerTest {

    public void startBatFile() throws IOException, InterruptedException {
        boolean flag = false;
        Runtime runtime = Runtime.getRuntime();
        runtime.exec("cmd /c start docker-up.bat");

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
                if (currentLine.contains("Node has been added")) {
                    System.out.println("Found the text about node in output file");
                    flag = true;
                    break;
                }
                currentLine = reader.readLine();
            }
            reader.close();
        }
        Assert.assertTrue(flag);
        runtime.exec("cmd /c start scale.bat");
        Thread.sleep(15000);
    }
}
