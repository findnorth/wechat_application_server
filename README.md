
## 1. 微信应用服务

它可以获取微信公众号token和ticket进行加密签名等。

---
## 2. 采用技术
- 后端框架--超级效率简单的JFinal框架，号称Java终结者。[官网](http://www.jfinal.com//)

- 缓存框架--ehcache  java开发的高效缓存不解释

---

## 3. 开发指南之导入到IDE
- 从git下载项目至本地（假使目录名为**SSE_WX_APPLICATION_SERVER**，如果你的目录名不是这个相应的下文目录请改为对应你的目录）

- 将workspace切换至SSE_WX_APPLICATION_SERVER的上级目录

- 打开**MyEclipse** or **Eclipse** --> 选择 File -> Switch WorkSpace -> other ->选择SSE_WX_APPLICATION_SERVER的上级目录

- 待Eclipse重启后右键点击 -> new -> Java Project -> 输入Project name **SSE_WX_APPLICATION_SERVER** -> Next -> **Default_output_folder** 处填写 **SSE_WX_APPLICATION_SERVER/WebRoot/WEB-INFO/classes** -> ok

- 右键点击项目 -> Properties -> 设置编码为UTF-8 -> apply ->ok
---

## 4. 开发指南之运行项目

- 找到类DemoConfig（路径src/com.demo/common）
- 右键点击DemoConfig -> Run as -> Java Application （就是这么简单！）

---
## 5. 打包部署指南 

- 选中项目 -> 点击鼠标右键 -> Export -> WAR file -> Destination 选择导出路径 -> Finash

- **导出后务必用压缩工具打开WAR包找到 -> WEB-INFO -> lib -> 删除jetty-server-8.1.8.jar**

- jetty-server-8.1.8.jar是在IDE中运行项目所加载的 如果不删除直接把WAR包放入Tomcat等WebServer中运行会报错！

---
## 6. 检查运行状况

- 项目运行后，打开浏览器输入服务地址如 localhost:8101 （端口8101为例）

![image](http://7xp9tt.com1.z0.glb.clouddn.com/QQ%E5%9B%BE%E7%89%8720170622102505.png?e=1498101914&token=8slkD8Omrwv8jl-FVdY_DTPxmXvoVLYVN-f4zsTG:pk3pzaKCvXJRiha25GuNm0CEQCA)

- 如果异常，请查看服务日志文件


    By yaokwok 2017.06.16 
