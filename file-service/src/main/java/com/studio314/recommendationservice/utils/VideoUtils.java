package com.studio314.recommendationservice.utils;

import com.github.kokorin.jaffree.ffmpeg.FFmpeg;
import com.github.kokorin.jaffree.ffmpeg.FFmpegResult;
import com.github.kokorin.jaffree.ffmpeg.UrlInput;
import com.github.kokorin.jaffree.ffmpeg.UrlOutput;
import com.github.kokorin.jaffree.ffprobe.FFprobe;
import com.github.kokorin.jaffree.ffprobe.Format;
import com.github.kokorin.jaffree.ffprobe.Stream;

import java.util.List;

public class VideoUtils {

    public static Stream getBitrate(String filePath) {
        Stream stream = FFprobe.atPath()
                .setShowStreams(true)
                .setInput(filePath)
                .execute()
                .getStreams()
                .get(0);

        return stream;
    }

    public static void compressVideo(String inputFilePath, String outputFilePath, long bitrate, int frameRate) {
        FFmpegResult result = FFmpeg.atPath()
                .addInput(UrlInput.fromUrl(inputFilePath))
                .addOutput(UrlOutput.toUrl(outputFilePath))
                .addArguments("-b:v", String.valueOf(bitrate) + "k") // 设置目标视频码率
                .addArguments("-c:a", "copy") // 复制音频流，不改变音频质量
                .addArguments("-r", String.valueOf(frameRate)) // 设置帧率为30
                .execute();
    }

    public static boolean isVideoReadable(String filePath) {
        try {
            Format format = FFprobe.atPath()
                    .setShowFormat(true)
                    .setInput(filePath)
                    .execute()
                    .getFormat();

            // 如果能够成功获取到视频的格式信息，那么视频文件应该是可以被正常读取的
            return format != null;
        } catch (Exception e) {
            // 如果在尝试获取视频的格式信息时抛出了异常，那么视频文件可能无法被正常读取
            return false;
        }

//        try {
//            List<Stream> streams = FFprobe.atPath()
//                    .setShowStreams(true)
//                    .setInput(filePath)
//                    .execute()
//                    .getStreams();
//
//            // 检查是否存在视频流
//            for (Stream stream : streams) {
//                if ("video".equals(stream.getCodecType())) {
//                    return true;
//                }
//            }
//
//            // 如果没有找到视频流，那么文件可能不是一个视频文件
//            return false;
//        } catch (Exception e) {
//            // 如果在尝试获取视频的流信息时抛出了异常，那么文件可能无法被正常读取
//            return false;
//        }
    }

    public static void main(String[] args) {
        compressVideo("E:/video/4.mp4", "E:/video/com.mp4", 300, 25);
//        System.out.println(getBitrate("E:/video/4.mp4"));
    }

}
