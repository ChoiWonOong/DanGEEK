package DanGEEK.app.controller;

import DanGEEK.app.domain.PostType;
import DanGEEK.app.dto.PostDto;
import DanGEEK.app.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/post")
@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    @PostMapping("/invite/create")
    public ResponseEntity<PostDto> createInvitePost(@RequestBody PostDto postDto){
        postDto.setPost_type(PostType.INVITE);
        postService.save(postDto);
        return ResponseEntity.ok(postDto);
    }
    @PostMapping("/group_buy/create")
    public ResponseEntity<PostDto> createGroupBuyPost(@RequestBody PostDto postDto){
        postDto.setPost_type(PostType.GROUP_BUY);
        postService.save(postDto);
        return ResponseEntity.ok(postDto);
    }
    @PostMapping("/complain/create")
    public ResponseEntity<PostDto> createComplainPost(@RequestBody PostDto postDto){
        postDto.setPost_type(PostType.COMPLAIN);
        postService.save(postDto);
        return ResponseEntity.ok(postDto);
    }
    @GetMapping("/invite/list")
    public ResponseEntity<List<PostDto>> getInviteList(){
        return ResponseEntity.ok(postService.getPosts(PostType.INVITE));
    }
    @GetMapping("/group_buy/list")
    public ResponseEntity<List<PostDto>> getGroupBuyList(){
        return ResponseEntity.ok(postService.getPosts(PostType.GROUP_BUY));
    }
    @GetMapping("/complain/list")
    public ResponseEntity<List<PostDto>> getComplainList(){
        return ResponseEntity.ok(postService.getPosts(PostType.COMPLAIN));
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostDetail(@PathVariable Long id){
        return ResponseEntity.ok(postService.getPost(id));
    }
}
