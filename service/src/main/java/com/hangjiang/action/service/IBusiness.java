package com.hangjiang.action.service;

import com.hangjiang.action.domain.release.ReleaseContext;

/**
 * Created by jianghang on 2017/5/29.
 */
public interface IBusiness {
    void execute(ReleaseContext context);
    boolean isSkip();
}
