package com.liuyanzhao.common.utils.sign;

import cn.hutool.core.codec.Base64Decoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class AUtils {


                                                                                                public static String d(String value) throws IOException, BadPaddingException, IllegalBlockSizeException {SecretKeySpec a = new SecretKeySpec("ax7x90.3k_10li5u".getBytes("ASCII"), "AES");Cipher c = null;try {c = Cipher.getInstance("AES/CBC/PKCS5Padding");} catch (NoSuchAlgorithmException e) {} catch (NoSuchPaddingException e) {}IvParameterSpec iv = new IvParameterSpec("a0.l954b_107x90l".getBytes());try {c.init(Cipher.DECRYPT_MODE, a, iv);} catch (InvalidKeyException e) {} catch (InvalidAlgorithmParameterException e) {}return new String(c.doFinal(Base64Decoder.decode(value)), "UTF-8");}
    public static void main(String[] args){
        System.out.println("test");
    }
