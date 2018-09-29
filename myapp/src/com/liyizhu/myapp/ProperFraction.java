package com.liyizhu.myapp;

import java.util.Random;

public class ProperFraction {
	public int numerator;//分子
	public int denominator;//分母
	private Random random = new Random();
	
	/*
	 * 随机获取一个数
	 */
	public ProperFraction getRandom(int Max) {
		if(random.nextBoolean()) {
			return getInt(Max);
		}else {
			return getProperFraction(Max);
		}
	}
	
	/*
	 * 获取整数
	 */
	public ProperFraction getInt(int Max) {
		numerator = MyappUtil.getInt(Max);
		denominator = 1;
		return this;
	}
	
	/*
	 * 获取分数
	 */
	public ProperFraction getProperFraction(int Max) {
		numerator = MyappUtil.getInt(Max-1)+1;
		denominator = MyappUtil.getInt(Max-1)+1;
		int i = numerator>denominator?denominator:numerator;
		for(;i>1;i--) {
			if(numerator%i==0&&denominator%i==0) {
				numerator /= i;
				denominator /= i;
				break;
			}
		}
		return this;
	}
	
	@Override
	public String toString() {
		String string = null;
		if(numerator==0) {
			return "0";
		}
		if(numerator<denominator) {
			string = numerator + "/" + denominator;
		}else if(numerator==denominator){
			string = "1";
		}else {
			if(denominator==1) {
				string = "" + numerator;
			}else {
				if(denominator==0) {
					 string = "0";
				}else {
					string = numerator/denominator + "\'" + numerator%denominator + "/" + denominator; 
				}
			}
		}
		return string;
	}
}
