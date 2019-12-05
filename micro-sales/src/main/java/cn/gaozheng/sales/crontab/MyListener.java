package cn.gaozheng.sales.crontab;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyListener implements ServletContextListener {


    private SSHConnection conexionssh;


    public MyListener() {
        super();
    }

    /**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) {
        System.out.println("Context initialized ... !");
        try {
            conexionssh = new SSHConnection(); // 监听到了 就装配文件
        } catch (Throwable e) {
            e.printStackTrace(); // error connecting SSH server
        }
    }

    /**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed( ServletContextEvent arg0) {
        System.out.println("Context destroyed ... !");
        conexionssh.closeSSH(); // disconnect
    }
}
