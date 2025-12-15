package com.project.logprocessor;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2 || !args[0].equals("--file")) {
            System.out.println("Usage: java -jar logprocessor.jar --file <filename>");
            return;
        }

        String filename = args[1];
        LogProcessor processor = new LogProcessor(filename);
        processor.process();
    }
}
