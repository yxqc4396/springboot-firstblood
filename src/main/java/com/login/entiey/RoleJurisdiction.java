package com.login.entiey;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 角色-权限表
 */
@Entity
@Table(name = "role_jurisdiction")
public class RoleJurisdiction {
    @Id
    private Integer id;
    // 角色id
    private Integer roleid;
    // 权限id
    private Integer jurid;

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public Integer getJurid() {
        return jurid;
    }

    public void setJurid(Integer jurid) {
        this.jurid = jurid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
