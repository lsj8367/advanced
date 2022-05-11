package io.github.lsj8367.aop.exam;

import io.github.lsj8367.aop.exam.aop.ExamAspect;
import io.github.lsj8367.aop.exam.aop.RetryAspect;
import org.junit.jupiter.api.RepeatedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import({ExamAspect.class, RetryAspect.class})
class ExamTest {

    @Autowired
    private ExamService examService;

    @RepeatedTest(5)
    void test() {
        examService.request("data");
    }

}
