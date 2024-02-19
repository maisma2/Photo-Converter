package org.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class Logger {
    private BufferedWriter bufferedWriter;
    private String creationDate;

    public Logger() throws IOException {
        String fileName = "log_" + LocalDate.now() + ".txt";
        FileWriter fileWriter = new FileWriter(fileName, true);
        bufferedWriter = new BufferedWriter(fileWriter);

        creationDate = LocalDate.now().toString();
        log("Log file created on " + creationDate);
    }

    public synchronized void log(String message) throws IOException {
        bufferedWriter.write(LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + ":" + LocalTime.now().getSecond() + " - " + message + "\n");
        bufferedWriter.flush();
    }

    public void close() throws IOException {
        bufferedWriter.close();
    }
}
