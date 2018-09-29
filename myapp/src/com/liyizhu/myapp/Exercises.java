package com.liyizhu.myapp;

import java.util.HashSet;
import java.util.Set;

public class Exercises {
	/*
	 * 外部获取保存运算式子的Set
	 */
	public static Set<Exercise> getExercisesSet(int numOfExercises,int Max){
		Set<Exercise> set = new HashSet<>();
		while(set.size()<numOfExercises) {
			Exercise exercise = new Exercise();
			exercise.expression = Exercise.getExpression(Max);
			set.add(exercise);
		}
		System.out.println(set.size());
		return set;
	}
	
	
}
