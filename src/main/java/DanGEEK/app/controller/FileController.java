package DanGEEK.app.controller;

import DanGEEK.app.dto.FileDto;
import DanGEEK.app.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/file")
public class FileController {
    private final FileService fileService;
    @PostMapping("/save")
    public String save(@ModelAttribute FileDto fileDto) throws IOException {
        return fileService.save(fileDto);
    }
}
