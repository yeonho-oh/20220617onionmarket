package org.kosta.myproject.service;

import java.util.List;

import org.kosta.myproject.vo.MemberVO;
import org.kosta.myproject.vo.PowerVO;

public interface MemberService {
	
	MemberVO findMemberById(String memberId);

	List<String> getAddressList();

	List<MemberVO> findMemberListByAddress(String memberAddress);

	int getMemberCount();

	void updateMember(MemberVO vo);

	void registerMember(MemberVO vo);

	String idcheck(String memberId);
	
	List<PowerVO> findAuthorityByUsername(String username);

	String findIdByTel(String memberTel);

	int findPasswordByIdTel(MemberVO vo);

	void updatePassword(MemberVO memberVO);

	void registerMemberTemp(MemberVO memberVO);
}
