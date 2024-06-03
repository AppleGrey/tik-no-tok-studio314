package com.studio314.videoservice.mapper;

import com.studio314.tiknotokcommon.dto.VideoMsgDTO;
import com.studio314.videoservice.domain.dto.VideoPreDTO;
import com.studio314.videoservice.domain.pojo.Video;
import com.studio314.videoservice.domain.pojo.VideoTmp;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface VideoMapper {

    @Select("select (vID, vTitle, coverUrl, state) from video where uID = #{uID} and !isDel limit #{page}, #{size}")
    List<VideoPreDTO> getMyVideo(Long uID, int page, int size);

    @Update("update video set vLikes=vLikes+1 where vID = #{vID} and uID = #{uID}")
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
}
