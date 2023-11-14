package ewhaguidebook.guidebook.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private Long id;
    private String uid;
    private String pw;
}