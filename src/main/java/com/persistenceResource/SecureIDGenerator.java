package com.persistenceResource;

import java.math.BigInteger;
import java.security.SecureRandom;


/**
 * This code was borrowed from http://stackoverflow.com/questions/41107/how-to-generate-a-random-alpha-numeric-string
 * to generate secure and random IDs for our entities.
 * @author N/A
 *
 */
public final class SecureIDGenerator {
  
	/**
	 * This method is used to generate a random 26 digit string that is used
	 * as an ID for some of our Entities.
	 * 
	 * @return	A random 26 digit string based on a SecureRandom and a BigInteger.
	 */
  public static String nextSecureId() {  
	SecureRandom random = new SecureRandom();
    return new BigInteger(130, random).toString(32);
  }

}