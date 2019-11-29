package function;

import model.Element;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class FunctionImpl implements Function {
    private final int kBufferSize = 8192;
    private KeyStore keystore;
    private String keystoreFile;

    private String password;
    private String othercert;
    private String inputFile;
    private String outputFile;


    public FunctionImpl(Element element) {
        keystoreFile = element.getKeystorePath();
        password = element.getKeystorePassword();
        othercert = element.getOtherCertPath();
        inputFile = element.getInputFilePath();
        outputFile = element.getOutputFilePath();
    }


    public void encrytion() {
        try {
            //打开发送者keystore文件
            keystore = KeyStore.getInstance("JKS");
            java.io.FileInputStream fis = new java.io.FileInputStream(keystoreFile);
            keystore.load(fis, password.toCharArray());
            fis.close();
            //产生随机会话密钥
            KeyGenerator keyGen = KeyGenerator.getInstance("DES");
            keyGen.init(56);
            Key key = keyGen.generateKey();
            byte[] keyencode = key.getEncoded();
            //读取接收者证书
            InputStream inStream = new FileInputStream(othercert);
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            X509Certificate oCert = (X509Certificate) cf.generateCertificate(inStream);
            inStream.close();
            //使用接收者证书加密会话密钥
            Cipher cipherRsa = Cipher.getInstance("RSA/ECB/PKCS1Padding",
                    new org.bouncycastle.jce.provider.BouncyCastleProvider());
            cipherRsa.init(Cipher.ENCRYPT_MODE, oCert);
            byte[] cipherkey = cipherRsa.doFinal(keyencode);

            //对原文进行签名
            Key oKey = keystore.getKey("sender", password.toCharArray());
            //创建签名对象/////////////////////////////////
            Signature oSign = Signature.getInstance("SHA1withRSA");
            //初始化签名对象////////////////////////////////////
            oSign.initSign((PrivateKey) oKey); //参数为签名者私钥对象
            byte[] signedBuf = null;
            byte[] buffer = new byte[kBufferSize];
            int len;
            System.out.println(inputFile);
            FileInputStream fin = new FileInputStream(new File(inputFile));
            try {
                while ((len = fin.read(buffer)) != -1) {
                    oSign.update(buffer, 0, len);
                    fin.close();
                }
            } catch (Exception e) {
                ;
            }
            //获得签名值
            signedBuf = oSign.sign();

            //使用会话密钥对文件加密。
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            fin = new java.io.FileInputStream(new File(inputFile));
            // |签名信息长度4Bytes|签名信息|会话密钥的密文长度4Bytes|会话密钥密文|原文数据的密文
            java.io.FileOutputStream fout = new java.io.FileOutputStream(new File(outputFile));
            byte[] blen = new byte[4];
            int datalen = signedBuf.length;
            blen[0] = (byte) (datalen & 0xff);
            blen[1] = (byte) ((datalen >> 8) & 0xff);
            blen[2] = (byte) ((datalen >> 16) & 0xff);
            blen[3] = (byte) (datalen >>> 24);
            fout.write(blen);//写入签名值长度到输出文件
            fout.write(signedBuf);//写入签名值到输出文件
            datalen = cipherkey.length;
            blen[0] = (byte) (datalen & 0xff);
            blen[1] = (byte) ((datalen >> 8) & 0xff);
            blen[2] = (byte) ((datalen >> 16) & 0xff);
            blen[3] = (byte) (datalen >>> 24);
            fout.write(blen);//写入密文会话密钥长度到输出文件
            fout.write(cipherkey);//写入密文会话密钥到输出文件
            byte[] cipherbuffer = null;
            try {
                while ((len = fin.read(buffer)) != -1)//读取原文，加密并写密文到输出文件。
                {
                    cipherbuffer = cipher.update(buffer, 0, len);
                    fout.write(cipherbuffer);
                }
                fin.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            cipherbuffer = cipher.doFinal();
            fout.write(cipherbuffer);
            fout.close();
        } catch (Exception e) {
            System.out.println("Err");
            e.printStackTrace();
        }
    }

    public void decrytion() {

        try {
            //打开接收者keystore文件
            keystore = KeyStore.getInstance("JKS");
            java.io.FileInputStream fis = new java.io.FileInputStream(keystoreFile);
            keystore.load(fis, password.toCharArray());
            fis.close();

	    /*
	     密文文件格式：
	     |签名信息长度4Bytes|签名信息|会话密钥的密文长度4Bytes|会话密钥密文|原文数据的密文
	    */

            //读取签名值、会话密钥的密文
            int len;
            byte[] blen = new byte[4];
            java.io.FileInputStream fin = new java.io.FileInputStream(inputFile);
            try {
                fin.read(blen);//读取签名值长度
            } catch (Exception e) {
                e.printStackTrace();
            }
            len = (blen[0] & 0xff) | ((blen[1] << 8) & 0xff00) | ((blen[2] << 24) >>> 8) | (blen[3] << 24);
            byte[] signedBuf = new byte[len];
            try {
                fin.read(signedBuf);//读取签名值
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                fin.read(blen);//读取密文会话密钥长度
            } catch (Exception e) {
                e.printStackTrace();
            }
            len = (blen[0] & 0xff) | ((blen[1] << 8) & 0xff00) | ((blen[2] << 24) >>> 8) | (blen[3] << 24);
            byte[] cipherkey = new byte[len];
            try {
                fin.read(cipherkey);//读取密文会话密钥
            } catch (Exception e) {
                e.printStackTrace();
            }

            //解密cipherkey
            Cipher cipherRsa = Cipher.getInstance("RSA/ECB/PKCS1Padding", new org.bouncycastle.jce.provider.BouncyCastleProvider());
            Key oRsaKey = keystore.getKey("receiver", password.toCharArray());
            cipherRsa.init(Cipher.DECRYPT_MODE, oRsaKey);
            byte[] keyencode = cipherRsa.doFinal(cipherkey);
            Key oKey = new SecretKeySpec(keyencode, "DES");
            //解密密文
            byte[] buffer = new byte[kBufferSize];
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, oKey);
            byte[] plainbuffer = null;
            java.io.FileOutputStream fout = new java.io.FileOutputStream(new File(outputFile));
            try {

                while ((len = fin.read(buffer)) != -1) {
                    plainbuffer = cipher.update(buffer, 0, len);
                    fout.write(plainbuffer);
                }
                fin.close();
            } catch (Exception e) {
                ;
            }
            plainbuffer = cipher.doFinal();
            fout.write(plainbuffer);
            fout.close();
            //验证签名
            //读取证书
            InputStream inStream = new FileInputStream(othercert);
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            X509Certificate oCert = (X509Certificate) cf.generateCertificate(inStream);
            inStream.close();
            Signature oSign = Signature.getInstance("SHA1withRSA");
            oSign.initVerify(oCert);
            fin = new FileInputStream(new File(outputFile));
            try {
                while ((len = fin.read(buffer)) != -1) {
                    oSign.update(buffer, 0, len);
                    fin.close();
                }
            } catch (Exception e) {
                ;
            }

            //验证签名
            boolean bVerifyed = false;
            try {
                bVerifyed = oSign.verify(signedBuf);
            } catch (SignatureException e) {
                bVerifyed = false;
            }
            if (bVerifyed) {
                System.out.println("验证签名正确。");
            } else {
                System.out.println("验证签名不正确，原文可能被篡改!");
            }

        } catch (Exception e) {
            System.out.println("Err");
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Element element = new Element();
        element.setKeystorePath("C:\\Users\\G\\Desktop\\cert-key\\sender.jks");
        element.setKeystorePassword("111111");
        element.setInputFilePath("C:\\Users\\G\\Desktop\\cert-key\\1.txt");
        element.setOtherCertPath("C:\\Users\\G\\Desktop\\cert-key\\'sender'.cer");
        element.setOutputFilePath("C:\\Users\\G\\Desktop\\cert-key\\2.txt");
        new FunctionImpl(element).encrytion();
    }
}
