package controller;

import model.Mbti;

public class MbtiCheck {
	public static boolean isMatched(String a, String b) {
        return Mbti.getOrDefault(a, List.of()).contains(b);
    }
}
