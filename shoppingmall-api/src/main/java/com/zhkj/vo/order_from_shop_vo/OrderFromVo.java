package com.zhkj.vo.order_from_shop_vo;

/**
 * 支付成功修改订单号参数
 */
public class OrderFromVo {
    //订单号
    private String orderNumber;
    //支付宝交易号
    private String transactionNumber;
    //支付宝交易状态
    private int paymentTypeId;

    public int getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(int paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }
}
