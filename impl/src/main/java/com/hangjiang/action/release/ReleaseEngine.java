package com.hangjiang.action.release;

import com.hangjiang.action.domain.release.ReleaseContext;
import com.hangjiang.action.util.ActionAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jianghang on 2017/5/29.
 */
@Component
public class ReleaseEngine {

    private static Logger logger = LoggerFactory.getLogger(ReleaseEngine.class);

    private ExecutorService executorService = null;

    public void begin(List<ReleaseContext> releaseContexts){
        if(CollectionUtils.isEmpty(releaseContexts)){
            return;
        }

        if(releaseContexts.size() > 1){
            int coreProcessors = Runtime.getRuntime().availableProcessors();
            logger.info("coreProcessors: " + coreProcessors);
            executorService = Executors.newFixedThreadPool(coreProcessors + 1);
        }else {
            executorService = Executors.newFixedThreadPool(1);
        }

        List<ReleaseCallable> releaseCallables = new ArrayList<ReleaseCallable>();
        ReleaseCallable releaseCallable = null;
        for(ReleaseContext releaseContext : releaseContexts){
            releaseCallable = new ReleaseCallable(releaseContext);
            releaseCallables.add(releaseCallable);
        }

        try {
            executorService.invokeAll(releaseCallables);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class ReleaseCallable implements Callable<ReleaseContext>{

        private ReleaseContext releaseContext;

        public ReleaseCallable(){

        }

        public ReleaseCallable(ReleaseContext releaseContext){
            this.releaseContext = releaseContext;
        }

        @Override
        public ReleaseContext call() throws Exception {
            ReleaseTaskExcutor releaseTaskExcutor = ActionAppContext.getApplicationContext().getBean("releaseTaskExcutor",ReleaseTaskExcutor.class);
            releaseTaskExcutor.executeBusinessLogic(releaseContext);

            return null;
        }
    }
}
