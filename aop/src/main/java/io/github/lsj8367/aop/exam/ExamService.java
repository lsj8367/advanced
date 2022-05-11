package io.github.lsj8367.aop.exam;

import io.github.lsj8367.aop.exam.annotation.Trace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamRepository examRepository;

    @Trace
    public void request(final String itemId) {
        examRepository.save(itemId);
    }

}
