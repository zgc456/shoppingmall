package com.zhkj.service.AndroidPay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeFastpayRefundQueryModel;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * 支付对外开放接口
 */
@RestController
public class PayCompound {
    /**
     * 支付
     * @param request
     * @param response
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/pay")
    public String pay(@ModelAttribute HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        if (request.getParameter(   "WIDout_trade_no") != null) {
            // 商户订单号，商户网站订单系统中唯一订单号，必填
            String out_trade_no = new String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"), "UTF-8");
            // 订单名称，必填
            String subject = new String(request.getParameter("WIDsubject").getBytes("ISO-8859-1"), "UTF-8");
            System.out.println(subject);
            // 付款金额，必填
            String total_amount = new String(request.getParameter("WIDtotal_amount").getBytes("ISO-8859-1"), "UTF-8");
            // 商品描述，可空
            String body = new String(request.getParameter("WIDbody").getBytes("ISO-8859-1"), "UTF-8");
            // 超时时间 可空
            String timeout_express = "2m";
            // 销售产品码 必填
            String product_code = "QUICK_WAP_WAY";
            /**********************/
            // SDK 公共请求类，包含公共请求参数，以及封装了签名与验签，开发者无需关注签名与验签
            //调用RSA签名方式
            AlipayClient client = new DefaultAlipayClient(AlipayConfigs.URL, AlipayConfigs.APPID, AlipayConfigs.RSA_PRIVATE_KEY, AlipayConfigs.FORMAT, AlipayConfigs.CHARSET, AlipayConfigs.ALIPAY_PUBLIC_KEY, AlipayConfigs.SIGNTYPE);
            AlipayTradeWapPayRequest alipay_request = new AlipayTradeWapPayRequest();

            // 封装请求支付信息
            AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
            model.setOutTradeNo(out_trade_no);
            model.setSubject(subject);
            model.setTotalAmount(total_amount);
            model.setBody(body);
            model.setTimeoutExpress(timeout_express);
            model.setProductCode(product_code);
            alipay_request.setBizModel(model);
            // 设置异步通知地址
            alipay_request.setNotifyUrl(AlipayConfigs.notify_url);
            // 设置同步地址
            alipay_request.setReturnUrl(AlipayConfigs.return_url);

            // form表单生产
            String form = "";
            try {
                // 调用SDK生成表单
                form = client.pageExecute(alipay_request).getBody();
                response.setContentType("text/html;charset=" + AlipayConfigs.CHARSET);
                response.getWriter().write(form);//直接将完整的表单html输出到页面
                response.getWriter().flush();
                response.getWriter().close();
            } catch (AlipayApiException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "1";
    }

    /**
     * 退款查询
     * @return
     */
    @RequestMapping("/selectRefundQuery")
   public String selectRefundQuery(@ModelAttribute HttpServletRequest request, HttpServletResponse response) throws AlipayApiException {
        if (request.getParameter("WIDout_trade_no") != null || request.getParameter("WIDtrade_no") != null && request.getParameter("WIDout_request_no") != null) {
            //商户订单号和支付宝交易号不能同时为空。 trade_no、  out_trade_no如果同时存在优先取trade_no
            //商户订单号，和支付宝交易号二选一
            String out_trade_no =request.getParameter("WIDout_trade_no");
            //支付宝交易号，和商户订单号二选一
            String trade_no =request.getParameter("WIDtrade_no");
            //请求退款接口时，传入的退款请求号，如果在退款请求时未传入，则该值为创建交易时的外部交易号
            String out_request_no = request.getParameter("WIDout_request_no");
            /**********************/
            // SDK 公共请求类，包含公共请求参数，以及封装了签名与验签，开发者无需关注签名与验签
            AlipayClient client = new DefaultAlipayClient(AlipayConfigs.URL, AlipayConfigs.APPID, AlipayConfigs.RSA_PRIVATE_KEY, AlipayConfigs.FORMAT, AlipayConfigs.CHARSET, AlipayConfigs.ALIPAY_PUBLIC_KEY, AlipayConfigs.SIGNTYPE);
            AlipayTradeFastpayRefundQueryRequest alipay_request = new AlipayTradeFastpayRefundQueryRequest();

            AlipayTradeFastpayRefundQueryModel model = new AlipayTradeFastpayRefundQueryModel();
            model.setOutTradeNo(out_trade_no);
            model.setTradeNo(trade_no);
            model.setOutRequestNo(out_request_no);
            alipay_request.setBizModel(model);

            AlipayTradeFastpayRefundQueryResponse alipay_response = client.execute(alipay_request);
           return  alipay_response.getBody();
        }else {
            return null;
        }
   }

    /**
     * 退款
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/refund")
   public String refund(@ModelAttribute HttpServletRequest request, HttpServletResponse response) throws AlipayApiException {
        if (request.getParameter("WIDout_trade_no") != null || request.getParameter("WIDtrade_no") != null) {
            //商户订单号和支付宝交易号不能同时为空。 trade_no、  out_trade_no如果同时存在优先取trade_no
            //商户订单号，和支付宝交易号二选一
            String out_trade_no =request.getParameter("WIDout_trade_no");
            //支付宝交易号，和商户订单号二选一
            String trade_no = request.getParameter("WIDtrade_no");
            //退款金额，不能大于订单总金额
            String refund_amount =request.getParameter("WIDrefund_amount");
            //退款的原因说明
            String refund_reason = request.getParameter("WIDrefund_reason");
            //标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传。
            String out_request_no =request.getParameter("WIDout_request_no");
            /**********************/
            // SDK 公共请求类，包含公共请求参数，以及封装了签名与验签，开发者无需关注签名与验签
            AlipayClient client = new DefaultAlipayClient(AlipayConfigs.URL, AlipayConfigs.APPID, AlipayConfigs.RSA_PRIVATE_KEY, AlipayConfigs.FORMAT, AlipayConfigs.CHARSET, AlipayConfigs.ALIPAY_PUBLIC_KEY, AlipayConfigs.SIGNTYPE);
            AlipayTradeRefundRequest alipay_request = new AlipayTradeRefundRequest();

            AlipayTradeRefundModel model = new AlipayTradeRefundModel();
            model.setOutTradeNo(out_trade_no);
            model.setTradeNo(trade_no);
            model.setRefundAmount(refund_amount);
            model.setRefundReason(refund_reason);
            model.setOutRequestNo(out_request_no);
            alipay_request.setBizModel(model);

            AlipayTradeRefundResponse alipay_response = client.execute(alipay_request);
          return  alipay_response.getBody();
        }else {
            return null;
        }
   }
}
