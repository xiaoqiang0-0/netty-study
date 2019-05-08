package com.xiaoqiang.ns.el;

import io.netty.channel.DefaultEventLoop;
import io.netty.channel.EventLoop;
import io.netty.util.concurrent.ScheduledFuture;

import java.util.concurrent.TimeUnit;

public class ScheduledTask {

    EventLoop executors = new DefaultEventLoop();


    public static void main(String[] args) {
        ScheduledTask scheduledTask = new ScheduledTask();

        scheduledTask.laterTask();
        scheduledTask.fixedRateTask();
        scheduledTask.cancelTask();

        scheduledTask.executors.schedule(() -> {
            scheduledTask.executors.shutdownGracefully();
        }, 30, TimeUnit.SECONDS);
    }

    public void laterTask() {
        executors.schedule(() -> {
            System.out.println("服务已启动6秒...");
        }, 6, TimeUnit.SECONDS);
    }

    public void fixedRateTask() {
        executors.scheduleAtFixedRate(() -> {
            System.out.println("每6秒执行一次");
        }, 1, 6, TimeUnit.SECONDS);
    }

    public void cancelTask() {
        ScheduledFuture<?> future = executors.scheduleAtFixedRate(() -> {
            System.out.println("每3秒执行一次,启动20秒后停止...");
        }, 0, 3, TimeUnit.SECONDS);
        executors.schedule(() -> {
            future.cancel(true);
            System.out.println("每三秒执行一次的定时任务已被清除");
        }, 20, TimeUnit.SECONDS);
    }


}
