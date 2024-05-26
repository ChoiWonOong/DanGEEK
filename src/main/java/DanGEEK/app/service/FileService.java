/*
package DanGEEK.app.service;

import DanGEEK.app.dto.FileDto;
import DanGEEK.app.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;

    public String save(FileDto fileDto) throws IOException {
        MultipartFile multipartFile = fileDto.getFile();
        String originalFileName = fileDto.getOriginalFileName();
        String storedFileName = System.currentTimeMillis() + "_" + originalFileName;
        String savePath = "C:/Users/dnd53/img" + storedFileName;
        multipartFile.transferTo(new File(savePath));
        return null;
    }
}
*/
