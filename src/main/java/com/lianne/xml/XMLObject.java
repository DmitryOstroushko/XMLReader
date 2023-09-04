package com.lianne.xml;

import Lombok;

public class XMLObject {

    private String id;

    public XMLObject() {
        id = "";
    }

    public XMLObject(String id) {
        setId(id);
    }

    public void clear() {
       setId("");
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
