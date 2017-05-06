package com.hangjiang.action.dao;

import com.hangjiang.action.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jianghang on 2017/3/27.
 */
public interface OrganizationRepository extends JpaRepository<Organization,Long> {
}
