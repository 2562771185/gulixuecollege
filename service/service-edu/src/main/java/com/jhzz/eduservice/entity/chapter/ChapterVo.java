package com.jhzz.eduservice.entity.chapter;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: Huanzhi
 * \* Date: 2022/5/28
 * \* Time: 22:22
 * \* Description:
 * \
 */
@Data

public class ChapterVo implements Serializable {
    private String id;
    private String title;
    /**
     * 表示小节
     */
    List<VideoVo> videoList;
}
