# ZiPng
> 用于压缩图片, 只是用作自己使用的工具，待完善

# 下载
[ZiPng.jar](http://7xs6lq.com1.z0.glb.clouddn.com/github/jar/ZiPng.jar)
## 待完善
1. 压缩包直接解析并压缩图片
3. 解析文件出现异常时跳过
5. 后期制作离线版本

## 已实现
- 压缩文件并生成同样的目录结构
`更新时间: 2016年04月26日17:26:36`
- 直接替换目标文件
- 生成一丢丢图片，舍弃其他文件

## 使用
1. 下载 并 打包成jar
2.
```shell
usage: java -jar zipng.jar [-h/--help] [-f/--from] fromPath [-t/--to] toPath
                         [-i/--ignore] ignore [-k/--key] key
                     -f,--from <arg>     要压缩的文件 或 文件夹.
                     -h,--help           print help for the command.
                     -i,--ignore <arg>   忽略文件名(正则)
                     -k,--key <arg>      自定义秘钥
                     -t,--to <arg>       压缩后存放的文件夹
```
## 截图
![image](http://7xs6lq.com1.z0.glb.clouddn.com/github/%E9%80%89%E5%8C%BA_017.png)

## 致谢
欢迎指点。。
