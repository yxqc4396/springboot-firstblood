package com.login.entiey;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 权限表
 */
@Entity
@Table(name = "jurisdiction")
public class Jurisdiction {
    @Id
    private Integer jurid;
    // 权限名称
    private String jurname;

    public Integer getJurid() {
        return jurid;
    }

    public void setJurid(Integer jurid) {
        this.jurid = jurid;
    }

    public String getJurname() {
        return jurname;
    }

    public void setJurname(String jurname) {
        this.jurname = jurname;
    }
}
