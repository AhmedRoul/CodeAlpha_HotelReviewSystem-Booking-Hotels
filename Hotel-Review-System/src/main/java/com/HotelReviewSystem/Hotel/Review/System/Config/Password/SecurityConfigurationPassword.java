package com.HotelReviewSystem.Hotel.Review.System.Config.Password;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

public class SecurityConfigurationPassword{
    private static final Logger log = LoggerFactory.getLogger(SecurityConfigurationPassword.class);


    @Bean
    private static byte[] randomSalt() {
        SecureRandom random = new SecureRandom();
        Random rndm = new Random();
        ArrayList<Integer> numberBytes = new ArrayList(Arrays.asList(16, 32, 64));
        int rndmIndx = rndm.nextInt(numberBytes.size());
        int numberbyte = (Integer)numberBytes.get(rndmIndx);
        byte[] salt = new byte[numberbyte];
        random.nextBytes(salt);
        return salt;
    }

    private static String passwordEncoder(@NotNull String password, byte[] salt) throws InvalidKeySpecException {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);

        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = factory.generateSecret(spec).getEncoded();

            return Base64.getEncoder().encodeToString(salt).length() + Base64.getEncoder().encodeToString(salt) + Base64.getEncoder().encodeToString(hash);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e ) {

            throw new InvalidKeySpecException("Error generating secret key", e);

        }
    }

    @Bean(
            name = {"PasswordEncoder"}
    )
    public static String NewPasswordEncoder(String password) throws InvalidKeySpecException {
        return passwordEncoder(password, randomSalt());
    }

    @Bean(
            name = {"matchPasswords"}
    )
    public static boolean matchPasswords(String plainTextPassword, String encodedPassword) throws InvalidKeySpecException {
        if (encodedPassword == null) {
            return false;
        } else {
            String stringlensalt = new String(encodedPassword.substring(0, 2));
            int lensalt = Integer.parseInt(stringlensalt);
            String saltString = encodedPassword.substring(2, 2 + lensalt);
            byte[] salt = Base64.getDecoder().decode(saltString);
            String inputHash = passwordEncoder(plainTextPassword, salt);
            return inputHash.equals(encodedPassword);
        }
    }
}
