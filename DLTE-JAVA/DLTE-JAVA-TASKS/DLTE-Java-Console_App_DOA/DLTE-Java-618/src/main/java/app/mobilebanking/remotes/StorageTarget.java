package app.mobilebanking.remotes;


import org.example.remotes.UserRepository;

/**
 * Here is the interface on which data store we are working (file or database)
 */
public interface StorageTarget {
    UserRepository getUserRepository();
}
