package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String requestLine = in.readLine();
                    System.out.println(requestLine);
                    Pattern pattern = Pattern.compile("GET /\\?msg=(.*) HTTP/1.1");
                    Matcher matcher = pattern.matcher(requestLine);
                    if (matcher.find()) {
                        String message = matcher.group(1);
                        if ("Bye".equals(message)) {
                            out.write("Server is shutting down. Bye!".getBytes(StandardCharsets.UTF_8));
                            out.flush();
                            server.close();
                        }
                    }
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        System.out.println(str);
                    }
                    out.flush();
                }
            }
        }
    }
}
