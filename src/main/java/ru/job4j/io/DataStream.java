package ru.job4j.io;

import java.io.*;

public class DataStream {
    public static void main(String[] args) {
        String path = "data/dataOutput.txt";
        String[] names = {"unit1", "unit2", "unit3"};
        int[] amount = {5, 7, 2};
        boolean[] checked = {true, false, true};

        /*Here DataOutputStream and DataInputStreams are demonstrated
        * In Java, the DataOutputStream class is a specialized stream that allows you to write primitive data types
        * as binary data to an underlying output stream. It provides methods for writing various data types, such as integers,
        *  floats, doubles, booleans, and strings, in a machine-independent manner.*/
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(path));
             DataInputStream in = new DataInputStream(new FileInputStream(path))) {
            for (int i = 0; i < names.length; i++) {
                out.writeUTF(names[i]);
                out.writeInt(amount[i]);
                out.writeBoolean(checked[i]);
            }
            /*Здесь важно понимать, что в файл мы пишем данные в двоичном формате, поэтому считывать записанные данные нужно
             в том же порядке и в те же типы данных, так как код в файле - это просто набор байтов. В файле нет никакой информации
              о типе данных или месте начала/конца каких-либо значений. Не зная порядок, в котором мы задавали данные в этот файл,
               прочитать эту информацию будет невозможно. */
            while (true) {
                String n = in.readUTF();
                int a = in.readInt();
                boolean c = in.readBoolean();
                System.out.println("Наименование: " + n + ", количество: " + a + ", проверен: " + c);
            }
        } catch (EOFException e) {
            System.out.println("Достигнут конец файла");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/*
1. Primitive Data Type Support: Unlike other streams, DataOutputStream provides methods to directly write data in primitive types,
 such as writeInt(int), writeFloat(float), writeBoolean(boolean), and so on. This makes it convenient to write data in its original format.
2. Machine Independence: The DataOutputStream class writes data in a format that is independent of the machine's architecture.
 This means that you can write data on one platform and read it on a different platform without worrying about byte order or other low-level details.
3. Serialization Support: DataOutputStream can be used to serialize objects by writing their data fields in a specific order.
 This is commonly done in conjunction with ObjectOutputStream, which wraps a DataOutputStream to handle object serialization.
4. Binary Data Writing: DataOutputStream writes data as binary, which is more compact and efficient than text-based formats like character streams (Writer).
This is particularly useful when working with low-level data, network protocols, or file formats that require binary representation.
*/
