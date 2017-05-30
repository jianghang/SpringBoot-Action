package com.hangjiang.action.domain.release;

import org.springframework.util.StopWatch;

/**
 * Created by jianghang on 2017/5/29.
 */
public class ReleaseContext {
    private String name;
    private Integer type;
    private StopWatch stopWatch;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public StopWatch getStopWatch() {
        return stopWatch;
    }

    public void setStopWatch(StopWatch stopWatch) {
        this.stopWatch = stopWatch;
    }
}
