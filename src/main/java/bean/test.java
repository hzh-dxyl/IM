package bean;

import com.alibaba.fastjson.JSONObject;
import dao.Dao;
import dao.UserMapper;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.codec.digest.DigestUtils;
import utils.DaoUtils;

import java.io.*;

public class test {
    public static void main(String[] args) throws IOException {
        User user=new User();
        user.setUser_id(1);
        user.setUser_phone("132321");
        user.setUser_pwd("12424");
        JSONObject object= (JSONObject) JSONObject.toJSON(user);
        object.put("msg","sdfsfs");
        System.out.println(JSONObject.toJSONString(object));
    }

    public static void testSetNull(){
        UserMapper mapper=DaoUtils.getMapper(UserMapper.class);
        Friend friend=new Friend();
        friend.setUser_id(1);
        friend.setFriend_id(5);
        friend.setFriend_note(null);
        friend.setNotice_rank(1);
        int i=mapper.updateFriend(friend);
    }

    public static void testSearch() throws IOException {
        OkHttpClient client = new OkHttpClient();
        User user = new User();
        user.setUser_phone("1355585627");
        FormBody body = new FormBody.Builder()
                .add("json", JSONObject.toJSONString(user))
                .build();
        Request request = new Request.Builder()
                .url("http://40f730q296.qicp.vip/userSearch")
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        String result=response.body().string();
        User user1=JSONObject.parseObject(result,User.class); //不存在时user1=null
        System.out.println(result);
    }

    public static void testRegister() throws IOException {
        OkHttpClient client = new OkHttpClient();
        User user = new User();
        user.setUser_name("大哥方式");
        user.setUser_phone("13555685627");
        user.setUser_pwd("12345");
        user.setUser_img("sdf765sf.jpg");
        user.setImg_data(getBytes("D:\\壁纸\\2pby60dr3pmupoj6ewzp3mjbh.jpg"));
        FormBody body = new FormBody.Builder()
                .add("json", JSONObject.toJSONString(user))
                .build();
        Request request = new Request.Builder()
                .url("http://40f730q296.qicp.vip/userRegister")
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }

    public static byte[] getBytes(String filePath) {
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    public static void getFile(byte[] bfile, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists() && dir.isDirectory()) {//判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(filePath + "/" + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

    }



    public static void md5() throws IOException {
        for (int i = 0; i < 4; i++) {
            File file = new File("D:\\课程\\maven\\IM\\src\\main\\webapp\\images\\defaultHead\\默认头像" + i + ".png");
            InputStream in = new FileInputStream(file);
            String md5Hex = DigestUtils.md5Hex(in);
            System.out.println(md5Hex);
            in.close();
        }
    }
}
