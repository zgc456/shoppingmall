package com.zhkj.service.pay;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by lenovo on 2018/4/11.
 */
public class AlipayConfig {
    //↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    // public static String app_id = "2016080800192280";

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016091000482530";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCaFvcbU+ehbu489nJzCBgsNAB26DXa7+m2kCKWijqQWjm3QJlXqp0YrNlOpt3AaSTkWoxSUk7/3CKwO9qIFn9+FXixHFvZrb4GAD8u2PvEeK8hn3AAnmxu2hY3/YPp9qOCNnFZ9mXAM5OhbfPOY6lCTOK2JzwkTmXfjTbn+jm+ttlthLV0zHQ0q/PEEWwG64a7JpHBB2NBrvwAscfWEfqhfmGhJi8e22ECKy8n8w+sKdscyGwSL++pHTvWRwGaJSluWHuSL3RvOWZSJ6+tz1FnnJN607KJ6tcC66LVWIHnor2DYwydu7FGp1w4U2TU197D2htQpYPwta/4tq7edoq3AgMBAAECggEAS8YnVkT4jyZh+ZCnz3GXkYUxBUhD05pPL/vJRoZLLllwMHGP9tBr2IqHgmoEem46OiFdB856EtBKLqgAWxPVNkeQxmfPs0gBPio45+zEQmWmmb4x8dv2Lr0IWBhPGmqzFYF4VGkauF2kp6nOTxwrS/NLOZApvyDecASdj445f1LcvtbCexsx3pRuNclvaanG6w1ozRPWU490VIP5NxtHxz8ncKctjO3vJ6mh2yYodumn24KeFHKaWeyP7z9HJD0YD0y1aVAUlDdQWwye5lpV4XOBzK4PenS6iVXHlUIb6CqoXe5m/nyyPAVpUV+GZI0d0JWL1Tdbykc9IAp410ZEIQKBgQDwoFkluVEaxN95zoPrj6fAncT7HztFrUXi1Y8BJis/pD9icjJGcuvEWBi/+MCW8RTJ/Ng3DTELYQdxOciiWUdz9V9wKipiyYz/3/KodssyX+QTLbKfKsjWUaXIdDlf0++CtPSnFhyY9ZkJH5FrH+x0JW+0TYY/4c0UsVWOrRIJdQKBgQCj7zxDjmG7aZWpIBm1kRZVP5EnmGMj6E99Wdc0aKEPS9zLhWNSSUhzSExgmBTs2tbM5cXfPPmwvZey8QSId7SXaV2qexJ2Xf42cv5k0//s3H9gz+kW3NPdDs/Wq2UW3+OFeD08R9KQAItf+YR+zkdh3hhT7RPiBg0uxxSr4zyR+wKBgQCi1vDdfabsZbUSmnZeMK6GxH4sr7sN8S/tTTbkwS7dd8rJVZy+XkE9c5z0NWQoAehetTShNNUu7S/7KO6lJ6QaTkCTCIX8qoc4FhcCeLoUUh5n+DH8UxjtKP33ROga+LMKkmlKtcUOoo45RVrE0yrSgw8wRpKGosmFqmjLEEa3jQKBgEkwRrx2dcWhJvMB5Nh99PWMUrbVvvVKAysYkA7S440btnCalbQoCZOGZKr/WuA/94Q01TmMVIGGs5JtLjPrFZI/tVSma6Cy6ArRHihPycOq0aecSxks792SflH95TzoKgDtpJEkE6q8w3zOvkuKtq3H5R6bCrRvNTV8JgtzgRg/AoGBAN73Eyqt2shSTgz/ssGGXiTphTnRRkOFkQCtZAg1kFjmF1OJO+ZeDYCCB14YGIKal7rnKd1POZNYGsrWbZ7a/+/tvNWrnSdYa3oxvUTV9T8mnWlrS/AYrUQLTmxbI5JWdFmA3ZzG3UKgPaF5egKtMNxSDLFKISUrmpp+uatCkFmh";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA6PuQzbqhVYcUAJHY+kGOZTTmNrkfyc+xaysk/sHU0ESukQBOCsf9BT683912ScBG9BOezUflQNPYYXWXivrVVvci95brdScUDjw8L9QcxGTSgbhy0YEToHBernj2Zn1hR2aBNodZTw9TmwPxCHEHQt1/xabLy9PD7NXm0yW/saltHQfzx6stXo2rZSkjnhokGDMaXhhirBcdSQn74CGyI8R7zKZVHK9I2bItL+H0ax6YTXHfUoJCP1JyvzQBl3b4eXq1tJ/fOVAnT47e0nSYW9hCJDj9z/ZV9ae1FNiS9+iVRlNfsyngBt8fkJj/LxllCvJmDNEVEJ3b5vrTFr9fzQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://192.168.43.188:8080/postreturning";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://192.168.43.188:8080/getreturning";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";



    // 支付宝网关
    public static String log_path = "D:\\test";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
