package controller;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import model.Database;
import model.Model;
import model.domain.*;

public class SeatController {
	
	public static Person[][] assignSeatWithMbti() {
		Person[][] seat = new Person[8][4];

		List[] visionList = Model.getVision();
		List<Person> lowVisionList = visionList[0];
		List<Person> highVisionList = visionList[1];

		Collections.shuffle(lowVisionList, new Random());
		Collections.shuffle(highVisionList, new Random());

		for (int i = 0; i < seat.length; i++) {
			for (int j = 0; j < seat[i].length; j++) {
				if (lowVisionList.isEmpty() && highVisionList.isEmpty()) {
					return seat;
				}

				if (j == 0) { //시력 좋은 사람 배치
					seat[i][j] = highVisionList.remove(0);
					continue;
				}
				
				//lowVisionList가 있는 경우 시력 안 좋은 사람 배치, 없는 경우 시력 좋은 사람 배치
				if (lowVisionList.isEmpty()) { 
					seat[i][j] = assignHighVsion(seat[i][j-1], highVisionList); // 시력 좋은 사람 배치
					continue;
				} else { 
					seat[i][j] = assignLowVsion(seat[i][j-1], lowVisionList); // 시력 안 좋은 사람 배치
					continue;
				}
			}
		}
		return seat;
	}

	public static Person assignHighVsion(Person p, List<Person> highVisionList) {
		int size = highVisionList.size();
		for (int i = 0; i < size; i++) {
			if (checkMbti(p.getMbti(), highVisionList.get(i).getMbti())) {
				return highVisionList.remove(i);
			}
		}
		return highVisionList.remove(0);
	}

	public static Person assignLowVsion(Person p, List<Person> lowVisionList) {
		for (int i = 0; i < lowVisionList.size(); i++) {
			if (checkMbti(p.getMbti(), lowVisionList.get(i).getMbti())) {
				return lowVisionList.remove(i);
			}
		}
		return lowVisionList.remove(0);
	}

	public static boolean checkMbti(String a, String b) {
		return Database.getmbti().getOrDefault(a, Collections.emptyList()).contains(b);
	}
	// 배열: 8행 4열
	// 시력 안 좋은 사람 먼저 배정
	// 시력 안 좋은 사람 없으면 좋은 사람 배정
	// 단 맨 끝 자리는 무조건 시력 좋은 사람만 배정 + 맨 끝에 앉았던 사람은 제외
}
