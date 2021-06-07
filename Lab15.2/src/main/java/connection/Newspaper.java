package connection;

public class Newspaper {
    public int id;
    public String newspapernumber;
    public int userid;

    public Newspaper(int id, String newspapernumber, int userid) {
        this.id = id;
        this.newspapernumber = newspapernumber;
        this.userid = userid;
    }

    public int getId() {
        return id;
    }

    public String getNumber() {
        return newspapernumber;
    }

    public void setNumber(String newspapernumber) {
        this.newspapernumber = newspapernumber;
    }

    public int getUserid() {
        return userid;
    }
}
