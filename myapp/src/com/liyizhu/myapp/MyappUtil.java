package com.liyizhu.myapp;

import java.util.Random;

public class MyappUtil {
	private static Random random = new Random();
	/*
	 * 随机获取小于Max随机整数
	 */
	public static int getInt(int Max) {
		return random.nextInt(Max);
	}
	
	/*
	 * 随机获取操作符
	 */
	private static char[] operators = {'+','-','*','/'};
	public static char getOperator() {
		return operators[random.nextInt(4)];
	}
	/*
	 * 计算优先级
	 */
	private static int calculatePriority(String operator) {
		switch (operator) {
		case "+":
		case "-":
			return 0;
		case "*":
		case "/":
			return 1;
		default:
			return -1;
				
		}
	}
	
	/*
	 * 比较优先级
	 */
	public static int newComparePriority(String a,String b) {
		if(calculatePriority(a)>calculatePriority(b)) {
			return 1;
		}else if(calculatePriority(a)<calculatePriority(b)){
			return -1;
		}else {
			return 0;
		}
	}
	/*
	 *比较优先级
	 */
	public static boolean comparePriority(String a,String b) {
		if(calculatePriority(a)>=calculatePriority(b)) {
			return true;
		}else {
			return false;
		}
	}
	/*
	 * 将字符串转换为分数
	 */
	private static ProperFraction translate(String s) {
		ProperFraction properFraction = new ProperFraction();
		if(s.contains("'")) {
			String[] strings = s.split("['/]");
			properFraction.numerator = Integer.parseInt(strings[0])*Integer.parseInt(strings[2]) + Integer.parseInt(strings[1]);
			properFraction.denominator = Integer.parseInt(strings[2]);
		}else if(s.contains("/")){
			String[] strings = s.split("/");
			properFraction.numerator = Integer.parseInt(strings[0]);
			properFraction.denominator = Integer.parseInt(strings[1]);
		}else {
			properFraction.numerator = Integer.parseInt(s);
			properFraction.denominator = 1;
		}
		return properFraction;
	}
	/*
	 * 将分数约分
	 */
	public static ProperFraction simplify(ProperFraction p) {
		int i = p.numerator>p.denominator?p.denominator:p.numerator;
		for(;i>1;i--) {
			if(p.numerator%i==0&&p.denominator%i==0) {
				p.numerator /= i;
				p.denominator /= i;
				break;
			}
		}
		return p;
	}
	/*
	 * 实现加法
	 */
	public static ProperFraction add(String s1,String s2) {
		ProperFraction p1 = translate(s1);
		ProperFraction p2 = translate(s2);
		ProperFraction p = new ProperFraction();
		p.numerator = p1.numerator*p2.denominator + p1.denominator*p2.numerator;
		p.denominator = p1.denominator*p2.denominator;
		return simplify(p);
	}
	/*
	 * 实现减法
	 */
	public static ProperFraction sub(String s1,String s2) {
		ProperFraction p1 = translate(s1);
		ProperFraction p2 = translate(s2);
		ProperFraction p = new ProperFraction();
		p.numerator = p1.numerator*p2.denominator - p1.denominator*p2.numerator;
		p.denominator = p1.denominator*p2.denominator;
		return simplify(p);
	}
	/*
	 * 实现乘法
	 */
	public static ProperFraction multiply(String s1,String s2) {
		ProperFraction p1 = translate(s1);
		ProperFraction p2 = translate(s2);
		ProperFraction p = new ProperFraction();
		p.numerator = p1.numerator*p2.numerator;
		p.denominator = p1.denominator*p2.denominator;
		return simplify(p);
	}
	/*
	 * 实现除法
	 */
	public static ProperFraction div(String s1,String s2) {
		ProperFraction p1 = translate(s1);
		ProperFraction p2 = translate(s2);
		ProperFraction p = new ProperFraction();
		p.numerator = p1.numerator*p2.denominator;
		p.denominator = p1.denominator*p2.numerator;
		return simplify(p);
	}
}
