package org.kosta.myproject.vo;

import java.io.Serializable;

import lombok.Data;
@Data
public class EmoticonVO implements Serializable{
	private static final long serialVersionUID = 6440047762418162093L;
	private int emoticonNo;
	private String emoticonAddress;
	private String emoticonName;
}
