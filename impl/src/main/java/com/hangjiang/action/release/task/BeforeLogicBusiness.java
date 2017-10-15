package com.hangjiang.action.release.task;

import com.hangjiang.action.dao.AuthorRepository;
import com.hangjiang.action.domain.release.ReleaseContext;
import com.hangjiang.action.entity.Author;
import com.hangjiang.action.service.IBusiness;
import com.hangjiang.action.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by jianghang on 2017/5/29.
 */
//@Component
//@Scope("prototype")
public class BeforeLogicBusiness implements IBusiness {

    private static Logger logger = LoggerFactory.getLogger(BeforeLogicBusiness.class);

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public void execute(ReleaseContext releaseContext) {
        Author author = authorRepository.findOne(2L);
        logger.info("before.............");
        logger.info(JsonUtil.obj2Json(author));
    }

    @Override
    public boolean isSkip() {
        return false;
    }
}
