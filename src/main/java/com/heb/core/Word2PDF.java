package com.heb.core;

import com.heb.link.Propt;
import com.heb.util.FileUtil;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

import java.io.File;

/**
 * 【Describe】 :  ---------  word 转 PDF 文件  -----------
 * 【 Author 】 :           YHBK
 * 【  Time  】 :     2017/4/13 0013 18:25
 * ----------------------------------------------------
 */
public class Word2PDF {

    static final int wdFormatPDF = 17;// PDF 格式

    /**
     *  必须要安装 SaveAsPDFandXPS.exe
     * return PDF url
     * @param propt
     */
    public static String wordToPDF(Propt propt){
        System.out.println("启动Word...");
        long start = System.currentTimeMillis();
        ActiveXComponent app = null;
        Dispatch doc = null;
        try {
            app = new ActiveXComponent("Word.Application");
            app.setProperty("Visible", new Variant(false));
            Dispatch docs = app.getProperty("Documents").toDispatch();

            doc = Dispatch.call(docs, "Open", propt.getFroFileName()).toDispatch();
            System.out.println("打开文档..." + propt.getFroFileName());
            System.out.println("转换文档到PDF..." + propt.getToFileName());
            File tofile = new File(propt.getToFileName());
            if (!tofile.exists()) {
                tofile.mkdir();
            }
            Dispatch.call(doc, "SaveAs", propt.getToFileName()+propt.getFileName(), wdFormatPDF);
            long end = System.currentTimeMillis();
            System.out.println("转换完成..用时：" + (end - start) + "ms.");

        } catch (Exception e) {
            System.out.println("========Error:文档转换失败：" + e.getMessage());
        } finally {
            Dispatch.call(doc, "Close", false);
            System.out.println("关闭文档");
            if (app != null)
                app.invoke("Quit", new Variant[] {});
        }
        // 如果没有这句话,winword.exe进程将不会关闭
        ComThread.Release();

        // 删除 源文件
        if(propt.isRvSource()){
            FileUtil.delete(propt.getFroFileName());
        }
        return propt.getToFileName()+propt.getFileName()+".pdf";
    }


}
