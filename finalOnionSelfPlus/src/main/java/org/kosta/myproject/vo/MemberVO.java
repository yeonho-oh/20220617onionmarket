package org.kosta.myproject.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberVO implements Serializable{
	private static final long serialVersionUID = 6440047762418162093L;
	private String memberId;
	private String memberName;
	private String memberPassword;
	private String memberAddress;
	private String memberTel;
	private int onionPoint;
	private String memberNickname;
	private String memberPicture;
	private TempVO tempVO;
	private int enabled;
}
