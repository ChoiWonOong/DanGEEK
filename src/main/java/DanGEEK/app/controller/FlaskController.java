package DanGEEK.app.controller;

import DanGEEK.app.dto.FlaskDto;
import DanGEEK.app.service.FlaskService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/flask")
@Slf4j
public class FlaskController {
    private final FlaskService flaskService;

    @PostMapping("/checkBadWords")
    public void checkBadWords() throws JsonProcessingException {
        FlaskDto flaskDto = new FlaskDto("나쁜 단어", 0);
        String result = flaskService.checkBadWords(flaskDto);
        log.info("result : ",result);
    }
}
