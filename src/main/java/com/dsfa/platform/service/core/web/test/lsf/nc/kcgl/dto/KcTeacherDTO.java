package com.dsfa.platform.service.core.web.test.lsf.nc.kcgl.dto;

import java.util.Date;

/**
 * @ClassName KcDTO
 * @Description TODO
 * @Author pocky
 * @Date 2021/7/21
 **/
public class KcTeacherDTO {
    private String name;

    private String timeDuration;

    private String score;

    private String hits;

    private Date timeRelation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimeDuration() {
        return timeDuration;
    }

    public void setTimeDuration(String timeDuration) {
        this.timeDuration = timeDuration;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getHits() {
        return hits;
    }

    public void setHits(String hits) {
        this.hits = hits;
    }

    public Date getTimeRelation() {
        return timeRelation;
    }

    public void setTimeRelation(Date timeRelation) {
        this.timeRelation = timeRelation;
    }
}
