package com.hangjiang.action.release.task;

import com.hangjiang.action.domain.release.ReleaseContext;
import com.hangjiang.action.service.IBusiness;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by jianghang on 2017/5/29.
 */
//@Component
//@Scope("prototype")
public class PostLogicBusiness implements IBusiness {

    private static Logger logger = LoggerFactory.getLogger(PostLogicBusiness.class);

    @Override
    public void execute(ReleaseContext releaseContext) {
        logger.info("post.........");
    }

    @Override
    public boolean isSkip() {
        return false;
    }
}
