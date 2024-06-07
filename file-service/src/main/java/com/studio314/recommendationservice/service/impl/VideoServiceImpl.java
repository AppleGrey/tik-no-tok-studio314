package com.studio314.recommendationservice.service.impl;

import com.studio314.recommendationservice.domain.pojo.VideoTmp;
import com.studio314.recommendationservice.mapper.VideoMapper;
import com.studio314.recommendationservice.service.VideoService;
import com.studio314.recommendationservice.utils.VideoUtils;
import com.studio314.tiknotokcommon.utils.Result;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

@Service
@Slf4j
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
        String uuid = UUID.randomUUID().toString();
        String uuidFileName = uuid + fileExtension;
        File dest = new File(uploadDir + File.separator + uuidFileName);
        videoMapper.addTempVideo(uuid, uID, uuidFileName);
        try {
            log.info("文件上传中，上传文件路径：" + dest.getPath());
            file.transferTo(dest);
            log.info("文件上传成功");

            // 判断视频格式是否正确
            if (!VideoUtils.isVideoReadable(dest.getPath())) {
                // 删除上传的文件
                dest.delete();
                return Result.error("视频格式不正确");
            }
            return Result.success(
                    new HashMap<>(){
                        {
                            put("UUID", uuid);
                        }
                    }
            );
        } catch (IOException e) {
            e.printStackTrace();
            log.error("文件上传失败");
            return Result.error("上传失败");
        }
    }

    @Override
    public boolean checkVideo(Long uID, String uuid) {
        VideoTmp tmpVideo = videoMapper.getTmpVideo(uuid);
        return tmpVideo != null && tmpVideo.getUID() == uID;
    }

    @Override
    public VideoTmp getVideoUuid(Long vID) {
        VideoTmp tmpVideo = videoMapper.getTmpVideoByVID(vID);
        return tmpVideo;
    }
}
