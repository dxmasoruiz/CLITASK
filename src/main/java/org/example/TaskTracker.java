package org.example;

import java.sql.SQLOutput;

public class TaskTracker {
    public static void  main (String[] args){
        System.out.println("Working Directory = " + System.getProperty("user.dir"));

        if (args.length==0){
            System.out.println("No se encontro ningún comando ");
            return;
        }
        String comando = args[0];
        String contenidoComando;
        TaskController controller = new TaskController();

        switch (comando){
            case "add":
                if (args.length < 2) {
                    System.out.println("Uso: java TaskTracker add <nombre tarea>");
                    return;
                }
                contenidoComando = args[1];
                controller.createTask(contenidoComando);
                break;
            case "update":
                if (args.length < 2) {
                    System.out.println("Uso: java TaskTracker update <nombre tarea>");
                    return;
                }
                contenidoComando = args[1];
                //Actualizar el nombre de la tarea
                break;
            case "Delete":
                if (args.length < 2) {
                    System.out.println("Uso: java TaskTracker update <nombre tarea>");
                    return;
                }
                contenidoComando = args[1];
                break;

        }

    }
}
