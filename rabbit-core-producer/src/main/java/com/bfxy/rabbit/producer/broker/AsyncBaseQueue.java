package com.bfxy.rabbit.producer.broker;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * 线程池
 * @Author Mengdexin
 * @date 2022 -04 -18 -22:44
 */
@Slf4j
public class AsyncBaseQueue {
    private static final int THREAD_SIZE = Runtime.getRuntime().availableProcessors();

    private static final int QUEUE_SIZE = 10000;

    public static ExecutorService executor =
            new ThreadPoolExecutor(20,
                    200,
                    60L,
                    TimeUnit.SECONDS,
                    new ArrayBlockingQueue<>(20),
                    Executors.defaultThreadFactory(),
                    new ThreadPoolExecutor.AbortPolicy());

    private static ExecutorService sendAsync =
            new ThreadPoolExecutor(THREAD_SIZE,
                    THREAD_SIZE,
                    60L,
                    TimeUnit.SECONDS,
                    new ArrayBlockingQueue<Runnable>(QUEUE_SIZE),
                    new ThreadFactory() {
                        @Override
                        public Thread newThread(Runnable r) {
                            Thread thread = new Thread();
                            thread.setName("rabbitmq_client_async_sender");
                            return thread;
                        }
                    },
                    new RejectedExecutionHandler() {
                        @Override
                        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                            log.error("async sender is error rejected, runnable: {}, executor: {}", r, executor);
                        }
                    });
    public static void submit(Runnable runnable) {
        executor.submit(runnable);
    }

}
