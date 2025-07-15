package controller;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.InputMismatchException;
import model.ModelDAO;
import model.domain.Person;
import model.util.Mbtiload;

public class SeatController {
	private static final ModelDAO model = ModelDAO.getModel();
	
	
    public static Person[][] assignSeatWithMbti() {
        Person[][] seat = new Person[8][4];
        
        // ✅ 시력 기준으로 리스트 분리
        List<Person> lowVisionList = model.getLowVision(true);
        List<Person> highVisionList = model.getLowVision(false);

        Collections.shuffle(lowVisionList, new Random());
        Collections.shuffle(highVisionList, new Random());

        for (int i = 0; i < seat.length; i++) {
        	
        	
            for (int j = 0; j < seat[i].length; j++) {
            	
            	

                if (lowVisionList.isEmpty() && highVisionList.isEmpty()) {
                	
                    return seat; // 모든 학생 배정 완료
                    
                }
                

                if (j == 0 && !highVisionList.isEmpty()) {
                	
                    // ✅ 맨 앞자리는 시력 좋은 사람만 배치
                	
                    seat[i][j] = highVisionList.remove(0);
                    
                    continue;
                    
                }

                // lowVisionList가 있는 경우 시력 안 좋은 사람 배치
                if (!lowVisionList.isEmpty()) {
                	
                    seat[i][j] = assignLowVision(seat[i][j - 1], lowVisionList);
                    
                } else if (!highVisionList.isEmpty()) {
                	
                    // lowVisionList가 비어있는 경우 시력 좋은 사람 배치
                    seat[i][j] = assignHighVision(seat[i][j - 1], highVisionList);
                    
                }
                
            }
            
        }
        
        return seat;
    }

    public static Person assignHighVision(Person prev, List<Person> highVisionList) {
        for (int i = 0; i < highVisionList.size(); i++) {
            if (checkMbti(prev.getMbti(), highVisionList.get(i).getMbti())) {
            	
                return highVisionList.remove(i);
                
            }
            
        }
        
        return highVisionList.remove(0); // 매칭 없으면 그냥 아무나 배정
    }

    public static Person assignLowVision(Person prev, List<Person> lowVisionList) {
        for (int i = 0; i < lowVisionList.size(); i++) {
        	
            if (checkMbti(prev.getMbti(), lowVisionList.get(i).getMbti())) {
            	
                return lowVisionList.remove(i);
                
            }
            
        }
        
        return lowVisionList.remove(0); // 매칭 없으면 그냥 아무나 배정
        
    }

    public static boolean checkMbti(String a, String b) {
    	
        return Mbtiload.getMbtiMap().getOrDefault(a, Collections.emptyList()).contains(b);
        
    }

    // 설명:
    // - 8행 4열 자리 구성
    // - 맨 앞자리는 시력 좋은 사람만 배치
    // - 나머지 자리는 시력 안 좋은 사람 우선 배치
    // - 없으면 시력 좋은 사람 배치
    // - MBTI 궁합 고려한 짝 배치
}
