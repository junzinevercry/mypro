package com.abs.dao.file;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.abs.dao.base.BaseDao;
import com.abs.entity.file.UploadFile;

/**
 * <P><B>说明：</B>描述这个类的作用</P>
 * <P><B>日期：</B>2013-6-6 下午5:24:18</P>
 * @author zhangjun
 * @version 1.0
 */
@Repository
public class UploadFileDao extends BaseDao {
    public List<UploadFile> listUploadFileForIds(String ids){
        StringBuilder sql = new StringBuilder("select * from upload_file where id in(").append(ids).append(")");
        return this.listObject(UploadFile.class, sql.toString());
    }
    
}
