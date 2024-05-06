package DanGEEK.app.domain;

import lombok.Getter;

@Getter
public enum PostType {
    INVITE("invite"),
    GROUP_BUY("group_buy"),
    COMPLAIN("complain");
    private final String type;
    PostType(String type){
        this.type = type;
    }
    public String getString(){
        return this.type;
    }
}
