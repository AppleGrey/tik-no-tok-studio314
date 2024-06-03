package com.studio314.videoservice.service.impl;

import com.studio314.tiknotokcommon.dto.VideoMsgDTO;
import com.studio314.tiknotokcommon.utils.Result;
import com.studio314.videoservice.domain.dto.VideoPostDTO;
import com.studio314.videoservice.domain.dto.VideoPreDTO;
import com.studio314.videoservice.mapper.VideoMapper;
import com.studio314.videoservice.domain.pojo.Video;
import com.studio314.videoservice.service.VideoService;
//import com.studio314.videoservice.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    VideoMapper videoMapper;

//    private final String uploadDir = Constant.UPLOAD_DIR.getValue();

//    @PostConstruct
//    public void init() {
//        // 打印当前工作目录和上传目录
////        System.out.println("Current working directory: " + System.getProperty("user.dir"));
////        System.out.println("Upload directory: " + uploadDir);
//
//        // 创建上传目录
//        File uploadDirFile = new File(uploadDir);
//        if (!uploadDirFile.exists()) {
//            uploadDirFile.mkdirs();
//        }
//    }

    @Override
    public Result getMyVideo(Long uID, int page, int size) {
        List<VideoPreDTO> myVideo = videoMapper.getMyVideo(uID, page, size);
        if (myVideo != null) {
            return Result.success(myVideo);
        }
        return null;
    }

    @Override
    public Result deleteVideo(Long uID, Long vID) {
        Video video = videoMapper.selectById(vID);
        if (video != null && video.getUID() == uID) {
            video.setDel(true);
            videoMapper.updateDelById(video.getVID());
            return Result.success();
        }
        return Result.error("您没有执行此操作的权限");
    }

    @Override
    public Result getVideo(Long vID) {
        Video video = videoMapper.selectById(vID);
        if (video != null) {
            return Result.success(video);
        }
        return Result.error("视频不存在");
    }

//    @Override
//    public Result uploadVideo(MultipartFile file, Long uid){
//        if (file.isEmpty()) {
//            return Result.error("上传失败，请选择一个文件。");
//        }
//
//        String originalFileName = file.getOriginalFilename();
//        String fileExtension = "";
//
//        if (originalFileName != null && originalFileName.contains(".")) {
//            fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
//        }
//
//        // 生成 UUID 作为文件名
//        String uuidFileName = UUID.randomUUID().toString() + fileExtension;
//        File dest = new File(uploadDir + File.separator + uuidFileName);
//        videoMapper.addTempVideo(uuidFileName, uid, dest.getPath());
//        try {
//            file.transferTo(dest);
//            return Result.success("上传成功");
//        } catch (IOException e) {
//            e.printStackTrace();
//            return Result.error("上传失败");
//        }
//    }

    @Override
    public Result publishVideo(VideoPostDTO videoPostDTO) {
//        VideoTmp tmpVideo = videoMapper.getTmpVideo(videoPostDTO.getUUID());
//        if (tmpVideo == null || tmpVideo.getUID() != videoPostDTO.getUID()) {
//            return Result.error("视频不存在，请先上传视频");
//        }
        // todo: 通过远程调用请求文件服务判断文件是否存在
        Video video = new Video();
        video.setUID(videoPostDTO.getUID());
        video.setVTitle(videoPostDTO.getVTitle());
        video.setVProfile(videoPostDTO.getVProfile());
        videoMapper.publishVideo(video);
        return null;
    }

    /**
     * 更新视频信息
     *
     * @param videoMsg 从文件系统接收到的视频信息
     * @return 是否更新成功
     */
    @Override
    public boolean updateVideo(VideoMsgDTO videoMsg) {
        Video video = videoMapper.selectById(videoMsg.getVID());
        if (video == null) {
            return false;
        }
        video.setVUrl(videoMsg.getVUrl());
        video.setCoverUrl(videoMsg.getCoverUrl());
        video.setState("已发布");
        videoMapper.updateVideo(video);
        return true;
    }

}
