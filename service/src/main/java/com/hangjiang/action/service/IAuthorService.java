package com.hangjiang.action.service;

import com.hangjiang.action.domain.AuthorBO;
import com.hangjiang.action.domain.OrganizationBO;

/**
 * Created by jianghang on 2017/3/26.
 */
public interface IAuthorService {

    AuthorBO findAuthorById(Integer id);

    int saveAuthor(AuthorBO authorBO);

    OrganizationBO saveOrganization(OrganizationBO organizationBO);
}
