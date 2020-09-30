package cn.songo.bean;

import java.io.Serializable;

/**
 * @Author SonGo
 * @Create 2020/9/30 16:06
 * @Description
 */
public class Songo implements Serializable {
    private static final long serialVersionUID = 8741371181408621568L;
    private int id;
    private String name;
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Songo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
