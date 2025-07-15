package view;

import model.ModelDAO;
import model.domain.Person;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentService {

    public static void insertStudent(Scanner sc) {
        System.out.print("이름: ");
        String name = sc.nextLine();

        System.out.print("MBTI: ");
        String mbti = sc.nextLine();

        System.out.print("저시력 여부 (true/false): ");
        boolean isLowVision = sc.nextBoolean();
        sc.nextLine(); // 개행 제거

        // ID 없이 생성자 사용 (Person에 생성자 필요)
        Person p = new Person(name, mbti, isLowVision);
        boolean result = ModelDAO.insertStudent(p);
        System.out.println("[INSERT] 결과: " + result);
    }

    public static void updateStudent(Scanner sc) {
        System.out.print("수정할 학생 ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.println("1. 시력 정보 수정");
        System.out.println("2. MBTI 수정");
        System.out.print("선택: ");
        int option = sc.nextInt();
        sc.nextLine();

        if (option == 1) {
            System.out.print("새 시력 상태 (true/false): ");
            boolean isLowVision = sc.nextBoolean();
            boolean result = ModelDAO.updateIsLowVisionStudent(id, isLowVision);
            System.out.println("[UPDATE] 시력 정보 수정 결과: " + result);
        } else if (option == 2) {
            System.out.print("새 MBTI: ");
            String mbti = sc.nextLine();
            boolean result = ModelDAO.updateMbtiStudent(id, mbti);
            System.out.println("[UPDATE] MBTI 수정 결과: " + result);
        } else {
            System.out.println("⚠️ 잘못된 선택입니다.");
        }
    }

    public static void deleteStudent(Scanner sc) {
        System.out.print("삭제할 학생 ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        boolean result = ModelDAO.deleteStudent(id);
        System.out.println("[DELETE] 결과: " + result);
    }

    public static void printAllStudents() {
        System.out.println("[전체 학생 목록]");
        ArrayList<Person> all = ModelDAO.getStudent();
        for (Person p : all) {
            System.out.printf("%d | %s | %s | %s\n",
                    p.getId(), p.getName(), p.getMbti(),
                    p.isLowVision() ? "저시력" : "정상");
        }
    }

    public static void printLowVisionStudents() {
        System.out.println("[시력 저하 학생 목록]");
        ArrayList<Person> list = ModelDAO.getLowVision(true);
        for (Person p : list) {
            System.out.printf("%d | %s | %s\n", p.getId(), p.getName(), p.getMbti());
        }
    }
}
