package app.mobilebanking.middleware;

import app.mobilebanking.remotes.StorageTarget;

public class FileStorageTarget implements StorageTarget {


    @Override
    public UserFileRepository getUserRepository() {
        return new UserFileRepository("mybank-acounts.doc") {
        };
    }
}
