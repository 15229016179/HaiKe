package com.haike.web.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;

/**
 * Created by bruce on 2015/6/30.
 */
public class FileUtil {

    /**
     * 日志组件
     */
    private static final Log LOG = LogFactory.getLog(FileUtil.class);

    /**
     * 文本文件
     */
    public static final String FILE_TYPE_TEXT = ".txt";

    /**
     * EXCEL表格
     */
    public static final String FILE_TYPE_EXCEL = ".xls";

    public static final String FILE_TYPE_EXCEL_1 = ".xlsx";

    /**
     * word文本文件
     */
    public static final String FILE_TYPE_WORD = ".doc";

    /**
     * 新建一个文件
     *
     * @param filePath
     * @param fileType
     * @return
     */
    public static File mkdir(String filePath, String fileType) {
        try {
            File folder = new File(filePath);
            if (!folder.exists() && !folder.isDirectory()) {
                //文件夹不存在
                if (LOG.isInfoEnabled()) {
                    LOG.info("[创建目录]-" + filePath + "目录不存在,需要创建目录");
                }
                folder.mkdirs();
            }
            filePath += "\\" + PrimaryKeyUtil.get().toString() + fileType;
            File file = new File(filePath);
            if (!file.exists()) {
                //文件夹不存在
                if (LOG.isInfoEnabled()) {
                    LOG.info("[创建文件]-" + filePath + "文件不存在,需要创建文件");
                }
                file.createNewFile();
            }
            return file;
        } catch (Exception e) {
            if (LOG.isErrorEnabled()) {
                LOG.error(e.getMessage(), e);
            }
        }
        return null;
    }

}
