package com.mall.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by song-pc on 2017/11/28.
 */
public interface IFileService {

    String upload(MultipartFile file, String path);

}
