package ru.dv.mailhelper.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import ru.dv.mailhelper.entities.dtos.FileMetaDTO;
import ru.dv.mailhelper.repositories.interfaces.IFileMetaProvider;

import java.util.Collection;
import java.util.UUID;

@Repository
public class FileMetaProvider implements IFileMetaProvider {

    private static final String GET_FILES_META = "select hash, file_name as filename from file_info_metadata" +
            " where sub_type =:subtype";

    private static final String GET_FILE_PATH_BY_HASH = "select file_name as filename from file_info_metadata" +
            " where hash = :hash";

    private static final String SAVE_FILE_META_DATA = "insert into file_info_metadata (hash, file_name, sub_type)" +
            " values (:hash, :file_name, :subtype)";

    private final Sql2o sql2o;

    public FileMetaProvider(@Autowired Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public String checkFileExists(UUID fileHash) {
       try(Connection connection = sql2o.open())  {
           return connection.createQuery(GET_FILE_PATH_BY_HASH, false)
                   .addParameter("hash", fileHash.toString())
                   .executeScalar(String.class);
       }
    }

    @Override
    public void saveFileMeta(UUID Hash, String fileName, int subType) {
        try(Connection connection = sql2o.open())  {
            connection.createQuery(SAVE_FILE_META_DATA, false)
                    .addParameter("hash", Hash.toString())
                    .addParameter("file_name", fileName)
                    .addParameter("subtype", subType)
                    .executeUpdate();
        }
    }

    @Override
    public Collection<FileMetaDTO> getMetaFiles(int subType) {
        try(Connection connection = sql2o.open())  {
            return connection.createQuery(GET_FILES_META, false)
                    .addParameter("subtype", subType)
                    .executeAndFetch(FileMetaDTO.class);
        }
    }
}
