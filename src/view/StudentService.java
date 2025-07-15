package view;

import model.ModelDAO;
import model.domain.Person;
import model.util.Mbtiload;

import java.util.InputMismatchException;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentService {

    public static void insertStudent(Scanner sc) {
    	try {
    	    System.out.print("이름: ");
    	    String name = sc.nextLine().trim();
    	    // 이름 검증
    	    if (name.isEmpty()) {
    	        System.out.println("[ERROR] 이름은 공백일 수 없습니다. 다시 입력해주세요.");
    	        return;
    	    }

    	    System.out.print("MBTI: ");
    	    String mbti = sc.nextLine().trim();
    	    // MBTI 공백 검증
    	    if (mbti.isEmpty()) {
    	        System.out.println("[ERROR] MBTI는 공백일 수 없습니다. 다시 입력해주세요.");
    	        return;
    	    }

    	    // MBTI 유효성 검증
    	    if (!Mbtiload.mbtiMap.containsKey(mbti.toUpperCase())) {
    	        System.out.println("[ERROR] 유효하지 않은 MBTI입니다. 다시 입력해주세요.");
    	        return;
    	    }

    	    System.out.print("저시력 여부 (true/false): ");
    	    boolean isLowVision;

    	    try {
    	        // boolean 입력 검증
    	        if (!sc.hasNextBoolean()) {  // true/false 외의 값이 들어오면
    	            System.out.println("[ERROR] true 또는 false로 입력해 주세요.");
    	            sc.nextLine(); // 잘못된 입력 제거
    	            return;
    	        }
    	        isLowVision = sc.nextBoolean();
    	    } catch (InputMismatchException ime) {
    	        System.out.println("[ERROR] true 또는 false로 입력해 주세요.");
    	        sc.nextLine(); // 잘못된 입력 제거
    	        return;
    	    }
    	    sc.nextLine(); // 개행 제거

    	    // Person 객체 생성
    	    Person p = new Person(name, mbti, isLowVision);

    	    try {
    	        boolean result = ModelDAO.insertStudent(p);
    	        System.out.println("[INSERT] 결과: " + result);
    	    } catch (Exception e) {
    	        System.out.println("[ERROR] 데이터 삽입 중 문제가 발생했습니다.");
    	        e.printStackTrace();
    	    }

    	} catch (Exception e) {
    	    System.out.println("[ERROR] 알 수 없는 오류가 발생했습니다.");
    	    e.printStackTrace();
    	}

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
        String id = sc.nextLine().trim();
        try{
            if(id.isEmpty()){
                System.out.println("이름은 공백일 수 없습니다. 다시 입력해주세요.");
                return;
            }
            int newId = Integer.parseInt(id);
            boolean result = ModelDAO.deleteStudent(newId);
            if(result == true){
                System.out.println(id + "번 학생이 성공적으로 삭제되었습니다!");
            }else{
                System.out.println(id + "번 학생은 존재하지 않습니다!");
            }
        }catch(NumberFormatException e){
            e.printStackTrace();
            System.out.println("숫자를 입력해주세요!");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("알 수 없는 에러가 발생했습니다!");
        }
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
