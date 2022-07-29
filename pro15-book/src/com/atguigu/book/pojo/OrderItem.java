package com.atguigu.book.pojo;

/**
 * 订单详情实体类
 */
public class OrderItem {
    private Integer id;
    private Integer book;  // M:1
    private Integer buyCount;
    private Integer orderBean;  // M:1

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBook() {
        return book;
    }

    public void setBook(Integer book) {
        this.book = book;
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public Integer getOrderBean() {
        return orderBean;
    }

    public void setOrderBean(Integer orderBean) {
        this.orderBean = orderBean;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", book=" + book +
                ", buyCount=" + buyCount +
                ", orderBean=" + orderBean +
                '}';
    }
}

