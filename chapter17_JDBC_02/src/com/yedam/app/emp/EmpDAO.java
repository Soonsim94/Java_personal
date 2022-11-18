package com.yedam.app.emp;

import java.util.List;

//해당테이블에 접근할때 쓰는 메소드 시그니처 정의 
public interface EmpDAO {

	// 전체조회
	List<EmpVO> selectAll();

	// 단건조회
	EmpVO selectOne(EmpVO empVO); // 하나를 어떻게 가져올건지 결정
	
	// 등록
	void insert(EmpVO empVO);
	
	// 수정
	void update(EmpVO empVO);
	
	// 삭제
	void delete(int empNo);
	
	

}
