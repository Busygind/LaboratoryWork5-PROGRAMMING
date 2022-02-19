package com.lab5.client.handlers;

import com.lab5.client.Dragon;
import java.io.File;
import java.util.HashSet;

public interface Reader {
//TODO придумать какой тип возвращать
    public HashSet<Dragon> read(File file);

}
