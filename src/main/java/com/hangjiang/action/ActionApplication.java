package com.hangjiang.action;

import com.hangjiang.action.domain.AuthorBO;
import com.hangjiang.action.domain.OrganizationBO;
import com.hangjiang.action.entity.AuthorSettings;
import com.hangjiang.action.service.IAuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jianghang on 2017/3/26.
 */
@RestController
@SpringBootApplication
public class ActionApplication {

    private static Logger logger = LoggerFactory.getLogger(ActionApplication.class);

    @Autowired
    private AuthorSettings authorSettings;

    @Autowired
    private IAuthorService authorService;

    @RequestMapping("/findAuthorById")
    public AuthorBO findAuthorById(@RequestParam Integer id){
        AuthorBO author = authorService.findAuthorById(id);

        return author;
    }

    @RequestMapping(value = "/saveAuthor",method = RequestMethod.POST)
    public int saveAuthor(@RequestBody AuthorBO authorBO){
        authorService.saveAuthor(authorBO);

        return 1;
    }

    @RequestMapping(value = "/saveOrganization",method = RequestMethod.POST)
    public OrganizationBO saveOrganization(@RequestBody OrganizationBO organizationBO){
        organizationBO = authorService.saveOrganization(organizationBO);

        return organizationBO;
    }

    @RequestMapping("/index")
    public String index(){
        return "Hello Srping Boot: " + authorSettings.getName() + "-" + authorSettings.getAge();
    }

    public static void main(String[] args){
        ApplicationContext applicationContext = SpringApplication.run(ActionApplication.class,args);
        String[] activeProfiles = applicationContext.getEnvironment().getActiveProfiles();
        for(String profile : activeProfiles){
            logger.debug("Srping Boot profile:{}",profile);
        }
    }
}
