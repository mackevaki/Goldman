package score.interfaces;

import score.objects.UserScore;

import java.util.ArrayList;

public interface ScoreManager {

    boolean saveScore(UserScore userScore);

    ArrayList<UserScore> getScoreList();
}
