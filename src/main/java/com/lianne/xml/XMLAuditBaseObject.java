package com.lianne.xml;

import lombok.*;

@Data
@ToString
public class XMLAuditBaseObject {
    private String id;

    public XMLAuditBaseObject() {
        id = "";
    }

    public XMLAuditBaseObject(String id) {
        setId(id);
    }

    public void clear() {
       setId("");
    }

    /*
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
     */

}
