\# 🪑 좌석 배치 프로젝트 - 후처리 작업 안내



본 문서는 좌석 배치 후 시각화를 위한 \*\*후처리 작업\*\*에 대해 설명합니다. 후처리 작업에는 \*\*배열 분할\*\*과 \*\*배열 반전\*\*이 포함됩니다.



---



\## 📌 전체 좌석 구조



\- 전체 좌석: 28명

\- 형태: 7행 × 4열 (`Person\[]\[] seat = new Person\[7]\[4];`)

\- 구성:

&nbsp; - 시력 안 좋은 사람: 8명

&nbsp; - 시력 좋은 사람: 20명

\- 좌석은 `\[i]\[j]` 형태로 접근하며, `i`는 행(앞~뒤), `j`는 열(왼쪽~오른쪽)



---



\## 🧩 1. 배열 분할 (좌우 나누기)



\### 🎯 목적

\- 화면 출력 시 좌우 팀(그룹)으로 나누어 보기 쉽게 정렬



\### 🧪 예시 코드

```java

for (int i = 0; i < 7; i++) {

&nbsp;   // 왼쪽 그룹 (0~1열)

&nbsp;   for (int j = 0; j < 2; j++) {

&nbsp;       System.out.print(seat\[i]\[j].getName() + "\\t");

&nbsp;   }

&nbsp;   System.out.print("|\\t");

&nbsp;   // 오른쪽 그룹 (2~3열)

&nbsp;   for (int j = 2; j < 4; j++) {

&nbsp;       System.out.print(seat\[i]\[j].getName() + "\\t");

&nbsp;   }

&nbsp;   System.out.println();

}



