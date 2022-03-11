package tmge.ui;

/**
 * Reacts to the closing of a Window.
 */
public interface WindowCloseReactable {
	
	/**
	 * Tell the WindowCloseReactable that the window has been closed.
	 */
	public void signalClosed();
	
}
