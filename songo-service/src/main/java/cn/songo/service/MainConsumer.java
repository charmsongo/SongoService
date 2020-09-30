package cn.songo.service;

import cn.songo.bean.Songo;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

/**
 * @Author SonGo
 * @Create 2020/9/30 16:12
 * @Description
 */
public class MainConsumer {
    public static void main(String[] args) {
        ISongoService iSongoService = (ISongoService) rpc(ISongoService.class);
        Songo songo = iSongoService.queryById(100);
        System.out.println(songo);
    }

    private static Object rpc(final Class clazz) {
        return Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class[]{clazz}, new InvocationHandler() {
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Socket socket = new Socket("127.0.0.1", 8888);
                        String className = clazz.getName();
                        String methodName = method.getName();
                        Class<?>[] parameterTypes = method.getParameterTypes();
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                        objectOutputStream.writeUTF(className);
                        objectOutputStream.writeUTF(methodName);
                        objectOutputStream.writeObject(parameterTypes);
                        objectOutputStream.writeObject(args);
                        objectOutputStream.flush();

                        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                        Object o = objectInputStream.readObject();
                        objectInputStream.close();
                        objectOutputStream.close();
                        return o;
                    }
                });
    }
}
