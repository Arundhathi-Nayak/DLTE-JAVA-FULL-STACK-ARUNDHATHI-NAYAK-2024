package org.example.remotes;


/**
 * Here is the interface on which data store we are working (file or database)
 */
public interface StorageTarget {
    UserRepository getUserRepository();
}
