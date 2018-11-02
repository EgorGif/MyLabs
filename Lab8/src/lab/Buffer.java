package lab;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Buffer {
    public static Record moveUp(Object index, Map<Object, Long> map) {
        Object[] objects = map.keySet().toArray();
        for (int i = 0; i < objects.length - 1; i++) {
            if (objects[i].equals(index)) {
                try (RandomAccessFile randomAccessFile = new RandomAccessFile("Magazine.dat", "rw")) {
                    int j = i - 1;
                    randomAccessFile.seek(map.get(objects[j]));
                    byte[] b = new byte[1024];
                    randomAccessFile.read(b);
                    Record deserialize = deserialize(b);
                    while (deserialize.isDeleted()) {
                        ++j;
                        if (j >= objects.length) return null;
                        randomAccessFile.seek(map.get(objects[j]));
                        byte[] array = new byte[1024];
                        randomAccessFile.read(array);
                        deserialize = deserialize(array);
                    }
                    return deserialize;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static Record moveLow(Object index, Map<Object, Long> map) {
        Object[] objects = map.keySet().toArray();
        for (int i = 0; i < objects.length; i++) {
            if (objects[i].equals(index)) {
                try (RandomAccessFile randomAccessFile = new RandomAccessFile("Magazine.dat", "rw")) {
                    int j = i + 1;
                    randomAccessFile.seek(map.get(objects[j]));
                    byte[] b = new byte[1024];
                    randomAccessFile.read(b);
                    Record record = deserialize(b);
                    while (record.isDeleted()) {
                        --j;
                        if (j < 0) {
                            return null;
                        }
                        randomAccessFile.seek(map.get(objects[j]));
                        byte[] array = new byte[1024];
                        randomAccessFile.read(array);
                        record = deserialize(array);
                    }
                    return record;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static void removeByIndex(Object index, Map<Object, Long> users) {
        for (Map.Entry<Object, Long> entry : users.entrySet()) {
            if (entry.getKey().equals(index)) {
                try (RandomAccessFile randomAccessFile = new RandomAccessFile("Magazine.dat", "rw")) {
                    randomAccessFile.seek(entry.getValue());
                    byte[] array = new byte[1024];
                    randomAccessFile.read(array);
                    Record record = deserialize(array);
                    record.setDeleted(true);
                    randomAccessFile.seek(entry.getValue());
                    randomAccessFile.write(serialize(record));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Map<Object, Long> write(ArrayList<Record> magazine) {
        Map<Object, Long> map = new HashMap<>();
        try (RandomAccessFile randomAccessFile = new RandomAccessFile("Magazine.dat", "rw")) {
            for (Record record : magazine) {
                long dataPointer = randomAccessFile.getFilePointer();
                map.put(record.getField(), dataPointer);
                randomAccessFile.write(serialize(record));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static Record read(Object index, Map<Object, Long> magazine) {
        Long dataPointer = magazine.get(index);
        try (RandomAccessFile randomAccessFile = new RandomAccessFile("Magazine.dat", "rw")) {
            randomAccessFile.seek(dataPointer);
            byte[] array = new byte[1024];
            randomAccessFile.read(array);
            Record record = deserialize(array);
            if (record.isDeleted()) {
                return null;
            }
            return deserialize(array);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] serialize(Record record) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(record);
            oos.flush();
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Record deserialize(byte[] byteArray) {
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(byteArray))) {
            Record record = (Record) ois.readObject();
            return record;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}