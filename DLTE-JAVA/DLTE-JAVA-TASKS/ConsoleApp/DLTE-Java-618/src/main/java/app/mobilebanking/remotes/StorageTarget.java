package app.mobilebanking.remotes;

import app.mobilebanking.middleware.UserFileRepository;

public interface StorageTarget {
    UserFileRepository getUserRepository();
}
