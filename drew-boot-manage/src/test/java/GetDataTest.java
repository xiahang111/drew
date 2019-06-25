import com.drew.mapper.DrewCategoryMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration
@MapperScan("com.drew.mapper")
public class GetDataTest {

    @Autowired
    private DrewCategoryMapper drewCategoryMapper;

    @Test
    public void testBean(){

        String ca = drewCategoryMapper.getNameById(1l);

        List<String> cas = drewCategoryMapper.getNamesByParentId(1l);

        System.out.println(ca + "===========================" +cas);

    }
}
