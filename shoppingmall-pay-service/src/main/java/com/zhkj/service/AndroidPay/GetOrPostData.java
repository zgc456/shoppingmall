package com.zhkj.service.AndroidPay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.zhkj.service.order_from_service.OrderFromUpdate;
import com.zhkj.vo.order_from_shop_vo.OrderFromVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.HandshakeResponse;
import javax.xml.ws.Action;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Controller
public class GetOrPostData {
    @Autowired
    OrderFromUpdate orderFromUpdate;
    private  static Logger logger=LoggerFactory.getLogger(GetOrPostData.class);
    /**
     * 异步接收Get请求
     * @param request
     * @param
     * @return
     * @throws UnsupportedEncodingException
     * @throws AlipayApiException
     */
    @RequestMapping("getdata")
    @ResponseBody
    public String getRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          if (null==request.getParameter("out_trade_no")&&null==request.getParameter("trade_no")){
              logger.error("传入参数为空，支付成功但是数据库未修改");
              return "404";
          }
        OrderFromVo orderFromVo=new OrderFromVo();
        orderFromVo.setOrderNumber(request.getParameter("out_trade_no"));
        orderFromVo.setPaymentTypeId(3);
        orderFromVo.setTransactionNumber(request.getParameter("trade_no"));
      int i=  orderFromUpdate.updateShopApi(orderFromVo);
      if (i==0){
          logger.error("数据库修改失败，支付成功但是数据库未修改");
          return "404";
      }
         // request.getRequestDispatcher("http://192.168.43.43:8080/home").forward(request,response);
        System.out.println(111);
        String html="<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<a href=\"http://localhost:8080/#/home\">111</a>\n" +
                "</body>\n" +
                "</html>";
       return html;
    }
    @RequestMapping("postdata")
    @ResponseBody
    public String postRequest(HttpServletRequest request) throws UnsupportedEncodingException, AlipayApiException {
        //获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
        //商户订单号

        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

        //支付宝交易号

        String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
        //计算得出通知验证结果
        //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
        boolean verify_result = AlipaySignature.rsaCheckV1(params, AlipayConfigs.ALIPAY_PUBLIC_KEY, AlipayConfigs.CHARSET, "RSA2");

        if (verify_result) {//验证成功
            //////////////////////////////////////////////////////////////////////////////////////////
            //请在这里加上商户的业务逻辑程序代码
            //该页面可做页面美工编辑

            return "验证成功<br />";
            //——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

            //////////////////////////////////////////////////////////////////////////////////////////
        } else {
            //该页面可做页面美工编辑

         return "验证失败";
        }
    }
}
