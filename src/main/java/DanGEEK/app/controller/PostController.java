package DanGEEK.app.controller;

import DanGEEK.app.domain.PostType;
import DanGEEK.app.dto.post.GroupBuyResponseDto;
import DanGEEK.app.dto.post.PostCreateRequestDto;
import DanGEEK.app.dto.post.PostResponseDto;
import DanGEEK.app.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/post")
@RestController
@RequiredArgsConstructor
@Slf4j
public class PostController {
    private final PostService postService;
    @PostMapping("/invite/create")
    public ResponseEntity<PostResponseDto> createInvitePost(@RequestBody PostCreateRequestDto postDto){
        postDto.setPost_type(PostType.INVITE.getType());
        log.info("postDto : {}",postDto);
        return ResponseEntity.ok(postService.save(postDto));
    }
    @PostMapping("/group_buy/create")
    public ResponseEntity<PostResponseDto> createGroupBuyPost(@RequestBody PostCreateRequestDto postDto){
        postDto.setPost_type(PostType.GROUP_BUY.getType());
        return ResponseEntity.ok(postService.save(postDto));
    }
    @PostMapping("/complain/create")
    public ResponseEntity<PostResponseDto> createComplainPost(@RequestBody PostCreateRequestDto postDto){
        postDto.setPost_type(PostType.COMPLAIN.getType());
        return ResponseEntity.ok(postService.save(postDto));
    }
    @GetMapping("/invite/list")
    public ResponseEntity<List<PostResponseDto>> getInviteList(){
        return ResponseEntity.ok(postService.getPosts(PostType.INVITE));
    }
    @GetMapping("/group_buy/list")
    public ResponseEntity<List<PostResponseDto>> getGroupBuyList(){
        return ResponseEntity.ok(postService.getPosts(PostType.GROUP_BUY));
    }
    @GetMapping("/complain/list")
    public ResponseEntity<List<PostResponseDto>> getComplainList(){
        return ResponseEntity.ok(postService.getPosts(PostType.COMPLAIN));
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> getPostDetail(@PathVariable Long id){
        return ResponseEntity.ok(postService.getPost(id));
    }
    @PostMapping("/update/")
    public ResponseEntity<PostResponseDto> updatePost(@RequestBody PostCreateRequestDto postDto){
        return ResponseEntity.ok(postService.update(new PostCreateRequestDto(postDto.getTitle(),postDto.getContents(),postDto.getUserId())));
    }

}
