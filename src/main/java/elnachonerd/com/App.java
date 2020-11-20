package elnachonerd.com;


import com.google.common.util.concurrent.ThreadFactoryBuilder;
import elnachonerd.com.hidden.UseCase;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newFixedThreadPool;

public class App {

    public static void main(String[] args) throws InterruptedException {
        final UseCase useCase = new UseCase();
        long startTime = System.currentTimeMillis();
        ExecutorService pool = newFixedThreadPool(10, new ThreadFactoryBuilder().setNameFormat("nachos-g-%d").build());
        Scheduler scheduler = Schedulers.from(pool);
        useCase.useCaseOne().subscribeOn(scheduler).subscribe(
            x -> {
                long endTime = System.currentTimeMillis();
                System.out.println("First Time " + (endTime-startTime));
            },
            Throwable::printStackTrace,
            () -> {
                long endTime = System.currentTimeMillis();
                System.out.println("Total Time " + (endTime-startTime));
            }
        );
        Thread.sleep(3000);
        pool.shutdownNow();
    }
}
