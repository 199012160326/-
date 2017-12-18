package com.jiupai.manhattan.test.util;

import com.jiupai.manhattan.channel.sender.bo.CITICSendReq;
import com.jiupai.manhattan.channel.sender.bo.CITICSendRes;
import com.jiupai.manhattan.channel.sender.impl.CITICSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by jiedaibao on 2017/11/24.
 */
public class sendTest {
    private static Logger logger = LoggerFactory.getLogger(CITICSender.class);


    public static void main(String[] args) {


        CITICSendRes res = new CITICSendRes();
        logger.info("----------------------------银企付款----------------------------");
        logger.info("请求地址:" + "127.0.0.3:12306"  + " 请求报文:" + "qqqqqqqqqqqqqqqqqq");
        String result = "";
        HttpURLConnection conn = null;
        OutputStream os = null;
        BufferedReader br = null;

        try {
            URL url = new URL("http://127.0.0.1:12306");
            conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setConnectTimeout(120000);
            os = conn.getOutputStream();
            os.write("qqqqqqqqqqqqqqqqqqqqqqqqqqqq".getBytes());
            os.close();

            String line;
            br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"GBK"));
            while((line = br.readLine()) != null){
                result = result + line;
            }

            logger.info("响应信息:{}" , result);
            res.setBusMsgCode(CITICSendRes.SUCCESS);
            res.setRespStr(result);
            res.setStatusCode("0");
        } catch (MalformedURLException e) {
            logger.error("异常:{}" , e.getMessage());
            res.setBusMsgCode(CITICSendRes.UN_SUCCESS);
            res.setRespStr(e.getMessage());
        } catch (UnsupportedEncodingException e) {
            logger.error("异常:{}" , e.getMessage());
            res.setBusMsgCode(CITICSendRes.UN_SUCCESS);
            res.setRespStr(e.getMessage());
        } catch (ProtocolException e) {
            e.printStackTrace();
            logger.error("异常:{}" , e.getMessage());
            res.setBusMsgCode(CITICSendRes.UN_SUCCESS);
            res.setRespStr(e.getMessage());
        } catch (IOException e) {
            logger.error("异常:{}" , e.getMessage());
            res.setBusMsgCode(CITICSendRes.UN_SUCCESS);
            res.setRespStr(e.getMessage());
        } finally {
            if(null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    logger.error("异常:" , e.getMessage());
                }
            }
            if(null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    logger.error("异常:" , e.getMessage());
                }
            }
            if(null != conn) {
                try {
                    conn.disconnect();
                } catch (Exception e) {
                    logger.error("异常:" , e.getMessage());
                }
            }
        }

    }
}
