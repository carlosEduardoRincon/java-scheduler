import entities.Task;
import logic.Scheduler;

import java.io.*;

public class SimulacaoTarefas {
    public static void main(String[] args) {
        try {
            int counter = 1;
            int quantum = 5;

            Scheduler scheduler = new Scheduler(5);

            BufferedReader reader = new BufferedReader(new FileReader("src/main/java/files/tasks.txt"));
            String line;
            
            while ((line = reader.readLine()) != null) {
                int totalTimeTask = 0;
                for (char character : line.toCharArray()) {
                    totalTimeTask += scheduler.returnTaskTime(character);
                }
                scheduler.enqueueTask(new Task(counter, totalTimeTask, line));
                counter++;
            }
            reader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/java/files/output.txt"));
            writer.write("Quantum: " + quantum + " segundos\n");

            scheduler.executeTasks();

            for (Task task : scheduler.finishedTask) {
                writer.write("\nTarefa finalizada ("+ task.getId() +")"
                        + ": " + task.getTask());
            }

            System.out.println("Tempo total de execução: " + scheduler.totalExecutionTime + " segundos");
            writer.write("\nTempo total de execução: " + scheduler.totalExecutionTime + " segundos");

            writer.close();
        } catch (IOException | InterruptedException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }
}