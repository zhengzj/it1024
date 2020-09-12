package bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 *
 * @author zhijian.zheng@ucarinc.com
 * @package: bio
 * @date: 2020-09-12 10:14
 **/
public class BIOServerHandler implements Runnable{

    private Socket socket;

    public BIOServerHandler(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader reader = null;
        PrintWriter writer = null;
        try {
             reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             writer = new PrintWriter(socket.getOutputStream(),true);
            String body = null;
            while(true){
                body = reader.readLine();
                if (null == body) {
                    break;
                }
                System.out.println("server服务端接收参数 : " + body);
                writer.println(body + " = " + CalculatorUtil.cal(body).toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null != writer) {
                writer.close();
            }
            try {
                if (null != reader) {
                    reader.close();
                }
                if (null != this.socket) {
                    this.socket.close();
                    this.socket = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
