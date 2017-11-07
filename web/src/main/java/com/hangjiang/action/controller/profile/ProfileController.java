package com.hangjiang.action.controller.profile;

import com.hangjiang.action.domain.profile.ProfileForm;
import com.hangjiang.action.profile.USLocalDateFormatter;
import com.hangjiang.action.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Locale;

/**
 * Created by jianghang on 2017/10/30.
 */
@Controller
public class ProfileController {

    private static Logger logger = LoggerFactory.getLogger(ProfileController.class);

    @RequestMapping("/profile")
    public String displayProfile(ProfileForm profileForm){
        return "profile/profilePage";
    }

    @RequestMapping(value = "/profile",method = RequestMethod.POST)
    public String saveProfile(@Valid ProfileForm profileForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "profile/profilePage";
        }
        logger.info("save ok " + JsonUtil.obj2Json(profileForm));

        return "redirect:/profile";
    }

    @ModelAttribute("dateFormat")
    public String localeFormat(Locale locale){
        return USLocalDateFormatter.getPattern(locale);
    }
}
