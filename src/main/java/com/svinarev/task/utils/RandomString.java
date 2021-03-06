package com.svinarev.task.utils;

import java.security.SecureRandom;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

public class RandomString {

	  private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	  private static final String LOWER = UPPER.toLowerCase(Locale.ROOT);
	  private static final String DIGITS = "0123456789";
	  private static final String ALPHA = UPPER + LOWER;
	  private static final String ALPHANUM = UPPER + LOWER + DIGITS;
	  private final Random random;
	  private final char[] symbols;
	  private final char[] buf;

	  public RandomString(int length, Random random, String symbols) {
		if (length < 1) {
			throw new IllegalArgumentException();
		}
		if (symbols.length() < 2) {
			throw new IllegalArgumentException();
		}
		this.random = Objects.requireNonNull(random);
		this.symbols = symbols.toCharArray();
		this.buf = new char[length];
	  }

	  public RandomString(int length, Random random) {
		  this(length, random, ALPHANUM);
	  }

	  public RandomString(int length) {
		  this(length, new SecureRandom());
	  }

	  public RandomString() {
		  this(21);
	  }

	  public String nextString() {
		  for (int idx = 0; idx < buf.length; ++idx) {
			  buf[idx] = symbols[random.nextInt(symbols.length)];
		  }
		  return new String(buf);
	  }

	  public static String getRandomStringCharAndNum(int length) {
		  RandomString randomString = new RandomString(length, new SecureRandom());
		  return randomString.nextString();
	  }

	  public static String getRandomStringChar(int length) {
		  RandomString randomString = new RandomString(length, new SecureRandom(), ALPHA);
		  return randomString.nextString();
	  }
	}
