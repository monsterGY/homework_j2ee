package utils;

import ai.AuthService;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述
 *
 * @author lenovo
 * @date 2023/10/05  13:23
 */
public class ImageUtils {
    /**
     * 将图片转化成byte数组
     * @param imgFile 图片文件在服务器上的地址
     * @return
     */
    public static byte[] imgToBytes(String imgFile) {
        InputStream in = null;
        byte[] data = null;
        // 读取图片字节数组
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
            return data;
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 将普通用户上传的照片放到服务器中，并同步到人脸库里
     * @param id 学号-即记录是谁上传的照片
     * @param imgFile 上传时照片的路径
     */
    public static void uploadImg(String id, String imgFile) {
        //1，将上传的照片放到服务器中
        //此处为绝对路径，请自行修改为你自己的图片存放地址
        imgFile="E:\\j2ee\\hw_maven\\src\\main\\webapp\\img\\"+imgFile;
        byte[] data = imgToBytes(imgFile);
        
        //此处为绝对路径，请自行修改为你自己的tomcat中对应此项目工件下的图片存放地址
        String outputImg = "D:\\tomcat\\apache-tomcat-9.0.80\\webapps\\hw_maven_war\\img\\"+id+".jpg";
        try {
            FileOutputStream fos = new FileOutputStream(new File(outputImg));
            fos.write(data,0,data.length);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //2，将照片同步到人脸库
        String addResult = addFace(id, Base64Util.encode(data));
        System.out.println(addResult);
    }

    /**
     * 向人脸库添加新信息
     * @return
     */
    public static String addFace(String id, String base64Img) {
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/add";
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("image", base64Img);
            map.put("image_type", "BASE64");

            //此处组名注意修改个人百度云中设置的组名
            map.put("group_id", "g_1");

            
            map.put("user_id", id);
            map.put("user_info", "");
            map.put("liveness_control", "NONE");
            map.put("quality_control", "LOW");

            String param = GsonUtils.toJson(map);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = AuthService.getAuth();

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
