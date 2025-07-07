package model.domain;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Person {
	
	private String name;
	private String mbti;
	private boolean hasLowVision;
	private boolean onSide;
	
}
