package models;

import lombok.Data;

@Data
public class CommonModel {
    private int id;
    private String ApiName;
    private String URI;
    private String RequestMethod;
    private String RequestParam;
    private String CaseName;
    private String ReturnedValueOld;
    private String ReturnedValueNew;
    private String Result;
    private String ResultValue;

    @Override
    public String toString(){
        return  (
                "{"+"\"id\":"+id+","+
                        "\"CaseName\":"+CaseName+","+
                        "\"ApiName\":"+ApiName+","+
                        "\"URI\":"+URI+","+
                        "\"RequestMethod\":"+RequestMethod+","+
                        "\"RequestParam\":"+RequestParam+","+
                        "\"ReturnedValueOld\":"+ReturnedValueOld+","+
                        "\"ReturnedValueNew\":"+ReturnedValueNew+","+
                        "\"Result\":"+Result+","+
                        "\"ResultValue\":"+ResultValue+"\""+" }"
        );
    }
}
