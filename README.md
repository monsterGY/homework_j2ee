# 学生信息管理+人脸搜索
<h2>j2ee+idea+servlet+maven+jsp+mysql+百度云人脸搜索API</h2><br>

人脸搜索API：https://cloud.baidu.com/product/face<br><br>
注册后  <br>
①共有云服务->应用列表->创建应用(API Key和Secret Key在<strong>ai.AuthService</strong>中使用)<br>
②可视化人脸库->点击应用->新建组(组名在<strong>ai.FaceSearch</strong>中使用)<br>
③添加以学号为用户ID的照片<br>
关于API的更多信息请参照操作文档
>https://cloud.baidu.com/doc/FACE/s/yk37c1u4t

在src\main\webapp\img下存放待上传或查找的照片<br>
照片仅支持jpg格式<br><br>
学生信息存放在表classtwo中，管理员信息存放在表admin<br>
以上两表在我的电脑中的存放地址为jdbc:mysql://localhost:3306/mysql<br>
如果不一样请修改/resources/db.properties中信息<br><br>
具体表请自行根据\main\webapp\WEB-INF\contacts下的xlsx自行创建<br>
表的效果请见sql文件夹下的图片<br><br>
在java代码中存在指向图片位置的绝对地址，需要根据个人电脑存放图片位置修改，需修改处均已注释指出，请自行查看<br>

本项目管理员通过name password登录，可增删改，信息查询(模糊查询)，图片查询(相似图片搜索)<br>
学生通过id mobile登录，可上传本人图片<br>
<br>

如果你是第一次使用idea，可能会由于没有配置maven环境无法正常使用本项目代码<br>
请参考该b站视频合集，具体为P5、P6、P7
>https://www.bilibili.com/video/BV12J411M7Sj/?spm_id_from=333.1007.top_right_bar_window_custom_collection.content.click

    If( you think this is useful) {
        Please star it,
        thank you!
    }else if (you think this is rubbish){
        say fuck directly,
        and close it!
    }
