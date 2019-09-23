package models;

import lombok.Data;




@Data
public class CommonModel {
    private int id;
    private String module;
    private String CaseName;
    private String RequestMethod;
    private String RequestParams;
    private String TestURL;
    private String TestURI;


    @Override
    public String toString(){
        return  (
                "{"+"\"id\":"+id+","+
                        "\"CaseName\":"+CaseName+","+
                        "\"module\":"+module+","+
                        "\"TestURL\":"+TestURL+","+
                        "\"RequestMethod\":"+RequestMethod+","+
                        "\"RequestParams\":"+RequestParams+","+
                        "\"TestURI\":"+TestURI+"\""+" }"
        );
    }
}
