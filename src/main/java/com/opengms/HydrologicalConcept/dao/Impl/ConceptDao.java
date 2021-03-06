package com.opengms.HydrologicalConcept.dao.Impl;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Component
public class ConceptDao {
    private static final String basePath = System.getProperty("user.dir") +"\\src\\main\\resources\\static\\upload\\conceptMap";

    public void saveConcept2File(MultipartFile mfile) {
        String folderPath = basePath;
        File folder = new File(folderPath);
        if (!folder.exists())
        {
            folder.mkdirs();
        }
        File file = new File(folderPath,mfile.getResource().getFilename());
        try {
            mfile.transferTo(file);
        } catch (IOException e) {
            System.out.println("文件转换出错了！");
            e.printStackTrace();
        }

    }
}
