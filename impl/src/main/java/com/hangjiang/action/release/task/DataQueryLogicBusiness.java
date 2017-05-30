package com.hangjiang.action.release.task;

import com.hangjiang.action.domain.release.ReleaseContext;
import com.hangjiang.action.service.IBusiness;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

/**
 * Created by jianghang on 2017/5/30.
 */
public class DataQueryLogicBusiness implements IBusiness,InitializingBean {

    private static Logger logger = LoggerFactory.getLogger(DataQueryLogicBusiness.class);

    @Override
    public void execute(ReleaseContext context) {
        logger.info("DataQuery.........");
    }

    @Override
    public boolean isSkip() {
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("DataQuery.........afterProperty");
    }
}
