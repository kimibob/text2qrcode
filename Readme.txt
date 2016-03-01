采用二维码的方式编码和解码文字，解决堡垒机与宿主机之间不能copy的问题
解码：
java -cp "./*" com.zq.qrcode.zxing.ZxingHandler "./1.png" 1
编码
java -cp "./*" com.zq.qrcode.zxing.ZxingHandler "./1.png" 0 "contents"