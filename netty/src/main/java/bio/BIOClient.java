package bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

/**
 * bio 客户端
 *
 * @author zhijian.zheng@ucarinc.com
 * @package: bio
 * @date: 2020-09-12 10:14
 **/
public class BIOClient {

    private static Integer PORT = 8888;
    private static String IP = "127.0.0.1";

    public static void main(String[] args) {
        // 模拟10个客户端发送请求
        for (int i = 0; i < 10; i++) {
            client(i);
        }
    }

    private static void client(int i){
        Socket socket = null;
        BufferedReader bufferedReader = null;
        PrintWriter printWriter = null;
        try{
            // 创建跟服务器的连接
            socket = new Socket(IP,PORT);
            // 获取服务器的数据
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream(),true);
            String []operators = {"+","-","*","/"};
            Random random = new Random(System.currentTimeMillis());
            String expression = random.nextInt(10)+operators[random.nextInt(4)]+(random.nextInt(10)+1);
            // 向服务器端发送数据
            System.out.println(i+"客户端向服务器发送数据："+expression);
            printWriter.println(expression);
            System.out.println(i + " 客户端打印返回数据 : " + bufferedReader.readLine());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (null != printWriter) {
                    printWriter.close();
                }
                if (null != socket) {
                    socket.close();
                    socket = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
