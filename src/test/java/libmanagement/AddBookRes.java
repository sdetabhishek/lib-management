package libmanagement;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddBookRes {

    @JsonProperty private String Msg;
    @JsonProperty private String ID;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }


}
