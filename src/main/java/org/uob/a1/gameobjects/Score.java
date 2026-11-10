package org.uob.a1.gameobjects;

public class Score {

    private int currentScore;
    final int startingScore = 100;
    final int moveScore = -1;
    final int unlockScore = 10;

    // constructor?
    public Score() {
        currentScore = startingScore;
    }

    public void updateMove() {
        currentScore += moveScore;
    }

    public void updateUnlock() {
        currentScore += unlockScore;
    }

    public void failScore() {
        currentScore /= 2;
    }

    // getter
    public int getCurrentScore() {
        return currentScore;
    }
    
}
