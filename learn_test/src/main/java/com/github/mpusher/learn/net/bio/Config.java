package com.github.mpusher.learn.net.bio;

/**
 * Created by Sean.liu on 2017/7/4.
 */
public class Config {
    final static String host = "localhost";
    final static int port = 8066;
    final static String msg = "\n" +
            "登录 | 注册\n" +
            "关闭\n" +
            "\n" +
            "This is bill的专属博客\n" +
            "这里是Bill的程序世界\n" +
            " 目录视图 摘要视图 订阅\n" +
            "【观点】物联网与大数据将助推工业应用的崛起，你认同么？      CSDN日报0703——《我一直在寻找答案》      赠书 | 7 月大咖新书：机器学习 / Android / python\n" +
            " TCP粘包，拆包及解决方法\n" +
            "2016-07-22 16:35 4376人阅读 评论(3) 收藏 举报\n" +
            " 分类： Java（102）  \n" +
            "目录(?)[+]\n" +
            "转自：http://blog.insanecoder.top/tcp-packet-splice-and-split-issue/\n" +
            "\n" +
            "\n" +
            "在进行Java NIO学习时，发现，如果客户端连续不断的向服务端发送数据包时，服务端接收的数据会出现两个数据包粘在一起的情况，这就是TCP协议中经常会遇到的粘包以及拆包的问题。\n" +
            "\n" +
            "我们都知道TCP属于传输层的协议，传输层除了有TCP协议外还有UDP协议。那么UDP是否会发生粘包或拆包的现象呢？答案是不会。UDP是基于报文发送的，从UDP的帧结构可以看出，在UDP首部采用了16bit来指示UDP数据报文的长度，因此在应用层能很好的将不同的数据报文区分开，从而避免粘包和拆包的问题。而TCP是基于字节流的，虽然应用层和TCP传输层之间的数据交互是大小不等的数据块，但是TCP把这些数据块仅仅看成一连串无结构的字节流，没有边界；另外从TCP的帧结构也可以看出，在TCP的首部没有表示数据长度的字段，基于上面两点，在使用TCP传输数据时，才有粘包或者拆包现象发生的可能。\n" +
            "\n" +
            "粘包、拆包表现形式\n" +
            "现在假设客户端向服务端连续发送了两个数据包，用packet1和packet2来表示，那么服务端收到的数据可以分为三种，现列举如下：\n" +
            "\n" +
            "第一种情况，接收端正常收到两个数据包，即没有发生拆包和粘包的现象，此种情况不在本文的讨论范围内。normal\n" +
            "\n" +
            "第二种情况，接收端只收到一个数据包，由于TCP是不会出现丢包的，所以这一个数据包中包含了发送端发送的两个数据包的信息，这种现象即为粘包。这种情况由于接收端不知道这两个数据包的界限，所以对于接收端来说很难处理。one\n" +
            "\n" +
            "第三种情况，这种情况有两种表现形式，如下图。接收端收到了两个数据包，但是这两个数据包要么是不完整的，要么就是多出来一块，这种情况即发生了拆包和粘包。这两种情况如果不加特殊处理，对于接收端同样是不好处理的。half_oneone_half\n" +
            "\n" +
            "粘包、拆包发生原因\n" +
            "发生TCP粘包或拆包有很多原因，现列出常见的几点，可能不全面，欢迎补充，\n" +
            "\n" +
            "1、要发送的数据大于TCP发送缓冲区剩余空间大小，将会发生拆包。\n" +
            "\n" +
            "2、待发送数据大于MSS（最大报文长度），TCP在传输前将进行拆包。\n" +
            "\n" +
            "3、要发送的数据小于TCP发送缓冲区的大小，TCP将多次写入缓冲区的数据一次发送出去，将会发生粘包。\n" +
            "\n" +
            "4、接收数据端的应用层没有及时读取接收缓冲区中的数据，将发生粘包。\n" +
            "\n" +
            "等等。\n" +
            "\n" +
            "粘包、拆包解决办法\n" +
            "通过以上分析，我们清楚了粘包或拆包发生的原因，那么如何解决这个问题呢？解决问题的关键在于如何给每个数据包添加边界信息，常用的方法有如下几个：\n" +
            "\n" +
            "1、发送端给每个数据包添加包首部，首部中应该至少包含数据包的长度，这样接收端在接收到数据后，通过读取包首部的长度字段，便知道每一个数据包的实际长度了。\n" +
            "\n" +
            "2、发送端将每个数据包封装为固定长度（不够的可以通过补0填充），这样接收端每次从接收缓冲区中读取固定长度的数据就自然而然的把每个数据包拆分开来。\n" +
            "\n" +
            "3、可以在数据包之间设置边界，如添加特殊符号，这样，接收端通过这个边界就可以将不同的数据包拆分开。\n" +
            "\n" +
            "等等。\n" +
            "\n" +
            "样例程序\n" +
            "我将在程序中使用两种方法来解决粘包和拆包问题，固定数据包长度和添加长度首部，这两种方法各有优劣。固定数据包长度传输效率一般，尤其是在要发送的数据长度长短差别很大的时候效率会比较低，但是编程实现比较简单；添加长度首部虽然可以获得较高的传输效率，冗余信息少且固定，但是编程实现较为复杂。下面给出的样例程序是基于之前的文章《Java中BIO，NIO和AIO使用样例》中提到的NIO实例的，如果对NIO的使用还不是很熟悉，可以先了解一下Java中NIO编程。\n" +
            "\n" +
            "固定数据包长度\n" +
            "这种处理方式的思路很简单，发送端在发送实际数据前先把数据封装为固定长度，然后在发送出去，接收端接收到数据后按照这个固定长度进行拆分即可。发送端程序如下：\n" +
            "\n" +
            "// 发送端\n" +
            "String msg = \"hello world \" + number++;  \n" +
            "socketChannel.write(ByteBuffer.wrap(new FixLengthWrapper(msg).getBytes()));\n" +
            "\n" +
            "// 封装固定长度的工具类\n" +
            "public class FixLengthWrapper {\n" +
            "\n" +
            "    public static final int MAX_LENGTH = 32;\n" +
            "    private byte[] data;\n" +
            "\n" +
            "    public FixLengthWrapper(String msg) {\n" +
            "        ByteBuffer byteBuffer = ByteBuffer.allocate(MAX_LENGTH);\n" +
            "        byteBuffer.put(msg.getBytes());\n" +
            "        byte[] fillData = new byte[MAX_LENGTH - msg.length()];\n" +
            "        byteBuffer.put(fillData);\n" +
            "        data = byteBuffer.array();\n" +
            "    }\n" +
            "\n" +
            "    public FixLengthWrapper(byte[] msg) {\n" +
            "        ByteBuffer byteBuffer = ByteBuffer.allocate(MAX_LENGTH);\n" +
            "        byteBuffer.put(msg);\n" +
            "        byte[] fillData = new byte[MAX_LENGTH - msg.length];\n" +
            "        byteBuffer.put(fillData);\n" +
            "        data = byteBuffer.array();\n" +
            "    }\n" +
            "\n" +
            "    public byte[] getBytes() {\n" +
            "        return data;\n" +
            "    }\n" +
            "\n" +
            "    public String toString() {\n" +
            "        StringBuilder sb = new StringBuilder();\n" +
            "        for (byte b : getBytes()) {\n" +
            "            sb.append(String.format(\"0x%02X \", b));\n" +
            "        }\n" +
            "        return sb.toString();\n" +
            "    }\n" +
            "}\n" +
            "可以看到客户端在发送数据前首先把数据封装为长度为32bytes的数据包，这个长度是根据目前实际数据包长度来规定的，这个长度必须要大于所有可能出现的数据包的长度，这样才不会出现把数据“截断”的情况。接收端程序如下：\n" +
            "\n" +
            "private static void processByFixLength(SocketChannel socketChannel) throws IOException {  \n" +
            "    while (socketChannel.read(byteBuffer) > 0) {\n" +
            "\n" +
            "        byteBuffer.flip();\n" +
            "        while (byteBuffer.remaining() >= FixLengthWrapper.MAX_LENGTH) {\n" +
            "            byte[] data = new byte[FixLengthWrapper.MAX_LENGTH];\n" +
            "            byteBuffer.get(data, 0, FixLengthWrapper.MAX_LENGTH);\n" +
            "            System.out.println(new String(data) + \" <---> \" + number++);\n" +
            "        }\n" +
            "        byteBuffer.compact();\n" +
            "    }\n" +
            "}\n" +
            "可以看出接收端的处理很简单，只需要每次读取固定的长度即可区分出来不同的数据包。\n" +
            "\n" +
            "添加长度首部\n" +
            "这种方式的处理较上面提到的方式稍微复杂一点。在发送端需要给待发送的数据添加固定的首部，然后再发送出去，然后在接收端需要根据这个首部的长度信息进行数据包的组合或拆分，发送端程序如下：\n" +
            "\n" +
            "// 发送端\n" +
            "String msg = \"hello world \" + number++;  \n" +
            "// add the head represent the data length\n" +
            "socketChannel.write(ByteBuffer.wrap(new PacketWrapper(msg).getBytes()));\n" +
            "\n" +
            "// 添加长度首部的工具类\n" +
            "public class PacketWrapper {\n" +
            "\n" +
            "    private int length;\n" +
            "    private byte[] payload;\n" +
            "\n" +
            "    public PacketWrapper(String payload) {\n" +
            "        this.payload = payload.getBytes();\n" +
            "        this.length = this.payload.length;\n" +
            "    }\n" +
            "\n" +
            "    public PacketWrapper(byte[] payload) {\n" +
            "        this.payload = payload;\n" +
            "        this.length = this.payload.length;\n" +
            "    }\n" +
            "\n" +
            "    public byte[] getBytes() {\n" +
            "        ByteBuffer byteBuffer = ByteBuffer.allocate(this.length + 4);\n" +
            "        byteBuffer.putInt(this.length);\n" +
            "        byteBuffer.put(payload);\n" +
            "        return byteBuffer.array();\n" +
            "    }\n" +
            "\n" +
            "    public String toString() {\n" +
            "        StringBuilder sb = new StringBuilder();\n" +
            "        for (byte b : getBytes()) {\n" +
            "            sb.append(String.format(\"0x%02X \", b));\n" +
            "        }\n" +
            "        return sb.toString();\n" +
            "    }\n" +
            "}\n" +
            "从程序可以看到，发送端在发送数据前首先给待发送数据添加了代表长度的首部，首部长为4bytes（即int型长度），这样接收端在收到这个数据之后，首先需要读取首部，拿到实际数据长度，然后再继续读取实际长度的数据，即实现了组包和拆包的操作。程序如下：\n" +
            "\n" +
            "private static void processByHead(SocketChannel socketChannel) throws IOException {\n" +
            "\n" +
            "    while (socketChannel.read(byteBuffer) > 0) {\n" +
            "        // 保存bytebuffer状态\n" +
            "        int position = byteBuffer.position();\n" +
            "        int limit = byteBuffer.limit();\n" +
            "        byteBuffer.flip();\n" +
            "        // 判断数据长度是否够首部长度\n" +
            "        if (byteBuffer.remaining() < 4) {\n" +
            "            byteBuffer.position(position);\n" +
            "            byteBuffer.limit(limit);\n" +
            "            continue;\n" +
            "        }\n" +
            "        // 判断bytebuffer中剩余数据是否足够一个包\n" +
            "        int length = byteBuffer.getInt();\n" +
            "        if (byteBuffer.remaining() < length) {\n" +
            "            byteBuffer.position(position);\n" +
            "            byteBuffer.limit(limit);\n" +
            "            continue;\n" +
            "        }\n" +
            "        // 拿到实际数据包\n" +
            "        byte[] data = new byte[length];\n" +
            "\n" +
            "        byteBuffer.get(data, 0, length);\n" +
            "        System.out.println(new String(data) + \" <---> \" + number++);\n" +
            "        byteBuffer.compact();\n" +
            "    }\n" +
            "}\n" +
            "关键信息已经在程序中做了注释，可以很明显的感觉到这种方法的处理难度相对于固定长度要大一些，不过这种方式可以获取更大的传输效率。\n" +
            "\n" +
            "这里需要提醒各位同学一个问题，由于我在测试的时候采用的是一台机器连续发送数据来模拟高并发的场景，所以在测试的时候会发现服务器端收到的数据包的个数经常会小于包的序号，好像发生了丢包。但经过仔细分析可以发现，这种情况是因为TCP发送缓存溢出导致的丢包，也就是这个数据包根本没有发出来。也就是说，发送端发送数据过快，导致接收端缓存很快被填满，这个时候接收端会把通知窗口设置为0从而控制发送端的流量，这样新到的数据只能暂存在发送端的发送缓存中，当发送缓存溢出后，就出现了我上面提到的丢包，这个问题可以通过增大发送端缓存来缓解这个问题，\n" +
            "\n" +
            "socketChannel.socket().setSendBufferSize(102400);  \n" +
            "当然这个话题不在本文的讨论范围，如果有兴趣的同学可以参阅《TCP/IP详解卷一》中的拥塞窗口一章。\n" +
            "\n" +
            "关于源码说明，源码默认是把粘包和拆包处理这一部分注释掉了，分别位于NIOTcpServer和NIOTcpClient文件中，需要测试粘包和拆包处理程序的同学需要把这一段注释给去掉。\n" +
            "\n" +
            "最后给出源码下载地址\n" +
            "\n" +
            "参考\n" +
            "Netty精粹之TCP粘包拆包问题\n" +
            "\n" +
            "\n" +
            "顶\n" +
            "2\n" +
            " \n" +
            "踩\n" +
            "0\n" +
            " \n" +
            " \n" +
            "上一篇Mockito：一个强大的用于Java开发的模拟测试框架\n" +
            "下一篇Mocks Aren't Stubs\n" +
            "  相关文章推荐\n" +
            "• （经典）tcp粘包分析\n" +
            "• Socket粘包问题\n" +
            "• TCP粘包问题\n" +
            "• 网络粘包及Nagle算法（粘包，断包）\n" +
            "• IOCP的一些思考（粘包，断包的处理）\n" +
            "• Socket TCP粘包拆包\n" +
            "• tcp粘包问题（经典分析）\n" +
            "• Socket/TCP粘包、多包和少包, 断包\n" +
            "• tcp粘包和拆包、断包\n" +
            "• Netty解决半包（TCP粘包/拆包导致）读写问题\n" +
            "\n" +
            "猜你在找\n" +
            "机器学习之概率与统计推断 机器学习之数学基础 机器学习之凸优化 机器学习之矩阵 响应式布局全新探索 探究Linux的总线、设备、驱动模型 深度学习基础与TensorFlow实践 深度学习之神经网络原理与实战技巧 前端开发在线峰会 TensorFlow实战进阶：手把手教你做图像识别应用\n" +
            "\n" +
            "查看评论\n" +
            "2楼 Victor的博客 2017-02-18 19:25发表 [回复] 这是我见过最详细的粘包，拆包及解决方法，太谢谢了。\n" +
            "\n" +
            "你提到了 TCP/IP卷一 ，我实在是看不懂，不过很佩服你\n" +
            "1楼 一个人喝不醉 2016-12-21 10:35发表 [回复] 看了这么多拆包和粘包问题，觉得此文解释的最为清晰透彻，让读者的知识层面又上了一个阶段，感谢作者!感谢CSDN平台！Re: This is bill 2016-12-23 10:08发表 [回复] 回复y_h_u_abc：不客气，一起加油\n" +
            "您还没有登录,请[登录]或[注册]\n" +
            "* 以上用户言论只代表其个人观点，不代表CSDN网站的观点或立场\n" +
            "About Me\n" +
            "个人网站\n" +
            "Github\n" +
            "英语配音\n" +
            "个人资料\n" +
            "访问我的空间  \n" +
            "This is bill\n" +
            " \n" +
            " 2  4\n" +
            "访问：626928次\n" +
            "积分：11163\n" +
            "等级： \n" +
            "排名：第1336名\n" +
            "原创：379篇转载：355篇译文：6篇评论：190条\n" +
            "博客专栏\n" +
            "\tc/c++底层知识库\n" +
            "文章：20篇\n" +
            "阅读：27074\n" +
            "友情链接\n" +
            "吕兄博客\n" +
            "行总博客\n" +
            "文章搜索\n" +
            "\n" +
            " 搜索\n" +
            "文章分类\n" +
            "随笔(31)\n" +
            "早起一水(137)\n" +
            "iOS开发(24)\n" +
            "技巧(121)\n" +
            "模板(34)\n" +
            "数字图像处理(63)\n" +
            "汇编(6)\n" +
            "c/c++(109)\n" +
            "Unix(57)\n" +
            "软件架构(24)\n" +
            "电子设计(3)\n" +
            "Qt(6)\n" +
            "Java(103)\n" +
            "dp(11)\n" +
            "图论(5)\n" +
            "网络编程(36)\n" +
            "数据库(6)\n" +
            "Hacker(4)\n" +
            "数字电子(10)\n" +
            "网络安全(12)\n" +
            "机器学习(22)\n" +
            "文章存档\n" +
            "2017年06月(9)\n" +
            "2017年05月(8)\n" +
            "2017年04月(10)\n" +
            "2017年03月(15)\n" +
            "2017年01月(8)\n" +
            "展开\n" +
            "阅读排行\n" +
            "SQL中inner join、outer join和cross join的区别(9945)\n" +
            "linux shadowsocks客户端配置(9846)\n" +
            "FC金手指使用方法+大全(9331)\n" +
            "java 中的Scanner（非常详细不看后悔）(6617)\n" +
            "用开源飞控套件做一架Mini四轴飞行器(5199)\n" +
            "vim常用命令总结(5017)\n" +
            "Centos 建立用户(4576)\n" +
            "C++反射机制的实现(4482)\n" +
            "从一个logger引发的lib和dll探讨(4426)\n" +
            "TCP粘包，拆包及解决方法(4373)\n" +
            "评论排行\n" +
            "sizeof小览(17)\n" +
            "Java基础学习过程(10)\n" +
            "简易Java爬虫制作(10)\n" +
            "【Tsinghua】列车调度(Train)(9)\n" +
            "要怎样努力，才能成为很厉害的人？(7)\n" +
            "SIFT的视频跟踪(7)\n" +
            "【Tsinghua】旅行商(TSP)(7)\n" +
            "从一个logger引发的lib和dll探讨(6)\n" +
            "关于c++显示调用析构函数的陷阱(5)\n" +
            "浏览器小览【欢迎讨论】(5)\n" +
            "推荐文章\n" +
            "* CSDN日报20170703——《从高考到程序员——我一直在寻找答案》\n" +
            "* 从源码剖析PopupWindow 兼容Android 6.0以上版本点击外部不消失\n" +
            "* 轻松学，Java 中的代理模式及动态代理\n" +
            "* ArcGIS水文分析实战教程——河流提取与河网分级\n" +
            "* Tensorflow中使用TFRecords高效读取数据--结合NLP数据实践\n" +
            "* 每周荐书：MyBatis、并行编程、Ansible（评论送书）\n" +
            "最新评论\n" +
            "Java基础学习过程\n" +
            "宏雷92: 是什么 干什么 怎么干 怎么实现 ？\n" +
            "Genymotion的安装与使用（附百度云盘下载地址，全套都有，无需注册Genymotion即可使用）\n" +
            "cumtdeyurenjie: 我去试试呢\n" +
            "高密度环境下行人检测和统计\n" +
            "zhikui3838: 请问楼主，可不可以分享下代码？只是拿来学习的，麻烦楼主了\n" +
            "Genymotion的安装与使用（附百度云盘下载地址，全套都有，无需注册Genymotion即可使用）\n" +
            "lopez66: 好像不错，去试试先\n" +
            "要怎样努力，才能成为很厉害的人？\n" +
            "This is bill: @worthliu:加油！！！\n" +
            "要怎样努力，才能成为很厉害的人？\n" +
            "This is bill: @u011043551:加油！！！\n" +
            "要怎样努力，才能成为很厉害的人？\n" +
            "This is bill: @justloveyou_:一起加油\n" +
            "word下使用Latex的公式\n" +
            "xerjava: 官网为什么打不开？？？\n" +
            "要怎样努力，才能成为很厉害的人？\n" +
            "书呆子Rico: 长大以后，感觉热血是一件很难得的事情。 热血不是无知及其附属品无畏，而是一种本心的坚韧与坚持，即使生...\n" +
            "要怎样努力，才能成为很厉害的人？\n" +
            "书呆子Rico: 转了 博主 好文谢谢挖掘与分享\n" +
            "\n" +
            "公司简介|招贤纳士|广告服务|联系方式|版权声明|法律顾问|问题报告|合作伙伴|论坛反馈\n" +
            "网站客服杂志客服微博客服webmaster@csdn.net400-660-0108|北京创新乐知信息技术有限公司 版权所有|江苏知之为计算机有限公司|江苏乐知网络技术有限公司\n" +
            "京 ICP 证 09002463 号|Copyright © 1999-2017, CSDN.NET, All Rights Reserved GongshangLogo\n" +
            "\n";
}
