package DanGEEK.app.domain;

import lombok.Getter;

@Getter
public enum Hobby {
    GAME(0,"game"), SPORTS(1, "sports"), READ(2,"read"), ART(3,"art"), MOVIE(4,"movie"), COLLECT(5,"collect"), CRAFT(6,"craft"), OBSERVE(7,"observe"), TRAVEL(8,"travel"), MUSIC(9,"music"), COOK(10,"cook"), PHOTO(11,"photo");
    private final int index;
    private final String name;
    Hobby(int index, String name){
        this.index = index;
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
