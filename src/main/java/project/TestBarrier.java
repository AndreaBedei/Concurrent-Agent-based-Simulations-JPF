package project;

// Per far partire il test: ./gradlew runTestBarrierVerify 
public class TestBarrier {
    public static void main(String[] args) throws Exception {
        Barrier barrier = new BarrierImpl(3);
        Runnable r = new Runnable() {
            @Override
            public void run() {
                barrier.waitBeforeActing();
            }
        };

        Thread th1 = new Thread(r);
        Thread th2 = new Thread(r); 
        Thread th3 = new Thread(r);  
        th1.start();
        th2.start();
        th3.start();
        th1.join();
        th2.join();
        th3.join();
    }
}
