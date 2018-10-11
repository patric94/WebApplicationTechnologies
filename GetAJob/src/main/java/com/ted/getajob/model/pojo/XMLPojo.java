package com.ted.getajob.model.pojo;

import com.ted.getajob.model.UserModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class XMLPojo {

    private List<UserXML> list;

    public XMLPojo() {
    }

    public XMLPojo(List<UserModel> list) {
        this.list = convertList(list);
    }

    public List<UserXML> getList() {
        return list;
    }
    @XmlElement
    public void setList(List<UserXML> list) {
        this.list = list;
    }

    public List<UserXML> convertList(List<UserModel> list) {
        List<UserXML> userXMLS = new ArrayList<>();
        for (UserModel userModel : list) {
            userXMLS.add(userModel.convertToUserXML());
        }
        return userXMLS;
    }
}
