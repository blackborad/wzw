package com.djxs.qrcode;
import java.io.IOException;

import com.djxs.util.*;
public class CreatQrcodeTest {
    public static void main(String[]args) throws IOException{
    	QrcodeTest.CreateQrcode(5, 'Q', "科技一班王智伟", "D:/QQ头像.jpg", 25, "D:/qrlogo.png", "96,142,124", "72,9,229");
    }
}
