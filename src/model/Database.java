package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.domain.Person;

public class Database {
	private static Person[] students;

	public static Person[] getStudents() {
		return students;
	}

	static {
		students = new Person[] { new Person("이조은", "ISFJ", false, false), new Person("이제현", "INFJ", true, false),
				new Person("이정이", "INFJ", false, true), new Person("임유진", "ISTJ", false, false),
				new Person("임채준", "ISTJ", false, false), new Person("최홍석", "INFJ", false, false),
				new Person("고태우", "INFJ", false, false), new Person("이노운", "ENTP", false, false),
				new Person("이기현", "ISTJ", false, false), new Person("강한솔", "ESFP", true, true),
				new Person("김현수", "ESTJ", false, false), new Person("박지원", "ISFJ", false, true),
				new Person("서민지", "ESFJ", true, true), new Person("문민경", "ISFP", false, false),
				new Person("정서현", "ISTJ", false, true), new Person("장송하", "ESFP", true, false),
				new Person("김동민", "ENFJ", false, false), new Person("이영주", "INTP", true, false),
				new Person("전수민", "INTP", true, false), new Person("신준수", "ENFP", false, false),
				new Person("박여명", "ISTJ", false, false), new Person("이용훈", "ISTP", false, true),
				new Person("황지환", "INTP", true, true), new Person("정다빈", "ENFJ", false, false),
				new Person("홍윤기", "ISTJ", false, false), new Person("김문석", "ESFJ", false, false),
				new Person("홍혜원", "INTJ", false, false), new Person("최소영", "ENTP", true, true),
				new Person("황병길", "INTP", false, false), new Person("신기범", "ISTP", false, false) };
	}

	public static Map<String, List<String>> mbtiMap = new HashMap<>();

	public static Map<String, List<String>> getmbti() {
		return mbtiMap;
	}
}
