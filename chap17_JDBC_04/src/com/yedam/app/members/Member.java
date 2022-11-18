package com.yedam.app.members;

public class Member {
	private String memberId;
	private String memberPassword;
	// memberRole : 0 - 관리자, 1 - 일반 
	private int memberRole;
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPassword() {
		return memberPassword;
	}
	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}
	public int getMemberRole() {
		return memberRole;
	}
	public void setMemberRole(int memberRole) {
		this.memberRole = memberRole;
	}
	
	@Override
	public String toString() {
		String info = "";
		
		if(memberRole == 0) {
			info = "관리자 계정 : " + memberId;
		}else {
			info = "일반 계정 : " + memberId;
		}
		
		return info;
	}
	
}
