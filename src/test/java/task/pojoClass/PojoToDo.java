package task.pojoClass;

public class PojoToDo {

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
        return "****PojoToDo****" +
                "userId : " + userId +
                "id : " + id +
                "title : " + title +
                "completed : " + completed ;
    }
}
