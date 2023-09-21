package entities;

public class Task {
    private int id;
    private int remainingTime;
    private String task;
    private int lastIndex = 0;

    public Task(int id, int remainingTime, String task) {
        this.id = id;
        this.remainingTime = remainingTime;
        this.task = task;
    }

    public int getId() {
        return id;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getLastIndex() {
        return lastIndex;
    }

    public void setLastIndex(int lastIndex) {
        this.lastIndex = lastIndex;
    }
}
