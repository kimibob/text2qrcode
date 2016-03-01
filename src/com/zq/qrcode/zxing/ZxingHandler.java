package com.zq.qrcode.zxing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class ZxingHandler {

	public void encode(String contents, int width, int height, String imgPath) {
		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		// 指定纠错等级
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		// 指定编码格式
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,
					BarcodeFormat.QR_CODE, width, height, hints);

			MatrixToImageWriter
					.writeToFile(bitMatrix, "png", new File(imgPath));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String decode(String imgPath) {  
        BufferedImage image = null;  
        Result result = null;  
        try {  
            image = ImageIO.read(new File(imgPath));  
            if (image == null) {  
                System.out.println("the decode image may be not exit.");  
            }  
            LuminanceSource source = new BufferedImageLuminanceSource(image);  
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));  
  
            Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>();  
            hints.put(DecodeHintType.CHARACTER_SET, "utf-8");  
  
            result = new MultiFormatReader().decode(bitmap, hints);  
            return result.getText();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
  
	/*
	 * java -cp "./*" com.zq.qrcode.zxing.ZxingHandler "./1.png" 1
	 * java -cp "./*" com.zq.qrcode.zxing.ZxingHandler "./1.png" 0 "contents"
	 */
	public static void main(String[] args) {
		
		ZxingHandler handler = new ZxingHandler();
		System.out.println(handler.decode("C:\\Users\\kimibob\\Desktop\\1.png"));
		/*
		if(args.length == 0){
			System.out.println("usage: java -cp $CLASSPATH com.zq.qrcode.zxing.ZxingHandler $imgpath 0 $contents ");
			System.out.println("or: java -cp $CLASSPATH com.zq.qrcode.zxing.ZxingHandler $imgpath 1 ");
			return;
		}
		
		String imgPath = args[0];
		String handlertype = args[1];
		
		ZxingHandler handler = new ZxingHandler();
		if("0".equals(handlertype)){
			String contents = args[2];
			handler.encode(contents, 300, 300, imgPath);
			
		}else if("1".equals(handlertype)) {
			if("".equals(imgPath)){
				imgPath = "C:\\Users\\kimibob\\Desktop\\1.png";
			}
			System.out.println(handler.decode(imgPath));
			
		}else{
			System.out.println("usage: java -cp $CLASSPATH com.zq.qrcode.zxing.ZxingHandler $imgpath 0 $contents ");
			System.out.println("or: java -cp $CLASSPATH com.zq.qrcode.zxing.ZxingHandler $imgpath 1 ");
		}
		*/
	}
}
