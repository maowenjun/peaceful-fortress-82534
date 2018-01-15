package org.spring.springboot.commons.listener;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by ThinkPad on 2018/1/15.
 */
public class NotificationTest {

    public static void main(String[] args) throws Exception{
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://ec2-107-21-95-70.compute-1.amazonaws.com:5432/d5rut42s3mls5v";

        // Create two distinct connections, one for the notifier
        // and another for the listener to show the communication
        // works across connections although this example would
        // work fine with just one connection.
        Connection lConn = DriverManager.getConnection(url,"yuipsdrddpvqfc","04a6f29ad9745dfc2a7325a5b14820dce94bc24aa9aa5cf981a323f9e292028a");
        // Create two threads, one to issue notifications and
        // the other to receive them.
        NotificationListener listener = new NotificationListener(lConn);
        listener.start();
    }
}
