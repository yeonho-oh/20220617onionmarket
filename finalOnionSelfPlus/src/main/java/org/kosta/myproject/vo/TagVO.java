package org.kosta.myproject.vo;

import java.io.Serializable;

import lombok.Data;
@Data
public class TagVO implements Serializable{
	private static final long serialVersionUID = 6440047762418162093L;
	private int tadId;
	private String tag;
	private int tagHits;
}
