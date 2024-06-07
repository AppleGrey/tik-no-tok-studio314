package com.studio314.videoservice.mapper;

import com.studio314.tiknotokcommon.dto.VideoMsgDTO;
import com.studio314.videoservice.domain.dto.VideoPreDTO;
import com.studio314.videoservice.domain.pojo.Video;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface VideoMapper {

    @Select("select vID, vTitle, coverUrl, state from video where uID = #{uID} and !isDel limit #{offset}, #{size}")
    List<VideoPreDTO> getMyVideo(Long uID, int offset, int size);

    @Update("update video set vLikes=vLikes+1 where vID = #{vID}")
    void likeVideo(Long vID);

    @Update("update video set vLikes=vLikes-1 where vID = #{vID} and uID = #{uID}")
    void cancelLike(Long vID);

    @Select("select * from video where vID = #{vID}")
    Video selectById(Long vID);

    @Update("update video set isDel = true where vID = #{vid}")
    void updateDelById(Long vid);

//    @Insert("insert into temp_video (uuid, uid, path) values (#{uuid}, #{uid}, #{path})")
//    void addTempVideo(String uuid, Long uid, String path);
//
//    @Select("select * from temp_video where UUID = #{uuid}")
//    VideoTmp getTmpVideo(String uuid);

    @Options(useGeneratedKeys = true, keyProperty = "vID")
    @Insert("insert into video (vTitle, vProfile, uID) values (#{vTitle}, #{vProfile}, #{uID})")
    void publishVideo(Video video);

    @Update("update video set coverUrl = #{coverUrl}, vUrl=#{vUrl}, state=#{state} where vID = #{vID}")
    void updateVideo(Video video);

    @Select("SELECT v.vID, v.vTitle, v.coverUrl, v.state " +
            "FROM video v " +
            "WHERE state='已发布' and v.vID NOT IN (SELECT h.vID FROM history h WHERE h.uID = #{uID}) " +
            "ORDER BY v.vLikes DESC " +
            "LIMIT #{offset}, #{size}")
    @Results({
            @Result(property = "vID", column = "vID"),
            @Result(property = "vTitle", column = "vTitle"),
            @Result(property = "coverUrl", column = "coverUrl"),
            @Result(property = "state", column = "state")
    })
    List<VideoPreDTO> getRecommendedVideos(Long uID, int offset, int size);

    @Update("update video set vViews=vViews+1 where vID = #{vID}")
    void updateView(Long vID);

    @Insert("insert into history (uID, vID) values (#{uID}, #{vID})")
    void updateHistory(Long uID, Long vID);

    @Select("SELECT vID, vTitle, coverUrl, state " +
            "FROM video " +
            "ORDER BY vLikes DESC " +
            "LIMIT #{offset}, #{size}")
    List<VideoPreDTO> getRecommendedVideos2(int offset, int size);
}
