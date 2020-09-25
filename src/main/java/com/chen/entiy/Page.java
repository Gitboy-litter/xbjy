package com.chen.entiy;

import java.util.List;

/**
 * @ProjectName: xbjy
 * @Package: com.chen.entiy
 * @Author: ChenZengWen
 * @Description: 描述
 * @Date: 2020/9/23 10:05
 * @Version: 1.0
 */
public class Page<T> {
   private Integer pageTotal;
   private Integer pageCurren=1;
   private Integer pageSize=5;
   private Integer pagecount;
   private List<T> data;

    public Integer getPageTotal() {
        return this.pagecount % this.pageSize == 0 ? this.pagecount / this.pageSize : this.pagecount / this.pageSize + 1;
//        return this.pagecount%this.pageSize==0?this.pagecount/this.pageSize:this.pagecount/this.pageSize+1;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageCurren()
    {
        if (this.pageCurren <= 0) {
            return 1;
        } else if (this.pageCurren >= getPageTotal()) {
            if (getPageTotal() == 0) {
                return 1;
            } else {
                return getPageTotal();
            }
        }
        return pageCurren;
    }

    public void setPageCurren(Integer pageCurren) {
        this.pageCurren = pageCurren;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPagecount() {
        return pagecount;
    }

    public void setPagecount(Integer pagecount) {
        this.pagecount = pagecount;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
