package com.heb.link;

/**
 * 【Describe】 :  ---------  生成二维码参数  -----------
 * 【 Author 】 :           YHBK
 * 【  Time  】 :     2017/4/13 0013 20:02
 * ----------------------------------------------------
 */
public class QrPropt {

    private int width = 100; // 二维码图片宽度
    private int height = 100; // 二维码图片高度
    private String format = "gif";// 二维码的图片格式
    private String outPath;//存放地址
    private String cotent;//内容

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getOutPath() {
        return outPath;
    }

    public void setOutPath(String outPath) {
        this.outPath = outPath;
    }

    public String getCotent() {
        return cotent;
    }

    public void setCotent(String cotent) {
        this.cotent = cotent;
    }
}
