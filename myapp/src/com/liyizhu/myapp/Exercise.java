package com.liyizhu.myapp;

import java.util.Random;
import java.util.Stack;
public class Exercise {
	public String expression[];
	
	public Stack<String> stack = new Stack<String>();
	public void getCheckStack(String s){
		Stack<String> operatedNumber = new Stack<String>();
 		Stack<String> operator = new Stack<String>();
 		String currentOperator = null;
 		boolean isBracket = false;//是否进入括号内
 		s = s.substring(0,s.length()-2);
 		String[] strings = s.split(" ");
 		for(int i=0;i<strings.length;i++) {
 			switch (strings[i]) {
			case "(":
				operator.push(strings[i]);
				isBracket = true;
				break;
			case ")":
				{
					String b = operatedNumber.pop();//后操作数
					
					String a = operatedNumber.pop();//前操作数
					String temp = operator.pop();
					if("+".equals(temp)) {
						operatedNumber.push(MyappUtil.add(a, b).toString());
						if(a.compareTo(b)>=0) {
							stack.push(a);
							stack.push(b);
						}else {
							stack.push(b);
							stack.push(a);
						}
						stack.push(temp);
					}else if("-".equals(temp)){
						operatedNumber.push(MyappUtil.sub(a, b).toString());
						stack.push(a);
						stack.push(b);
						stack.push(temp);
					}else if("*".equals(temp)) {
						operatedNumber.push(MyappUtil.multiply(a, b).toString());
						if(a.compareTo(b)>=0) {
							stack.push(a);
							stack.push(b);
						}else {
							stack.push(b);
							stack.push(a);
						}
						stack.push(temp);
					}else {
						operatedNumber.push(MyappUtil.div(a, b).toString());
						stack.push(a);
						stack.push(b);
						stack.push(temp);
					}
					operator.pop();
					isBracket = false;
					break;
				}
			case "+":
			case "-":
				if(isBracket) {
					operator.push(strings[i]);
				}else if(currentOperator==null) {
					currentOperator = strings[i];
					operator.push(strings[i]);
				}else {
					if(MyappUtil.newComparePriority(strings[i], currentOperator)<=0) {
						String b = operatedNumber.pop();//后操作数
						String a = operatedNumber.pop();//前操作数
						String temp = operator.pop();
						if("+".equals(temp)) {
							operatedNumber.push(MyappUtil.add(a, b).toString());
							if(a.compareTo(b)>=0) {
								stack.push(a);
								stack.push(b);
							}else {
								stack.push(b);
								stack.push(a);
							}
							stack.push(temp);
						}else if("-".equals(temp)){
							operatedNumber.push(MyappUtil.sub(a, b).toString());
							stack.push(a);
							stack.push(b);
							stack.push(temp);
						}else if("*".equals(temp)) {
							operatedNumber.push(MyappUtil.multiply(a, b).toString());
							if(a.compareTo(b)>=0) {
								stack.push(a);
								stack.push(b);
							}else {
								stack.push(b);
								stack.push(a);
							}
							stack.push(temp);
						}else {
							operatedNumber.push(MyappUtil.div(a, b).toString());
							stack.push(a);
							stack.push(b);
							stack.push(temp);
						}
						operator.push(strings[i]);
						currentOperator = strings[i];
					}else {
						operator.push(strings[i]);
						currentOperator = strings[i];
					}
				}
				break;
			case "*":
			case "/":
				if(isBracket) {
					operator.push(strings[i]);		
				}else if(currentOperator==null) {
					currentOperator = strings[i];
					operator.push(strings[i]);
				}else {
					if(MyappUtil.newComparePriority(strings[i], currentOperator)<=0){
						String b = operatedNumber.pop();//后操作数
						String a = operatedNumber.pop();//前操作数
						String temp = operator.pop();
						if("+".equals(temp)) {
							operatedNumber.push(MyappUtil.add(a, b).toString());
							if(a.compareTo(b)>=0) {
								stack.push(a);
								stack.push(b);
							}else {
								stack.push(b);
								stack.push(a);
							}
							stack.push(temp);
						}else if("-".equals(temp)){
							operatedNumber.push(MyappUtil.sub(a, b).toString());
							stack.push(a);
							stack.push(b);
							stack.push(temp);
						}else if("*".equals(temp)) {
							operatedNumber.push(MyappUtil.multiply(a, b).toString());
							if(a.compareTo(b)>=0) {
								stack.push(a);
								stack.push(b);
							}else {
								stack.push(b);
								stack.push(a);
							}
							stack.push(temp);
						}else {
							operatedNumber.push(MyappUtil.div(a, b).toString());
							stack.push(a);
							stack.push(b);
							stack.push(temp);
						}
						operator.push(strings[i]);
						currentOperator = strings[i];
					}else if(!strings[i+1].equals("(")){
						String b = strings[i+1];//后操作数
						String a = operatedNumber.pop();//前操作数
						String temp = strings[i];
						if("+".equals(temp)) {
							operatedNumber.push(MyappUtil.add(a, b).toString());
							if(a.compareTo(b)>=0) {
								stack.push(a);
								stack.push(b);
							}else {
								stack.push(b);
								stack.push(a);
							}
							stack.push(temp);
						}else if("-".equals(temp)){
							operatedNumber.push(MyappUtil.sub(a, b).toString());
							stack.push(a);
							stack.push(b);
							stack.push(temp);
						}else if("*".equals(temp)) {
							operatedNumber.push(MyappUtil.multiply(a, b).toString());
							if(a.compareTo(b)>=0) {
								stack.push(a);
								stack.push(b);
							}else {
								stack.push(b);
								stack.push(a);
							}
							stack.push(temp);
						}else {
							operatedNumber.push(MyappUtil.div(a, b).toString());
							stack.push(a);
							stack.push(b);
							stack.push(temp);
						}
						operator.push(strings[i]);
						currentOperator = strings[i];
					}else {
						operator.push(strings[i]);
						currentOperator = strings[i];
					}
				}
				break;
			default:
				operatedNumber.push(strings[i]);
				break;
			}
 		}
 		while(!operatedNumber.isEmpty()&&!operator.isEmpty()) {
 			String b = operatedNumber.pop();//后操作数
			String a = operatedNumber.pop();//前操作数
			String temp = operator.pop();
			switch (temp) {
			case "+":
				operatedNumber.push(MyappUtil.add(a, b).toString());
				if(a.compareTo(b)>=0) {
					stack.push(a);
					stack.push(b);
				}else {
					stack.push(b);
					stack.push(a);
				}
				stack.push(temp);
				break;
			case "-":
				stack.push(a);
				stack.push(b);
				stack.push(temp);
				operatedNumber.push(MyappUtil.sub(a, b).toString());
				break;
			case "*":
				if(a.compareTo(b)>=0) {
					stack.push(a);
					stack.push(b);
				}else {
					stack.push(b);
					stack.push(a);
				}
				stack.push(temp);
				operatedNumber.push(MyappUtil.multiply(a, b).toString());
				break;
			case "/":
				stack.push(a);
				stack.push(b);
				stack.push(temp);
				operatedNumber.push(MyappUtil.div(a, b).toString());
				break;
			default:
				break;
			}
 		}
	}
	public static String[] getExpression(int Max) {
		String[] strings = new String[2];
		do {
			isIllegal = true;
			strings[0] = getExercise(Max);
			strings[1] = answerOfExercise(strings[0]);
		}while(!isIllegal);
		return strings;
	}
	/*
	 * 获取一道四则运算题
	 */
 	public static String getExercise(int Max) {
 		StringBuilder sb = new StringBuilder();
 		ProperFraction p = new ProperFraction();
 	 	Random random = new Random();
 	 	//生成不含括号的表达式
 		int i = 0;
 		do{
 			if(i==0) {
 				sb.append(p.getRandom(Max)+" "+MyappUtil.getOperator()+" "+p.getRandom(Max));
 			}else {
 				sb.append(" "+MyappUtil.getOperator()+" "+p.getRandom(Max));
 			}
 			i++;
 	 	}while(random.nextBoolean()&&i<3);
 		
 		//添加括号
 		String[] strings = sb.toString().split(" ");
 		if(strings.length==5) {
 			if(random.nextBoolean()) {
 				if(MyappUtil.comparePriority(strings[1],strings[3])){
 					sb = new StringBuilder(strings[0]+" "+strings[1]+" ( "+strings[2]+" "+strings[3]+" "+strings[4]+" )");
 				}else {
 					sb = new StringBuilder("( "+strings[0]+" "+strings[1]+" "+strings[2]+" ) "+strings[3]+" "+strings[4]);
 				}
 			}
 		}
 		if(strings.length==7) {
 			boolean bool1 = true;//标志中间位置是否生成括号
 			boolean bool2 = true;//标志开始位置是否生成括号
 			if(random.nextBoolean()) {
 				if(MyappUtil.comparePriority(strings[1],strings[3])){
 					sb = new StringBuilder(strings[0]+" "+strings[1]+" ( "+strings[2]+" "+strings[3]+" "+strings[4]+" )"
 							+" "+strings[5]+" "+strings[6]);
 					bool1 = false;
 				}else{
 					sb = new StringBuilder("( "+strings[0]+" "+strings[1]+" "+strings[2]+" ) "+strings[3]+" "+strings[4]
 							+" "+strings[5]+" "+strings[6]);
 					bool2 = false;
 				}
 			}
 			strings = sb.toString().split(" ");
 			if(bool1&&random.nextBoolean()) {
				if(MyappUtil.comparePriority(strings[3], strings[5])) {
					if(bool2) {
						sb = new StringBuilder(strings[0]+" "+strings[1]+" "+strings[2]+" "+strings[3]+" ( "+strings[4]
	 							+" "+strings[5]+" "+strings[6]+" )");
					}else {
						sb = new StringBuilder(strings[0]+" "+strings[1]+" "+strings[2]+" "+strings[3]+" "+strings[4]
	 							+" "+strings[5]+" ( "+strings[6]+" "+strings[7]+" "+strings[8]+" )");
					}
				}else {
					if(bool2) {
						sb = new StringBuilder(strings[0]+" "+strings[1]+" ( "+strings[2]+" "+strings[3]+" "+strings[4]
	 							+" ) "+strings[5]+" "+strings[6]);
					}
				}
			}
 		}
 		sb.append(" "+"=");
 		return sb.toString();
 	}
 	
