package DanGEEK.app.controller;

import DanGEEK.app.domain.Post;
import DanGEEK.app.domain.PostType;
import DanGEEK.app.dto.post.PostCreateRequestDto;
import DanGEEK.app.dto.post.PostResponseDto;
import DanGEEK.app.service.PostService;
import DanGEEK.app.service.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/post")
@RestController
@RequiredArgsConstructor
@Slf4j
public class PostController {
    private final PostService postService;
    private final S3Service s3Service;

    @PostMapping("/invite/create")
    public ResponseEntity<PostResponseDto> createInvitePost(@RequestBody PostCreateRequestDto postDto){
        postDto.setPost_type(PostType.INVITE.getType());
        log.info("postDto : {}",postDto);
        return ResponseEntity.ok(postService.createInvitePost(postDto).toResponseDto());
    }
    @PostMapping("/group_buy/create")
    public ResponseEntity<PostResponseDto> createGroupBuyPost(@RequestPart(value = "file", required = false) MultipartFile file, @RequestPart PostCreateRequestDto postDto){
        postDto.setPost_type(PostType.GROUP_BUY.getType());
        String url = s3Service.upload(file);
        Post post = postService.createGroupBuyPost(postDto, url);
        return ResponseEntity.ok(post.toResponseDto());
    }
    @PostMapping("/complain/create")
    public ResponseEntity<PostResponseDto> createComplainPost(@RequestBody PostCreateRequestDto postDto){
        postDto.setPost_type(PostType.COMPLAIN.getType());
        return ResponseEntity.ok(postService.createComplainPost(postDto).toResponseDto());
    }
    @GetMapping("/invite/list")
    public ResponseEntity<List<PostResponseDto>> getInviteList(){
        return ResponseEntity.ok(postService.getPosts(PostType.INVITE)
                .stream().map(Post::toResponseDto).toList());
    }
    @GetMapping("/group_buy/list")
    public ResponseEntity<List<PostResponseDto>> getGroupBuyList(){
        return ResponseEntity.ok(postService.getPosts(PostType.GROUP_BUY)
                .stream().map(Post::toResponseDto).toList());
    }
    @GetMapping("/complain/list")
    public ResponseEntity<List<PostResponseDto>> getComplainList(){
        return ResponseEntity.ok(postService.getPosts(PostType.COMPLAIN)
                .stream().map(Post::toResponseDto).toList());
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> getPostDetail(@PathVariable Long id){
        return ResponseEntity.ok(postService.getPost(id));
    }
    @PostMapping("/update/")
    public ResponseEntity<PostResponseDto> updatePost(@RequestBody PostCreateRequestDto postDto){
        return ResponseEntity.ok(postService.update(new PostCreateRequestDto(
                postDto.getTitle(),postDto.getContents(),postDto.getUserId()
                )).toResponseDto());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<PostResponseDto> deletePost(@PathVariable Long id){
        return ResponseEntity.ok(postService.delete(id).toResponseDto());
    }
}
