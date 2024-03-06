package org.middleware;

import java.io.IOException;
import java.util.List;

public interface FileOperation {
    void  create(List<Employee> employee) throws IOException, ClassNotFoundException;

}
