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
public class CoreLogicBusiness implements IBusiness {

    private Logger logger = LoggerFactory.getLogger(CoreLogicBusiness.class);

    private String name;

    public CoreLogicBusiness(){

    }

    public CoreLogicBusiness(String name){
        this.name = name;
    }

    @Override
    public void execute(ReleaseContext releaseContext) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("core........." + name);
    }

    @Override
    public boolean isSkip() {
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
