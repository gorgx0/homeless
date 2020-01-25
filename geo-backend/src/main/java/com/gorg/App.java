package com.gorg;

import org.h2.server.Service;
import org.h2.tools.Server;

import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) throws SQLException {
        Server tcpServer = Server.createTcpServer("-tcpAllowOthers","-tcpDaemon","-baseDir", "/tmp/h2");
        String url = tcpServer.getURL();
        System.out.println(url);
        tcpServer.start();
        String status = tcpServer.getStatus();
        System.out.println("Status: " + status);
        Service service = tcpServer.getService();
        service.listen();
        System.out.println("Press Enter to end");
        try{System.in.read();}
        catch(Exception e){}
    }
}
