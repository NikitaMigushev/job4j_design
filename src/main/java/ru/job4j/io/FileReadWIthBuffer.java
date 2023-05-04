package ru.job4j.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileReadWIthBuffer {
    public static void main(String[] args) throws IOException {
        RandomAccessFile file = new RandomAccessFile("./path/to/file", "r");
        FileChannel channel = file.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (channel.read(buffer) > 0) {
            buffer.flip();
            while (buffer.hasRemaining()) {
                System.out.println((char) buffer.get());
            }
            buffer.clear();
        }
        channel.close();
        file.close();
    }
}
