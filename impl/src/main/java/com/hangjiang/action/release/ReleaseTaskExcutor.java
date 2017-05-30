package com.hangjiang.action.release;

import com.hangjiang.action.domain.release.LogicTaskChain;
import com.hangjiang.action.domain.release.ReleaseContext;
import com.hangjiang.action.service.IBusiness;
import com.hangjiang.action.util.ActionAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StopWatch;

import java.util.List;

/**
 * Created by jianghang on 2017/5/29.
 */
@Component
public class ReleaseTaskExcutor {

    private static Logger logger = LoggerFactory.getLogger(ReleaseTaskExcutor.class);

    public void executeBusinessLogic(ReleaseContext releaseContext){
        logger.info("Thread: " + Thread.currentThread().getName() + "-" + Thread.currentThread().getId());
        String name = releaseContext.getName();
        LogicTaskChain logicTaskChain = (LogicTaskChain) ActionAppContext.getApplicationContext().getBean(name);
        StopWatch stopWatch = new StopWatch(System.currentTimeMillis() + " - " + releaseContext.getName());
        releaseContext.setStopWatch(stopWatch);

        executeBeforeLogic(logicTaskChain.getBeforeLogicBusiness(),releaseContext);
        for(int i = 0;i < releaseContext.getType();i++){
            executeCoreLogic(logicTaskChain.getCoreLogiBusiness(),releaseContext);
        }
        executePostLogic(logicTaskChain.getPostLogicBusiness(),releaseContext);

        logger.info(stopWatch.prettyPrint());
    }

    private void executePostLogic(List<IBusiness> postLogicBusiness,ReleaseContext releaseContext) {
        doBusiness(postLogicBusiness,releaseContext);
    }

    private void executeCoreLogic(List<IBusiness> coreLogiBusiness,ReleaseContext releaseContext) {
        doBusiness(coreLogiBusiness,releaseContext);
    }

    private void executeBeforeLogic(List<IBusiness> beforeLogicBusiness,ReleaseContext releaseContext) {
        doBusiness(beforeLogicBusiness,releaseContext);
    }

    private void doBusiness(List<IBusiness> businesses,ReleaseContext releaseContext) {
        if(CollectionUtils.isEmpty(businesses)){
            return;
        }
        for (IBusiness business : businesses) {
            if(business != null && !business.isSkip()){
                business.execute(releaseContext);
            }
        }
    }
}
