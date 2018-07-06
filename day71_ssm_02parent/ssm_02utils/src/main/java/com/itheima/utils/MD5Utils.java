package com.itheima.utils;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
    /**
     * 使用md5的算法进行加密
     */
    public static String md5(String plainText) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(
                    plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有md5这个算法！");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
        // 如果生成数字未满32位，需要前面补0
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }

    public static void main(String[] args) {
//        MD5加密方式所加密出来的数值是固定不变的  因此容易被破解
//        81dc9bdb52d04dc20036dbd8313ed055
//        81dc9bdb52d04dc20036dbd8313ed055
//        System.out.println(md5("1234"));

        /*MD5Hash加密方式*/
        Md5Hash m = new Md5Hash("123", "张三", 2);
//        获得MD5Hash加密后的密文
        m.toString();
        System.out.println(m.toString());                              //653f2ee4531e2f78800925e6d9d9d3ff
        Md5Hash m1 = new Md5Hash("123", "李四", 2);
        System.out.println(m1.toString());//换盐后的结果                 07a1a7115a9bb86b68a1035d4e146a1d
        Md5Hash m2 = new Md5Hash("123", "李四", 3);
        System.out.println(m2.toString());//加盐次数变了之后的结果         02ed973b3172eadb434d1bc8ee634c28
//        应用springSecurity  框架提供的方法加密
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("123");
        System.out.println(encode);
//        $2a$10$61NGbABh0l0.QWkIleuBB.UjfVeAuTF.SfUNe.XmOJctQidOfQ9hK
//        $2a$10$/E7NBNQSsZlGoP6Ey/zbXuFOTe6c0UdgXvcShC3REe2ub3n9zyfhG
        boolean matches = passwordEncoder.matches("123", "$2a$10$61NGbABh0l0.QWkIleuBB.UjfVeAuTF.SfUNe.XmOJctQidOfQ9hK");
        System.out.println(matches);
        boolean matches1 = passwordEncoder.matches("123", "$2a$10$/E7NBNQSsZlGoP6Ey/zbXuFOTe6c0UdgXvcShC3REe2ub3n9zyfhG");
        System.out.println(matches1);

    }
}