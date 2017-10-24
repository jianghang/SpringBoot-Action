package com.hangjiang.action.controller;

import org.springframework.social.twitter.api.Tweet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jianghang on 2017/9/29.
 */
@Controller
@RequestMapping("twitter")
public class TweetController {

    @RequestMapping("/search")
    public String search(){
        return "searchPage";
    }


    @RequestMapping("/result")
    public String searchTwitter(@RequestParam(defaultValue = "action") String search, Model model){
//        SearchResults searchResults = twitter.searchOperations().search(search);
        List<Tweet> tweets = new ArrayList<>();
        Tweet tweet = new Tweet(1,
                "yyyyyyy",
                new Date(),
                "fromUser",
                "https://pic1.zhimg.com/v2-d1ab33830cdfe9e37319b631dcb2cda0_b.jpg",
                45L,
                64L,
                "EN",
                "source");
        tweets.add(tweet);
        model.addAttribute("tweets",tweets);
        model.addAttribute("search",search);

        return "resultPage";
    }
}
