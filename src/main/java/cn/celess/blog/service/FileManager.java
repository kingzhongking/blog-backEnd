package cn.celess.blog.service;

import cn.celess.blog.entity.model.FileInfo;
import cn.celess.blog.entity.model.FileResponse;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

/**
 * @author : xiaohai
 * @date : 2020/10/15 18:19
 * @desc : 文件管理器 定义操作文件的方法
 */
@Service
public interface FileManager {

    /**
     * 解决语法错误 占位方法
     *
     */
    FileResponse uploadFile(InputStream is, String fileName);

    /**
     * 解决语法错误 占位方法
     */
    List<FileInfo> getFileList();
}
