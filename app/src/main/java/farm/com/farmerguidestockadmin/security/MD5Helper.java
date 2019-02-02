package farm.com.farmerguidestockadmin.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Helper {
    public String decryptMD(String s) {
        try {
            byte[] bytes = MessageDigest.getInstance("MD5").digest(s.getBytes());
            StringBuilder sb = new StringBuilder();
            int length = bytes.length;
            for (int i = 0; i < length; i++) {
                sb.append(String.format("%02x", new Object[]{Byte.valueOf(bytes[i])}));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
