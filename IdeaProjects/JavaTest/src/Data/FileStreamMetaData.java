package Data;

public class FileStreamMetaData extends MetaData{

    @Override
    public void read() {
        System.out.println("read file stream data");
    }

    @Override
    public void write() {
        System.out.println("write file stream data");
    }
}
