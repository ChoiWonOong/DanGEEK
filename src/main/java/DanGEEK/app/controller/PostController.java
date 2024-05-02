package DanGEEK.app.controller;

import DanGEEK.app.domain.PostType;
import DanGEEK.app.dto.PostDto;
import DanGEEK.app.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/post")
@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    @PostMapping("/invite/create")
    public ResponseEntity<PostDto> createInvitePost(@RequestBody PostDto postDto){
        postDto.setPost_type(PostType.INVITE.getType());
        postService.save(postDto);
        return ResponseEntity.ok(postDto);
    }
    @PostMapping("/group_buy/create")
    public ResponseEntity<PostDto> createGroupBuyPost(@RequestBody PostDto postDto){
        postDto.setPost_type(PostType.GROUP_BUY.getType());
        postService.save(postDto);
        return ResponseEntity.ok(postDto);
    }
    @PostMapping("/complain/create")
    public ResponseEntity<PostDto> createComplainPost(@RequestBody PostDto postDto){
        postDto.setPost_type(PostType.COMPLAIN.getType());
        postService.save(postDto);
        return ResponseEntity.ok(postDto);
    }
}
