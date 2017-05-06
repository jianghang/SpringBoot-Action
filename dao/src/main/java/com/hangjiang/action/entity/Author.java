package com.hangjiang.action.entity;

import javax.persistence.*;

/**
 * Created by jianghang on 2017/3/26.
 */
@Entity
@Table(name = "t_author")
public class Author {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "real_name")
    private String realName;

    @Column(name = "nick_name")
    private String nickName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
