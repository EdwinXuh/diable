package com.ds.common.entity.domain;

import java.io.Serializable;

/**
 * @author EdwinXu
 * @date 2020/9/14 - 10:28
 */
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 7_410_732_905_451_820_274L;

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                '}';
    }
}
