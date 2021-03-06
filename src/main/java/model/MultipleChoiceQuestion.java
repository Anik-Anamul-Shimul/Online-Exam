package model;

import java.util.List;

public class MultipleChoiceQuestion {
    private int id;
    private String question;
    private List<String> choices;
    private int rightIndex;
    private double mark;

    public MultipleChoiceQuestion() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MultipleChoiceQuestion(int id, String question, List<String> choices,
                                  int rightIndex, double mark) {
        this.id = id;
        this.question = question;
        this.choices = choices;
        this.rightIndex = rightIndex;
        this.mark = mark;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }

    public int getRightIndex() {
        return rightIndex;
    }

    public void setRightIndex(int rightIndex) {
        this.rightIndex = rightIndex;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "MultipleChoiceQuestion{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", choices=" + choices +
                ", rightIndex=" + rightIndex +
                '}';
    }
}
