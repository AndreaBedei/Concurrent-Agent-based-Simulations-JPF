package project;

// Per far partire il test: ./gradlew runTestReadersWritersVerify 
public class TestReadersWriters {
	
	private static volatile Boolean stopRequested = false;
	private static StopMonitor stopMonitor = new StopMonitor();

    public TestReadersWriters(){

    }

	// Methods synchronized useful to stop the simulation after button stop pressed.
	public static boolean isStopped(){
		stopMonitor.requestRead();
		boolean state = stopRequested;
		stopMonitor.releaseRead();
		return state;
	}

	public static void stop(){
		stopMonitor.requestWrite();
		stopRequested = true;
		stopMonitor.releaseWrite();
	}


    public static void main(String[] args) throws Exception {
        Runnable rCheck = new Runnable() {
            @Override
            public void run() {
                isStopped();
            }
        };

        Thread th0 = new Thread(rCheck);
        Thread th1 = new Thread(rCheck);
        Thread th2 = new Thread(() -> {
            stop();          
        });   
        th0.start();
        th1.start();
        th2.start();
        th0.join();
        th1.join();
        th2.join();
        assert isStopped() == true;
    }
}
