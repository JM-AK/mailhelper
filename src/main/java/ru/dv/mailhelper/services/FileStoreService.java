package ru.dv.mailhelper.services;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dv.mailhelper.entities.dtos.FileMetaDTO;
import ru.dv.mailhelper.repositories.FileMetaProvider;
import ru.dv.mailhelper.repositories.FileSystemProvider;
import ru.dv.mailhelper.repositories.interfaces.IFileMetaProvider;
import ru.dv.mailhelper.repositories.interfaces.IFileSystemProvider;
import ru.dv.mailhelper.utils.HashHelper;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.UUID;

@Component
public class FileStoreService implements IFileStoreService {

    private IFileSystemProvider systemProvider;

    private IFileMetaProvider fileMetaProvider;

    private static final Logger logger = LoggerFactory.getLogger(FileStoreService.class);

    @Autowired
    public void setSystemProvider(IFileSystemProvider systemProvider) {
        this.systemProvider = systemProvider;
    }

    @Autowired
    public void setFileMetaProvider(IFileMetaProvider fileMetaProvider) {
        this.fileMetaProvider = fileMetaProvider;
    }

    @Override
    public String storeFile(byte[] content, String fileName, int subFileType) throws IOException, NoSuchAlgorithmException {
        final UUID md5 = HashHelper.getMd5Hash(content);
        logger.info(String.valueOf(md5));

        String filename = fileMetaProvider.checkFileExists(md5);
        if (filename == null) {
            fileMetaProvider.saveFileMeta(md5, fileName, subFileType);
            filename = systemProvider.storeFile(content, md5, fileName);
        }

        return filename;
    }

    @Override
    public byte[] getFile(UUID md5) throws IOException {
       String filename = fileMetaProvider.checkFileExists(md5);
       String ext = FilenameUtils.getExtension(filename);
       String fullFileName = md5.toString() + "." + ext;
       return systemProvider.getFile(fullFileName);
    }

    @Override
    public Collection<FileMetaDTO> getMetaFiles(int subtype) {
        return fileMetaProvider.getMetaFiles(subtype);
    }
}
