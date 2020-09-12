**BIO概念**

Block-IO ,阻塞、同步的通信模式
是一种传统的通信方式、并发处理能力差，通信耗时长，依赖于网速

**原理**

服务器通过接收一个Acceptor线程，负责监听客户端的请求和为每个客户端创建一个新的线程进行链路处理
、典型的请求响应模型

客户端请求量多起来后，服务端频繁的创建和销毁线程，很消耗资源。

为每个客户请求创建thread -> 通过线程池来管理线程的创建和销毁（伪异步IO）


**客户端的创建**

1.new Socket(IP,PORT),创建socket 对象,发起连接
2.new BufferedReader(new InputStreamReader(socket.getInputStream()))对象、从服务端读取数据
3.new PrinterWriter(socket.getOutputStream()) 客户端发送数据
4. 关闭流 socket.close(),writer.close()


**服务端的创建**
1.new ServerSocket(PORT) 服务端监听PORT端口
2.服务端不断去轮询判断，是否有客户端连接 socket = server.accept()
3.接收到客户的请求后，服务器另开线程handler去处理请求信息 new Thread(new Handler(socket)).start();


**handler 处理器**

1.从socket读取从客户端传递的数据 new BufferReader()
2.循环获取 readLine() 数据
3.将数据返回给客户端 new PrinterWriter()