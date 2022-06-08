package score.abstracts;

import score.interfaces.ScoreSaver;
import score.objects.UserScore;

public abstract class AbstractScoreSaver implements ScoreSaver {

    private UserScore userScore;

    public UserScore getUserScore() {
        return userScore;
    }

    public void setUserScore(UserScore userScore) {
        this.userScore = userScore;
    }

    public void saveScore() {
        saveScore(userScore);
    }
}
