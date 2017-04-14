package com.heb.core;

import com.heb.link.Propt;

/**
 * 【Describe】 :  ---------  word 转 图片 （中转了PDF）  -----------
 * 【 Author 】 :           YHBK
 * 【  Time  】 :     2017/4/13 0013 18:52
 * ----------------------------------------------------
 */
public class Word2image {

    /**
     *  word 转 Image
      * @param propt
     */
    public static void wordToImage(Propt propt){

        try {
            //1.先生成 PDF
            String pdfUrl = Word2PDF.wordToPDF(propt);
            propt.setFroFileName(pdfUrl);
            propt.setRvSource(propt.isRvPDF());

            //PDF 生成 Image
            if(propt.isJonctionImage()){
                PDF2image.pdf2multiImage(propt);
            }else{
                PDF2image.buildOneImage(propt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
