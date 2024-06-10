package DanGEEK.app.domain;

import lombok.Getter;

@Getter
public enum Hobby {
    GAME("game"), SPORTS("sports"), READ("read"), ART("art"), MOVIE("movie"), COLLECT("collect"), CRAFT("craft"), OBSERVE("observe"), TRAVEL("travel"), MUSIC("music"), COOK("cook"), PHOTO("photo");
    private final String name;
    Hobby(String name){
        this.name = name;
    }
    public static Hobby findHobby(String name){
        for(Hobby hobby : Hobby.values()){
            if(hobby.name.equals(name)){
                return hobby;
            }
        }
        return null;
    }
}
