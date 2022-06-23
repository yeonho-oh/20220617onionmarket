package org.kosta.myproject.controller;

import org.kosta.myproject.service.MemberService;
import org.kosta.myproject.vo.MemberVO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	//비인증 상태에서도 접근 가능하도록 /guest/ 이하로 url 등록 
	//org.kosta.myproject.config.security.WebSecurityConfig 설정되어 있음 
	@RequestMapping("guest/findMemberById")
	public String findMemberById(String memberId,Model model) {		
		MemberVO vo = memberService.findMemberById(memberId);
		if (vo == null)
			return "member/findMemberById_fail";
		else {
			model.addAttribute("memberVO",vo);
			return "member/findMemberById_success" ;
		}
	}
	@RequestMapping("guest/findIdForm")
	//@AuthenticationPrincipal : Spring Security를 통해 로그인한 인증정보를 받아오는 어노테이션 
	public String findIdForm(@AuthenticationPrincipal MemberVO memberVO,Model model) {
		model.addAttribute("member", memberVO);
		return "member/findIdForm";
	}
	@RequestMapping("guest/findIdByTel")
	public String findIdByTel(String memberTel,Model model) {		
		String id = memberService.findIdByTel(memberTel);
		if (id == null)
			return "member/findIdByTel_fail";
		else {
			model.addAttribute("memberId",id);
			return "member/findIdByTel_success" ;
		}
	}
	@RequestMapping("guest/findPasswordForm")
	//@AuthenticationPrincipal : Spring Security를 통해 로그인한 인증정보를 받아오는 어노테이션 
	public String findPasswordForm(@AuthenticationPrincipal MemberVO memberVO,Model model) {
		model.addAttribute("member", memberVO);
		return "member/findPasswordForm";
	}
	@RequestMapping("guest/findPasswordByIdTel")
	public String findPasswordByIdTel(String memberTel,String memberId,Model model) {
		MemberVO vo = new MemberVO();
		vo.setMemberId(memberId);
		vo.setMemberTel(memberTel);
		int result = memberService.findPasswordByIdTel(vo);
		if (result == 0)
			return "member/findPasswordByIdTel_fail";
		else {
			model.addAttribute("memberTel",memberTel);
			model.addAttribute("memberId",memberId);
			return "member/findPasswordByIdTel_success" ;
		}
	}
	@PostMapping("guest/updatePassword")
	public String updatePassword(MemberVO memberVO) {
		memberService.updatePassword(memberVO);//변경시 service에서 비밀번호를 암호화 한다 
		return "redirect:/guest/updatePasswordView?memberId=" + memberVO.getMemberId();
	}

	@RequestMapping("guest/updatePasswordView")
	public ModelAndView updatePasswordView(String memberId) {
		MemberVO memberVO = memberService.findMemberById(memberId);
		return new ModelAndView("member/updatePasswordView-result", "memberVO", memberVO);
	}
	//WebSecurityConfig에 등록되어 있음 ( failureUrl("/login_fail") )
	@RequestMapping("login_fail")
	public String loginFail() {
		return "member/login_fail";
	}
    //spring security에서 로그인 , 로그아웃 처리를 하므로 login , logout 관련 메서드는 필요없다  
	//guest/ 가 아닌 모든 컨트롤러는 인증이 되어야 한다. 비인증 상태에서 접근할 경우 로그인 폼이 있는 home으로 redirect 됨 
	@RequestMapping("enterCafe")
	public String enterCafe() {
		return "member/ajax-cafe";
	}
	
	@RequestMapping("guest/idcheckAjax")
	@ResponseBody
	public String idcheckAjax(String memberId) {
		return memberService.idcheck(memberId);
	}
	@GetMapping("getMemberTotalCount")	
	@ResponseBody
	public int getMemberTotalCount() {
		return memberService.getMemberCount();
	}
	@PostMapping("postAjaxTest")
	@ResponseBody
	public String postAjaxTest(String message) {
		//log.debug("post ajax 는 csrf token 이 필요합니다 {}",message);
		return message+" ajax 요청에 대한 응답입니다";
	}	
	
	@RequestMapping("updateForm")
	//@AuthenticationPrincipal : Spring Security를 통해 로그인한 인증정보를 받아오는 어노테이션 
	public String updateForm(@AuthenticationPrincipal MemberVO memberVO,Model model) {
		model.addAttribute("member", memberVO);
		return "member/updateForm";
	}

	@PostMapping("updateMemberAction")
	//첫번째 매개변수 Authentication : Spring Security 인증 정보 , 두번째 매개변수 memberVO : 수정폼에서 전달받는 데이터 
	public String updateMemberAction(Authentication authentication, MemberVO memberVO) {
		MemberVO vo = (MemberVO)authentication.getPrincipal();			
		memberService.updateMember(memberVO);//service에서 변경될 비밀번호를 암호화한다 
		// 수정한 회원정보로 Spring Security 회원정보를 업데이트한다
		vo.setMemberPassword(memberVO.getMemberPassword());
		vo.setMemberName(memberVO.getMemberName());
		vo.setMemberAddress(memberVO.getMemberAddress());
		vo.setMemberNickname(memberVO.getMemberNickname());
		vo.setMemberTel(memberVO.getMemberTel());
		return "redirect:updateResult";
	}
	@GetMapping("updateResult")
	public String updateResult(){
		return "member/update_result";
	}
	@RequestMapping("guest/registerForm")
	public String registerForm() {
		return "member/registerForm";
	}
	@PostMapping("guest/registerMember")
	public String register(MemberVO memberVO) {
		memberService.registerMember(memberVO);//등록시 service에서 비밀번호를 암호화 한다 
		memberService.registerMemberTemp(memberVO);
		return "redirect:/guest/registerResultView?memberId=" + memberVO.getMemberId();
	}

	@RequestMapping("guest/registerResultView")
	public ModelAndView registerResultView(String memberId) {
		MemberVO memberVO = memberService.findMemberById(memberId);
		return new ModelAndView("member/register_result", "memberVO", memberVO);
	}


	
}
