package com.atguigu.book.pojo;

import java.util.Date;
import java.util.List;

/**
 * 订单实体类
 */
public class OrderBean {
    private Integer id;
    private String orderNo;
    private Date orderDate;
    private Integer orderUser;
    private Double orderMoney;
    private Integer orderStatus;

    private List<OrderItem> orderItems;  // 1:N

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(Integer orderUser) {
        this.orderUser = orderUser;
    }

    public Double getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(Double orderMoney) {
        this.orderMoney = orderMoney;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "OrderBean{" +
                "id=" + id +
                ", orderNo='" + orderNo + '\'' +
                ", orderDate=" + orderDate +
                ", orderUser=" + orderUser +
                ", orderMoney=" + orderMoney +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
