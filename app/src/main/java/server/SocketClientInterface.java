package server;

/**
 * Created by Santosh on 25-07-2015.
 */
public interface SocketClientInterface {
    boolean openConnection();
    void handleSession();
    void closeSession();
}
