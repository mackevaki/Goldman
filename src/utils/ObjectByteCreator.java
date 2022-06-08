package utils;

import java.io.*;

public class ObjectByteCreator {
    public static byte[] getBytes(Object obj) {
        ObjectOutputStream oos = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            return baos.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public static Object getObject(byte[] bytes) {
        Object obj = null;
        InputStream is = null;
        ObjectInputStream ois = null;

        try {
            is = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(is);

            obj = ois.readObject();

            return obj;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                ois.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return null;
    }
}
