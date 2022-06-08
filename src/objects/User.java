package objects;

public class User {

    private int id;
    private String userName;
    //private int score;
   // private int playCount;

    public User() {
    }

    public User(String name) {
        this.userName = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

//    public int getScore() {
//        return score;
//    }
//
//    public void setScore(int score) {
//        this.score = score;
//    }
//
//    public int getPlayCount() {
//        return playCount;
//    }
//
//    public void setPlayCount(int playCount) {
//        this.playCount = playCount;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
