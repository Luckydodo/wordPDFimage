package com.heb.core;

import com.heb.link.Propt;
import com.heb.util.FileUtil;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.util.PDFImageWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 【Describe】 :  ---------  PDF 转图片  -----------
 * 【 Author 】 :           YHBK
 * 【  Time  】 :     2017/4/13 0013 18:52
 * ----------------------------------------------------
 */
public class PDF2image {

    /**
     * PDF 转 图片 （多页则为多张图片 文件明后 a1.jpg a2.jpg...）
     * @param propt
     */
    public static void buildOneImage(Propt propt){
        try {
            File file = new File(propt.getFroFileName());
            PDDocument doc = PDDocument.load(file);
            PDFImageWriter imageWriter = new PDFImageWriter();

            int pageCount = doc.getNumberOfPages();
            for (int i = 1; i <= pageCount; i++) {
                // 将第 i 到 i 页(也就是第i页)转换成图片, 并存到文件中
                imageWriter.writeImage(doc, propt.getImageForma(), null, i, i,
                        propt.getToFileName()+propt.getFileName()+"."+propt.getImageForma());
            }

            //删除源文件 即 PDF 文件
            if(propt.isRvSource()){
                FileUtil.delete(propt.getFroFileName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将pdf中的maxPage页，转换成一张图片
     *
     */
    public static void pdf2multiImage(Propt propt) {
        try {
            InputStream is = new FileInputStream(propt.getFroFileName());
            PDDocument pdf = PDDocument.load(is, true);
            List<PDPage> pages = pdf.getDocumentCatalog().getAllPages();
            List<BufferedImage> piclist = new ArrayList<BufferedImage>();
            int actSize = pages.size(); // pdf中实际的页数

            for (int i = 0; i < actSize; i++) {
                piclist.add(pages.get(i).convertToImage());
            }
            yPic(piclist, propt.getToFileName()+propt.getFileName()+"."+propt.getImageForma());
            is.close();

            //删除源文件 即 PDF 文件
            if(propt.isRvSource()){
                FileUtil.delete(propt.getFroFileName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将宽度相同的图片，竖向追加在一起 ##注意：宽度必须相同
     *
     * @param piclist
     *            文件流数组
     * @param outPath
     *            输出路径
     */
    public static void yPic(List<BufferedImage> piclist, String outPath) {// 纵向处理图片
        if (piclist == null || piclist.size() <= 0) {
            System.out.println("图片数组为空!");
            return;
        }
        try {
            int height = 0, // 总高度
                    width = 0, // 总宽度
                    _height = 0, // 临时的高度 , 或保存偏移高度
                    __height = 0, // 临时的高度，主要保存每个高度
                    picNum = piclist.size();// 图片的数量
            int[] heightArray = new int[picNum]; // 保存每个文件的高度
            BufferedImage buffer = null; // 保存图片流
            List<int[]> imgRGB = new ArrayList<int[]>(); // 保存所有的图片的RGB
            int[] _imgRGB; // 保存一张图片中的RGB数据
            for (int i = 0; i < picNum; i++) {
                buffer = piclist.get(i);
                heightArray[i] = _height = buffer.getHeight();// 图片高度
                if (i == 0) {
                    width = buffer.getWidth();// 图片宽度
                }
                height += _height; // 获取总高度
                _imgRGB = new int[width * _height];// 从图片中读取RGB
                _imgRGB = buffer
                        .getRGB(0, 0, width, _height, _imgRGB, 0, width);
                imgRGB.add(_imgRGB);
            }
            _height = 0; // 设置偏移高度为0
            // 生成新图片
            BufferedImage imageResult = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < picNum; i++) {
                __height = heightArray[i];
                if (i != 0)
                    _height += __height; // 计算偏移高度
                imageResult.setRGB(0, _height, width, __height, imgRGB.get(i),
                        0, width); // 写入流中
            }
            File outFile = new File(outPath);
            ImageIO.write(imageResult, "jpg", outFile);// 写图片
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取文件页数
     * @param fileName
     * @return
     */
    public static int getNumberOfPages(String fileName){
        try {
            File file = new File(fileName);
            PDDocument doc = PDDocument.load(file);
            return doc.getNumberOfPages();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
