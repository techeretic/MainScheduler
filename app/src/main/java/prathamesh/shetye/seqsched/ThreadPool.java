package prathamesh.shetye.seqsched;

/**
 * Created by p.shetye on 4/20/15.
 */

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * Naive implementation of ThreadPool. Just for illustration
 */
public class ThreadPool
{
    private final BlockingQueue<Runnable> workerQueue;
    private volatile boolean shutdown;
    private Thread worker;
    public ThreadPool(int N) {
        workerQueue = new LinkedBlockingQueue<>();
        worker = new Worker("SequentialScheduler");
        worker.start();
    }

    public void addTask(Runnable r)
    {
        try {
            workerQueue.put(r);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void shutdown()
    {
        while (!workerQueue.isEmpty()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                //interruption
            }
        }
        shutdown = true;
        worker.interrupt();
    }

    private class Worker extends Thread
    {
        public Worker(String name)
        {
            super(name);
        }

        public void run()
        {
            while (!shutdown) {
                try {
                    //each thread wait for next runnable and executes it's run method
                    Runnable r = workerQueue.take();
                    r.run();
                } catch (InterruptedException e) {
                    //ignore
                }
            }
        }
    }

}

