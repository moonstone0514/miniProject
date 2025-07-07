package model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.domain.Person;

public class Database {
    private static Person[] student;

    static {
        student = new Person[] {
            new Person("이조은", "ISFJ", false),
            new Person("이제현", "INFJ", true),
            new Person("이정이", "INFJ", false),
            new Person("임유진", "ISTJ", false),
            new Person("임채준", "ISTJ", false),
            new Person("최홍석", "INFJ", false),
            new Person("고태우", "INFJ", false),
            new Person("이노운", "ENTP", false),
            new Person("이기현", "ISTJ", false),
            new Person("강한솔", "ESFP", true),
            new Person("김현수", "ESTJ", false),
            new Person("박지원", "ISFJ", false),
            new Person("서민지", "ESFJ", true),
            new Person("문민경", "ISFP", false),
            new Person("정서현", "ISTJ", false),
            new Person("장송하", "ESFP", true),
            new Person("김동민", "ENFJ", false),
            new Person("이영주", "INTP", true),
            new Person("전수민", "INTP", true),
            new Person("신준수", "ENFP", false),
            new Person("박여명", "ISTJ", false),
            new Person("이용훈", "ISTP", false),
            new Person("황지환", "INTP", true),
            new Person("정다빈", "ENFJ", false),
            new Person("홍윤기", "ISTJ", false),
            new Person("김문석", "ESFJ", false),
            new Person("홍혜원", "INTJ", false),
            new Person("최소영", "ENTP", true),
            new Person("황병길", "INTP", false),
            new Person("신기범", "ISTP", false)
        };
    }
    
    public static final Map<String, List<String>> mbtiMap = new HashMap<>();

    static {
        mbtiMap.put("INFP", Arrays.asList("ENFJ", "ENTJ", "ENFP", "INFJ", "INTJ", "INTP", "ENTP"));
        mbtiMap.put("ENFP", Arrays.asList("INFJ", "INTJ", "INFP", "ENFJ", "ENTJ", "INTP", "ENTP"));
        mbtiMap.put("INFJ", Arrays.asList("ENFP", "ENTP", "INFP", "ENFJ", "INTJ", "ENTJ", "INTP"));
        mbtiMap.put("ENFJ", Arrays.asList("INFP", "ISFP", "ENFP", "INFJ", "INTJ", "ENTJ", "INTP", "ENTP"));
        mbtiMap.put("INTJ", Arrays.asList(
            "ENFP", "ENTP",                           // :심박:
            "INFP", "INFJ", "ENFJ", "ENTJ", "INTP",   // 초록
            "ISFP", "ESFP", "ISTP", "ESTP", "ISFJ", "ESFJ", "ISTJ", "ESTJ" // 노랑
        ));
        mbtiMap.put("ENTJ", Arrays.asList(
            "INFP", "INTP",                           // :심박:
            "ENFP", "INFJ", "ENFJ", "INTJ", "ENTP",   // 초록
            "ISFP", "ESFP", "ISTP", "ESTP", "ISFJ", "ESFJ", "ISTJ", "ESTJ" // 노랑
        ));
        mbtiMap.put("INTP", Arrays.asList(
            "ENTJ", "ESTJ",                           // :심박:
            "INFP", "ENFP", "INFJ", "ENFJ", "INTJ", "ENTP", // 초록
            "ISFP", "ESFP", "ISTP", "ISFJ", "ESFJ", "ISTJ"  // 노랑
        ));
        mbtiMap.put("ENTP", Arrays.asList(
            "INFJ", "INTJ",                           // :심박:
            "INFP", "ENFP", "ENFJ", "ENTJ", "INTP",   // 초록
            "ISFP", "ISTP", "ESTP", "ISFJ", "ESFJ", "ISTJ", "ESTJ" // 노랑
        ));
        mbtiMap.put("ISFP", Arrays.asList(
            "ENFJ", "ESFJ", "ESTJ",                   // :심박:
            "INTJ", "ENTJ", "INTP", "ENTP", "ESFP", "ISTP", "ESTP", "ISFJ", "ISTJ" // 노랑
        ));
        mbtiMap.put("ESFP", Arrays.asList(
            "ISFJ", "ISTJ",                           // :심박:
            "INTJ", "ENTJ", "INTP", "ENTP", "ISFP", "ISTP", "ESTP", "ESFJ", "ESTJ" // 노랑
        ));
        mbtiMap.put("ISTP", Arrays.asList(
            "ESFJ", "ESTJ",                           // :심박:
            "INTJ", "ENTJ", "INTP", "ENTP", "ISFP", "ESFP", "ESTP", "ISFJ", "ISTJ" // 노랑
        ));
        mbtiMap.put("ESTP", Arrays.asList(
            "ISFJ", "ISTJ",                           // :심박:
            "INTJ", "ENTJ", "INTP", "ENTP", "ISFP", "ESFP", "ISTP", "ESFJ", "ESTJ" // 노랑
        ));
        mbtiMap.put("ISFJ", Arrays.asList(
            "ESFP", "ESTP",                           // :심박:
            "ESFJ", "ISTJ", "ESTJ",                   // 초록
            "INTJ", "ENTJ", "INTP", "ENTP", "ISFP", "ISTP" // 노랑
        ));
        mbtiMap.put("ESFJ", Arrays.asList(
            "ISFP", "ISTP",                           // :심박:
            "ISFJ", "ISTJ", "ESTJ",                   // 초록
            "INTJ", "ENTJ", "INTP", "ENTP", "ESFP", "ESTP" // 노랑
        ));
        mbtiMap.put("ISTJ", Arrays.asList(
            "ESFP", "ESTP",                           // :심박:
            "ISFJ", "ESFJ", "ESTJ",                   // 초록
            "INTJ", "ENTJ", "INTP", "ENTP", "ISFP", "ISTP" // 노랑
        ));
        mbtiMap.put("ESTJ", Arrays.asList(
            "ISFP", "ISTP",                           // :심박:
            "ISFJ", "ESFJ", "ISTJ",                   // 초록
            "INTJ", "ENTJ", "INTP", "ENTP", "ESFP", "ESTP" // 노랑
        ));
    
    }
    
    public static Person[] getStudents() {
        return student;
    }
    
    public static Map<String, List<String>> getMbti(){
       return mbtiMap;
    }
    
    
}
