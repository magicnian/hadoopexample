package cn.magicnian.example;

import org.apache.commons.codec.binary.Base64;

/**
 * Created by liunn on 2017/12/29.
 */
public class Test {

    public static void main(String[] args) {
        String s = "13222434730";
        byte[] resultArr = Base64.encodeBase64(s.getBytes());
        System.out.println(new String(resultArr));
        if(false){
            System.out.println("123");
            return;
        }
        System.out.println("continue");
    }
}
