package com.mini.core.util.thread;


import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.EventListener;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static java.lang.Runtime.getRuntime;

/**
 * 开启后台执行任务和定时任务
 *
 * @author xchao
 */
public final class ThreadExecutor implements EventListener, Serializable {
    private static final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2 * getRuntime().availableProcessors());
    private static final Map<String, ScheduledFuture<?>> futures = new HashMap<>();

    /**
     * 在后台线程执行一个任务
     *
     * @param runnable 任务内容
     */
    public static void post(@Nonnull Runnable runnable) {
        executor.execute(runnable);
    }

    /**
     * 在后台线程延时执行一个任务
     *
     * @param runnable 任务内容
     * @param delay    延时时间
     */
    public static void post(@Nonnull Runnable runnable, long delay) {
        post(runnable, delay, TimeUnit.MILLISECONDS);
    }

    /**
     * 在后台线程延时执行一个任务
     *
     * @param runnable 任务内容
     * @param delay    延时时间
     * @param unit     时间单位
     */
    public static void post(@Nonnull Runnable runnable, long delay, @Nonnull TimeUnit unit) {
        executor.schedule(runnable, delay, unit);
    }

    /**
     * 在后台线程延时执行一个可取消的任务
     *
     * @param id       定时器ID
     * @param runnable 任务内容
     * @param delay    延时时间
     */
    public static void post(@Nonnull String id, @Nonnull Runnable runnable, long delay) {
        post(id, runnable, delay, TimeUnit.MILLISECONDS);
    }

    /**
     * 在后台线程延时执行一个可取消的任务
     *
     * @param id       定时器ID
     * @param runnable 任务内容
     * @param delay    延时时间
     * @param unit     时间单位
     */
    public static void post(@Nonnull String id, @Nonnull Runnable runnable, long delay, @Nonnull TimeUnit unit) {
        futures.put(id, executor.schedule(() -> {
            try {
                runnable.run();
            } finally {
                futures.remove(id);
            }
        }, delay, unit));
    }

    /**
     * 开启一个定时任务，该任务不受任务的执行时间影响
     *
     * @param runnable 任务内容
     * @param delay    上次开始到下次开始的时间间隔
     * @param unit     时间单位
     */
    public static void postAtFixedRate(@Nonnull Runnable runnable, long delay, @Nonnull TimeUnit unit) {
        executor.scheduleAtFixedRate(runnable, 0, delay, unit);
    }

    /**
     * 开启一个定时任务，该任务不受任务的执行时间影响
     *
     * @param runnable     任务内容
     * @param initialDelay 第一次延迟的时间
     * @param delay        上次开始到下次开始的时间间隔
     */
    public static void postAtFixedRate(@Nonnull Runnable runnable, long initialDelay, long delay) {
        postAtFixedRate(runnable, initialDelay, delay, TimeUnit.MILLISECONDS);
    }

    /**
     * 开启一个定时任务，该任务不受任务的执行时间影响
     *
     * @param runnable     任务内容
     * @param initialDelay 第一次延迟的时间
     * @param delay        上次开始到下次开始的时间间隔
     * @param unit         时间单位
     */
    public static void postAtFixedRate(@Nonnull Runnable runnable, long initialDelay, long delay, @Nonnull TimeUnit unit) {
        executor.scheduleAtFixedRate(runnable, initialDelay, delay, unit);
    }

    /**
     * 开启一个可以取消定时任务，该任务不受任务的执行时间影响
     *
     * @param runnable     任务内容
     * @param initialDelay 第一次延迟的时间
     * @param delay        上次开始到下次开始的时间间隔
     */
    public static void postAtFixedRate(@Nonnull String id, @Nonnull Runnable runnable, long initialDelay, long delay) {
        postAtFixedRate(id, runnable, initialDelay, delay, TimeUnit.MILLISECONDS);
    }

    /**
     * 开启一个可以取消定时任务，该任务不受任务的执行时间影响
     *
     * @param runnable     任务内容
     * @param initialDelay 第一次延迟的时间
     * @param delay        上次开始到下次开始的时间间隔
     * @param unit         时间单位
     */
    public static void postAtFixedRate(@Nonnull String id, @Nonnull Runnable runnable, long initialDelay, long delay, @Nonnull TimeUnit unit) {
        futures.put(id, executor.scheduleAtFixedRate(() -> {
            try {
                runnable.run();
            } finally {
                futures.remove(id);
            }
        }, initialDelay, delay, unit));
    }

    /**
     * 开启一个定时任务，该任务延时会受任务执行时间的影响
     *
     * @param runnable 任务内容
     * @param delay    上次任务结束到下次任务开始的时间间隔
     */
    public static void postWithFixedDelay(@Nonnull Runnable runnable, long delay) {
        postWithFixedDelay(runnable, delay, TimeUnit.MILLISECONDS);
    }

    /**
     * 开启一个定时任务，该任务延时会受任务执行时间的影响
     *
     * @param runnable 任务内容
     * @param delay    上次任务结束到下次任务开始的时间间隔
     * @param unit     时间单位
     */
    public static void postWithFixedDelay(@Nonnull Runnable runnable, long delay, @Nonnull TimeUnit unit) {
        executor.scheduleWithFixedDelay(runnable, 0, delay, unit);
    }

    /**
     * 开启一个定时任务，该任务延时会受任务执行时间的影响
     *
     * @param runnable     任务内容
     * @param initialDelay 第一次延迟的时间
     * @param delay        上次任务结束到下次任务开始的时间间隔
     */
    public static void postWithFixedDelay(@Nonnull Runnable runnable, long initialDelay, long delay) {
        postWithFixedDelay(runnable, initialDelay, delay, TimeUnit.MILLISECONDS);
    }

    /**
     * 开启一个定时任务，该任务延时会受任务执行时间的影响
     *
     * @param runnable     任务内容
     * @param initialDelay 第一次延迟的时间
     * @param delay        上次任务结束到下次任务开始的时间间隔
     * @param unit         时间单位
     */
    public static void postWithFixedDelay(@Nonnull Runnable runnable, long initialDelay, long delay, @Nonnull TimeUnit unit) {
        executor.scheduleWithFixedDelay(runnable, initialDelay, delay, unit);
    }

    /**
     * 开启一个定时任务，该任务延时会受任务执行时间的影响
     *
     * @param id           定时任务唯一识别码
     * @param runnable     任务内容
     * @param initialDelay 第一次延迟的时间
     * @param delay        上次任务结束到下次任务开始的时间间隔
     */
    public static void postWithFixedDelay(@Nonnull String id, @Nonnull Runnable runnable, long initialDelay, long delay) {
        postWithFixedDelay(id, runnable, initialDelay, delay, TimeUnit.MILLISECONDS);
    }

    /**
     * 开启一个定时任务，该任务延时会受任务执行时间的影响
     *
     * @param id           定时任务唯一识别码
     * @param runnable     任务内容
     * @param initialDelay 第一次延迟的时间
     * @param delay        上次任务结束到下次任务开始的时间间隔
     * @param unit         时间单位
     */
    public static void postWithFixedDelay(@Nonnull String id, @Nonnull Runnable runnable, long initialDelay, long delay, @Nonnull TimeUnit unit) {
        futures.put(id, executor.scheduleWithFixedDelay(() -> {
            try {
                runnable.run();
            } finally {
                futures.remove(id);
            }
        }, initialDelay, delay, unit));
    }

    /**
     * 强制开启一个立即执行的线程<br/> 该线程无法取消，不受线程池管理约束
     *
     * @param runnable 任务内容
     */
    public static void thread(Runnable runnable) {
        new Thread(runnable).start();
    }

    /**
     * 是否在指定在定时任务正在运行
     *
     * @param id 定时任务唯一识别码
     * @return true-是
     */
    public static boolean has(@Nonnull String id) {
        return futures.containsKey(id);
    }

    /**
     * 取消未执行的延时任务
     *
     * @param id 定时器ID
     */
    public static void cancel(@Nonnull final String id) {
        final ScheduledFuture<?> future = futures.get(id);
        if (future instanceof RunnableScheduledFuture) {
            executor.remove((Runnable) future);
            future.cancel(false);
        }
    }
}