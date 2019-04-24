package ioBasis;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
public class CopyFileDemo {
	
	public static void main(String[] args) {
		try {
//			copyFileMethod1("/home/cocolee/文档/12.txt", "/home/cocolee/文档/23.txt");
//			copyFileMethod2("/home/cocolee/文档/23.txt", "/home/cocolee/文档/34.txt");
			copyFileMethod3("/home/cocolee/文档/34.txt", "/home/cocolee/文档/45.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 第一种拷贝方式，直接构造一个FileInputSream和一个FileOutputSream进行传输。
	 * @throws IOException 
	 */
	public static void copyFileMethod1(String args0, String args1) throws IOException {
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
	}
	
	/**
	 * 使用FileChannel拷贝文件
	 */
	public static void copyFileMethod2(String args0, String args1) throws IOException{
		try(
			FileChannel inChannel = new FileInputStream(args0).getChannel();
			FileChannel outChannel = new FileOutputStream(args1).getChannel();){
			for(long n=inChannel.size(); n>0;) {
				long transferred = inChannel.transferTo(inChannel.position(), n, outChannel);
				n -= transferred;
			}
		}
	}
	
	/**
	 * 标准类库
	 */
	public static void copyFileMethod3(String args0, String args1) throws IOException{
		Files.copy(new File(args0).toPath(), new File(args1).toPath(), StandardCopyOption.REPLACE_EXISTING);
	}
}
