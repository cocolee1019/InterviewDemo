package chapter2;

import java.io.*;

/**
 * IO：java.io下的包，基于流模型实现，交互是同步、阻塞的。
 * 部分类解读：
 * BufferedInputStream缓冲的实现原理：在BufferedInputStream中有一个buf缓冲字节数组，fill之后，会调用
 * 	InputStream的read方法中的数据存入到buf中，每次会读取8192个字符（默认的，也可以自己指定），然后bufferedInputStream.read
 * 	会从字节数组中读出数据。
 * BufferedOutputStream也是同理，有一个缓冲字节用于存放待写入的字符，当字节数组要满的时候，会调用flushBuffer将数组刷新到磁盘。
 * ByteArrayInputStream，允许将内存缓冲区当作InputStream使用。
 *
 * NIO：在java 1.4之后引入的包，提供了Channel、Selector、Buffer等更新的抽象，
 * 可以构建多路复用，同步非阻塞的I/O程序，同时也提供了更接近底层的高性能数据操作方式。
 * NIO组成部分：
 * 	Buffer：高效的数据容器，除了布尔类型，所有原始数据类型都有相应的Buffer实现。
 * 	Channel：类似在linux中的文件描述符，是NIO中被用来支持批量式IO操作的一种抽象。
 * 	Selector：是NIO实现多路复用的基础，它提供了一种高效机制，可以检测到注册在Selector上的多个Channel中，
 * 			 是否有Channel处于就绪状态，进而实现了单线程对多Channel的高效管理。
 * 			 Selector同样是基于底层操作系统机制，不同模式、不同版本都存在区别。
 * 			 在linux上依赖于epoll。
 * 
 * AIO：异步非阻塞IO。在1.7中引入，异步非阻塞IO基于事件机制和回调机制。
 *
 *
 * 问题1：怎么理解"ByteArrayInputStream，允许将内存缓冲区当作InputStream使用。"
 *  所谓内存缓冲区，其实就是指java的堆空间。ByteArrayInputStream的数据源是字节数组。
 *
 */
public class Course3_IO {

	public static void main(String[] args) throws IOException {
//		ioDemo("/home/cocolee/文档/a.txt");
		byteArrayDemo();
	}

	public static void ioDemo(String args0) throws IOException {
		//无缓冲
		try(FileInputStream fin = new FileInputStream(args0);) {
			//FileInputStream.read直接调用read0，是一个native方法。
			fin.read();
			
			//FileInputStream.read(byte[])方法直接调用readbytes，是一个native方法。
			byte[] buff = new byte[8192];
			fin.read(buff);
		}
		
		//有缓冲
		try(BufferedInputStream bin = new BufferedInputStream(new FileInputStream(args0));) {
			//BufferedInputStream所谓的缓冲，其实是在内存中开辟一个字节数组，作用是减少了去读写磁盘的次数。
			//数组的大小默认是8192,也可以自己在构造器中指定。
			byte[] buff = new byte[1];
			while(bin.read(buff) != -1) {
				System.out.println((char) buff[0]);
			}
		}
	}

	private static void byteArrayDemo() {
		byte[] buffs = "byteArrayInputStream".getBytes();
		ByteArrayInputStream inputStream = new ByteArrayInputStream(buffs);
		int read ;
		while ((read = inputStream.read()) != -1) {
			System.out.print((char)read);
		}
	}

	private static void stringBufferDemo() {
		StringBufferInputStream inputStream = new StringBufferInputStream("byteArrayInputStream");
		int read ;
		while ((read = inputStream.read()) != -1) {
			System.out.print((char)read);
		}
	}

	private static void fileInputStreamDemo() throws IOException {
		FileInputStream inputStream = new FileInputStream("c:/Desktop/a.TXT");
		int read ;
		while ((read = inputStream.read()) != -1) {
			System.out.print((char)read);
		}
	}
}
