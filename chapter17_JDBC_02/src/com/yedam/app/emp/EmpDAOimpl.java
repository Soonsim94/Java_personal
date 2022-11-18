package com.yedam.app.emp;

import java.util.ArrayList;
import java.util.List;

import com.yedam.app.commo.DAO;

public class EmpDAOimpl extends DAO implements EmpDAO {
	// 싱글톤
	private static EmpDAO instance = null;

	public static EmpDAO getInstance() {
		if (instance == null)
			instance = new EmpDAOimpl();
		return instance;
	}

	@Override
	public List<EmpVO> selectAll() {
		List<EmpVO> list = new ArrayList<>(); // try구문전 변수지정
		try { // 사용하고자하는 sql 넣기.
			connect();
			// 반드시 필요함
			stmt = conn.createStatement(); // 객체를 만들고
			String sql = "SELECT * FROM employees"; // sql문
			rs = stmt.executeQuery(sql); // 실행할결과 받아오고
			int count = 0; // 카운트를 넣어 20개넘으면 브레이크 걸음
			while (rs.next()) {
				EmpVO empVO = new EmpVO();
				empVO.setEmpNo(rs.getInt("emp_no"));
				empVO.setBirthDate(rs.getString("birth_date"));
				empVO.setFirstName(rs.getString("first_name"));
				empVO.setLastName(rs.getString("last_name"));
				empVO.setGender(rs.getString("gender"));
				empVO.setHireDate(rs.getString("hire_date"));
				list.add(empVO);
				
				if(++count >=20) break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list; // 리턴
	}

	@Override
	public EmpVO selectOne(EmpVO empVO) {
		EmpVO findVO = null;
		try {
			connect();
			stmt = conn.createStatement();

			String sql = "SELECT * FROM employees WHERE emp_no = " + empVO.getEmpNo();
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				findVO = new EmpVO();
				findVO.setEmpNo(rs.getInt("emp_no"));
				findVO.setBirthDate(rs.getString("birth_date"));
				findVO.setFirstName(rs.getString("first_name"));
				findVO.setLastName(rs.getString("last_name"));
				findVO.setGender(rs.getString("gender"));
				findVO.setHireDate(rs.getString("hire_date"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return findVO;
	}

	@Override
	public void insert(EmpVO empVO) {
		try {
			connect();
			String sql = "INSERT INTO employees VALUES (?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, empVO.getEmpNo());
			pstmt.setString(2, empVO.getBirthDate());
			pstmt.setString(3, empVO.getFirstName());
			pstmt.setString(4, empVO.getLastName());
			pstmt.setString(5, empVO.getGender());
			pstmt.setString(6, empVO.getHireDate());

			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("정상적으로 등록되었습니다.");
			} else {
				System.out.println("정상적으로 등록되지 않았습니다.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

	}

	@Override
	public void update(EmpVO empVO) {
		try {
			connect();
			String sql = "UPDATE employees SET first_name = ? WHERE emp_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, empVO.getFirstName());
			pstmt.setInt(2, empVO.getEmpNo());

			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("정상적으로 수정되었습니다.");
			} else {
				System.out.println("정상적으로 수정되지 않았습니다.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	@Override
	public void delete(int empNo) {
		try {
			connect();
			stmt = conn.createStatement();
			String sql = "DELETE FROM employees WHERE emp_no = " + empNo;
			int result = stmt.executeUpdate(sql);
			if (result > 0) {
				System.out.println("정상적으로 삭제되었습니다.");
			} else {
				System.out.println("정상적으로 삭제되지 않았습니다.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();

		}

	}
}
