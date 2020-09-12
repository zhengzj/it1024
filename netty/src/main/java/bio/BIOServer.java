package bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * bio server
 *
 * @author zhijian.zheng@ucarinc.com
 * @package: bio
 * @date: 2020-09-12 10:06
 **/
public class BIOServer {

    private static Integer PORT = 8888;


    public static void main(String[] args) {
        startServer();
    }

    private static void startServer(){
        ServerSocket server = null;
        Socket socket = null;
        ThreadPoolExecutor executor = null;
        try {
            // 启动服务，监听8888端口
             server = new ServerSocket(PORT);
            System.out.println("BIO Server 服务器启动.........");
           /* // 每次有请求，都转给hanlder处理
            while(true){
                socket = server.accept();
                new Thread(new BIOServerHandler(socket)).start();
            }*/

            executor = new ThreadPoolExecutor(10, 100, 1000, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(50));
            while (true) {
                // 服务器监听：阻塞，等待Client请求
                socket = server.accept();
                BIOServerHandler serverHandler = new BIOServerHandler(socket);
                executor.execute(serverHandler);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (null != socket) {
                    socket.close();
                    socket = null;
                }
                if (null != server) {
                    server.close();
                    server = null;
                    System.out.println("BIO Server 服务器关闭了！！！！");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
