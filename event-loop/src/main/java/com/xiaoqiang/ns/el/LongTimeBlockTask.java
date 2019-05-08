package com.xiaoqiang.ns.el;

import io.netty.channel.DefaultEventLoop;
import io.netty.channel.EventLoop;

import java.util.concurrent.TimeUnit;

/**
 * 长时间任务不应该放入EventLoop中，会阻塞后续所有任务
 */
public class LongTimeBlockTask {
    EventLoop executors = new DefaultEventLoop();

    public static void main(String[] args) {
        LongTimeBlockTask longTimeBlockTask = new LongTimeBlockTask();
        longTimeBlockTask.longTimeTask("1");
        longTimeBlockTask.longTimeTask("2");
    }

    public void longTimeTask(String taskName) {
        executors.schedule(() -> {
            System.out.println("task [" + taskName + "] start ...");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task[" + taskName + "] finished!");
        }, 1, TimeUnit.SECONDS);
    }
}
