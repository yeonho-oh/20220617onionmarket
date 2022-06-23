package org.kosta.myproject.service;

import java.util.List;

import org.kosta.myproject.mapper.MemberMapper;
import org.kosta.myproject.vo.MemberVO;
import org.kosta.myproject.vo.PowerVO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	private final MemberMapper memberMapper;
	/*
	 * 비밀번호 암호화처리를 위한 객체를 주입받는다 
	 * org.kosta.config.security.WebSecurityConfig 에 @Bean 설정 되어 있음 
	 */
	private final BCryptPasswordEncoder passwordEncoder;

	
	// 회원가입시 반드시 권한까지 부여되도록 트랜잭션 처리한다
	@Transactional
	@Override
	public void registerMember(MemberVO memberVO) {
		// 비밀번호를 bcrypt 알고리즘으로 암호화하여 DB에 저장한다
		String encodedPwd = passwordEncoder.encode(memberVO.getMemberPassword());
		memberVO.setMemberPassword(encodedPwd);
		memberMapper.registerMember(memberVO);
		// 회원 가입시 반드시 권한이 등록되도록 트랜잭션처리를 한다
		PowerVO powerVO = new PowerVO(memberVO.getMemberId(), "ROLE_MEMBER");
		memberMapper.registerRole(powerVO);
	}

	@Override
	public void updateMember(MemberVO memberVO) {
		System.out.println("확인용입니다 : "+memberVO);
		// 변경할 비밀번호를 암호화한다
		String encodePassword = passwordEncoder.encode(memberVO.getMemberPassword());
		memberVO.setMemberPassword(encodePassword);
		memberMapper.updateMember(memberVO);
	}

	@Override
	public MemberVO findMemberById(String memberId) {
		return memberMapper.findMemberById(memberId);
	}

	@Override
	public List<String> getAddressList() {
		return memberMapper.getAddressList();
	}

	@Override
	public List<MemberVO> findMemberListByAddress(String memberAddress) {
		return memberMapper.findMemberListByAddress(memberAddress);
	}

	@Override
	public int getMemberCount() {
		return memberMapper.getMemberCount();
	}

	@Override
	public String idcheck(String memberId) {
		int count = memberMapper.idcheck(memberId);
		return (count == 0) ? "ok" : "fail";
	}

	@Override
	public List<PowerVO> findAuthorityByUsername(String username) {
		return memberMapper.findAuthorityByUsername(username);
	}

	@Override
	public String findIdByTel(String memberTel) {
		return memberMapper.findIdByTel(memberTel);
	}

	@Override
	public int findPasswordByIdTel(MemberVO vo) {
		return memberMapper.findPasswordByIdTel(vo);
	}

	@Override
	@Transactional
	public void updatePassword(MemberVO memberVO) {
		// 비밀번호를 bcrypt 알고리즘으로 암호화하여 DB에 저장한다
		String encodedPwd = passwordEncoder.encode(memberVO.getMemberPassword());
		memberVO.setMemberPassword(encodedPwd);
		memberMapper.updatePassword(memberVO);
	}

	@Override
	public void registerMemberTemp(MemberVO memberVO) {
		memberMapper.registerMemberTemp(memberVO);
		
	}
}
