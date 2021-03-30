package lab1.Options;

public class ServerOptions {
    private int port;
    private String name;

    ServerOptions(){}

    ServerOptions(int port, String name){
        this.port = port;
        this.name = name;
    }

    public int getPort() {
        return port;
    }
    public String getName() {
        return name;
    }
}
