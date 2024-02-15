package com.quarkus.totochto.auth.helper;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.jboss.logging.Logger;

public class EncrypterHelper {

	private final Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * 
	 * @param originalPassword
	 * @param storedPassword
	 * @return boolean - true: in case both passwords are the same, false: otherwise
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public boolean validatePassword(String originalPassword, String storedPassword) throws NoSuchAlgorithmException, InvalidKeySpecException{
         try {
            String[] parts = storedPassword.split(":");
            int iterations = Integer.parseInt(parts[0]);
            byte[] salt = fromHex(parts[1]);
            byte[] hash = fromHex(parts[2]);

            PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, hash.length * 8);

            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

            byte[] testHash = skf.generateSecret(spec).getEncoded();
                       
            int diff = hash.length ^ testHash.length;
            for(int i = 0; i < hash.length && i < testHash.length; i++) {
                   diff |= hash[i] ^ testHash[i];
            }

            return diff == 0;

         } catch (NumberFormatException e) {
            log.error("NumberFormatException al validar contraseña");
            return false;
         }
    }

	
	/**
	 * Parse a string into a byte[]
	 * @param hex
	 * @return string parsed
	 */
    private static byte[] fromHex(String hex) {

        byte[] bytes = new byte[hex.length() / 2];
        for(int i = 0; i<bytes.length ;i++) {
            bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }

        return bytes;
    }
    
}
