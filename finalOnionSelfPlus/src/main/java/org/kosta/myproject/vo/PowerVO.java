package org.kosta.myproject.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PowerVO implements Serializable{
	private static final long serialVersionUID = 6440047762418162093L;
	private String username;
	private String authority;
}
