package model.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Mbtiload {

    public static final Map<String, List<String>> mbtiMap = new HashMap<>();

    static {
        // 16개의 MBTI 테이블 이름
        String[] tables = {
            "infp","enfp","infj","enfj","intj","entj","intp","entp",
            "isfp","esfp","istp","estp","isfj","esfj","istj","estj"
        };

        try (Connection conn = DBUtil.getConnection()) {
            for (String tableName : tables) {
                List<String> matchedList = new ArrayList<>();

                String sql = "SELECT matched_mbti FROM " + tableName;
                try (Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery(sql)) {

                    while (rs.next()) {
                        matchedList.add(rs.getString("matched_mbti"));
                    }
                }

                // 테이블 이름을 대문자로 키로 사용
                mbtiMap.put(tableName.toUpperCase(), matchedList);
            }
            System.out.println("[MBTI 매칭 정보 로딩 완료]");
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }

    // ✅ 외부에서 MBTI Map을 가져올 수 있도록 public static 메서드 추가
    public static Map<String, List<String>> getMbtiMap() {
        return mbtiMap;
    }

    // (선택) MBTI 궁합 직접 확인하는 헬퍼도 추가 가능
    public static boolean isCompatible(String a, String b) {
        return mbtiMap.getOrDefault(a, Collections.emptyList()).contains(b);
    }
}
