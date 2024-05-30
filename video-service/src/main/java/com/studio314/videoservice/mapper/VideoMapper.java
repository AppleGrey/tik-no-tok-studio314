package com.studio314.videoservice.mapper;

import com.studio314.videoservice.domain.dto.VideoPreDTO;
import com.studio314.videoservice.domain.pojo.Video;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface VideoMapper {

    @Select("select (vID, vTitle, coverUrl, state) from video where uID = #{uID} and !idDel limit #{page}, #{size}")
    List<VideoPreDTO> getMyVideo(int uID, int page, int size);

    @Update("update video set vLikes=vLikes+1 where vID = #{vID} and uID = #{uID}")
    void likeVideo(int vID);

    @Update("update video set vLikes=vLikes-1 where vID = #{vID} and uID = #{uID}")
    void cancelLike(int vID);

    @Select("select * from video where vID = #{vID}")
    Video selectById(int vID);

    @Update("update video set isDel = true where vID = #{vid}")
    void updateDelById(Long vid);
}
