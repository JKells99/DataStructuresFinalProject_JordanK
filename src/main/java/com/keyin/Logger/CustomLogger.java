package com.keyin.Logger;

public class CustomLogger {

    public void LogErrorMessage(String msg){
        System.err.println("ERROR: " + msg);
    }

    public void LogAction(String msg){

        System.out.println("\u001B[4m " + "\u001B[32m" +"LOG: " + msg + "Has Fired" + "\u001B[0m");

    }


}
