package com.tz.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Page<T> {
    public static final Integer PAGE_SIZE = 4;
    // 当前页码
    private Integer pageNo;
    // 总页码
    private Integer pageTotal;
    // 当前页显示数量
    private Integer pageSize = PAGE_SIZE;
    // 总记录数
    private Integer pageTotalCount;
    // 当前页数据
    private List<T> items;

    public void setPageNo(Integer pageNo) {
        if (pageNo < 1) {
            this.pageNo = 1;
        }else if (pageNo > this.pageTotal) {
            this.pageNo = this.pageTotal;
        }else{
            this.pageNo = pageNo;
        }
//        this.pageNo = pageNo<1?1:pageNo;
//        this.pageNo = pageNo>this.pageTotal?this.pageTotal:pageNo;
    }
}
