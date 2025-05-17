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

    public void createTask(String description) {
        int newId = getHighestId();
        newId = newId++;
        Task newTask = new Task(newId, description);
        tasks.add(newTask);
        saveTasksToFile();
    }

    public  int getHighestId() {
        int maxId = 0;
        for (Task task : this.tasks) {
            if (task.getId() > maxId) {
                maxId = task.getId();
            }
        }
        return maxId;
    }














    //Persistencia
    private void uploadData() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("No se encuentra el archivo con los datos");
        }

        try {
            assert mapper != null;
            // Leer la lista de tareas desde el archivo
            List<Task> loadedTasks = mapper.readValue(file, new TypeReference<List<Task>>() {});
            // Actualizar el atributo privado con la lista leída
            this.tasks = loadedTasks;
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


