package DanGEEK.app.controller;

import DanGEEK.app.dto.FlaskDto;
import DanGEEK.app.dto.member.SurveyRequestDto;
import DanGEEK.app.service.FlaskService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/flask")
@Slf4j
public class FlaskController {
    private final FlaskService flaskService;

    @PostMapping("/checkBadWords/{words}")
    public ResponseEntity<?> checkBadWords(@PathVariable(value = "words") String words) throws JsonProcessingException, ParseException {
        FlaskDto flaskDto = new FlaskDto(words,1);
        boolean result = flaskService.checkBadWords(flaskDto);
        log.info("result : ",result);
        return ResponseEntity.ok(result);
    }
    @PostMapping("/send/info")
    public ResponseEntity<?> sendInfo(@RequestBody SurveyRequestDto surveyRequestDto) {
        return ResponseEntity.ok(flaskService.sendInfo(surveyRequestDto));
    }
}
