package com.djxs.qrcode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import com.swetake.util.Qrcode;

public class CreateQrcode_day1 {

	
	public static void main(String[] args) throws IOException {
		int ver=5;//假设版本号为5
		
		Qrcode qrcode=new Qrcode();
		qrcode.setQrcodeErrorCorrect('Q');
		qrcode.setQrcodeVersion(ver);//设置版本号
		/*二维码大小，国家标准：
		 * ver = 1 , imagesize=21
		 * ver = 2 , imagesize=25
		 * ver = 3 , imagesize=29
		 * ver = n , imagesize=21+(ver-1)*4
		 * 当每一位用m位像素点表示时（默认一位）：
		 * imagesize=(21+(ver-1)*4)*m
		 * 当四边加x个像素的白边时，（默认不加）：
		 *imagesize=((21+x*2)+(ver-1)*4)*m
		 */
		int x=2;//假设加两个像素的白边
		int imagesize=67+12*(ver-1);//二维码大小
		
		BufferedImage bufferedimage=new BufferedImage(imagesize,imagesize,BufferedImage.TYPE_INT_RGB);//设置二维码大小
		Graphics2D gs1=bufferedimage.createGraphics();
		gs1.setBackground(Color.WHITE);
		gs1.setColor(Color.BLACK);
		gs1.clearRect(0,0,imagesize,imagesize);//与上面的bufferedimage的大小一样，不然会有黑边
        String str="科技一班王智伟";//二维码扫出来的内容
        
        int startR=96;
        int startG=142;
        int startB=124;
        
        int endR=72;
        int endG=9;
        int endB=229;
        boolean[][] calQrcode=qrcode.calQrcode(str.getBytes());
        for (int i=0;i<calQrcode.length;i++){
        	for (int j=0;j<calQrcode.length;j++){
        		if(calQrcode[i][j]){
        		   /*x=开始值+（结束值-开始值）*（）/长度
        			*                   j+1       （上下       渐变）
        			*                   i+1       （左右       渐变）
        			*                  (i+j)/2（正对角线渐变）
        			*/ 
        		
        		   int num1=startR+(endR-startR)*((i+j)/2)/calQrcode.length;
        		   int num2=startG+(endG-startG)*((i+j)/2)/calQrcode.length;
        		   int num3=startB+(endB-startB)*((i+j)/2)/calQrcode.length;
        		
        		   Color color1= new Color(num1,num2,num3);
        		   gs1.setColor(color1);
        		   gs1.fillRect(i*3+x,j*3+x,3,3);//(i*m+x,j*m+x,m,m)
        		  
        		}
        	}
        }
        Image logo=ImageIO.read(new File("D:/QQ头像.jpg"));
        int logosize=30;
        int logox=(imagesize-logosize)/2;
        gs1.drawImage(logo,logox, logox, logosize, logosize, null);
        gs1.dispose();
    	bufferedimage.flush();
    	ImageIO.write(bufferedimage,"png",new File("D:/qrlogo.png"));
	}
}
