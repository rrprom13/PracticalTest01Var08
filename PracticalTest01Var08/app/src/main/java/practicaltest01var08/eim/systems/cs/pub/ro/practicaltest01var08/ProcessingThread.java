package practicaltest01var08.eim.systems.cs.pub.ro.practicaltest01var08;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Random;

public class ProcessingThread extends Thread {

    private Context context = null;
    private boolean isRunning = true;

    private Random random = new Random();

    public ProcessingThread(Context context) {
        this.context = context;
    }

    @Override
    public void run() {
        Log.d("[ProcessingThread]", "Thread has started!");
        while (isRunning) {
            sendMessage();
            sleep();
        }
        Log.d("[ProcessingThread]", "Thread has stopped!");
    }

    private void sendMessage() {
        Intent intent = new Intent();
        int int1 = random.nextInt(100);
        int int2 = random.nextInt(100);
        int int3 = random.nextInt(100);
        int int4 = random.nextInt(100);
        intent.putExtra("first", String.valueOf(int1));
        intent.putExtra("second", String.valueOf(int2));
        intent.putExtra("third", String.valueOf(int3));
        intent.putExtra("fourth", String.valueOf(int4));
        intent.setAction("eimsimulationtest01.systems.cs.pub.ro.simulationtest01.string");
        Log.i("SERVICE", String.valueOf(int1) + " " + String.valueOf(int2) + " " + String.valueOf(int3) + " " + String.valueOf(int4));

        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}