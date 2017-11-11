package Data;

public class RDBMetaData extends MetaData{

    @Override
    public void read(){
        System.out.println("read relation data base data");
    }

    @Override
    public void write(){
        System.out.println("write relation data base data");
    }
}
