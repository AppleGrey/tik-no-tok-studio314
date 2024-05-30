package com.studio314.videoservice.service.impl;

import com.studio314.tiknotokcommon.utils.Result;
import com.studio314.videoservice.domain.dto.VideoPreDTO;
import com.studio314.videoservice.mapper.VideoMapper;
import com.studio314.videoservice.domain.pojo.Video;
import com.studio314.videoservice.service.VideoService;
import com.studio314.videoservice.utils.Constant;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    VideoMapper videoMapper;

    private final String uploadDir = Constant.UPLOAD_DIR.getValue();

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
    public Result getMyVideo(int uID, int page, int size) {
        List<VideoPreDTO> myVideo = videoMapper.getMyVideo(uID, page, size);
        if (myVideo != null) {
            return Result.success(myVideo);
        }
        return null;
    }

    @Override
    public Result deleteVideo(int uID, int vID) {
        Video video = videoMapper.selectById(vID);
        if (video != null && video.getUID() == uID) {
            video.setDel(true);
            videoMapper.updateDelById(video.getVID());
            return Result.success();
        }
        return Result.error("您没有执行此操作的权限");
    }

    @Override
    public Result getVideo(int vID) {
        Video video = videoMapper.selectById(vID);
        if (video != null) {
            return Result.success(video);
        }
        return Result.error("视频不存在");
    }

    @Override
    public Result uploadVideo(MultipartFile file, int uid){
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
        videoMapper.addTempVideo(uuidFileName, uid, dest.getPath());
        try {
            file.transferTo(dest);
            return Result.success("上传成功");
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error("上传失败");
        }
    }

}
