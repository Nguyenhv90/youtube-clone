package com.hvn.youtubeclone.service;

import com.hvn.youtubeclone.dto.UploadVideoResponse;
import com.hvn.youtubeclone.dto.VideoDto;
import com.hvn.youtubeclone.model.Video;
import com.hvn.youtubeclone.reoisitory.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final S3Service s3Service;
    private final VideoRepository videoRepository;

    public UploadVideoResponse uploadVideo(MultipartFile multipartFile) {
        String videoUrl = s3Service.uploadFile(multipartFile);
        var video = new Video();
        video.setVideoUrl(videoUrl);
        var savedVideo = videoRepository.save(video);
        return new UploadVideoResponse(savedVideo.getId(), savedVideo.getVideoUrl());
    }

    public VideoDto editVideo(VideoDto videoDto) {
        var saveVideo = getVideoById(videoDto.getId());
        saveVideo.setTitle(videoDto.getTitle());
        saveVideo.setDescription(videoDto.getDescription());
        saveVideo.setTags(videoDto.getTags());
        saveVideo.setThumbnailUrl(videoDto.getThumbnailUrl());
        saveVideo.setVideoStatus(videoDto.getVideoStatus());

        videoRepository.save(saveVideo);
        return videoDto;
    }

    public String uploadThumbnail(MultipartFile file, String videoId) {
        var video = getVideoById(videoId);
        String thumbnailUrl = s3Service.uploadFile(file);
        video.setThumbnailUrl(thumbnailUrl);
        videoRepository.save(video);
        return thumbnailUrl;
    }

    Video getVideoById(String videoId) {
        return videoRepository.findById(videoId)
                .orElseThrow(() -> new IllegalArgumentException("Can't find video by id - " + videoId));
    }

    public VideoDto getVideoDetails(String videoId) {
        var savedVideo = getVideoById(videoId);
        VideoDto videoDto = VideoDto.builder()
                .videoUrl(savedVideo.getVideoUrl())
                .thumbnailUrl(savedVideo.getThumbnailUrl())
                .id(savedVideo.getId())
                .title(savedVideo.getTitle())
                .description(savedVideo.getDescription())
                .tags(savedVideo.getTags())
                .videoStatus(savedVideo.getVideoStatus())
                .build();
        return videoDto;
    }
}
