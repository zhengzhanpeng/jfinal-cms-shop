package com.gz.utils;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.upload.UploadFile;
import com.sun.xml.internal.ws.api.message.Attachment;

public class FileUtil  {
	public String smallimg = "";

	/**
	 * 多文件上传重命名后的文件名
	 */
	public static String savepath = PropKit.get("FILE_UPLOAD_PATH") + "\\upload\\";

	public static Map<String, Object> upload(UploadFile file) {
		String path = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
		File source = file.getFile();
		String fileName = file.getFileName();
		String extension = fileName.substring(fileName.lastIndexOf("."));
		String prefix;
		if(".png".equals(extension) || ".jpg".equals(extension) || ".gif".equals(extension)){
			prefix = "img";
			fileName = generateWord() + extension;
		}else{
			prefix = "file";
		}
		Map<String,Object> result = new HashMap<>();
		try {
			FileInputStream fis = new FileInputStream(source);
			File targetDir = new File(savepath + "/" + prefix + "/u/"
					+ path);
			if (!targetDir.exists()) {
				targetDir.mkdirs();
			}
			File target = new File(targetDir, fileName);
			if (!target.exists()) {
				target.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(target);
			byte[] bts = new byte[300];
			while (fis.read(bts, 0, 300) != -1) {
				fos.write(bts, 0, 300);
			}
			fos.close();
			fis.close();
			result.put("code", 0);
			result.put("url", "upload/" + prefix + "/u/" + path + "/" + fileName);
			source.delete();
		} catch (FileNotFoundException e) {
			result.put("code", 1);
			result.put("msg", "上传出现错误，请稍后再上传");
		} catch (IOException e) {
			result.put("msg", 1);
			result.put("msg", "文件写入服务器出现错误，请稍后再上传");
		}
		return result;
	}
	private static String generateWord() {
		String[] beforeShuffle = new String[] { "2", "3", "4", "5", "6", "7",
				"8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
				"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
				"W", "X", "Y", "Z" };
		List<String> list = Arrays.asList(beforeShuffle);
		Collections.shuffle(list);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i));
		}
		String afterShuffle = sb.toString();
		String result = afterShuffle.substring(5, 9);
		return result;
	}

	/**
	 * 循环删除目录或文件
	 * 
	 * @param path
	 */
	public static void deleteFile(String path) {
		File file = new File(path);
		if (file != null && file.exists()) {
			if (file.isDirectory()) {
				for (File f : file.listFiles()) {
					f.delete();
				}
			}
			file.delete();
		}
	}

	public static String read(String filePath) {
		StringBuilder str = new StringBuilder();
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(filePath));
			String s;
			try {
				while ((s = in.readLine()) != null)
					str.append(s + '\n');
			} finally {
				in.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str.toString();
	}

	public static boolean write(String filePath, String content) {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter(filePath));
			try {
				out.write(content);
			} finally {
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// 写文件
	public static boolean writeFile(InputStream inputStream, String savePath, String fileName) {
		try {
			savePath = savePath + fileName;
			System.out.println(savePath);
			FileOutputStream fw = new FileOutputStream(savePath, true);
			byte[] b = new byte[1024];
			while (inputStream.read(b) != -1) {
				fw.write(b);
			}
			fw.flush();
			fw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 重命名
	public String renameto(UploadFile file) {
		String tempname = file.getFileName();
		smallimg = System.currentTimeMillis() + FileUtil.getSuffixName(tempname);
		File dest = new File(savepath + smallimg);
		if (file.getFile().renameTo(dest))
			return smallimg;
		else {
			return null;
		}
	}

	// 读取远程url图片,得到宽高
	public static int[] returnImgWH(String imgurl) {
		boolean b = false;
		try {
			// 实例化url
			URL url = new URL(imgurl);
			// 载入图片到输入流
			BufferedInputStream bis = new BufferedInputStream(url.openStream());
			// 实例化存储字节数组
			byte[] bytes = new byte[100];
			// 设置写入路径以及图片名称
			OutputStream bos = new FileOutputStream(new File(savepath + "thetempimg.jpg"));
			int len;
			while ((len = bis.read(bytes)) > 0) {
				bos.write(bytes, 0, len);
			}
			bis.close();
			bos.flush();
			bos.close();
			// 关闭输出流
			b = true;
		} catch (Exception e) {
			// 如果图片未找到
			b = false;
		}
		int[] a = new int[2];
		if (b) {// 图片存在
				// 得到文件
			File file = new File(savepath + "thetempimg.jpg");
			BufferedImage bi = null;
			boolean imgwrong = false;
			try {
				// 读取图片
				bi = ImageIO.read(file);
				try {
					// 判断文件图片是否能正常显示,有些图片编码不正确
					// int i = bi.getType();
					imgwrong = true;
				} catch (Exception e) {
					imgwrong = false;
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			if (imgwrong) {
				a[0] = bi.getWidth(); // 获得 宽度
				a[1] = bi.getHeight(); // 获得 高度
			} else {
				a = null;
			}
			// 删除文件
			file.delete();
		} else {// 图片不存在
			a = null;
		}
		return a;

	}

	// 读取远程url图片
	public static File readFileFromUrl(String fileUrl) {
		boolean b = false;
        String tempPath=savepath + System.currentTimeMillis()+"."+FileUtil.getFileExtension(fileUrl);
		try {
			// 实例化url
			URL url = new URL(fileUrl);
			// 载入图片到输入流
			BufferedInputStream bis = new BufferedInputStream(url.openStream());
			// 实例化存储字节数组
			byte[] bytes = new byte[100];
			// 设置写入路径以及图片名称
			OutputStream bos = new FileOutputStream(new File(tempPath));
			int len;
			while ((len = bis.read(bytes)) > 0) {
				bos.write(bytes, 0, len);
			}
			bis.close();
			bos.flush();
			bos.close();
			// 关闭输出流
			b = true;
		} catch (Exception e) {
			// 如果图片未找到
			b = false;
		}
		int[] a = new int[2];
		if (b) {// 图片存在
			// 得到文件
			File file = new File(tempPath);
			BufferedImage bi = null;
			boolean imgwrong = false;
			try {
				// 读取图片
				bi = ImageIO.read(file);
				try {
					// 判断文件图片是否能正常显示,有些图片编码不正确
					// int i = bi.getType();
					imgwrong = true;
					return file;
				} catch (Exception e) {
					imgwrong = false;
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}

		}else {// 图片不存在
			return null;
		}
		return null;

	}


	// 读取远程url图片,得到宽高
	public static int[] returnImgWH2(String savepath) {
		int[] a = new int[2];
		// 得到文件
		File file = new File(savepath);
		BufferedImage bi = null;
		boolean imgwrong = false;
		try {
			// 读取图片
			bi = ImageIO.read(file);
			try {
				// 判断文件图片是否能正常显示,有些图片编码不正确
				// int i = bi.getType();
				imgwrong = true;
			} catch (Exception e) {
				imgwrong = false;
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		if (imgwrong) {
			a[0] = bi.getWidth(); // 获得 宽度
			a[1] = bi.getHeight(); // 获得 高度
		} else {
			a = null;
		}
		return a;

	}

	public static boolean copyFile(String fileFrom, String fileTo) {
		try {
			FileInputStream in = new FileInputStream(fileFrom);
			FileOutputStream out = new FileOutputStream(fileTo);
			byte[] bt = new byte[1024];
			int count;
			while ((count = in.read(bt)) > 0) {
				out.write(bt, 0, count);
			}
			in.close();
			out.close();
			return true;
		} catch (IOException ex) {
			return false;
		}
	}

	

	public static void downloadImg(String imgUrl, String imgPath) {
		try {
			BufferedImage src = ImageIO.read(new URL(imgUrl));
			ImageIO.write(src, "JPEG", new File(imgPath));// 输出到文件流
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @description：压缩文件操作
	 * 
	 * @param filePath
	 *            要压缩的文件路径
	 * 
	 * @param descDir
	 *            压缩文件保存的路径
	 * 
	 */
	public static void zipFiles(String filePath, String descDir) {
		ZipOutputStream zos = null;
		try {
			// 创建一个Zip输出流
			zos = new ZipOutputStream(new FileOutputStream(descDir));
			// 启动压缩
			startZip(zos, "", filePath);
			System.out.println("******************压缩完毕********************");
		} catch (IOException e) {
			// 压缩失败，则删除创建的文件
			File zipFile = new File(descDir);
			if (zipFile.exists()) {
				zipFile.delete();
			}
			// System.out.println("******************压缩失败********************");
			e.printStackTrace();
		}

		finally {
			try {
				if (zos != null) {
					zos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * @description：对目录中所有文件递归遍历进行压缩
	 * 
	 * @param zos
	 *
	 *            ZIP压缩输出流
	 * 
	 * @param oppositePath
	 * 
	 *            在zip文件中的相对路径
	 * 
	 * @param directory
	 * 
	 *            要压缩的文件的路径
	 * 
	 * @throws IOException
	 * 
	 */
	private static void startZip(ZipOutputStream zos, String oppositePath, String directory) throws IOException

	{
		File file = new File(directory);
		if (file.isDirectory()) {
			// 如果是压缩目录
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				File aFile = files[i];
				if (aFile.isDirectory()) {
					// 如果是目录，修改相对地址
					String newOppositePath = oppositePath + aFile.getName() + "/";
					// 压缩目录，这是关键，创建一个目录的条目时，需要在目录名后面加多一个"/"
					ZipEntry entry = new ZipEntry(oppositePath + aFile.getName() + "/");
					zos.putNextEntry(entry);
					zos.closeEntry();
					// 进行递归调用
					startZip(zos, newOppositePath, aFile.getPath());
				} else {
					// 如果不是目录，则进行压缩
					zipFile(zos, oppositePath, aFile);
				}
			}
		} else {
			// 如果是压缩文件，直接调用压缩方法进行压缩
			zipFile(zos, oppositePath, file);
		}
	}

	/**
	 * @description：
	 * 
	 * 压缩单个文件到目录中
	 * 
	 * @param zos
	 * 
	 *            zip输出流
	 * 
	 * @param oppositePath
	 * 
	 *            在zip文件中的相对路径
	 * 
	 * @param file
	 * 
	 *            要压缩的的文件
	 * 
	 */
	private static void zipFile(ZipOutputStream zos, String oppositePath, File file) {
		// 创建一个Zip条目，每个Zip条目都是必须相对于根路径
		InputStream is = null;
		try {
			ZipEntry entry = new ZipEntry(oppositePath + file.getName());
			// 将条目保存到Zip压缩文件当中
			zos.putNextEntry(entry);
			// 从文件输入流当中读取数据，并将数据写到输出流当中.
			is = new FileInputStream(file);
			int length = 0;
			int bufferSize = 1024;
			byte[] buffer = new byte[bufferSize];
			while ((length = is.read(buffer, 0, bufferSize)) >= 0) {
				zos.write(buffer, 0, length);
			}
			zos.closeEntry();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static String getSuffixName(String fileName){
		if(StringUtil.isEmpty(fileName)){
			return fileName;
		}
		int index=fileName.lastIndexOf('.');
		String suffix=fileName.substring(index);
		return SecurityUtil.getMD5(fileName.substring(0, index))+suffix;
	}

	/**
	 *
	 * @param fileName
	 * @return
	 * @return 文件拓展名
	 */
	public static String getFileExtension(String fileName) {
		int index=fileName.lastIndexOf('.');
        if(index<0)
        {
            return null;
        }
		return fileName.substring(index+1).toLowerCase();
	}
	public static boolean isImage(String imgName) {
		System.out.println("imgName=="+imgName);
		if (imgName.endsWith(".jpg")||imgName.endsWith(".JPG")||imgName.endsWith(".jpeg")||imgName.endsWith(".JPEG")||imgName.endsWith(".png")||imgName.endsWith(".PNG")) {
			return true;
		}
		return false;
	}

	/**
	 * @param path 文件下载路径
	 */
	public static void download(HttpServletResponse response, String path) {
		path=path.replace("\\","/");
		//1.用response获取outputstream
		ServletOutputStream output = null;
		try {
			output = response.getOutputStream();
			//2.获取inputstream
			//下面代码是通过相对路径来获取绝对路径
			InputStream in=new FileInputStream(path);
			if(in==null)
				return;
			int len=0;
			byte[] buffer=new byte[1024];
			while((len=in.read(buffer))>0) {
				output.write(buffer,0, len);
			}
			in.close();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
