package com.hangjiang.action.impl;

import com.google.common.collect.Lists;
import com.hangjiang.action.dao.AuthorRepository;
import com.hangjiang.action.dao.OrganizationRepository;
import com.hangjiang.action.domain.AuthorBO;
import com.hangjiang.action.domain.OrganizationBO;
import com.hangjiang.action.domain.release.ReleaseContext;
import com.hangjiang.action.entity.Author;
import com.hangjiang.action.entity.Organization;
import com.hangjiang.action.release.ReleaseEngine;
import com.hangjiang.action.service.IAuthorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jianghang on 2017/3/26.
 */
@Service
public class AuthorServiceImpl implements IAuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private ReleaseEngine releaseEngine;

    @Override
    public AuthorBO findAuthorById(Integer id) {
        ReleaseContext releaseContext = new ReleaseContext();
        releaseContext.setName("generalTaskChain");
        releaseContext.setType(3);
        ReleaseContext releaseContext1 = new ReleaseContext();
        releaseContext1.setName("specialTaskChain");
        releaseContext1.setType(1);
        releaseEngine.begin(Lists.<ReleaseContext>newArrayList(releaseContext,releaseContext1));

        Author author = authorRepository.findOne(Long.valueOf(id));
        AuthorBO authorBO = new AuthorBO();
        BeanUtils.copyProperties(author,authorBO);

        return authorBO;
    }

    @Override
    public int saveAuthor(AuthorBO authorBO) {
        Author author = new Author();
        BeanUtils.copyProperties(authorBO,author);
        authorRepository.save(author);

        return 1;
    }

    @Override
    public OrganizationBO saveOrganization(OrganizationBO organizationBO) {
        Organization organization = new Organization();
        BeanUtils.copyProperties(organizationBO,organization);
//        organization = organizationRepository.save(organization);
        organization = organizationRepository.saveAndFlush(organization);
        BeanUtils.copyProperties(organization,organizationBO);

        return organizationBO;
    }
}
