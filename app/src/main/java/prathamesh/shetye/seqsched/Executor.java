package prathamesh.shetye.seqsched;

import android.util.Log;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by p.shetye on 4/21/15.
 */
public class Executor {
    private static final String LOG_TAG = "Executor";
    private static BlockingQueue<Integer> workerQueue;
    private static Executor sInstance;
    private static Worker sWorker;

    public static Executor getsInstance() {
        if (sInstance == null) {
            sInstance = new Executor();
        }
        return sInstance;
    }

    public static void addToQueue(int id) {
        if (workerQueue == null) {
            workerQueue = new LinkedBlockingQueue<>();
        }
        workerQueue.offer(id);
    }

    private static Integer takeFromQueue() {
        if (workerQueue == null) {
            return null;
        }
        try {
            return workerQueue.take();
        } catch (InterruptedException e) {
            Log.e(LOG_TAG, "workerQueue Exception", e);
            return null;
        }
    }
    private class Worker extends Thread {
        public Worker() {
            super("SequentialScheduler");
        }

        @Override
        public void run() {
            super.run();
            int count = takeFromQueue();

        }
    }
}
