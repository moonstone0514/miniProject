package view;

import controller.SeatController;
import model.ModelDAO;
import model.domain.Person;

import java.util.ArrayList;

public class StartView {

    public static void main(String[] args) {
        // 1. INSERT 테스트
        Person newStudent = new Person(999, "테스트학생", "INFJ", false);
        boolean insertResult = ModelDAO.insertStudent(newStudent);
        System.out.println("[INSERT] 결과: " + insertResult);

        // 2. SELECT 전체 조회
        System.out.println("\n[전체 학생 목록]");
        ArrayList<Person> allStudents = ModelDAO.getStudent();
        for (Person p : allStudents) {
            System.out.println(p.getId() + " | " + p.getName() + " | " + p.getMbti() + " | " + (p.isLowVision() ? "저시력" : "정상"));
        }

        // 3. UPDATE 테스트 - 시력 정보 수정
        boolean updateResult = ModelDAO.updateIsLowVisionStudent(999, true);
        System.out.println("\n[UPDATE] 시력 정보 수정 결과: " + updateResult);

        // 4. UPDATE 테스트 - MBTI 정보 수정
        boolean mbtiUpdate = ModelDAO.updateMbtiStudent(999, "ENFP");
        System.out.println("[UPDATE] MBTI 수정 결과: " + mbtiUpdate);

        // 5. SELECT - 시력이 안 좋은 학생만 조회
        System.out.println("\n[시력 저하 학생]");
        ArrayList<Person> lowVisionList = ModelDAO.getLowVision(true);
        for (Person p : lowVisionList) {
            System.out.println(p.getName() + " (" + p.getMbti() + ")");
        }

        // 6. DELETE 테스트
        boolean deleteResult = ModelDAO.deleteStudent(999);
        System.out.println("\n[DELETE] 결과: " + deleteResult);

        // 7. 좌석 배정 결과 출력
        Person[][] seat = SeatController.assignSeatWithMbti();
        System.out.println("\n===== 좌석 배정 결과 =====");
        for (int row = 1; row < seat.length; row += 2) {
            for (int col = 0; col < seat[row].length; col++) {
                printPerson(seat[row][col]);
            }
            int prevRow = row - 1;
            for (int col = seat[prevRow].length - 1; col >= 0; col--) {
                printPerson(seat[prevRow][col]);
            }
            System.out.println();
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
