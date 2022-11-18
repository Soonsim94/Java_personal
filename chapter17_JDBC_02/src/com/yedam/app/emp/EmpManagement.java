package com.yedam.app.emp;

import java.util.List;
import java.util.Scanner;

public class EmpManagement {
	Scanner sc = new Scanner(System.in);
	EmpDAO empDAO = EmpDAOimpl.getInstance();

	public EmpManagement() {
		while (true) {
			// 메뉴출력
			menuPrint();
			// 메뉴선택
			int menuNo = menuSelect();
			// 각 메뉴의 기능을 실행
			if (menuNo == 1) {
				// 전체조회
				selectAll();
			} else if (menuNo == 2) {
				// 단건조회
				selectOne();
			} else if (menuNo == 3) {
				// 사원등록
				insertEmp();
			} else if (menuNo == 4) {
				// 사원수정
				updateEmp();
			} else if (menuNo == 5) {
				// 사원삭제
				deleteEmp();
			} else if (menuNo == 9) {
				// 종료
				end();
			} else {
				// 기타사항
				printErrorMessage();
			}

		}
	}
	// 가장 최소의 단위로 단위를 나눔.

	private void printErrorMessage() {
		System.out.println("===================");
		System.out.println("메뉴를 잘못 입력하였습니다.");
		System.out.println("메뉴를 다시 한번 확인해주세요.");
		System.out.println("===================");
	}

	private void end() {
		System.out.println("===================");
		System.out.println("프로그램을 종료합니다.");
		System.out.println("===================");
	}

	private void menuPrint() {
		System.out.println("======================================");
		System.out.println("1.전체사원 2.사원조회 3.사원등록 4.사원수정 9.종료");
		System.out.println("======================================");
	}

	private int menuSelect() {
		int menuNo = 0;
		try {
			menuNo = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("메뉴는 숫자로 구성되어 있습니다.");
		}
		return menuNo;
	}

	private void selectAll() {
		List<EmpVO> list = empDAO.selectAll();
		if (list.isEmpty()) {
			System.out.println("정보가 존재하지 않습니다.");
			return;
		}
		for (EmpVO empVO : list) {
			System.out.printf("%d: %s, %s \n", empVO.getEmpNo(), empVO.getFirstName(), empVO.getLastName());
		}
	}

	private void selectOne() {
		EmpVO findEmp = inputEmpInfo();
		EmpVO empVO = empDAO.selectOne(findEmp);
		if (empVO == null) {
			System.out.printf("%d 사원은 존재하지 않습니다. \n", findEmp.getEmpNo());
		} else {
			System.out.println("검색결과 >");
			System.out.println(empVO);
		}
	}

	private void insertEmp() {
		EmpVO empVO = inputEmpAll();
		empDAO.insert(empVO);
	}

	private void updateEmp() {
		EmpVO empVO = inputEmpInfo(); // 값 입력받고 DAO에 전달하고
		empDAO.update(empVO);
	}

	private void deleteEmp() {
		int empNo = inputEmpNo();
		empDAO.delete(empNo);
	}

	private int inputEmpNo() {
		int empNo = 0;
		try {
			empNo = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("사원번호는 숫자로 구성되어 있습니다.");
		}
		return empNo;
	}

	private EmpVO inputEmpAll() {
		EmpVO empVO = new EmpVO();
		System.out.println("사원번호 >");
		empVO.setEmpNo(Integer.parseInt(sc.nextLine()));
		System.out.println("생년월일(yyyy-MM-dd) >");
		empVO.setBirthDate(sc.nextLine());
		System.out.println("이름 >");
		empVO.setFirstName(sc.nextLine());
		System.out.println("성 >");
		empVO.setLastName(sc.nextLine());
		System.out.println(" 성별(M,F) > ");
		empVO.setGender(sc.nextLine());
		System.out.println("입사일(yyyy-MM-dd) > ");
		empVO.setHireDate(sc.nextLine());
		return empVO;
	}

	private EmpVO inputEmpInfo() {
		EmpVO empVO = new EmpVO();
		System.out.println("사원번호 >");
		empVO.setEmpNo(Integer.parseInt(sc.nextLine()));
		System.out.println("이름 >");
		empVO.setFirstName(sc.nextLine());

		return empVO;
	}
}
