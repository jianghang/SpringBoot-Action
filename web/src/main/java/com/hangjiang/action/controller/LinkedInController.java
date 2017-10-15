package com.hangjiang.action.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.linkedin.api.Job;
import org.springframework.social.linkedin.api.JobSearchParameters;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by jianghang on 2017/10/15.
 */
@Controller
public class LinkedInController {

    @Autowired
    private LinkedIn linkedIn;

    @RequestMapping("linkedIn")
    public String search(@RequestParam(defaultValue = "Google") String search, Model model){
        JobSearchParameters jobSearchParameters = new JobSearchParameters();
        jobSearchParameters.setCompanyName(search);
        List<Job> jobs = linkedIn.jobOperations().searchJobs(jobSearchParameters).getJobs();
        model.addAttribute("jobs",jobs);
        model.addAttribute("search",search);

        return "linkedInResultPage";
    }

}
