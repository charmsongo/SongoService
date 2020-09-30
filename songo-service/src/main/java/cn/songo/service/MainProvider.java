package cn.songo.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author SonGo
 * @Create 2020/9/30 16:35
 * @Description
 */
public class MainProvider {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            while (true) {
                Socket socket = serverSocket.accept();
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                String className = objectInputStream.readUTF();
                String methodName = objectInputStream.readUTF();
                Class[] parameterTypes = (Class[]) objectInputStream.readObject();
                Object[] argesMethod = (Object[]) objectInputStream.readObject();
                Class clazz = null;
                if (ISongoService.class.getName().equals(className)) {
                    clazz = ISongoServiceImpl.class;
                }
                Method method = clazz.getMethod(methodName, parameterTypes);
                Object invoke = method.invoke(clazz.newInstance(), argesMethod);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.writeObject(invoke);
                objectOutputStream.flush();

                objectInputStream.close();
                objectOutputStream.close();
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
