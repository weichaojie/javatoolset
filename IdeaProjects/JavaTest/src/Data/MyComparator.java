package Data;

import java.util.Comparator;

public class MyComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        MetaData m1 = (MetaData)o1;
        MetaData m2 = (MetaData)o2;
        if ( m1.getNumberId() < m2.getNumberId() )
            return -1;
        if ( m1.getNumberId() > m2.getNumberId())
            return 1;
        return 0;
    }
}
