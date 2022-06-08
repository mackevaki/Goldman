package score.impls;

import score.interfaces.ScoreSaver;
import score.objects.UserScore;

import java.util.ArrayList;

public class FSScoreSaver implements ScoreSaver {

    @Override
    public boolean saveScore(UserScore userScore) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<UserScore> getScoreList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
