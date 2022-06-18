package restAssuredTraining.todosPojo;

public class TodosPojo {

    private int userId;
    private int id;
    private String title;
    private Boolean completed;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "****TodosPojo****" +
                "\nuserId = " + userId +
                "\nid = " + id +
                "\ntitle='" + title +
                "completed=" + completed ;
    }
    public TodosPojo(int userId, int id, String title, Boolean completed){
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.completed = completed;


    }
}
