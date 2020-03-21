package com.aws.db.repository.impl;

import com.aws.db.repository.IDao;
import com.aws.db.util.BaseJDBC;

public class Dao extends BaseJDBC implements IDao {

    public String getTime() {

        return getCurrentTime();
    }
}
