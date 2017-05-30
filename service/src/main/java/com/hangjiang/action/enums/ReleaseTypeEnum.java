package com.hangjiang.action.enums;

/**
 * Created by jianghang on 2017/5/29.
 */
public enum ReleaseTypeEnum {
    ACTION("action",1);

    private String name;
    private Integer type;

    ReleaseTypeEnum(String name,Integer type){
        this.name = name;
        this.type = type;
    }

    public String getName(){
        return name;
    }

    public Integer getType(){
        return type;
    }

    public static ReleaseTypeEnum getReleaseType(String name){
        for(ReleaseTypeEnum typeEnum : ReleaseTypeEnum.values()){
            if(typeEnum.name.equals(name)){
                return typeEnum;
            }
        }

        return null;
    }

    public static ReleaseTypeEnum getReleaseType(Integer type){
        for(ReleaseTypeEnum typeEnum : ReleaseTypeEnum.values()){
            if(typeEnum.type.equals(type)){
                return typeEnum;
            }
        }

        return null;
    }
}
