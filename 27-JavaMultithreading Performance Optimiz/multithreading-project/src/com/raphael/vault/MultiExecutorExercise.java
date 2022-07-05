package com.raphael.vault;

import java.util.List;

public class MultiExecutorExercise {

    // Add any necessary member variables here
    List<Runnable> tasks;
    /*
     * @param tasks to executed concurrently
     */
    public MultiExecutorExercise(List<Runnable> tasks) {
        this.tasks = tasks;
    }

    /**
     * Starts and executes all the tasks concurrently
     */
    public void executeAll() {
        for (Runnable task : tasks) {
            Thread thread = new Thread(task);
            thread.start();
        }
    }
}
