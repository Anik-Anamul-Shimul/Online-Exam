package model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String handle;
    private String password;
    private List<Result> history;
    private List<ExamInfo> createdExams;

    public User() {
    }

    public User(String name, String handle, String password) {
        this.name = name;
        this.handle = handle;
        this.password = password;
        history = new ArrayList<>();
        createdExams = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Result> getHistory() {
        return history;
    }

    public void addResult(Result latestResult) {
        history.add(latestResult);
    }

    public void addExam(ExamInfo examInfo) {
        createdExams.add(examInfo);
    }

    public void setCreatedExams(List<ExamInfo> createdExams) {
        this.createdExams = createdExams;
    }

    public List<ExamInfo> getCreatedExams() {
        return createdExams;
    }

    public void setHistory(List<Result> history) {
        this.history = history;
    }
}
