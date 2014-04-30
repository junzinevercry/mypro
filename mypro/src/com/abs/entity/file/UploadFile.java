package com.abs.entity.file;

import com.abs.entity.base.BaseEntity;
import com.abs.util.annotation.Columns;
import com.abs.util.annotation.Table;

/**
 * <P><B>说明：</B>描述这个类的作用</P>
 * <P><B>日期：</B>2013-6-6 下午5:26:58</P>
 * @author zhangjun
 * @version 1.0
 */
@Table("upload_file")
public class UploadFile extends BaseEntity {

    /** @Fields serialVersionUID : 描述这个变量表示什么 */
    private static final long serialVersionUID = 4031300591326234248L;
    /** file_name   varchar 300 0   -1  0   0   0   0       0       utf8    utf8_general_ci     0   0*/
    private String fileName;
    /** file_path   varchar 1000    0   -1  0   0   0   0       0       utf8    utf8_general_ci     0   0*/
    private String filePath;
    /** file_type   varchar 100 0   -1  0   0   0   0       0       utf8    utf8_general_ci     0   0*/
    private String fileType;

    /**
     * @return the fileName
     */
    @Columns("file_name")
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the filePath
     */
    @Columns("file_path")
    public String getFilePath() {
        return filePath;
    }

    /**
     * @param filePath the filePath to set
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * @return the fileType
     */
    @Columns("file_type")
    public String getFileType() {
        return fileType;
    }

    /**
     * @param fileType the fileType to set
     */
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

}
