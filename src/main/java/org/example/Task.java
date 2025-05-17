package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Task {
    public  enum TaskStatus{
        IN_PROGRESS,
        FINISHED
    }
    private int id;
    private String description;
    private TaskStatus status;
    private String createdAt;
    private String updatedAt;

    public Task(int id, String description) {
        this.id = id;
        this.description = description;
        this.status = TaskStatus.IN_PROGRESS;
        this.createdAt = getCurrentDateTime();
        this.updatedAt = getCurrentDateTime();
    }

    private String getCurrentDateTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public void setDescription(String description) {
        this.description = description;
        this.updatedAt = getCurrentDateTime();
    }

    public void markInProgress() {
        this.status = TaskStatus.IN_PROGRESS;
        this.updatedAt = getCurrentDateTime();
    }

    public void markDone() {
        this.status = TaskStatus.FINISHED;
        this.updatedAt = getCurrentDateTime();
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("description", description);
        map.put("status", status.toString());
        map.put("createdAt", createdAt);
        map.put("updatedAt", updatedAt);
        return map;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + description + " (" + status + ") - Creado: " + createdAt + ", Actualizado: " + updatedAt;
    }
}
