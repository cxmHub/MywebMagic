package test;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TestMain {

    public static boolean isHostConnectable(String host, int port) {
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(host, port));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Test
    public void test(){
        boolean hostConnectable =
                isHostConnectable("61.135.155.82", 443);
        System.out.println(hostConnectable);
    }
    // 210.77.24.191 1080
    // 221.11.105.70 56120

    @Test
    public void test1(){
    }



}
