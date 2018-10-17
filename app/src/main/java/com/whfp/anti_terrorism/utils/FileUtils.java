package com.whfp.anti_terrorism.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class FileUtils {

	/**
	 * TXT存储目录:外部存储路径+APP工程名+“TXT”+项目名称项目ID
	 */
	public static String SDPATH = Environment.getExternalStorageDirectory() + "/MONEY/JPG/";
	/**
	 * 获取内部存储目录:内部存储路径+
	 */
	public static String INTERNALPATH = Environment.getDataDirectory() + "/MONEY/";
	/**
	 * 数据库存储目录：外部存储路径+APP工程名+“DB”
	 */
	public static String DBPATH = Environment.getExternalStorageDirectory() + "/MONEY/DB/";

	public static final int SIZETYPE_B = 1;// 获取文件大小单位为B的double值
	public static final int SIZETYPE_KB = 2;// 获取文件大小单位为KB的double值
	public static final int SIZETYPE_MB = 3;// 获取文件大小单位为MB的double值
	public static final int SIZETYPE_GB = 4;// 获取文件大小单位为GB的double值

	/**
	 * 获得TXT文件的存储路径 TXT存储目录:外部存储路径+APP工程名+“TXT”+(项目ID+项目名称)
	 * 
	 * @param project_id
	 *            项目ID
	 * @param projectName
	 *            项目名称
	 * @return
	 */
	public static String getTxtPath(String project_id, String projectName) {
		final String path = SDPATH + projectName + project_id;
		Log.i("TXT存储的路径：", path);
		// 如果路径不存在就创建
		if (!FileUtils.fileIsExists(path)) {
			FileUtils.creatFolder(path);
		}
		return path;
	}

	/**
	 * 获取TXT文件名称
	 * 
	 * @param projectName
	 *            项目名称
	 * @return
	 */
	public static String getTxtName(String projectName) {
		String txtName = projectName + getTime(
				"yyyyMMddHHmmssS")/*
									 * +UUID.randomUUID().toString().replaceAll(
									 * "-", "").substring(0, 17)
									 */ + ".txt";
		Log.i("生成的TXT文件名是：", txtName);
		return txtName;
	}

	/**
	 * 生成32位ID
	 * 
	 * @return
	 */
	public static String generateID() {
		String id =(getTime("yyyyMMddHHmmssS")+ UUID.randomUUID().toString().replaceAll("-", "")).substring(0, 32);
		Log.i("生成的ID是：", id);
		return id;
	}

	/**
	 * 获取当前系统时间
	 * 
	 * @param type
	 *            自定义到哪，如：HH：mm
	 * @return
	 */
	public static String getTime(String type) {
		/* 获取当前系统时间 */
		SimpleDateFormat sdf = new SimpleDateFormat(type);
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		String time = sdf.format(curDate);
		return time;
	}

	/**
	 * 判断SDCard是否插入
	 *
	 * @return
	 */
	public static boolean isSDcard() {
		String status = Environment.getExternalStorageState();
		if (status.equals(Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 删除给定的File对象表示的文件或目录(路径下的所有文件及文件夹)
	 */
	public static void delete(File file) {
		if (file.isDirectory()) {
			File[] subs = file.listFiles();
			for (File sub : subs) {
				delete(sub);
			}
		}
		file.delete();
	}

	/**
	 * 获取文件指定文件的指定单位的大小
	 *
	 * @param filePath
	 *            文件路径
	 * @param sizeType
	 *            获取大小的类型1为B、2为KB、3为MB、4为GB
	 * @return double值的大小
	 */
	public static double getFileOrFilesSize(String filePath, int sizeType) {
		File file = new File(filePath);
		long blockSize = 0;
		try {
			if (file.isDirectory()) {
				blockSize = getFileSizes(file);
			} else {
				blockSize = getFileSize(file);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("获取文件大小", "获取失败!");
		}
		return FormetFileSize(blockSize, sizeType);
	}

	/**
	 * 获取指定文件大小
	 *
	 * @param file
	 * @return
	 * @throws Exception
	 */
	private static long getFileSize(File file) throws Exception {
		long size = 0;
		if (file.exists()) {
			FileInputStream fis = null;
			fis = new FileInputStream(file);
			size = fis.available();
		} else {
			file.createNewFile();
			Log.e("获取文件大小", "文件不存在!");
		}
		return size;
	}

	/**
	 * 获取指定文件夹
	 *
	 * @param f
	 * @return
	 * @throws Exception
	 */
	private static long getFileSizes(File f) throws Exception {
		long size = 0;
		File flist[] = f.listFiles();
		for (int i = 0; i < flist.length; i++) {
			if (flist[i].isDirectory()) {
				size = size + getFileSizes(flist[i]);
			} else {
				size = size + getFileSize(flist[i]);
			}
		}
		return size;
	}

	/**
	 * 转换文件大小,指定转换的类型
	 *
	 * @param fileS
	 * @param sizeType
	 * @return
	 */
	private static double FormetFileSize(long fileS, int sizeType) {
		DecimalFormat df = new DecimalFormat("#.00");
		double fileSizeLong = 0;
		switch (sizeType) {
		case SIZETYPE_B:
			fileSizeLong = Double.valueOf(df.format((double) fileS));
			break;
		case SIZETYPE_KB:
			fileSizeLong = Double.valueOf(df.format((double) fileS / 1024));
			break;
		case SIZETYPE_MB:
			fileSizeLong = Double.valueOf(df.format((double) fileS / 1048576));
			break;
		case SIZETYPE_GB:
			fileSizeLong = Double.valueOf(df.format((double) fileS / 1073741824));
			break;
		default:
			break;
		}
		return fileSizeLong;
	}

	/**
	 * 创建文件夹
	 *
	 * @param path
	 *            文件件路径
	 */
	public static void creatFolder(String path) {
		try {
			if (!isFileExist(path)) {
				File tempf = createSDDir(path);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将图片压缩保存到文件夹
	 *
	 * @param bm
	 * @param picName
	 */
	public static void saveBitmap(Bitmap bm, String picName) {
		try {

			// 如果没有文件夹就创建一个程序文件夹
			if (!isFileExist("")) {
				File tempf = createSDDir("");
			}
			File f = new File(SDPATH, picName + ".JPEG");
			// 如果该文件夹中有同名的文件，就先删除掉原文件
			if (f.exists()) {
				f.delete();
			}
			FileOutputStream out = new FileOutputStream(f);
			bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 质量压缩 并返回Bitmap
	 *
	 * @param image
	 *            要压缩的图片
	 * @return 压缩后的图片
	 */
	private Bitmap compressImage(Bitmap image) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
		int options = 100;
		while (baos.toByteArray().length / 1024 > 100) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
			baos.reset();// 重置baos即清空baos
			image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
			options -= 10;// 每次都减少10
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
		return bitmap;
	}

	/**
	 * 质量压缩
	 *
	 * @param bitmap
	 * @param picName
	 */
	public static void compressImageByQuality(final Bitmap bitmap, String picName) {
		// 如果没有文件夹就创建一个程序文件夹
		if (!isFileExist("")) {
			try {
				File tempf = createSDDir("");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		File f = new File(SDPATH, picName + ".JPEG");
		// 如果该文件夹中有同名的文件，就先删除掉原文件
		if (f.exists()) {
			f.delete();
		}

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int options = 100;
		// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
		bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
		// 循环判断如果压缩后图片是否大于200kb,大于继续压缩
		while (baos.toByteArray().length / 1024 > 500) {
			// 重置baos即让下一次的写入覆盖之前的内容
			baos.reset();
			// 图片质量每次减少5
			options -= 5;
			// 如果图片质量小于10，则将图片的质量压缩到最小值
			if (options < 0)
				options = 0;
			// 将压缩后的图片保存到baos中
			bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
			// 如果图片的质量已降到最低则，不再进行压缩
			if (options == 0)
				break;
		}
		// 将压缩后的图片保存的本地上指定路径中
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(new File(SDPATH, picName + ".JPEG"));
			fos.write(baos.toByteArray());
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/**
	 * 创建文件夹
	 *
	 * @param dirName
	 *            文件夹名称
	 * @return 文件夹路径
	 * @throws IOException
	 */
	public static File createSDDir(String dirName) throws IOException {
		File dir = new File(/* SDPATH + */dirName);
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

			System.out.println("createSDDir:" + dir.getAbsolutePath());
			System.out.println("createSDDir:" + dir.mkdirs());
		}
		return dir;
	}

	/**
	 * 判断改文件是否是一个标准文件
	 *
	 * @param fileName
	 *            判断的文件路径
	 * @return 判断结果
	 */
	public static boolean isFileExist(String fileName) {
		File file = new File(SDPATH + fileName);
		file.isFile();
		return file.exists();
	}

	/**
	 * 删除指定文件
	 *
	 * @param filePath
	 */
	public static void delFile(String filePath) {
		File file = new File(filePath);
		if (file.isFile()) {
			file.delete();
			Log.i("TAG", "delFile: 删除成功！");
		}
	}

	/**
	 * 删除指定文件夹中的所有文件
	 */
	public static void deleteDir() {
		File dir = new File(SDPATH);
		if (dir == null || !dir.exists() || !dir.isDirectory())
			return;

		for (File file : dir.listFiles()) {
			if (file.isFile())
				file.delete();
			else if (file.isDirectory())
				deleteDir();
		}
		dir.delete();
	}

	/**
	 * 删除指定文件夹中的所有文件
	 * 
	 * @param path
	 *            要删除的文件夹路径
	 */
	public static void deleteDir(String path) {
		File dir = new File(path);
		if (dir == null || !dir.exists() || !dir.isDirectory())
			return;

		for (File file : dir.listFiles()) {
			if (file.isFile())
				file.delete();
			else if (file.isDirectory())
				deleteDir();
		}
		dir.delete();
	}

	/**
	 * 判断是否存在该文件
	 *
	 * @param path
	 *            文件路径
	 * @return
	 */
	public static boolean fileIsExists(String path) {
		try {
			File f = new File(path);
			if (!f.exists()) {
				return false;
			}
		} catch (Exception e) {

			return false;
		}
		return true;
	}

}
