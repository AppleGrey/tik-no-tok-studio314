package com.studio314.recommendationservice.controller;

import com.studio314.recommendationservice.domain.pojo.VideoTmp;
import com.studio314.recommendationservice.service.VideoService;
import com.studio314.tiknotokcommon.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/file/videos")
public class VideoController {

    @Autowired
    VideoService videoService;

    @Value("${me.file.upload-path}")
    private String uploadDir;

    @PostMapping
    public Result uploadVideo(@RequestParam("file") MultipartFile file, @RequestHeader("userID") Long uID){
        return videoService.uploadVideo(file, uID);
    }

    @GetMapping("/check")
    public boolean checkVideo(@RequestParam("UUID") String UUID,
                              @RequestParam("uID") Long uID){
        return videoService.checkVideo(uID, UUID);
    }

    /**
     * 获取视频
     */
    @GetMapping("/{vID}")
    public ResponseEntity<StreamingResponseBody> getVideo(@RequestParam(required = false) String token, @RequestParam(required = false) Long com, @PathVariable("vID") Long vID, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("getVideo");
        //获取视频信息
        VideoTmp tmpVideo = videoService.getVideoUuid(vID);
        if (tmpVideo == null) {
            return ResponseEntity.notFound().build();
        }

        try {
            // 假设视频文件存储在 "videos" 目录下，以 vID 作为文件名
            Path videoPath = null;
            if(com != null && com == 1 && tmpVideo.getCompress() != null) {
                videoPath = Paths.get(uploadDir, tmpVideo.getCompress());
            } else {
                videoPath = Paths.get(uploadDir, tmpVideo.getPath());
            }
            Resource video = new UrlResource(videoPath.toUri());

            if (video.exists() || video.isReadable()) {
                long rangeStart;
                long rangeEnd;
                byte[] buffer = new byte[1024];
                long totalBytes = Files.size(videoPath);

                if (request.getHeader("Range") != null) {
                    String rangeValue = request.getHeader("Range").substring("bytes=".length());
                    int dash = rangeValue.indexOf('-');
                    rangeStart = Long.parseLong(rangeValue.substring(0, dash));
                    if (dash < rangeValue.length() - 1) {
                        rangeEnd = Long.parseLong(rangeValue.substring(dash + 1));
                    } else {
                        rangeEnd = totalBytes - 1;
                    }
                } else {
                    rangeStart = 0;
                    rangeEnd = totalBytes - 1;
                }

                long contentLength = rangeEnd - rangeStart + 1;
                response.setHeader("Content-Type", "video/mp4");
                response.setHeader("Content-Length", contentLength + "");
                response.setHeader("Content-Range", "bytes " + rangeStart + "-" + rangeEnd + "/" + totalBytes);
                response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);

                InputStream videoStream = video.getInputStream();

                StreamingResponseBody stream = out -> {
                    try {
                        videoStream.skip(rangeStart);

                        long bytesRead;
                        long bytesLeft = contentLength;
                        while ((bytesRead = videoStream.read(buffer)) != -1 && bytesLeft > 0) {
                            out.write(buffer, 0, (int) bytesRead);
                            bytesLeft -= bytesRead;
                        }
                    } catch (IOException e) {
                        throw new RuntimeException("IOException in StreamingResponseBody", e);
                    }
                };

                return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                        .header(HttpHeaders.CONTENT_TYPE, "video/mp4")
                        .body(stream);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * 获取图片
     */
    @GetMapping("/cover/{vID}")
    public ResponseEntity<StreamingResponseBody> getCover(@PathVariable("vID") Long vID, HttpServletRequest request, HttpServletResponse response) {
        //获取视频信息
        VideoTmp tmpVideo = videoService.getVideoUuid(vID);
        if (tmpVideo == null) {
            return ResponseEntity.notFound().build();
        }

        try {
            // 假设封面图片存储在 "covers" 目录下，以 vID 作为文件名
            Path coverPath = Paths.get(uploadDir, tmpVideo.getUUID() + ".jpg");
            Resource cover = new UrlResource(coverPath.toUri());

            if (cover.exists() || cover.isReadable()) {
                long totalBytes = Files.size(coverPath);
                byte[] buffer = new byte[1024];

                response.setHeader("Content-Type", "image/jpeg");
                response.setHeader("Content-Length", totalBytes + "");

                InputStream coverStream = cover.getInputStream();

                StreamingResponseBody stream = out -> {
                    try {
                        long bytesRead;
                        while ((bytesRead = coverStream.read(buffer)) != -1) {
                            out.write(buffer, 0, (int) bytesRead);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException("IOException in StreamingResponseBody", e);
                    }
                };

                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                        .body(stream);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
