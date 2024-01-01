package com.quanvm.meplanner.task;

public class TaskDataModel {
    String TaskTitle, TaskDesc, TaskPriority, TaskSubject, DueDate;
    //Long dayOfMonth, month, year, minute, hour; - datatype should be long
    CharSequence taskName, selectedTime;
    public TaskDataModel(){

    }

    public TaskDataModel(String taskTitle, String taskDesc, String taskPriority, String taskSubject, String dueDate, CharSequence taskName, CharSequence selectedTime) {
        TaskTitle = taskTitle;
        TaskDesc = taskDesc;
        TaskPriority = taskPriority;
        TaskSubject = taskSubject;
        DueDate = dueDate;
        this.taskName = taskName;
        this.selectedTime = selectedTime;
    }

    public String getTaskTitle() {
        return TaskTitle;
    }

    public String getTaskDesc() {
        return TaskDesc;
    }

    public String getTaskPriority() {
        return TaskPriority;
    }

    public String getTaskSubject() {
        return TaskSubject;
    }

    public String getDueDate() {
        return DueDate;
    }

    public CharSequence getTaskName() {
        return taskName;
    }

    public CharSequence getSelectedTime() {
        return selectedTime;
    }

    public void setTaskTitle(String taskTitle) {
        TaskTitle = taskTitle;
    }

    public void setTaskDesc(String taskDesc) {
        TaskDesc = taskDesc;
    }

    public void setTaskPriority(String taskPriority) {
        TaskPriority = taskPriority;
    }

    public void setTaskSubject(String taskSubject) {
        TaskSubject = taskSubject;
    }

    public void setDueDate(String dueDate) {
        DueDate = dueDate;
    }

    public void setTaskName(CharSequence taskName) {
        this.taskName = taskName;
    }

    public void setSelectedTime(CharSequence selectedTime) {
        this.selectedTime = selectedTime;
    }
}
