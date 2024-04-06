package com.courage.shardingsphere.jdbc.common.result;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResultPage<T> implements Serializable {

    private static final long serialVersionUID = -8080399458717111600L;

    private long total;

    private List<T> data;

    public ResultPage() {
        total = 0;
        data = new ArrayList<T>();
    }

    public ResultPage(List<T> rows) {
        this.init(rows);
    }

    private void init(List<T> rows) {

    }

    public long getTotal() {
        return total;
    }

    public ResultPage setTotal(long total) {
        this.total = total;
        return this;
    }

    public List<T> getData() {
        return data;
    }

    public ResultPage setData(List<T> data) {
        this.data = data;
        return this;
    }
}
