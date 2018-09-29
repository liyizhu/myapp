package com.liyizhu.myapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class MyappMain {

	public static void main(String[] args) {
		while(true) {
	    Scanner scanner = new Scanner(System.in);
	    System.out.println("请输入命令：");
	    String s=scanner.nextLine();
	    String[] orders = s.trim().split(" ");
	    if(orders.length==5) {
	    	if("Myapp".equals(orders[0])&&"-n".equals(orders[1])&&"-r".equals(orders[3])) {
	    		int numOfExercises = Integer.parseInt(orders[2]);
	    		int Max = Integer.parseInt(orders[4]);
	    		BufferedWriter bw;
	    		BufferedWriter bw1;
				try {
					bw = new BufferedWriter(new FileWriter(new File("E:\\exercise.txt")));
					bw1 = new BufferedWriter(new FileWriter(new File("E:\\answer.txt")));
					Set<Exercise> set = Exercises.getExercisesSet(numOfExercises, Max);
		    	    Iterator<Exercise> eIterator = set.iterator();
		    	    int i = 0;
		    	    while(eIterator.hasNext()) {
		    	    	i++;
		    	    	String[] string = eIterator.next().expression;
		    	    	bw.write(i+",  "+string[0]);
		    	    	bw.newLine();
		    	    	bw1.write(i+",   "+string[1]);
		    	    	bw1.newLine();
		    	    }
		    	    bw.close();
		    	    bw1.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	    	    
	    	    
	    	}else if("Myapp".equals(orders[0])&&"-e".equals(orders[1])&&"-a".equals(orders[3])){
	    		try {
					BufferedReader br1 = new BufferedReader(new FileReader(new File(orders[2])));
					BufferedReader br2 = new BufferedReader(new FileReader(new File(orders[4])));
					int i=0;
					int numOfCorrect = 0;
					int numOfError = 0;
					StringBuilder corret = new StringBuilder("(");
					StringBuilder error = new StringBuilder("(");
					String e = null;
					String a = null;
					while(true) {
						i++;
						try {
							if((e=br1.readLine())!=null&&(a=br2.readLine())!=null){
						
								if(e.split("=")[1].trim().equals(a.split("\\s+")[1].trim())) {
									corret.append(i+",");
									numOfCorrect++;
								}else {
									error.append(i+",");
									numOfError++;
								}
							}else {
								break;
							}
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					br1.close();
					br2.close();
					corret.deleteCharAt(corret.length()-1);
					error.deleteCharAt(error.length()-1);
					System.out.println("Corret: "+numOfCorrect+corret.toString()+")");
					System.out.println("Error: "+numOfError+error.toString()+")");
				} catch (FileNotFoundException e) {
					System.out.println("文件不存在");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
	    	}else{
	    		System.out.println("错误命令");
				System.out.println("输入\"Myapp help\"或\"Myapp ？\"查看帮助文档");
	    	}
	    }else if(orders.length==2){
	    	if("Myapp".equals(orders[0])&&("help".equals(orders[1])||"?".equals(orders[1]))){
	    		System.out.println();
	    		System.out.println("Myapp -n 生成题目数目 -r 生成题目数值的最大值         功能：生成题目");
	    		System.out.println("Myapp -e <exercisefile>.txt -a <answerfile>.txt   功能：判定答案中的对错并进行数量统计");
	    	}
	    }else {
	    	System.out.println("错误命令");
			System.out.println("输入\"Myapp help\"或\"Myapp ？\"查看帮助文档");
	    }
	    
	}
	}
}
