package com.hangjiang.action.domain.release;

import com.hangjiang.action.service.IBusiness;

import java.util.List;

/**
 * Created by jianghang on 2017/5/29.
 */
public class LogicTaskChain {

    private List<IBusiness> beforeLogicBusiness;

    private List<IBusiness> coreLogiBusiness;

    private List<IBusiness> postLogicBusiness;

    public List<IBusiness> getBeforeLogicBusiness() {
        return beforeLogicBusiness;
    }

    public void setBeforeLogicBusiness(List<IBusiness> beforeLogicBusiness) {
        this.beforeLogicBusiness = beforeLogicBusiness;
    }

    public List<IBusiness> getCoreLogiBusiness() {
        return coreLogiBusiness;
    }

    public void setCoreLogiBusiness(List<IBusiness> coreLogiBusiness) {
        this.coreLogiBusiness = coreLogiBusiness;
    }

    public List<IBusiness> getPostLogicBusiness() {
        return postLogicBusiness;
    }

    public void setPostLogicBusiness(List<IBusiness> postLogicBusiness) {
        this.postLogicBusiness = postLogicBusiness;
    }
}
