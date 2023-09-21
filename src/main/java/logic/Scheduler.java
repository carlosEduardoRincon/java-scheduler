package logic;

import entities.Task;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Scheduler {
    private Queue<Task> taskQueue;
    public List<Task> finishedTask = new ArrayList<>();
    private final int timeQuantum;
    public int totalExecutionTime = 0;

    public Scheduler(int timeQuantum) {
        this.taskQueue = new LinkedList<>();
        this.timeQuantum = timeQuantum;
    }

    public void enqueueTask(Task task) {
        taskQueue.add(task);
    }

    public void executeTasks() throws InterruptedException {
        while (!taskQueue.isEmpty()) {
            Task currentTask = taskQueue.poll();
            int executionTime = Math.min(timeQuantum, currentTask.getRemainingTime());
            int totalTimeTask = 0;
            int counter = 0;
            int qtdProcess = currentTask.getTask().toCharArray().length;

            for (char character : currentTask.getTask().toCharArray()) {
                if (totalTimeTask < timeQuantum) {
                    if (counter >= currentTask.getLastIndex()) {
                        int taskTime = returnTaskTime(character, false);
                        totalTimeTask += taskTime;
                        totalExecutionTime += taskTime;

                        System.out.println("\nExecuting task: " + currentTask.getId()
                                + "\n-----------------------------------------------"
                                + "\nProcess(ID): " + character
                                + "\nTotal time(s): " + totalTimeTask + " seconds"
                                + "\nRemain Process(num): " + (qtdProcess - counter - 1));

                        System.out.print("List of Ready tasks(ID's): ");
                        taskQueue.forEach(task -> System.out.print(task.getId() + " "));

                        System.out.println("\n-----------------------------------------------");
                    }
                    counter++;
                }
            }

            currentTask.setLastIndex(counter);
            currentTask.setRemainingTime(currentTask.getRemainingTime() - executionTime);

            if (currentTask.getRemainingTime() > 1) {
                System.out.println("\nAdding Task " + currentTask.getId() + " to ready list (5 seconds)...");

                if (taskQueue.peek() != null) {
                    System.out.println("Popping Task " + taskQueue.peek().getId() + " of ready list");
                } else {
                    System.out.println("There are no tasks.");
                }

                Thread.sleep(5000);
                taskQueue.add(currentTask);
            } else {
                System.out.println("Removing task: " + currentTask.getId());
                finishedTask.add(currentTask);
            }
        }
    }

    public int returnTaskTime(char character) {
        return returnTaskTime(character, false);
    }

    public int returnTaskTime(char character, boolean sleep) {
        int returnTime = 0;
        switch (character) {
            case 'A':
                returnTime = 1;
                break;
            case 'B':
                returnTime = 2;
                break;
            case 'C':
                returnTime = 3;
                break;
            case 'D':
                returnTime = 4;
                break;
            default:
                break;
        }

        if (sleep) {
            try {
                Thread.sleep(returnTime * 1000);
            } catch (InterruptedException e) { /* IGNORE */}
        }

        return returnTime;
    }
}

