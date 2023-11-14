package GuideBook.GuideBook.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityUtils {
    public static String hashPassword(String password) {
        try {
            // 로그 출력
            System.out.println("Hashing password for: " + password);

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedPassword = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedPassword) {
                sb.append(String.format("%02x", b));
            }

            // 해시된 결과 로그 출력
            System.out.println("Hashed password: " + sb.toString());

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to hash password", e);
        }
    }

    public static boolean checkPassword(String password, String hashedPassword) {
        try {
            // 사용자가 제출한 비밀번호를 해싱하여 비교
            String hashedInputPassword = hashPassword(password);
            return hashedInputPassword.equals(hashedPassword);
        } catch (RuntimeException e) {
            // 에러 처리
            e.printStackTrace();
            return false;
        }
    }
}
