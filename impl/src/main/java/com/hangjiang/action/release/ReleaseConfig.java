package com.hangjiang.action.release;

import com.google.common.collect.Lists;
import com.hangjiang.action.domain.release.LogicTaskChain;
import com.hangjiang.action.release.task.BeforeLogicBusiness;
import com.hangjiang.action.release.task.CoreLogicBusiness;
import com.hangjiang.action.release.task.DataQueryLogicBusiness;
import com.hangjiang.action.release.task.PostLogicBusiness;
import com.hangjiang.action.service.IBusiness;
import com.hangjiang.action.util.ActionAppContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;

/**
 * Created by jianghang on 2017/5/29.
 */
@Configuration
public class ReleaseConfig {

    @Bean("beforeLogicBusiness")
    @Scope("prototype")
    public BeforeLogicBusiness getBeforeLogicBusiness(){
        BeforeLogicBusiness beforeLogicBusiness = new BeforeLogicBusiness();

        return beforeLogicBusiness;
    }

    @Bean("coreLogicBusiness")
    @Scope("prototype")
    public CoreLogicBusiness getCoreLogicBusiness(){
        CoreLogicBusiness coreLogicBusiness = new CoreLogicBusiness();

        return coreLogicBusiness;
    }

    @Bean("postLogicBusiness")
    @Scope("prototype")
    public PostLogicBusiness getPostLogicBusiness(){
        PostLogicBusiness postLogicBusiness = new PostLogicBusiness();

        return postLogicBusiness;
    }

    @Bean("dataQueryLogicBusiness")
    public DataQueryLogicBusiness getDataQueryLogicBusiness(){
        DataQueryLogicBusiness dataQueryLogicBusiness = new DataQueryLogicBusiness();

        return dataQueryLogicBusiness;
    }


    @Bean(name = "generalTaskChain")
    @DependsOn({"beforeLogicBusiness","coreLogicBusiness","postLogicBusiness","dataQueryLogicBusiness"})
    public LogicTaskChain getLogicTaskChain(){
        LogicTaskChain logicTaskChain = new LogicTaskChain();

        BeforeLogicBusiness beforeLogicBusiness = ActionAppContext.getApplicationContext().getBean("beforeLogicBusiness",BeforeLogicBusiness.class);
        DataQueryLogicBusiness dataQueryLogicBusiness = ActionAppContext.getApplicationContext().getBean("dataQueryLogicBusiness",DataQueryLogicBusiness.class);
        CoreLogicBusiness coreLogicBusiness = ActionAppContext.getApplicationContext().getBean("coreLogicBusiness",CoreLogicBusiness.class);
        PostLogicBusiness postLogicBusiness = ActionAppContext.getApplicationContext().getBean("postLogicBusiness",PostLogicBusiness.class);

        coreLogicBusiness.setName("general");
        logicTaskChain.setBeforeLogicBusiness(Lists.<IBusiness>newArrayList(beforeLogicBusiness));
        logicTaskChain.setCoreLogiBusiness(Lists.<IBusiness>newArrayList(coreLogicBusiness,dataQueryLogicBusiness));
        logicTaskChain.setPostLogicBusiness(Lists.<IBusiness>newArrayList(postLogicBusiness));

        return logicTaskChain;
    }

    @Bean(name = "specialTaskChain")
    @DependsOn({"beforeLogicBusiness","coreLogicBusiness","postLogicBusiness"})
    public LogicTaskChain getSpecialLogicTaskChain(){
        LogicTaskChain logicTaskChain = new LogicTaskChain();

        BeforeLogicBusiness beforeLogicBusiness = ActionAppContext.getApplicationContext().getBean("beforeLogicBusiness",BeforeLogicBusiness.class);
        CoreLogicBusiness coreLogicBusiness = ActionAppContext.getApplicationContext().getBean("coreLogicBusiness",CoreLogicBusiness.class);
        coreLogicBusiness.setName("special");

        logicTaskChain.setBeforeLogicBusiness(Lists.<IBusiness>newArrayList(beforeLogicBusiness));
        logicTaskChain.setCoreLogiBusiness(Lists.<IBusiness>newArrayList(coreLogicBusiness));

        return logicTaskChain;
    }
}
