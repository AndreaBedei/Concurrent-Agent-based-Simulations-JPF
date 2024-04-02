package project;

public interface Barrier {

	/**
	 * 
	 */
	void waitBeforeActing();
	/**
	 * 
	 * @param onAllWaiting
	 */
	void waitBeforeActing(Runnable onAllWaiting);
}
