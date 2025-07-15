package model.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class Person {
	private int id;
    private String name;
    private String mbti;
    private boolean isLowVision;
    
}
