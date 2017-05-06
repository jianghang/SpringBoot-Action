package com.hangjiang.action.dao;

import com.hangjiang.action.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jianghang on 2017/3/26.
 */
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
