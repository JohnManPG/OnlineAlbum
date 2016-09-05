package com.shiyanlou.photo.util;

import java.io.InputStream;

import org.json.JSONException;

import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.rs.PutPolicy;
import com.qiniu.api.rs.RSClient;

/**
 * ͼƬ�����ࣨʹ����ţ�ƴ洢����
 * */
public class FileUtils {
	private static final String ACCESS_KEY = "4PSyEEjkRfqlfqNX_YgqpR3hJhAOqbvSUVdtIb2J";//�������������ǽ����ģ���Կ�����������Կ
    private static final String SECRET_KEY = "iSb9418PdjeL41BvY0JJGgYIQM-PFoTMpHY0t0pS";
    private static final String BUCKET_NAME = "shiyanlouphoto";//����������ţ�ƴ����� Bucket ���� 
       /**
        * �ϴ�ͼƬ����ţ�ƴ洢
        * @param reader
        * @param fileName
        * */
    
    public static void upload(InputStream reader,String fileName){
    	String uptoken;
    	 try {
             Mac mac = new Mac(ACCESS_KEY, SECRET_KEY);
             PutPolicy putPolicy = new PutPolicy(BUCKET_NAME);
             uptoken = putPolicy.token(mac);
             IoApi.Put(uptoken, fileName, reader, null);
         } catch (AuthException e) {
             e.printStackTrace();
         } catch (JSONException e) {
             e.printStackTrace();
         }
     }
    public static void delete(String key) {
    	Mac mac = new Mac(ACCESS_KEY, SECRET_KEY);
    	RSClient client = new RSClient(mac);
    	client.delete(BUCKET_NAME, key);
    }
}
