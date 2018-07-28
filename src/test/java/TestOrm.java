import hello.models.RoleEntity;
import leap.core.junit.AppTestBase;
import org.junit.Test;

public class TestOrm extends AppTestBase{
    @Test
    public void testROLE() {
        RoleEntity r = RoleEntity.findOrNull("1233488e93");
        if (null == r ){
            r = new RoleEntity();
            r.setRId("1233488e93");
            r.setRName("fang1");
            r.setRType(0);
            r.create();
        }
    }
}
