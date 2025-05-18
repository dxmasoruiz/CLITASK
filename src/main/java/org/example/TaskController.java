package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TaskController {
    private static final String FILE_NAME = "tasks.json";
    private List<Task> tasks;
    private static final String TASKS_FILE = "tasks.json";
    private ObjectMapper mapper;

    public TaskController() {
        mapper = new ObjectMapper();
        uploadData();
    }

    public void changeState(int id ){
        for (Task task : tasks ){
            if ((task.getId()==id)){
                if (task.getStatus() == Task.TaskStatus.IN_PROGRESS){task.setStatus(Task.TaskStatus.FINISHED);
                }else {
                    task.setStatus(Task.TaskStatus.IN_PROGRESS);
                }

            }
        }

    }

    public void createTask(String description) {
        int newId = getHighestId();
        newId = newId++;
        Task newTask = new Task(newId, description);
        tasks.add(newTask);
        saveTasksToFile();
    }

    public void deleteTask(int id ){
        tasks.removeIf(t -> t.getId() == id );
        saveTasksToFile();

    }

    public  int getHighestId() {
        int maxId = 0;
        if (!tasks.isEmpty()) {
            for (Task task : this.tasks) {
                if (task.getId() > maxId) {
                    maxId = task.getId();
                }
            }
        }
        return maxId;
    }

    public void listTasks(){
        for(Task task : tasks){
            System.out.println(task.toString());
        }
    }

    public void listDone(){
        for(Task task : tasks){
            if (task.getStatus()== Task.TaskStatus.FINISHED) {
                System.out.println(task.toString());
            }
        }
    }

    public void listNotDone(){
        for(Task task : tasks){
            if (task.getStatus()== Task.TaskStatus.IN_PROGRESS) {
                System.out.println(task.toString());
            }
        }
    }



    public void  update (int id,String description){
        Task searchedTask ;
        boolean found = false;
        for (Task task : tasks){
            if (task.getId()==id){
                task.setDescription(description);
                saveTasksToFile();
                found = true;
                return;
            }
        }
        if (!found){
            System.out.println("No se encontro la tarea con el id especificado");
        }
    }














    //Persistencia
    private void uploadData() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("No se encuentra el archivo con los datos");
        }

        try {
            assert mapper != null;
            if (file.length() == 0) {
                this.tasks = new ArrayList<>();
            } else {
                ObjectMapper mapper = new ObjectMapper();
                this.tasks= mapper.readValue(file, new TypeReference<List<Task>>() {});
            }

        } catch (Exception e) {
            System.out.println("Error al cargar tareas: " + e.getMessage());
        }
    }

    private void saveTasksToFile() {
        try {
            File file = new File(FILE_NAME);
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, tasks);
            System.out.println("Tareas guardadas correctamente en el archivo JSON.");
        } catch (IOException e) {
            System.err.println("Error al guardar las tareas: " + e.getMessage());
        }
    }

}


