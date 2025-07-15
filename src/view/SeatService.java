package view;

import controller.SeatController;
import model.domain.Person;

public class SeatService {

    public static void printSeatArrangement() {
    	
        Person[][] seat = SeatController.assignSeatWithMbti();
        
        System.out.println("\n===== 좌석 배정 결과 =====");
        int prevRow = 0;
        
        for (int row = 1; row < seat.length; row += 2) {
            for (int col = 0; col < seat[row].length; col++) {
                printPerson(seat[row][col]);
            }
            
            prevRow = row - 1;
            
            for (int col = seat[prevRow].length - 1; col >= 0; col--) {
                printPerson(seat[prevRow][col]);
            }
            
            System.out.println();
        }
        
    }

    private static void printPerson(Person p) {
        if (p == null) {
            System.out.print("[빈자리] ");
        } else {
            System.out.print("[" + p.getName() + "] ");
        }
    }
}
