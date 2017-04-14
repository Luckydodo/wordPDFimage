package com.heb.link;

/**
 * 【Describe】 :  ---------  用于设置属性  -----------
 * 【 Author 】 :           YHBK
 * 【  Time  】 :     2017/4/13 0013 18:26
 * ----------------------------------------------------
 */
public class Propt {

    /**
     * 源文件地址
     */
    private String froFileName;
    /**
     * 保存地址(转换成 PDF 时候是文件夹路径 不带文件名称)
     */
    private String toFileName;
    /**
     * 是否删除 源文件
     */
    private boolean isRvSource=Boolean.FALSE;
    /**
     * 是否删除 PDF
     */
    private boolean isRvPDF=Boolean.TRUE;
    /**
     * 图片格式
     */
    private String imageForma="jpg";
    /**
     * 是否组合成一张图片
     */
    private boolean isJonctionImage=Boolean.TRUE;
    /**
     * 文件名称
     */
    private String fileName;//文件名称

    public boolean isRvSource() {
        return isRvSource;
    }

    public void setRvSource(boolean rvSource) {
        isRvSource = rvSource;
    }

    public boolean isRvPDF() {
        return isRvPDF;
    }

    public void setRvPDF(boolean rvPDF) {
        isRvPDF = rvPDF;
    }

    public String getImageForma() {
        return imageForma;
    }

    public void setImageForma(String imageForma) {
        this.imageForma = imageForma;
    }

    public String getFroFileName() {
        return froFileName;
    }

    public void setFroFileName(String froFileName) {
        this.froFileName = froFileName;
    }

    public String getToFileName() {
        return toFileName;
    }

    public void setToFileName(String toFileName) {
        this.toFileName = toFileName;
    }

    public boolean isJonctionImage() {
        return isJonctionImage;
    }

    public void setJonctionImage(boolean jonctionImage) {
        isJonctionImage = jonctionImage;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
