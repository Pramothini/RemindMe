package controller;

public interface SocketClientInterface {
	boolean openConnection();
    void handleSession();
    void closeSession();

}
