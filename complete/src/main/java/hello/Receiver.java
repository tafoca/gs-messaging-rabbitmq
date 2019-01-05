package hello;

import java.util.concurrent.CountDownLatch;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        latch.countDown();
    }

    String genStr(String...s){
        String res="";
        for (int i=0; i < s.length;i++) res += " | " + s[i] + " | ";
        return res;
    }

    public void receiveMessage1(String...messages) {
        System.out.println("Received  << " + genStr(messages) + " >>");
        latch.countDown();
    }


    public CountDownLatch getLatch() {
        return latch;
    }

}
