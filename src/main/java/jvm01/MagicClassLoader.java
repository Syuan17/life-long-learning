package jvm01;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author: Syuan
 * @Date: 2021/8/6 6:09 下午
 */
public class MagicClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            FileInputStream inputStream = new FileInputStream(name);
            String[] split = name.split("/");
            String fileName = split[split.length - 1].split("\\.")[0];
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            byte[] classByte = new byte[bytes.length];
            for (int i = 0; i < bytes.length; ++ i) {
                classByte[i] = (byte)(255 - (int)bytes[i]);
            }
            return defineClass(fileName, classByte, 0, classByte.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new ClassNotFoundException();
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        MagicClassLoader loader = new MagicClassLoader();
        Class<?> clazz = loader.loadClass(System.getProperty("user.dir") + "/src/main/java/jvm01/Hello.xlass");
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.getName().equals("hello")) {
                method.invoke(clazz.newInstance());
                break;
            }
        }
    }

}
