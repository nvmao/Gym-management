package com.mao;

public class Data {
    private User user;
    private ExercisesListModel exercisesListModel;

    private static Data instance;

    public static Data getInstance(){
        if(instance == null ){
            instance = new Data();
        }
        return instance;
    }

    public User getUser() {
        return user;
    }

    public ExercisesListModel getExercisesListModel() {
        return exercisesListModel;
    }

    public void setExercisesListModel(ExercisesListModel exercisesListModel) {
        this.exercisesListModel = exercisesListModel;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static void setInstance(Data instance) {
        Data.instance = instance;
    }
}
