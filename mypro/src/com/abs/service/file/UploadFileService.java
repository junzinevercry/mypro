package com.abs.service.file;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abs.dao.file.UploadFileDao;
import com.abs.entity.file.UploadFile;

@Service
public class UploadFileService {
    @Autowired
    private UploadFileDao uploadFileDao;

    public UploadFile findUploadFileById(String id) {
        return this.uploadFileDao.findObjectById(UploadFile.class, id);
    }

    public void createUploadFile(UploadFile uploadFile) {
        uploadFileDao.createObject(uploadFile);
    }

    public void deleteUploadFile(String fileId) {
        if (StringUtils.isNotEmpty(fileId)) {
            UploadFile file = uploadFileDao.findObjectById(UploadFile.class, fileId);
            //            String filePath = file.getFilePath();
            uploadFileDao.deleteObject(file);
        }
    }
}
