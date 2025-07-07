package model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Person {
	private String name;
	private String mbti;
	private boolean hasLowVision;
	private boolean OnSide;
}
