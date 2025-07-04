package model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class Person {
    private String name;
    private String mbti;
    private boolean isLowVision;
    private boolean isSide;
}
