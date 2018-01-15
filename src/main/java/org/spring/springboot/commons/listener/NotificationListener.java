package org.spring.springboot.commons.listener;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by ThinkPad on 2018/1/15.
 */
public class NotificationListener extends Thread {

    private Connection conn;

    private org.postgresql.PGConnection pgconn;

    public NotificationListener( Connection conn) throws SQLException{
        this.conn = conn;
        this.pgconn = (org.postgresql.PGConnection)conn;
        Statement stmt = conn.createStatement();
        stmt.execute("LISTEN mymessage");
        stmt.close();
    }

    @Override
    public void run() {
        while (true){
            try {
               Statement stmt  = conn.createStatement();
               ResultSet rs = stmt.executeQuery("select 1");
               rs.close();
               stmt.close();
               org.postgresql.PGNotification notifications [] = pgconn.getNotifications();
               if(notifications != null){
                   for(int i=0;i<notifications.length;i++){
                       System.out.println("PID:"+notifications[i].getPID()+" 进程中收到："+notifications[i].getName());
                       System.out.println("接收到的数据: "+notifications[i].getParameter());
                   }
               }
               Thread.sleep(500);
            }catch (SQLException sqle){
                sqle.printStackTrace();
            }catch (InterruptedException ie){
                ie.printStackTrace();
            }
        }
    }
}
