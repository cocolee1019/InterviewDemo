package chapter2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * I/O之拷贝文件
 * 1、使用FileInputStream和FileOutputStream进行拷贝。
 * 2、NIO包下FileChannel.transferTo：在unix/linux上，transferTo使用零拷贝技术，
 * 数据传输不需要用户态的参与，省去了上下文切换的开销和不必要的内存拷贝，进而可能提高应用拷贝性能。
 * 3、使用java.nio.file.Files中的静态方法copy：copy方法最后调用的是ChannelInputStream.read()。
 */
public class Course2_CopyFile {
	
	public static void main(String[] args) {
		try {
			copyFileMethod1("/home/cocolee/文档/56.txt", "/home/cocolee/文档/23.txt");
			copyFileMethod2("/home/cocolee/文档/23.txt", "/home/cocolee/文档/34.txt");
			copyFileMethod3("/home/cocolee/文档/34.txt", "/home/cocolee/文档/45.txt");
			copyFileMethod4("/home/cocolee/文档/45.txt", "/home/cocolee/文档/12.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 第一种拷贝方式，直接构造一个FileInputSream和一个FileOutputSream进行传输。
	 * @throws IOException 
	 */
	public static void copyFileMethod1(String args0, String args1) throws IOException {
		long timeMill = System.currentTimeMillis();
		BufferedInputStream bin = null;
		BufferedOutputStream bout = null;
		try {
			bin = new BufferedInputStream(new FileInputStream(args0));
			bout = new BufferedOutputStream(new FileOutputStream(args1));
			int n;
			byte[] buf = new byte[1024];
			while((n = bin.read(buf)) > 0) {
				bout.write(buf, 0, n);
			}
		}finally {
			if(bin != null) bin.close();
			if(bout != null) bout.close();
		}
		System.out.println("用时：" + (System.currentTimeMillis() - timeMill));
	}
	
	/**
	 * 使用FileChannel拷贝文件
	 */
	public static void copyFileMethod2(String args0, String args1) throws IOException{
		long timeMill = System.currentTimeMillis();
		try(
			FileChannel inChannel = new FileInputStream(args0).getChannel();
			FileChannel outChannel = new FileOutputStream(args1).getChannel();
		){
			for(long n=inChannel.size(); n>0;) {
				long transferred = inChannel.transferTo(inChannel.position(), n, outChannel);
				n -= transferred;
			}
		}
		System.out.println("用时：" + (System.currentTimeMillis() - timeMill));
	}
	
	/**
	 * 标准类库
	 */
	public static void copyFileMethod3(String args0, String args1) throws IOException{
		long timeMill = System.currentTimeMillis();
		Files.copy(new File(args0).toPath(), new File(args1).toPath(), StandardCopyOption.REPLACE_EXISTING);
		System.out.println("用时：" + (System.currentTimeMillis() - timeMill));
	}
	
	/**
	 * 使用Channel+byte
	 */
	public static void copyFileMethod4(String args0, String args1) throws IOException{
		long timeMill = System.currentTimeMillis();
		try(
			FileChannel channel1 = new FileInputStream(new File(args0)).getChannel();
			FileChannel channel2 = new FileOutputStream(new File(args1)).getChannel();
		){
			ByteBuffer buff = ByteBuffer.allocate(1024);
			while(channel1.read(buff) != -1) {
				buff.flip();
				channel2.write(buff);
				buff.clear();
			}
		}
		System.out.println("用时：" + (System.currentTimeMillis() - timeMill));
	}
}
