package elnachonerd.com;


import elnachonerd.com.hidden.UseCase;

public class App {

    public static void main(String[] args) throws InterruptedException {
        final UseCase useCase = new UseCase();
        long startTime = System.currentTimeMillis();
        useCase.useCaseOne().subscribe(
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
    }
}
