package view;

import controller.SeatController;
import model.domain.Person;

public class StartView {
	
    public static void main(String[] args) {
    	
        Person[][] seat = SeatController.assignSeatWithMbti();

        System.out.println("===== 좌석 배정 결과 =====");

        for (int row = 1; row < seat.length; row += 2) {
            // 현재 홀수행: 정방향 출력
            for (int col = 0; col < seat[row].length; col++) {
                printPerson(seat[row][col]);
            }

            // 이전 짝수행: 역방향 출력
            int prevRow = row - 1;
            for (int col = seat[prevRow].length - 1; col >= 0; col--) {
                printPerson(seat[prevRow][col]);
            }

            System.out.println(); // 한 줄 완성
        }
        
    }

    public static void printPerson(Person p) {
        if (p == null) {
            System.out.print("[빈자리] ");
        } else {
            System.out.print("[" + p.getName() + "] ");
        }
    }
    
}
