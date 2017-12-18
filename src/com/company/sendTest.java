package com.company;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.logging.Logger;

/**
 * Created by jiedaibao on 2017/11/24.
 */
public class sendTest {
    //private static Logger logger = LoggerFactory.getLogger(CITICSender.class);


    public static void main(String[] args) {


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


        } catch (MalformedURLException e) {

        } catch (UnsupportedEncodingException e) {

        } catch (ProtocolException e) {
            e.printStackTrace();

        } catch (IOException e) {

        } finally {
            if(null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                 }
            }
            if(null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                 }
            }
            if(null != conn) {
                try {
                    conn.disconnect();
                } catch (Exception e) {
                 }
            }
        }

    }
}
