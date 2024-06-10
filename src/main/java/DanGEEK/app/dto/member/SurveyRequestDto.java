package DanGEEK.app.dto.member;

import lombok.Getter;

@Getter
public class SurveyRequestDto {
    private Cleanliness cleanliness;
    private WakeSleep wakeSleep;

    public int getWakeTime() {
        return wakeSleep.wakeTime;
    }
    public int getSleepTime() {
        return wakeSleep.sleepTime;
    }

    private static class Cleanliness {
        private boolean answer1;
        private boolean answer2;
        private boolean answer3;
        private boolean answer4;
        private boolean answer5;
        private boolean answer6;
        private boolean answer7;
        private boolean answer8;
        private boolean answer9;
        private boolean answer10;
    }
    private static class WakeSleep {
        private int wakeTime;
        private int sleepTime;
    }
    public int getCleanliness(){
        return (cleanliness.answer1 ? 1 : 0) +
                (cleanliness.answer2 ? 1 : 0) +
                (cleanliness.answer3 ? 1 : 0) +
                (cleanliness.answer4 ? 1 : 0) +
                (cleanliness.answer5 ? 1 : 0) +
                (cleanliness.answer6 ? 1 : 0) +
                (cleanliness.answer7 ? 1 : 0) +
                (cleanliness.answer8 ? 1 : 0) +
                (cleanliness.answer9 ? 1 : 0) +
                (cleanliness.answer10 ? 1 : 0);
    }

}
