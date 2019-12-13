/*
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fintech.modules.drools.example.cashflow.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Description
 * <br>  定义账户期限实体类
 *
 * @author chendongdong
 * @date 2019/12/10
 **/
@ApiModel(value = "账户期限")
public class AccountPeriod {
    /**
     * 开始日期
     */
    @ApiModelProperty(value = "开始日期")
    private Date start;
    /**
     * 开始日期
     */
    @ApiModelProperty(value = "结束日期")
    private Date end;

    public Date getStart() {
        if (start != null) {
            return (Date) start.clone();
        }
        return null;
    }

    public void setStart(Date start) {
        if (start != null) {
            this.start = (Date) start.clone();
        } else {
            this.start = null;
        }
    }

    public Date getEnd() {
        if (end != null) {
            return (Date) end.clone();
        }
        return null;
    }

    public void setEnd(Date end) {
        if (end != null) {
            this.end = (Date) end.clone();
        } else {
            this.end = null;
        }
    }

    public AccountPeriod(Date start, Date end) {
        this.start = start != null ? (Date) start.clone() : null;
        this.end = end != null ? (Date) end.clone() : null;
    }

    public AccountPeriod() {
    }

    @Override
    public String toString() {
        return "AccountPeriod{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
