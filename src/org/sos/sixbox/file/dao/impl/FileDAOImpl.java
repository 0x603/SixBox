package org.sos.sixbox.file.dao.impl;

import org.sos.sixbox.file.dao.FileDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsResource;

import java.io.IOException;


public class FileDAOImpl implements FileDAO{

    @Autowired
    private GridFsOperations gridFsOperations;

    @Override
    public boolean store(Resource file, String filename) {
        try {
            gridFsOperations.store(file.getInputStream(), filename);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void delete(String filename) {
        gridFsOperations.delete(Query.query(whereFileName().is(filename)));
    }

    @Override
    public GridFsResource read(String filename) {
        return null;
    }
}
