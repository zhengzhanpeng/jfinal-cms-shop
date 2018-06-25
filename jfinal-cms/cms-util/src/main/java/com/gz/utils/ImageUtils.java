package com.gz.utils;

/**
 * Copyright (c) 2015-2016, Michael Yang 杨福海 (fuhai999@gmail.com).
 *
 * Licensed under the GNU Lesser General Public License (LGPL) ,Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class ImageUtils {

	public static int[] ratio(String src) throws IOException {
		BufferedImage bufferedImage = ImageIO.read(new File(src));
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		return new int[] { width, height };
	}

	public static String ratioAsString(String src) throws IOException {
		File file = new File(src);
		if (!file.exists()) {
			return null;
		}
		BufferedImage bufferedImage = ImageIO.read(file);
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		return String.format("%s x %s", width, height);
	}

	public static String scale(String src, int w, int h) throws IOException {
		int inserTo = src.lastIndexOf(".");
		String dest = src.substring(0, inserTo) + String.format("_%sx%s", w, h) + src.substring(inserTo, src.length());
		scale(src, dest, w, h);
		return dest;
	}

	/**
	 * 等比缩放，居中剪切
	 * 
	 * @param src
	 * @param dest
	 * @param w
	 * @param h
	 * @throws IOException
	 */
	public static void scale(String src, String dest, int w, int h) throws IOException {
		String srcSuffix = src.substring(src.lastIndexOf(".") + 1);
		Iterator<ImageReader> iterator = ImageIO.getImageReadersByFormatName(srcSuffix);
		ImageReader reader = (ImageReader) iterator.next();

		InputStream in = new FileInputStream(src);
		ImageInputStream iis = ImageIO.createImageInputStream(in);
		reader.setInput(iis);

		BufferedImage srcBuffered = readBuffereImage(reader, w, h);
		BufferedImage targetBuffered = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

		Graphics graphics = targetBuffered.getGraphics();
		graphics.drawImage(srcBuffered.getScaledInstance(w, h, Image.SCALE_DEFAULT), 0, 0, null); // 绘制缩小后的图

		graphics.dispose();
		srcBuffered.flush();

		ImageIO.write(targetBuffered, srcSuffix, new File(dest));
		targetBuffered.flush();
	}

	private static BufferedImage readBuffereImage(ImageReader reader, int w, int h) throws IOException {
		ImageReadParam param = reader.getDefaultReadParam();
		int srcWidth = reader.getWidth(0);
		int srcHeight = reader.getHeight(0);

		Rectangle rect = null;

		if (w / h > srcWidth / srcHeight) {
			h = h * srcWidth / w;
			w = srcWidth;
			rect = new Rectangle(0, (srcHeight - h) / 2, w, h);
		} else {
			w = w * srcHeight / h;
			h = srcHeight;
			rect = new Rectangle((srcWidth - w) / 2, 0, w, h);
		}
		param.setSourceRegion(rect);
		BufferedImage srcBuffered = reader.read(0, param);
		return srcBuffered;
	}

	public final static void pressImage(String watermarkImg, String srcImageFile) {
		pressImage(watermarkImg, srcImageFile, srcImageFile, 5, -1, -1, 0.2f, 1);
	}

	public final static void pressImage(String watermarkImg, String srcImageFile, String destImageFile) {
		pressImage(watermarkImg, srcImageFile, destImageFile, 5, -1, -1, 0.2f, 1);
	}

	public final static void pressImage(String watermarkImg, String srcImageFile, String destImageFile, int position,
			float alpha) {
		pressImage(watermarkImg, srcImageFile, destImageFile, position, -1, -1, 0.2f, alpha);
	}

	/**
	 * @param watermarkImg
	 *            水印图片位置
	 * @param srcImageFile
	 *            源图片位置
	 * @param destImageFile
	 *            生成的图片位置
	 * @param position
	 *            水印打印的位置： 1->左上角，2->右上角，1->居中，1->左下角，1->右下角
	 * @param xOffset
	 *            x轴偏移量，xOffset小于0，自动偏移
	 * @param yOffset
	 *            y轴偏移量，yOffset小于0，自动偏移
	 * @param radio
	 *            默认为原图的 1/4
	 * @param alpha
	 *            透明度（0~1），PNG图片建议设置为1
	 */
	public final static void pressImage(String watermarkImg, String srcImageFile, String destImageFile, int position,
			int xOffset, int yOffset, float radio, float alpha) {
		try {
			File img = new File(srcImageFile);
			Image src = ImageIO.read(img);
			int srcWidth = src.getWidth(null);
			int srcHeight = src.getHeight(null);

			BufferedImage image = new BufferedImage(srcWidth, srcHeight, BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics = image.createGraphics();
			graphics.drawImage(src, 0, 0, srcWidth, srcHeight, null);

			// 水印文件
			Image wmImage = ImageIO.read(new File(watermarkImg));
			int wmWidth = wmImage.getWidth(null);
			int wmHeight = wmImage.getHeight(null);

			radio = radio <= 0 ? 0.2f : radio;
			int newWidth = (int) (srcWidth * radio);
			int newHeight = (int) (wmHeight * (newWidth / (float) wmWidth));

			xOffset = (xOffset < 0) ? (int) (newWidth * 0.1f) : xOffset;
			yOffset = (yOffset < 0) ? (int) (newHeight * 0.1f) : yOffset;

			int xPostion = 0;
			int yPostion = 0;

			switch (position) {
			case 1:
				xPostion = xOffset;
				yPostion = yOffset;
				break;
			case 2:
				xPostion = (int) (srcWidth * (1 - radio) - xOffset);
				yPostion = yOffset;
				break;
			case 3:
				xPostion = (int) (srcWidth - newWidth) / 2;
				yPostion = (int) (srcHeight - newHeight) / 2;
				break;
			case 4:
				xPostion = xOffset;
				yPostion = (int) (srcHeight - newHeight - yOffset);
				break;
			case 5:
				xPostion = (int) (srcWidth * (1 - radio) - xOffset);
				yPostion = (int) (srcHeight - newHeight - yOffset);
				break;
			default:
				xPostion = xOffset;
				yPostion = yOffset;
				break;
			}

			graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
			graphics.drawImage(wmImage, xPostion, yPostion, newWidth, newHeight, null);
			// 水印文件结束
			graphics.dispose();
			ImageIO.write((BufferedImage) image, "JPEG", new File(destImageFile));
		} catch (Exception e) {
			System.out.println("ImageUtils pressImage error" + e);
		}
	}

	/**
	 * 给图片添加文字水印
	 * 
	 * @param pressText
	 *            水印文字
	 * @param srcImageFile
	 *            源图像地址
	 * @param destImageFile
	 *            目标图像地址
	 * @param fontName
	 *            水印的字体名称
	 * @param fontStyle
	 *            水印的字体样式
	 * @param color
	 *            水印的字体颜色
	 * @param fontSize
	 *            水印的字体大小
	 * @param x
	 *            修正值
	 * @param y
	 *            修正值
	 * @param alpha
	 *            透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
	 */
	public final static void pressText(String pressText, String srcImageFile, String destImageFile, String fontName,
			int fontStyle, Color color, int fontSize, int x, int y, float alpha) {
		try {
			File img = new File(srcImageFile);
			Image src = ImageIO.read(img);
			pressText(pressText, src, destImageFile, fontName, fontStyle, color, fontSize, x, y, alpha);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public final static void pressText(String pressText, Image src, String destImageFile, String fontName,
			int fontStyle, Color color, int fontSize, int x, int y, float alpha) {
		try {
			int width = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			g.drawImage(src, 0, 0, width, height, null);
			g.setColor(color);
			g.setFont(new Font(fontName, fontStyle, fontSize));
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
			// 在指定坐标绘制水印文字
			g.drawString(pressText, (width - (getLength(pressText) * fontSize)) / 2 + x, (height - fontSize) / 2 + y);
			g.dispose();
			ImageIO.write((BufferedImage) image, "PNG", new File(destImageFile));// 输出到文件流
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 计算text的长度（一个中文算两个字符）
	 * 
	 * @param text
	 * @return
	 */
	public final static int getLength(String text) {
		int length = 0;
		for (int i = 0; i < text.length(); i++) {
			if (new String(text.charAt(i) + "").getBytes().length > 1) {
				length += 2;
			} else {
				length += 1;
			}
		}
		return length / 2;
	}
	
	/**
	 * 同时添加图片和文字
	 * @param watermarkImg
	 *            水印图片位置
	 * @param srcImageFile
	 *            源图片位置
	 * @param destImageFile
	 *            生成的图片位置
	 * @param position
	 *            水印打印的位置： 1->左上角，2->右上角，3->居中，4->左下角，5->右下角
	 * @param xOffset
	 *            x轴偏移量，xOffset小于0，自动偏移
	 * @param yOffset
	 *            y轴偏移量，yOffset小于0，自动偏移
	 * @param radio
	 *            默认为原图的 1/4
	 * @param pressText
	 *            水印文字
	 * @param fontName
	 *            水印的字体名称
	 * @param fontStyle
	 *            水印的字体样式
	 * @param color
	 *            水印的字体颜色
	 * @param fontSize
	 *            水印的字体大小
	 * @param x
	 *            修正值
	 * @param y
	 *            修正值
	 * @param alpha
	 *            透明度（0~1），PNG图片建议设置为1
	 */
	public final static void pressImageAndText(Image  watermarkImg, String srcImageFile, String destImageFile, int position,
			int xOffset, int yOffset, float radio,String pressText, String fontName,
			int fontStyle, Color color, int fontSize, int x, int y, float alpha) {
		try {
			File img = new File(srcImageFile);
			Image src = ImageIO.read(img);
			int srcWidth = src.getWidth(null);
			int srcHeight = src.getHeight(null);

			BufferedImage image = new BufferedImage(srcWidth, srcHeight, BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics = image.createGraphics();
			graphics.drawImage(src, 0, 0, srcWidth, srcHeight, null);

			// 水印文件
//			Image wmImage = ImageIO.read(new File(watermarkImg));
			Image wmImage = watermarkImg;
			int wmWidth = wmImage.getWidth(null);
			int wmHeight = wmImage.getHeight(null);

			radio = radio <= 0 ? 0.2f : radio;
			int newWidth = (int) (srcWidth * radio);
			int newHeight = (int) (wmHeight * (newWidth / (float) wmWidth));

			xOffset = (xOffset < 0) ? (int) (newWidth * 0.1f) : xOffset;
			yOffset = (yOffset < 0) ? (int) (newHeight * 0.1f) : yOffset;

			int xPostion = 0;
			int yPostion = 0;

			switch (position) {
			case 1:
				xPostion = xOffset;
				yPostion = yOffset;
				break;
			case 2:
				xPostion = (int) (srcWidth * (1 - radio) - xOffset);
				yPostion = yOffset;
				break;
			case 3:
				xPostion = (int) (srcWidth - newWidth) / 2;
				yPostion = (int) (srcHeight - newHeight) / 2;
				break;
			case 4:
				xPostion = xOffset;
				yPostion = (int) (srcHeight - newHeight - yOffset);
				break;
			case 5:
				xPostion = (int) (srcWidth * (1 - radio) - xOffset);
				yPostion = (int) (srcHeight - newHeight - yOffset);
				break;
			default:
				xPostion = xOffset;
				yPostion = yOffset;
				break;
			}

			// 水印图片
			graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
			graphics.drawImage(wmImage, xPostion, yPostion, newWidth, newHeight, null);
			
			// 水印文字
			graphics.setColor(color);
			graphics.setFont(new Font(fontName, fontStyle, fontSize));
			graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
			// 在指定坐标绘制水印文字
			graphics.drawString(pressText, (srcWidth - (getLength(pressText) * fontSize)) / 2 + x, (srcHeight - fontSize) / 2 + y);
			graphics.dispose();
			ImageIO.write((BufferedImage) image, "JPEG", new File(destImageFile));// 输出到文件流
			
		} catch (Exception e) {
			System.out.println("ImageUtils pressImage error" + e);
		}
	}

}