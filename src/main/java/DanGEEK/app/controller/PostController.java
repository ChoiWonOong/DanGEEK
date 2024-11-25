package DanGEEK.app.controller;

import DanGEEK.app.Exception.ErrorResponse;
import DanGEEK.app.Exception.RestApiException;
import DanGEEK.app.domain.Post;
import DanGEEK.app.domain.PostType;
import DanGEEK.app.dto.post.*;
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
    public ResponseEntity<MateInviteResponseDto> createInvitePost(@RequestBody PostCreateRequestDto postDto){
        postDto.setPost_type(PostType.INVITE.getType());
        log.info("postDto : {}",postDto);
        return ResponseEntity.ok((MateInviteResponseDto) postService.createInvitePost(postDto).toResponseDto());
    }
    @PostMapping("/group_buy/create")
    public ResponseEntity<?> createGroupBuyPost(@RequestPart(value = "file", required = false) MultipartFile file, @RequestPart(value = "text") PostCreateRequestDto postDto){
        try{
            postDto.setPost_type(PostType.GROUP_BUY.getType());
            String url = s3Service.upload(file);
            Post post = postService.createGroupBuyPost(postDto, url);
            return ResponseEntity.ok((GroupBuyResponseDto)post.toResponseDto());
        }
        catch (RuntimeException e){
            e.printStackTrace();
            return ErrorResponse.toResponseEntity(e);
        }
    }
    @PostMapping("/complain/create")
    public ResponseEntity<?> createComplainPost(@RequestBody PostCreateRequestDto postDto){
        try{
            postDto.setPost_type(PostType.COMPLAIN.getType());
            return ResponseEntity.ok((ComplainResponseDto)postService.createComplainPost(postDto).toResponseDto());
        }
        catch (RuntimeException e){
            return ErrorResponse.toResponseEntity(e);
        }
    }
    @GetMapping("/invite/list")
    public ResponseEntity<?> getInviteList(){
        try{
            return ResponseEntity.ok(postService.getPosts(PostType.INVITE)
                    .stream().map(Post::toResponseDto).toList());
        }
        catch (RestApiException e){
            return ErrorResponse.toResponseEntity(e.getErrorCode());
        }
    }
    @GetMapping("/group_buy/list")
    public ResponseEntity<?> getGroupBuyList(){
        try{
            return ResponseEntity.ok(postService.getPosts(PostType.GROUP_BUY)
                    .stream().map(Post::toResponseDto).toList());
        }
        catch (RestApiException e){
            return ErrorResponse.toResponseEntity(e.getErrorCode());
        }
    }
    @GetMapping("/complain/list")
    public ResponseEntity<List<PostResponseDto>> getComplainList(){
        return ResponseEntity.ok(postService.getPosts(PostType.COMPLAIN)
                .stream().map(Post::toResponseDto).toList());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getPostDetail(@PathVariable Long id){
        try{
            return ResponseEntity.ok(postService.getPost(id));
        }
        catch (RestApiException e){
            return ErrorResponse.toResponseEntity(e.getErrorCode());
        }
    }
    @PostMapping("/update/")
    public ResponseEntity<?> updatePost(@RequestBody PostCreateRequestDto postDto){
        try{
            return ResponseEntity.ok(postService.update(new PostCreateRequestDto(
                    postDto.getTitle(),postDto.getContents(),postDto.getUserId()
            )).toResponseDto());
        }
        catch (RestApiException e) {
            return ErrorResponse.toResponseEntity(e.getErrorCode());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id){
        try{
            return ResponseEntity.ok(postService.delete(id).toResponseDto());
        }
        catch (RuntimeException e){
            return ErrorResponse.toResponseEntity(e);
        }
    }
}
