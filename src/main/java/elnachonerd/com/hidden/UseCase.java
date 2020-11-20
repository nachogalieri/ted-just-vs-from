package elnachonerd.com.hidden;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class UseCase {
    public String lazyGreeting() throws InterruptedException {
        Thread.sleep(1000);
        return "Hello world.";
    }

    public String getGreeting() {
        return "Hello world.";
    }


    public Observable<String> useCaseOne() throws InterruptedException {
        return Observable.just(lazyGreeting());
    }

    public Observable<String> useCaseTwo() throws InterruptedException {
        return Observable.fromCallable(this::lazyGreeting);
    }

    public Observable<String> useCaseThree() throws InterruptedException {
        return Observable.zip(useCaseOne(), useCaseTwo(), (s, s2) -> s);
    }

    public Observable<String> useCaseFour() throws InterruptedException {
        return Observable.zip(useCaseTwo(), useCaseTwo(), (s, s2) -> s);
    }

    public Observable<String> useCaseFive() throws InterruptedException {
        return Observable.zip(useCaseOne().subscribeOn(Schedulers.newThread()), useCaseTwo().subscribeOn(Schedulers.newThread()), (s, s2) -> s);
    }

    public Observable<String> useCaseSix() throws InterruptedException {
        return Observable.zip(useCaseTwo().subscribeOn(Schedulers.newThread()), useCaseTwo().subscribeOn(Schedulers.newThread()), (s, s2) -> s);
    }
}
