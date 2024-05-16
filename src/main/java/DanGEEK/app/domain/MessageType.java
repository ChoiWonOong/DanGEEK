package DanGEEK.app.domain;

public enum MessageType {

    ENTER("님이 들어오셨습니다."), TALK(""), EXIT("님이 퇴장하셨습니다.");
    public final String message;
    MessageType(String message){
        this.message = message;
    }
}