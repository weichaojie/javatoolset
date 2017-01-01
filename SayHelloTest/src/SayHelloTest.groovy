/**
 * Created by Administrator on 2017/1/1.
 */
import groovy.util.GroovyTestCase
import org.codehaus.groovy.ant.Groovy;

class SayHelloTest extends groovy.util.GroovyTestCase {

    static main(){
        print("run main function ");
        runTest();
    }
    protected void runTest() throws Throwable {
        super.runTest()
        print("run groovy test");
    }
}
