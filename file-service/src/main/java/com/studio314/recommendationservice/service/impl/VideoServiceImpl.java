package com.studio314.recommendationservice.service.impl;

import com.studio314.recommendationservice.domain.pojo.VideoTmp;
import com.studio314.recommendationservice.mapper.VideoMapper;
import com.studio314.recommendationservice.service.VideoService;
import com.studio314.tiknotokcommon.utils.Result;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    VideoMapper videoMapper;

    @Value("${me.file.upload-path}")
    private String uploadDir;

    @PostConstruct
    public void init() {
        // 打印当前工作目录和上传目录
//        System.out.println("Current working directory: " + System.getProperty("user.dir"));
//        System.out.println("Upload directory: " + uploadDir);

        // 创建上传目录
        File uploadDirFile = new File(uploadDir);
        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdirs();
        }
    }

    @Override
    public Result uploadVideo(MultipartFile file, Long uID) {
        if (file.isEmpty()) {
            return Result.error("上传失败，请选择一个文件。");
        }

        String originalFileName = file.getOriginalFilename();
        String fileExtension = "";

        if (originalFileName != null && originalFileName.contains(".")) {
            fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        }

        // 生成 UUID 作为文件名
        String uuidFileName = UUID.randomUUID().toString() + fileExtension;
        File dest = new File(uploadDir + File.separator + uuidFileName);
        videoMapper.addTempVideo(uuidFileName, uID, dest.getPath());
        try {
            file.transferTo(dest);
            return Result.success("上传成功");
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error("上传失败");
        }
    }

    @Override
    public boolean checkVideo(Long uID, String uuid) {
        VideoTmp tmpVideo = videoMapper.getTmpVideo(uuid);
        return tmpVideo != null && tmpVideo.getUID() == uID;
    }
}
