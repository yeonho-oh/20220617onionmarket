package org.kosta.myproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.kosta.myproject.vo.MemberVO;
import org.kosta.myproject.vo.PowerVO;
@Mapper
public interface MemberMapper {

	MemberVO findMemberById(String memberId);

	List<String> getAddressList();

	List<MemberVO> findMemberListByAddress(String memberAddress);	

	int getMemberCount();

	void updateMember(MemberVO vo);

	void registerMember(MemberVO vo);

	int idcheck(String memberId);

	List<PowerVO> findAuthorityByUsername(String username);

	void registerRole(PowerVO authority);

	String findIdByTel(String memberTel);

	int findPasswordByIdTel(MemberVO vo);

	void updatePassword(MemberVO memberVO);

	void registerMemberTemp(MemberVO memberVO);

}