
import com.drew.config.ArticleDataConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration
public class SelfConfigTest {

    @Autowired
    ArticleDataConfig articleDataConfig;

    @Test
    public void testBean(){
        System.out.println("文章作者是==========="+articleDataConfig.getArticleAuthor());
    }
}