 	public static boolean isIllegal = true;
 	/*
 	 * 判断表达式是否合法并获取表达式答案
 	 */
 	public static String answerOfExercise(String s) {
 		Stack<String> operatedNumber = new Stack<String>();
 		Stack<String> operator = new Stack<String>();
 		String currentOperator = null;
 		boolean isBracket = false;//是否进入括号内
 		s = s.substring(0,s.length()-2);
 		String[] strings = s.split(" ");
 		for(int i=0;i<strings.length;i++) {
 			switch (strings[i]) {
			case "(":
				operator.push(strings[i]);
				isBracket = true;
				break;
			case ")":
				{
					String b = operatedNumber.pop();//后操作数
					String a = operatedNumber.pop();//前操作数
					String temp = operator.pop();
					if("-".equals(temp)) {
						if(MyappUtil.sub(a, b).toString().contains("-")) {
							isIllegal = false;
						}
					}else if("/".equals(temp)&&"0".equals(b)) {
						isIllegal = false;
					}
					if("+".equals(temp)) {
						operatedNumber.push(MyappUtil.add(a, b).toString());
					}else if("-".equals(temp)){
						operatedNumber.push(MyappUtil.sub(a, b).toString());
					}else if("*".equals(temp)) {
						operatedNumber.push(MyappUtil.multiply(a, b).toString());
					}else {
						operatedNumber.push(MyappUtil.div(a, b).toString());
					}
					operator.pop();
					isBracket = false;
					break;
				}
			case "+":
			case "-":
				if(isBracket) {
					operator.push(strings[i]);
				}else if(currentOperator==null) {
					currentOperator = strings[i];
					operator.push(strings[i]);
				}else {
					if(MyappUtil.newComparePriority(strings[i], currentOperator)<=0) {
						String b = operatedNumber.pop();//后操作数
						String a = operatedNumber.pop();//前操作数
						String temp = operator.pop();
						if("-".equals(temp)) {
							if(MyappUtil.sub(a, b).toString().contains("-")) {
								isIllegal = false;
							}
						}else if("/".equals(temp)&&"0".equals(b)) {
							isIllegal = false;
						}
						if("+".equals(temp)) {
							operatedNumber.push(MyappUtil.add(a, b).toString());
						}else if("-".equals(temp)){
							operatedNumber.push(MyappUtil.sub(a, b).toString());
						}else if("*".equals(temp)) {
							operatedNumber.push(MyappUtil.multiply(a, b).toString());
						}else {
							operatedNumber.push(MyappUtil.div(a, b).toString());
						}
						operator.push(strings[i]);
						currentOperator = strings[i];
					}else {
						operator.push(strings[i]);
						currentOperator = strings[i];
					}
				}
				break;
			case "*":
			case "/":
				if(isBracket) {
					operator.push(strings[i]);		
				}else if(currentOperator==null) {
					currentOperator = strings[i];
					operator.push(strings[i]);
				}else {
					if(MyappUtil.newComparePriority(strings[i], currentOperator)<=0){
						String b = operatedNumber.pop();//后操作数
						String a = operatedNumber.pop();//前操作数
						String temp = operator.pop();
						if("-".equals(temp)) {
							if(MyappUtil.sub(a, b).toString().contains("-")) {
								isIllegal = false;
							}
						}else if("/".equals(temp)&&"0".equals(b)) {
							isIllegal = false;
						}
						if("+".equals(temp)) {
							operatedNumber.push(MyappUtil.add(a, b).toString());
						}else if("-".equals(temp)){
							operatedNumber.push(MyappUtil.sub(a, b).toString());
						}else if("*".equals(temp)) {
							operatedNumber.push(MyappUtil.multiply(a, b).toString());
						}else {
							operatedNumber.push(MyappUtil.div(a, b).toString());
						}
						operator.push(strings[i]);
						currentOperator = strings[i];
					}else if(!strings[i+1].equals("(")){
						String b = strings[i+1];//后操作数
						String a = operatedNumber.pop();//前操作数
						String temp = strings[i];
						if("-".equals(temp)) {
							if(MyappUtil.sub(a, b).toString().contains("-")) {
								isIllegal = false;
							}
						}else if("/".equals(temp)&&"0".equals(b)) {
							isIllegal = false;
						}
						
						if("+".equals(temp)) {
							operatedNumber.push(MyappUtil.add(a, b).toString());
						}else if("-".equals(temp)){
							operatedNumber.push(MyappUtil.sub(a, b).toString());
						}else if("*".equals(temp)) {
							operatedNumber.push(MyappUtil.multiply(a, b).toString());
						}else {
							operatedNumber.push(MyappUtil.div(a, b).toString());
						}
						operator.push(strings[i]);
						currentOperator = strings[i];
					}else {
						operator.push(strings[i]);
						currentOperator = strings[i];
					}
				}
				break;
			default:
				operatedNumber.push(strings[i]);
				break;
			}
 		}
 		while(!operatedNumber.isEmpty()&&!operator.isEmpty()) {
 			String b = operatedNumber.pop();//后操作数
			String a = operatedNumber.pop();//前操作数
			String temp = operator.pop();
			switch (temp) {
			case "+":
				operatedNumber.push(MyappUtil.add(a, b).toString());
				break;
			case "-":
				if("-".equals(temp)) {
					if(MyappUtil.sub(a, b).toString().contains("-")) {
						isIllegal = false;
					}
				}
				operatedNumber.push(MyappUtil.sub(a, b).toString());
				break;
			case "*":
				operatedNumber.push(MyappUtil.multiply(a, b).toString());
				break;
			case "/":
				if("0".equals(b)) {
					isIllegal = false;
				}
				operatedNumber.push(MyappUtil.div(a, b).toString());
				break;
			default:
				break;
			}
 		}
 		return operatedNumber.pop();
 	}
	
	@Override
	public int hashCode() {
		return expression[1].hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		this.getCheckStack(expression[0]);
		Exercise exercise = (Exercise)obj;
		exercise.getCheckStack(exercise.expression[0]);
		Stack<String> stack1 = exercise.stack;
		while(!stack.isEmpty()&&!stack1.isEmpty()) {
			if(!stack.pop().equals(stack1.pop())) {
				return false;
			}
		}
		if(stack.isEmpty()&&stack1.isEmpty()) {
			return true;
		}
		return false;
	}
}
