package repositories;

import java.io.*;

public class FileWorker {

    public static void serializeObject(Object object, String file) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            objectOutputStream.writeObject(object);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object deserializeObject(String file) {
        try  {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            Object list = objectInputStream.readObject();
            objectInputStream.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return new Object();
        }
    }
}
