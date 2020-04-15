package com.rss.framework;

public class ConnectIP {
    private String IP;
    private String port;

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public int getPort() {
        return Integer.parseInt(port);
    }

    public void setPort(int port) {
        this.port = String.valueOf(port);
    }
}
