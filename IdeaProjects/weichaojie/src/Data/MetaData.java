package Data;

import java.util.Comparator;

abstract public class MetaData {

    abstract public void read();
    abstract public void write();

    public MetaData() {
        super();
        numberId = (long)(1000000*Math.random() ) % 1024;
    }

    public MetaData(long numberId) {
        this.numberId = numberId;
    }

    public long getNumberId() {
        return numberId;
    }

    public void setNumberId(long numberId) {
        this.numberId = numberId;
    }

    private long numberId;
}
